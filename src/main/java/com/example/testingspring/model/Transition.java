package com.example.testingspring.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Transition {
    private OrderStatus currentStatus;
    private EventResponse eventResponse;
    private OrderStatus finalOrderStatus;
}
