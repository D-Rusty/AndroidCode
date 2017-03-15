package com.project.onepice.basicproject.javaBasic.DesignPatterns.strategy.duck;


import com.project.onepice.basicproject.javaBasic.DesignPatterns.strategy.flybehavior.BadFlyBehavior;
import com.project.onepice.basicproject.javaBasic.DesignPatterns.strategy.quackbehavior.GeGeQuackBehavior;

/**
 * Created by onepice2015 on 2016/11/22.
 */

public class RedHeadDuck extends Duck {

    /**
     * 1.分析项目变化与不变部分，提取变化部分，抽象成接口+实现
     * 2.多用组合少用继承，用行为类组合，而不是行为的继承，更有弹性
     * 3.策略模式: 分别封装行为接口，实现算法族，超类放行为接口对象，在子类里具体设定行为对象，原则就是:分离变化部分
     * 4.好处:新增行为简单，行为类更好的复用，组合更方便，既有集成带来的复用好处，没有挖坑
     * 5.封装接口，基于接口编程各种功能，此模式让行为算法的变化独立于算法的使用者
     * */

    public RedHeadDuck(){
        mFlyBehavior = new BadFlyBehavior();
        mQuackBehavior = new GeGeQuackBehavior();
    }

    @Override
    public void display() {
        System.out.println("**RedHead**");
    }
}
