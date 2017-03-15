package com.project.onepice.basicproject.javaBasic.DesignPatterns.Decorator;

/**
 *
 * 装饰着基类 ,扩展了原有的抽象类基本方法，分装
 *
 * Created by onepice2015 on 16/6/20.
 */
public abstract class CondimentDecorator extends  Beverage{
   public abstract String getDescription();
}
