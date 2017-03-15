package com.project.onepice.basicproject.javaBasic.DesignPatterns.decoration.decorator;


import com.project.onepice.basicproject.javaBasic.DesignPatterns.decoration.Drink;

/**
 * Created by onepice2015 on 2016/11/22.
 */

public class Chocolate extends Decorator {
    public Chocolate(Drink Obj) {
        super(Obj);
        // TODO Auto-generated constructor stub
        super.setDescription("Chocolate");
        super.setPrice(3.0f);
    }

}
