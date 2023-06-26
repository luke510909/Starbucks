package com.example.springcashier;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
@Controller
@RequestMapping("/user/starbucks-cashier")
public class SpringCashierController {


    @Value("${API_KEY}")
    private String apiKey;

    @Value("${API_HOST}")
    private String apiHost;

    @GetMapping
    public String getAction(@ModelAttribute("command") Command command,
                            Model model, HttpSession session) {

        String message = "";

        command.setRegister("5012349");
        message = "Starbucks Reserved Order" + "\n\n" +
                "Register: " + command.getRegister() + "\n" +
                "Status:   " + "Ready for New Order" + "\n";

        String server_ip = "";
        String host_name = "";
        try {
            InetAddress ip = InetAddress.getLocalHost();
            server_ip = ip.getHostAddress();
            host_name = ip.getHostName();
        } catch (Exception e) {
        }

        model.addAttribute("message", message);
        model.addAttribute("server", host_name + "/" + server_ip);

        return "user/starbucks";

    }

    @PostMapping
    public String postAction(@Valid @ModelAttribute("command") Command command,
                             @RequestParam(value = "action", required = true) String action,
                             Errors errors, Model model, HttpServletRequest request) {

        String message = "";

        log.info("Action: " + action);
        command.setRegister(command.getStores());
        log.info("Command: " + command);

        /* Process Post Action */
        if (action.equals("Place Order")) {
            try {

                RestTemplate restTemplate = new RestTemplate();
                String resourceUrl = "" ;
                message = "";
                resourceUrl = "http://" + apiHost + "/api/order/register/"+ command.getRegister() + apiKey;
          //      resourceUrl = "http://" + "localhost:80" + "/api/order/register/"+ command.getRegister() + "?apikey=" + "2H3fONTa8ugl1IcVS7CjLPnPIS2Hp9dJ";

               // resourceUrl = "http://" + "35.184.82.149:80/" + "/api/order/register/"+ command.getRegister() + "?apikey=" + "Zkfokey2311";
                // get response as POJO
                Order orderRequest = Order.GetNewOrder(request);
                HttpEntity<Order> newOrderRequest = new HttpEntity<Order>(orderRequest) ;
                ResponseEntity<Order> newOrderResponse = restTemplate.postForEntity(resourceUrl, newOrderRequest, Order.class);
                Order newOrder = newOrderResponse.getBody();
                System.out.println( newOrder );

                ObjectMapper objectMapper = new ObjectMapper() ;
                String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(newOrder);
                System.out.println( jsonString) ;
                message = "\n" + jsonString ;

                newOrder.setRegister(command.getRegister());
                message = "Starbucks Reserved Order" + "\n\n" +
                        "Drink: " + newOrder.getDrink() + "\n" +
                        "Milk:  " + newOrder.getMilk() + "\n" +
                        "Size:  " + newOrder.getSize() + "\n" +
                        "Total: " + newOrder.getTotal() + "\n" +
                        "\n" +
                        "Register: " + newOrder.getRegister() + "\n" +
                        "Status:   " + newOrder.getStatus() + "\n";
            } catch (Exception e) {
                e.printStackTrace();
                message = "An active order already exists for register " + command.getRegister() +
                        " Please clear the order before placing a new one.";
            }

        } else if (action.equals("Get Order")) {
            try {
              //String resourceUrl = "http://" + "35.184.82.149:80/" + "/api/order/register/"+ command.getRegister() + "?apikey=" + "Zkfokey2311";
               String resourceUrl = "http://" + apiHost + "/api/order/register/"+ command.getRegister() + apiKey;
            //   String resourceUrl = "http://" + "localhost:80" + "/api/order/register/"+ command.getRegister() + "?apikey=" + "2H3fONTa8ugl1IcVS7CjLPnPIS2Hp9dJ";

                RestTemplate restTemplate = new RestTemplate();
                Order newOrder = restTemplate.getForObject(resourceUrl, Order.class);

                message = "Starbucks Reserved Order" + "\n\n" +
                        "Drink: " + newOrder.getDrink() + "\n" +
                        "Milk:  " + newOrder.getMilk() + "\n" +
                        "Size:  " + newOrder.getSize() + "\n" +
                        "Total: " + newOrder.getTotal() + "\n" +
                        "\n" +
                        "Register: " + newOrder.getRegister() + "\n" +
                        "Status:   " + newOrder.getStatus() + "\n";
            } catch (Exception e) {
                message = "Starbucks Reserved Order" + "\n\n" +
                        "Register: " + command.getRegister() + "\n" +
                        "Status:   " + "Ready for New Order" + "\n";
            }
        } else if (action.equals("Clear Order")) {
            try {
                String resourceUrl = "http://" + apiHost + "/api/order/register/"+ command.getRegister() + apiKey;

//                String resourceUrl = "http://" + "localhost:80" + "/api/order/register/"+ command.getRegister() + "?apikey=" + "2H3fONTa8ugl1IcVS7CjLPnPIS2Hp9dJ";
               // String resourceUrl = "http://" + "35.184.82.149:80/" + "/api/order/register/"+ command.getRegister() + "?apikey=" + "2H3fONTa8ugl1IcVS7CjLPnPIS2Hp9dJcat";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.delete(resourceUrl);
                message = "Order has been cleared!" + "\n\n" +
                        "Starbucks Reserved Order" + "\n\n" +
                        "Register: " + command.getRegister() + "\n" +
                        "Status:   " + "Ready for New Order" + "\n";
            } catch (Exception e) {
                message = "Starbucks Reserved Order" + "\n\n" +
                        "Register: " + command.getRegister() + "\n" +
                        "Status:   " + "Ready for New Order" + "\n";
            }
        }
        command.setMessage(message);

        String server_ip = "";
        String host_name = "";
        try {
            InetAddress ip = InetAddress.getLocalHost();
            server_ip = ip.getHostAddress();
            host_name = ip.getHostName();
        } catch (Exception e) {
        }

        model.addAttribute("message", message);
        model.addAttribute("server", host_name + "/" + server_ip);

        return "user/starbucks";
    }
}
