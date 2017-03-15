package com.project.onepice.basicproject.javaBasic.DesignPatterns.observer.push;

/**
 * Created by onepice2015 on 16/6/20.
 */
public class ConcreteObserver implements Observer {
    //观察者的状态
    private String observerState;

    @Override
    public void update(String state) {
        /**
         * 更新观察者的状态，使其与目标的状态保持一致
         */
        observerState = state;
        System.out.println("状态为："+observerState);
    }

}


/**
 *
 * 推模型和拉模型
 　　在观察者模式中，又分为推模型和拉模型两种方式。

 　　●　　推模型

 　　　　 主题对象向观察者推送主题的详细信息，不管观察者是否需要，推送的信息通常是主题对象的全部或部分数据。

 　　●　　拉模型

 　　　　 主题对象在通知观察者的时候，只传递少量信息。如果观察者需要更具体的信息，由观察者主动到主题对象中获取，相当于是观察者从主题对象中拉数据。一般这种模型的实现中，会把主题对象自身通过update()方法传递给观察者，这样在观察者需要获取数据的时候，就可以通过这个引用来获取了。

 　　根据上面的描述，发现前面的例子就是典型的推模型，下面给出一个拉模型的实例。

 　　拉模型的抽象观察者类

 　　拉模型通常都是把主题对象当做参数传递。
 *
 * */