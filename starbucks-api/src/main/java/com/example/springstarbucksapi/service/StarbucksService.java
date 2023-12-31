package com.example.springstarbucksapi.service;

//import com.example.springstarbucksapi.config.StarbucksWebSecurityConfig;
import com.example.springstarbucksapi.repository.StarbucksRepository;
import com.example.springstarbucksapi.model.StarbucksModel;
import com.example.springstarbucksapi.model.StarbucksCard;
import com.example.springstarbucksapi.model.StarbucksOrder;
import com.example.springstarbucksapi.repository.StarbucksCardRepository;
import com.example.springstarbucksapi.repository.StarbucksOrderRepository;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.AmqpAdmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.UUID;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Service("StarbucksService")
public class StarbucksService {
    @Autowired
    private RabbitTemplate rabbit;

    // REF: https://www.moreofless.co.uk/spring-mvc-java-autowired-component-null-repository-service

    @Autowired private AmqpAdmin amqpAdmin;
    @Autowired private StarbucksRepository starbucksRepository;
    @Autowired private StarbucksOrderRepository ordersRepository;
    @Autowired private StarbucksCardRepository cardsRepository;




    /* https://docs.spring.io/spring-data/jpa/docs/2.4.5/api/ */

    /* Create a New Starbucks Card */
    public StarbucksCard newCard() {
        StarbucksCard newcard = new StarbucksCard();
        Random random = new Random();
        int num = random.nextInt(900000000) + 100000000;
        int code = random.nextInt(900) + 100;
        newcard.setCardNumber(String.valueOf(num));
        newcard.setCardCode(String.valueOf(code));
        newcard.setBalance(20.00);
        newcard.setActivated(false);
        newcard.setStatus("New Card");
        return cardsRepository.save(newcard);
    }

    /* Get List of Starbucks Cards */
    public List<StarbucksCard> allCards() {
        return cardsRepository.findAll();
    }

    /* Delete All Starbucks Cards (Cleanup for Unit Testing) */
    public void deleteAllCards() {
        cardsRepository.deleteAllInBatch();
    }

    /* Get Details of a Starbucks Card */
    public StarbucksCard findCard( String num ) {
        return cardsRepository.findByCardNumber(num);
    }

    /* Activate a Starbucks Card */
    public void activateCard(String num, String code) {
        StarbucksCard card = cardsRepository.findByCardNumber(num) ;
        if (card != null && card.getCardCode().equals(code)) {
            card.setActivated(true);
            cardsRepository.save(card);
        }
    }

    // https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html
  //  private HashMap<String, StarbucksOrder> orders = new HashMap<>();
    /* https://docs.spring.io/spring-data/jpa/docs/2.4.5/api/ */

    /* Get List of Starbucks Orders */
    public List<StarbucksOrder> allOrders() {
        return ordersRepository.findAll();
    }

    /* Delete All Starbucks Orders (Cleanup for Unit Testing) */
    public void deleteAllOrders() {
        ordersRepository.deleteAllInBatch();
       // orders.clear();
    }

    /* Create a New Starbucks Order */
    public StarbucksOrder newOrder(String regid, StarbucksOrder order) throws ResponseStatusException {
        System.out.println("Placing Order (Reg ID = " + regid + ") => " + order);
        // check input
        if (order.getDrink().equals("") || order.getMilk().equals("") || order.getSize().equals("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Order Request!");
        }
        // check for active order
        List<StarbucksOrder> orders = ordersRepository.findByRegister(regid);
        if (!orders.isEmpty()) {
            StarbucksOrder active = orders.get(orders.size() - 1); // Fetch the last order
            System.out.println("Active Order (Reg ID = " + regid + ") => " + active);
            if (active.getStatus().equals("Ready for Payment."))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Active Order Exists!");
        }
        // set price
        double price = 0.0;
        switch (order.getDrink()) {
            case "Caffe Latte":
                switch (order.getSize()) {
                    case "Tall":
                        price = 2.95;
                        break;
                    case "Grande":
                        price = 3.65;
                        break;
                    case "Venti":
                    case "Your Own Cup":
                        price = 3.95;
                        break;
                    default:
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Size!");
                }
                break;
            case "Caffe Americano":
                switch (order.getSize()) {
                    case "Tall":
                        price = 2.25;
                        break;
                    case "Grande":
                        price = 2.65;
                        break;
                    case "Venti":
                    case "Your Own Cup":
                        price = 2.95;
                        break;
                    default:
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Size!");
                }
                break;
            case "Caffe Mocha":
                switch (order.getSize()) {
                    case "Tall":
                        price = 3.45;
                        break;
                    case "Grande":
                        price = 4.15;
                        break;
                    case "Venti":
                    case "Your Own Cup":
                        price = 4.45;
                        break;
                    default:
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Size!");
                }
                break;
            case "Espresso":
                switch (order.getSize()) {
                    case "Short":
                        price = 1.75;
                        break;
                    case "Tall":
                        price = 1.95;
                        break;
                    default:
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Size!");
                }
                break;
            case "Cappuccino":
                switch (order.getSize()) {
                    case "Tall":
                        price = 2.95;
                        break;
                    case "Grande":
                        price = 3.65;
                        break;
                    case "Venti":
                    case "Your Own Cup":
                        price = 3.95;
                        break;
                    default:
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Size!");
                }
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Drink!");
        }
        double tax = 0.0725;
        double total = price + (price * tax);
        double scale = Math.pow(10, 2);
        double rounded = Math.round(total * scale) / scale;
        order.setTotal(rounded);
        // save order
        order.setRegister(regid);
        order.setStatus("Ready for Payment.");
        //StarbucksOrder new_order = ordersRepository.save(order);
        //orders.put(regid, new_order);
      //  return new_order;
        return ordersRepository.save(order);
    }

    /* Get Details of a Starbucks Order */
    public StarbucksOrder getActiveOrder(String regid) {
        List<StarbucksOrder> orders = ordersRepository.findByRegister(regid);
        if(orders.isEmpty()) {
            return null;
        } else {
            return orders.get(orders.size() - 1); // Return the last order as the active order
        }
    }

    /* Clear Active Order */
    @Transactional
    public void clearActiveOrder(String regid) {
        ordersRepository.deleteByRegister(regid);
    }

    /*  Process payment for the "active" Order.
        REF:  https://www.baeldung.com/transaction-configuration-with-jpa-and-spring
     */
    @Transactional
    public StarbucksCard processOrder(String regid, String cardnum) throws ResponseStatusException {
        Queue queue = new Queue("cashier");
        amqpAdmin.declareQueue(queue);
        System.out.println("Pay for Order: Reg ID = " + regid + " Using Card = " + cardnum);
        StarbucksOrder active = getActiveOrder(regid);
        if (active == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order Not Found!");
        }
        if (cardnum.equals("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Card Number Not Provided!");
        }
        if (active.getStatus().startsWith("Paid with Card")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Clear Paid Active Order!");
        }
        StarbucksCard card = cardsRepository.findByCardNumber(cardnum);
        if (card == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Card Not Found!");
        }
        if (!card.isActivated()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Card Not Activated.");
        }
        double price = active.getTotal();
        double balance = card.getBalance();
        if (balance - price < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient Funds on Card.");
        }
        double new_balance = balance - price;
        card.setBalance(new_balance);
        String status = "Paid with Card: " + cardnum + " Balance: $" + new_balance + ".";
        active.setStatus(status);
        cardsRepository.save(card);
        active.setCard(card);
        ordersRepository.save(active);
        StarbucksModel model = new StarbucksModel() ;
        StarbucksOrder order = new StarbucksOrder();
        String orderNumber = active.getRegister() + "-" + UUID.randomUUID().toString();
        model.setOrderNumber( orderNumber ) ;
        model.setStatus( "PAID" ) ;
        starbucksRepository.save( model ) ;
        rabbit.convertAndSend(queue.getName(), orderNumber);
        return card;
    }

}
