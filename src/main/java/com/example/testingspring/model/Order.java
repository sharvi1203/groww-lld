package com.example.testingspring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    public int orderId;

    public int quantity;
    public int stockId;
    public int userId;
    public OrderType orderType;
    public OrderStatus orderStatus = OrderStatus.PENDING;
}
