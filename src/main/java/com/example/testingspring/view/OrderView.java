package com.example.testingspring.view;

import com.example.testingspring.model.OrderType;
import lombok.Builder;

@Builder
public class OrderView {
    public int orderId;
    public int stockId;
    public int userId;

    public int quantity;
    public OrderType orderType;
}
