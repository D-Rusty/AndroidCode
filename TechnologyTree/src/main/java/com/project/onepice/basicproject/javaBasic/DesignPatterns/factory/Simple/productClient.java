package com.project.onepice.basicproject.javaBasic.DesignPatterns.factory.Simple;


/**
 *首先将需要创建的各种不同对象（例如各种不同的 Chart 对象）的相关代码封装到不同的类中，这些类称为具体产品类，而将它们公共的代码进行抽象和提取后封装在一个抽象产品类中，每一个具体产品类都是抽象产品类的子类；然后提供一个工厂类用于创建各种产品，在工厂类中提供一个创建产品的工厂方法，该方法可以根据所传入的参数不同创建不同的具体产品对象；客户端只需调用工厂类的工厂方法并传入相应的参数即可得到一个产品对象。

 *简单工厂模式定义如下：

 *简单工厂模式（Simple Factory Pattern）：定义一个工厂类，它可以根据参数的不同返回不同类的实例，被创建的实例通常都具有共同的父类。因为在简单工厂模式中用于创建实例的方法是静态（static）方法，因此简单工厂模式又被称为静态工厂方法（Static Factory Method）模式，它属于类创建型模式。

 *简单工厂模式的要点在于：当你需要什么，只需要传入一个正确的参数，就可以获取你所需要的对象，而无须知道其创建细节。简单工厂模式结构比较简单，其核心是工厂类的设计，其结构如图所示：
 *
 *闯入不同的值到工厂类里面去工厂类通过调用一个静态方法创建不同的对象，每个对象都是集成自同一个父类方法
 * */




/**
 * Created by onepice2015 on 16/6/21.
 */
abstract class Product {
    //所有产品类的公共业务方法
    public void methodSame() {
        //公共方法的实现
    }

    //声明抽象业务方法
    public abstract void methodDiff();
}

class ConcreteProduct extends Product {
    //实现业务方法
    public void methodDiff() {
        //业务方法的实现
    }
}


class ConcreteProductA extends ConcreteProduct{
    @Override
    public void methodDiff() {
        super.methodDiff();
        System.out.println("ConcreteProductA !!! ");
    }
}

class ConcreteProductB extends ConcreteProduct{
    @Override
    public void methodDiff() {
        super.methodDiff();
        System.out.println("ConcreteProductB !!! ");
    }
}



class Factory {
    //静态工厂方法
    public static Product getProduct(String arg) {
        Product product = null;
        if (arg.equalsIgnoreCase("A")) {
            product = new ConcreteProductA();
            //初始化设置product
        }
        else if (arg.equalsIgnoreCase("B")) {
            product = new ConcreteProductB();
            //初始化设置product
        }
        return product;
    }
}

public class productClient {
    public static void main(String args[]) {
        Product product;
        product = Factory.getProduct("A"); //通过工厂类创建产品对象
        product.methodSame();
        product.methodDiff();
    }
}