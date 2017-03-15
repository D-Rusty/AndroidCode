package com.project.onepice.basicproject.javaBasic.DesignPatterns.factory.method;

import com.project.onepice.basicproject.javaBasic.DesignPatterns.factory.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by onepice2015 on 2016/11/22.
 */

public abstract class OrderPizza {

    public OrderPizza() {
        Pizza pizza = null;
        String ordertype;

        do {
            ordertype = gettype();
            pizza = createPizza(ordertype);

            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
        } while (true);
    }

    abstract Pizza createPizza(String ordertype);

    private String gettype() {
        try {
            BufferedReader strin = new BufferedReader(new InputStreamReader(
                    System.in));
            System.out.println("input pizza type:");
            String str = strin.readLine();

            return str;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

}
