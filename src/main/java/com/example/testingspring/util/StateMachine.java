package com.example.testingspring.util;

import com.example.testingspring.model.EventResponse;
import com.example.testingspring.model.OrderStatus;
import com.example.testingspring.model.Transition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StateMachine {
    private List<Transition> transitions =  new ArrayList<>();

    private HashMap<EventResponse, List<Transition>> eventResponseTransitionHashMap;

    public StateMachine(){
        buildUpTheTransitions();
    }

    private void buildUpTheTransitions(){
        Transition transition = Transition.builder().currentStatus(OrderStatus.PENDING).eventResponse(EventResponse.EVENT_ORDER_INITIATED).finalOrderStatus(OrderStatus.IN_PROGRESS).build();
        addTransition(OrderStatus.PENDING,EventResponse.EVENT_ORDER_INITIATED,OrderStatus.IN_PROGRESS);
        transitions.add(Transition.builder().currentStatus(OrderStatus.IN_PROGRESS).eventResponse(EventResponse.EVENT_ORDER_INITIATED).finalOrderStatus(OrderStatus.IN_PROGRESS).build());
        transitions.add(Transition.builder().currentStatus(OrderStatus.IN_PROGRESS).eventResponse(EventResponse.EVENT_ORDER_COMPLETED).finalOrderStatus(OrderStatus.COMPLETED).build());
        transitions.add(Transition.builder().currentStatus(OrderStatus.IN_PROGRESS).eventResponse(EventResponse.EVENT_ORDER_REJECTED).finalOrderStatus(OrderStatus.REJECTED).build());
        transitions.add(Transition.builder().currentStatus(OrderStatus.COMPLETED).eventResponse(EventResponse.EVENT_ORDER_INITIATED).finalOrderStatus(OrderStatus.COMPLETED).build());
    }

    public void addTransition(OrderStatus currOrderStatus, EventResponse eventResponse, OrderStatus finalOrderStatus){
        Transition transition = Transition.builder().currentStatus(currOrderStatus).eventResponse(eventResponse).finalOrderStatus(finalOrderStatus).build();
        transitions.add(transition);
        //eventResponseTransitionHashMap.put(eventResponse,transition);
    }
}
