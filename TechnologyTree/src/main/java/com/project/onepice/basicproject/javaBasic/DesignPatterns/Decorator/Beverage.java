package com.project.onepice.basicproject.javaBasic.DesignPatterns.Decorator;

/**
 * 装饰者模式
 *
 * 抽象基类
 *
 * Created by onepice2015 on 16/6/20.
 */
public abstract class Beverage {
    String description = "Unknown Beverage";

    public String getDescription(){
        return description;
    }

    public abstract  double cost();
}
