package com.example.testingspring.view;


import lombok.Builder;

@Builder
public class StockInventoryView {
    public int stockId;
    public int pricePerShare;
    public String name;
}
