package com.project.onepice.basicproject.javaBasic.DesignPatterns.strategy;

/**
 * Created by onepice2015 on 16/6/20.
 */
public class BlackEnemy implements IStrategy {
    @Override
    public void operate() {
        System.out.println("孙夫人断后，挡住追兵");
    }
}
