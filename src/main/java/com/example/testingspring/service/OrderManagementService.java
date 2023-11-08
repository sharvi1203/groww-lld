package com.example.testingspring.service;

import com.example.testingspring.clients.ExchangeClient;
import com.example.testingspring.exception.InSufficientBalanceException;
import com.example.testingspring.model.EventResponse;
import com.example.testingspring.model.Order;
import com.example.testingspring.model.OrderStatus;
import com.example.testingspring.model.OrderType;
import com.example.testingspring.model.StockInventory;
import com.example.testingspring.view.OrderView;
import com.sun.org.apache.xpath.internal.operations.Or;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

@Slf4j
public class OrderManagementService {

    public HashMap<Integer, Order> orderHashMap;

    private UserService userService ;

    public ExchangeClient exchangeClient;

    public OrderManagementService(UserService userService, StockManagemnetService stockManagemnetService, ExchangeClient exchangeClient) {
        this.userService = userService;
        this.stockManagemnetService = stockManagemnetService;
        this.exchangeClient = exchangeClient;
    }

    private StockManagemnetService stockManagemnetService;



    public HashMap<Integer,Order> getOrderHashMap(){
        if(orderHashMap == null){
            orderHashMap =  new HashMap<>();
        }
        return orderHashMap;
    }
    // Place Order

    public Order placeOrder(OrderView orderView) throws InSufficientBalanceException {
        if(orderView.orderType.equals(OrderType.BUY)){
            int userAmount  = userService.getUserMap().get(orderView.userId).getUserBalance().amount;
            StockInventory stockInventory1 = stockManagemnetService.getStockInventoryHashMap().get(orderView.stockId);
            if(userAmount < stockInventory1.pricePerShare * orderView.quantity){
                throw new InSufficientBalanceException();
            }else{
                userService.getUserMap().get(orderView.userId).getUserBalance().amount =  userAmount - stockInventory1.pricePerShare * orderView.quantity;
            }
        }
        Order order1 = Order.builder()
                .orderId(orderView.orderId)
                .orderStatus(OrderStatus.PENDING)
                .orderType(orderView.orderType)
                .userId(orderView.userId)
                .stockId(orderView.stockId)
                .quantity(orderView.quantity)
                .build();
        getOrderHashMap().put(order1.orderId , order1);
        exchangeClient.updateOrder(order1, EventResponse.EVENT_ORDER_INITIATED);
        return order1;
    }


    // Update Order


    //View Order By User
    public OrderStatus viewOrder(int orderId){
        Order order = getOrderHashMap().get(orderId);
        if(order.orderStatus.equals(OrderStatus.COMPLETED) && order.orderType.equals(OrderType.SELL)){
            int userAmount  = userService.getUserMap().get(order.userId).getUserBalance().amount;
            StockInventory stockInventory1 = stockManagemnetService.getStockInventoryHashMap().get(order.stockId);
            log.info("User Amount" + userAmount);
            log.info("Stock Amount "+ stockInventory1.pricePerShare);
            log.info("Quantity  "+ order.quantity);
            userService.getUserMap().get(order.userId).getUserBalance().amount+=stockInventory1.pricePerShare * order.quantity;
            userAmount = userService.getUserMap().get(order.userId).getUserBalance().amount;
            log.info("User Amount" + userAmount);
        }
        return order.orderStatus;
    }


}
