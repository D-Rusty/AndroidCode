package com.project.onepice.basicproject.javaBasic.DesignPatterns.strategy;

/**
 * Created by onepice2015 on 16/6/20.
 */
public class GivenGreenLight  implements  IStrategy{
    @Override
    public void operate() {
        System.out.println("求吴国太开个绿灯，放行");
    }
}
