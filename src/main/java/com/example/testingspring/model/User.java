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
public class User{
    public  int userId = 1;
    public String name;
    public String phoneNumber;
    public UserBalance userBalance;

}
