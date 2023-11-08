package com.example.testingspring.clients;

import com.example.testingspring.model.EventResponse;
import com.example.testingspring.model.Order;
import com.example.testingspring.model.OrderStatus;
import com.example.testingspring.util.StateMachine;

public class ExchangeClient {
    private StateMachine stateMachine = new StateMachine();

    public void updateOrder(Order order, EventResponse eventResponse){
        switch (eventResponse){
            case EVENT_ORDER_INITIATED:
                if(order.orderStatus.equals(OrderStatus.PENDING)){
                    order.orderStatus = OrderStatus.IN_PROGRESS;
                }
                break;
            case EVENT_ORDER_COMPLETED:
                if(order.orderStatus.equals(OrderStatus.IN_PROGRESS)){
                    order.orderStatus = OrderStatus.COMPLETED;
                }
                break;
            case EVENT_ORDER_REJECTED:
                if(order.orderStatus.equals(OrderStatus.IN_PROGRESS)){
                    order.orderStatus = OrderStatus.REJECTED;
                }
                break;
        }
    }
}
