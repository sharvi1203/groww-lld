package com.example.testingspring.view;

import com.example.testingspring.model.UserBalance;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserView{
    public int userId;
    public String name;
    public String phoneNumber;
    public int balanceId;

    public int amount;
}
