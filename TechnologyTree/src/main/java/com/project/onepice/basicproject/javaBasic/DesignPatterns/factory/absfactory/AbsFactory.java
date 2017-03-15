package com.project.onepice.basicproject.javaBasic.DesignPatterns.factory.absfactory;

import com.project.onepice.basicproject.javaBasic.DesignPatterns.factory.pizza.Pizza;

/**
 * Created by onepice2015 on 2016/11/22.
 */

public interface AbsFactory {
    public Pizza CreatePizza(String ordertype) ;
}
