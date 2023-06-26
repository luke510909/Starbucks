package com.example.springcashier;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class Order {
    private String drink;
    private String milk;
    private String size;
    private double total;
    private String register;
    private String status;

    public static Order GetNewOrder(HttpServletRequest request) {
        Map<String, Map<String, Double>> prices = new HashMap<>();
        prices.put("Caffe Latte", Map.of("Tall", 2.95, "Grande", 3.65, "Venti", 3.95));
        prices.put("Caffe Americano", Map.of("Tall", 2.25, "Grande", 2.65, "Venti", 2.95));
        prices.put("Caffe Mocha", Map.of("Tall", 3.45, "Grande", 4.15, "Venti", 4.45));
        prices.put("Espresso", Map.of("Tall", 1.75, "Grande", 1.95));
        prices.put("Cappuccino", Map.of("Tall", 2.95, "Grande", 3.65, "Venti", 3.95));

        Order o = new Order();

        o.drink = request.getParameter("drinks");
        o.milk = request.getParameter("milkTypes");
        o.size = request.getParameter("sizes");

        return o;
    }


}


/*

https://priceqube.com/menu-prices/%E2%98%95-starbucks

var DRINK_OPTIONS = [ "Caffe Latte", "Caffe Americano", "Caffe Mocha", "Espresso", "Cappuccino" ];
var MILK_OPTIONS  = [ "Whole Milk", "2% Milk", "Nonfat Milk", "Almond Milk", "Soy Milk" ];
var SIZE_OPTIONS  = [ "Short", "Tall", "Grande", "Venti", "Your Own Cup" ];

Caffè Latte
=============
tall 	$2.95
grande 	$3.65
venti 	$3.95 (Your Own Cup)

Caffè Americano
===============
tall 	$2.25
grande 	$2.65
venti 	$2.95 (Your Own Cup)

Caffè Mocha
=============
tall 	$3.45
grande 	$4.15
venti 	$4.45 (Your Own Cup)

Cappuccino
==========
tall 	$2.95
grande 	$3.65
venti 	$3.95 (Your Own Cup)

Espresso
========
short 	$1.75
tall 	$1.95

 */



