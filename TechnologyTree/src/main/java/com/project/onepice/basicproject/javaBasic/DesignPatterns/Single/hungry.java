package com.project.onepice.basicproject.javaBasic.DesignPatterns.Single;

/**
 *
 * 饿汉式单例类，在类初始化时，已经自行实例化
 *
 * Created by onepice2015 on 16/6/20.
 */
public class hungry {
    private hungry(){}
    private static  final  hungry  single = new hungry();

    //静态工厂方法
    public static  hungry getInstance(){
        return  single;
    }

}
