package com.example.starbuckscashierworker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;


@Component
@RabbitListener(queues = "cashier")
public class StarbucksCashierOrderWorker {

    private static final Logger log = LoggerFactory.getLogger(StarbucksCashierOrderWorker.class);

    @Autowired
    private StarbucksCashierOrderRepository orders ;

    @RabbitHandler
    public void processStarbucksCashierOrders(String orderNumber) {
        log.info( "Received  Order # " + orderNumber) ;

        // Sleeping to simulate buzy work
        try {
            Thread.sleep(60000); // 60 seconds
        } catch (Exception e) {}


        List<StarbucksCashierOrder> list = orders.findByOrderNumber( orderNumber ) ;
        if ( !list.isEmpty() ) {
            StarbucksCashierOrder order = list.get(0) ;
            order.getStatus();
            order.setStatus ( "FULFILLED" ) ;

            orders.save(order) ;
            log.info( "Processed Order # " + orderNumber );
        } else {
            log.info( "[ERROR] Order # " + orderNumber + " Not Found!" );
        } 

    }
}