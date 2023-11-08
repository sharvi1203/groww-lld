package com.example.testingspring;

import com.example.testingspring.clients.ExchangeClient;
import com.example.testingspring.exception.InSufficientBalanceException;
import com.example.testingspring.model.EventResponse;
import com.example.testingspring.model.Order;
import com.example.testingspring.model.OrderStatus;
import com.example.testingspring.model.OrderType;
import com.example.testingspring.model.StockInventory;
import com.example.testingspring.model.User;
import com.example.testingspring.service.OrderManagementService;
import com.example.testingspring.service.StockManagemnetService;
import com.example.testingspring.service.UserService;
import com.example.testingspring.view.OrderView;
import com.example.testingspring.view.StockInventoryView;
import com.example.testingspring.view.UserView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@Slf4j
public class TestingSpringApplication {

    public static void main(String[] args){
        SpringApplication.run(TestingSpringApplication.class, args);
        StockManagemnetService stockManagemnetService = new StockManagemnetService();
        ExchangeClient exchangeClient =  new ExchangeClient();
        UserService userService =  new UserService();

        OrderManagementService orderManagementService = new OrderManagementService(userService,stockManagemnetService,exchangeClient);

        UserView userView =  UserView.builder()
                        .userId(1).name("ABC").amount(1000).phoneNumber("123456789").balanceId(1).build();
        User user = userService.registerUser(userView);
        log.info("User "+ user.toString());

        StockInventoryView stockInventoryView = StockInventoryView.builder()
                .stockId(1)
                .pricePerShare(200)
                .name("Stock 1")
                .build();
        StockInventory stockInventory = stockManagemnetService.addMoneyToStock(stockInventoryView);
        List<StockInventory> stockInventoryList = stockManagemnetService.viewStockInventory();
        for(StockInventory stockInventory1 : stockInventoryList){
            log.info("Stock Inventory Name " + stockInventory1.name);
            log.info("Stock Inventory price per share " + stockInventory1.pricePerShare);
            log.info("Stock Inventory Id " + stockInventory1.stockId);
        }
        int money =  userService.viewMoney(1);
        log.info("Money in wallet "+ money);

        OrderView orderView =  OrderView.builder()
                .orderId(1)
                .orderType(OrderType.BUY)
                .quantity(1)
                .userId(1)
                .stockId(1)
                .build();
        Order order = null;
        try {
            order = orderManagementService.placeOrder(orderView);
            log.info("Order orderid "+ order.orderId);
        }catch(InSufficientBalanceException inSufficientBalanceException){
            log.error("Insufficient Balance");
        }

         money =  userService.viewMoney(1);
        log.info("Update Money in wallet "+ money);
        OrderStatus orderStatus = orderManagementService.viewOrder(1);
        log.info("Order Status "+orderStatus.name());
        exchangeClient.updateOrder(order, EventResponse.EVENT_ORDER_COMPLETED);
        orderStatus = orderManagementService.viewOrder(1);
        log.info("Order Status "+orderStatus.name());
        OrderView orderView1 =  OrderView.builder()
                .orderId(2)
                .orderType(OrderType.SELL)
                .quantity(1)
                .userId(1)
                .stockId(1)
                .build();
        try {
            order = orderManagementService.placeOrder(orderView1);
            log.info("Order orderid "+ order.orderId);
        }catch(InSufficientBalanceException inSufficientBalanceException){
            log.error("Insufficient Balance");
        }
        money =  userService.viewMoney(1);
        log.info("Update Money in wallet "+ money);
        exchangeClient.updateOrder(order, EventResponse.EVENT_ORDER_COMPLETED);
        orderStatus = orderManagementService.viewOrder(2);
        log.info("Order Status "+orderStatus.name());
        money =  userService.viewMoney(1);
        log.info("Update Money in wallet "+ money);
    }

}
