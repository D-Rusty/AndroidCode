package com.project.onepice.basicproject.javaBasic.DesignPatterns.Decorator;

/**
 * 混合咖啡
 *
 * 被装饰者1
 *
 * Created by onepice2015 on 16/6/20.
 */
public class HouseBlend extends Beverage{

    public  HouseBlend(){
        description = "HouseBlend";
    }

    @Override
    public double cost() {
        return 0.89;
    }
}
