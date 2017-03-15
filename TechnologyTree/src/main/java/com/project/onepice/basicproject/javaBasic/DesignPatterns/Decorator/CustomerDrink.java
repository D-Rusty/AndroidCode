package com.project.onepice.basicproject.javaBasic.DesignPatterns.Decorator;

/**
 * Created by onepice2015 on 16/6/20.
 */
public class CustomerDrink {
    public static void main(String[] args){
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription() + " $"+beverage.cost());

        Beverage darkBeverage = new DarkRoast();

        darkBeverage = new Mocha(darkBeverage);
        darkBeverage = new Mocha(darkBeverage);
        darkBeverage = new Whip(darkBeverage);

        System.out.println(darkBeverage.getDescription()+",$"+darkBeverage.cost());

        Beverage housBeverage = new HouseBlend();

        housBeverage = new soy(housBeverage);
        housBeverage = new Mocha(housBeverage);
        housBeverage = new Whip(housBeverage);

        System.out.println(housBeverage.getDescription()+",$"+housBeverage.cost());
    }
}
