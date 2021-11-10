package com.aop.example.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {

    public Boolean addAccountMember() {

        // call the buisness methode
        System.out.println(getClass() + ": doing my db work: adding a membership account");

        return true;
    }

    public void lala() {
        System.out.println("inside lala method");
    }
}
