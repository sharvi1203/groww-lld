package com.example.testingspring.service;

import com.example.testingspring.model.StockInventory;
import com.example.testingspring.model.User;
import com.example.testingspring.view.StockInventoryView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StockManagemnetService {

    //Add Stock Inventory

    public HashMap<Integer, StockInventory> stockInventoryHashMap;

    public HashMap<Integer,StockInventory> getStockInventoryHashMap(){
        if(stockInventoryHashMap == null){
            stockInventoryHashMap =  new HashMap<>();
        }
        return stockInventoryHashMap;
    }

    public StockInventory addMoneyToStock(StockInventoryView stockView){
        StockInventory stockInventory =  StockInventory.builder()
                .stockId(stockView.stockId)
                .pricePerShare(stockView.pricePerShare)
                .name(stockView.name).build();
        getStockInventoryHashMap().put(stockInventory.stockId,stockInventory);
        return stockInventory;
    }

    //View Stock Inventory
    public List<StockInventory> viewStockInventory(){
        List<StockInventory> stockInventories =  getStockInventoryHashMap().entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
        return stockInventories;
    }

}
