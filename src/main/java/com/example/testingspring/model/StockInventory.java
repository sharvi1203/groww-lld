package com.example.testingspring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockInventory {
    public int stockId;
    public int pricePerShare;
    public String name;
}

