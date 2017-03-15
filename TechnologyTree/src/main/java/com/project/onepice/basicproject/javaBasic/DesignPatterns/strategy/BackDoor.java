package com.project.onepice.basicproject.javaBasic.DesignPatterns.strategy;

/**
 * Created by onepice2015 on 16/6/20.
 */
public class BackDoor implements  IStrategy {

    @Override
    public void operate() {
        System.out.println("找乔国老帮忙，让吴国太给孙权施加压力，使孙权不能杀刘备");
    }
}
