package com.example.testingspring.service;

import com.example.testingspring.model.User;
import com.example.testingspring.model.UserBalance;
import com.example.testingspring.view.UserView;

import java.util.HashMap;

public class UserService {
    // Register user;
    public HashMap<Integer,User> userStore;

    public HashMap<Integer,User> getUserMap(){
        if(userStore == null){
            userStore =  new HashMap<>();
        }
        return userStore;
    }

    public User registerUser(UserView userView){
        User user =  User.builder()
                .name(userView.name)
                .phoneNumber(userView.phoneNumber)
                .userId(userView.userId)
                .build();
        UserBalance userBalance = UserBalance.builder()
                .userId(user.getUserId())
                .amount(userView.amount)
                .balanceId(userView.balanceId)
                .build();
        user.setUserBalance(userBalance);
        getUserMap().put(user.userId, user);
        return user;
    }

    //Add Money To Balance
    public int addMoneyToWallet(int amount,int userId){
        UserBalance userBalance = getUserMap().get(userId).getUserBalance();
        int initialAmount =  userBalance.getAmount();
        userBalance.setAmount(amount + initialAmount);
        return userBalance.getAmount();
    }

    //View Money

    public int viewMoney(int userId){
        UserBalance userBalance = getUserMap().get(userId).getUserBalance();
        return userBalance.getAmount();
    }


}
