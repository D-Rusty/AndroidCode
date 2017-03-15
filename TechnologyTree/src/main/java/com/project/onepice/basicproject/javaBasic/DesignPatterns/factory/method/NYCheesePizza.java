package com.project.onepice.basicproject.javaBasic.DesignPatterns.factory.method;

import com.project.onepice.basicproject.javaBasic.DesignPatterns.factory.pizza.Pizza;

/**
 * Created by onepice2015 on 2016/11/22.
 */


public class NYCheesePizza extends Pizza {

    @Override
    public void prepare() {
        // TODO Auto-generated method stub
        super.setname("NYCheesePizza");

        System.out.println(name+" preparing;");
    }

}

