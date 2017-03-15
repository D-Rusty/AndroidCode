package com.project.onepice.basicproject.javaBasic.DesignPatterns.factory.Abstract;

/**
 * Created by onepice2015 on 16/6/21.
 */


//在本实例中我们对代码进行了大量简化，实际使用时，界面组件的初始化代码较为复杂，还需要使用JDK中一些已有类，为了突出核心代码，在此只提供框架代码和演示输出。
//按钮接口：抽象产品


interface Button {
    public void display();
}


//Spring按钮类：具体产品
class SpringButton implements Button {
    public void display() {
        System.out.println("显示浅绿色按钮。");
    }
}

//Summer按钮类：具体产品
class SummerButton implements Button {
    public void display() {
        System.out.println("显示浅蓝色按钮。");
    }
}

//文本框接口：抽象产品
interface TextField {
    public void display();
}

//Spring文本框类：具体产品
class SpringTextField implements TextField {
    public void display() {
        System.out.println("显示绿色边框文本框。");
    }
}

//Summer文本框类：具体产品
class SummerTextField implements TextField {
    public void display() {
        System.out.println("显示蓝色边框文本框。");
    }
}

//组合框接口：抽象产品
interface ComboBox {
    public void display();
}

//Spring组合框类：具体产品
class SpringComboBox implements ComboBox {
    public void display() {
        System.out.println("显示绿色边框组合框。");
    }
}

//Summer组合框类：具体产品
class SummerComboBox implements ComboBox {
    public void display() {
        System.out.println("显示蓝色边框组合框。");
    }
}

//界面皮肤工厂接口：抽象工厂
interface SkinFactory {
    public Button createButton();
    public TextField createTextField();
    public ComboBox createComboBox();
}

//Spring皮肤工厂：具体工厂
class SpringSkinFactory implements SkinFactory {
    public Button createButton() {
        return new SpringButton();
    }

    public TextField createTextField() {
        return new SpringTextField();
    }

    public ComboBox createComboBox() {
        return new SpringComboBox();
    }
}

//Summer皮肤工厂：具体工厂
class SummerSkinFactory implements SkinFactory {
    public Button createButton() {
        return new SummerButton();
    }

    public TextField createTextField() {
        return new SummerTextField();
    }

    public ComboBox createComboBox() {
        return new SummerComboBox();
    }
}


/**
 * 抽象工厂方法， 抽象工厂内容走向了多元素，组合，而不是单一功能，这是和普通工厂方法的区别
 *
 *
 * 抽象工厂模式总结
 抽象工厂模式是工厂方法模式的进一步延伸，由于它提供了功能更为强大的工厂类并且具备较好的可扩展性，在软件开发中得以广泛应用，尤其是在一些框架和 API 类库的设计中，例如在 Java 语言的 AWT（抽象窗口工具包）中就使用了抽象工厂模式，它使用抽象工厂模式来实现在不同的操作系统中应用程序呈现与所在操作系统一致的外观界面。抽象工厂模式也是在软件开发中最常用的设计模式之一。

 主要优点

 抽象工厂模式的主要优点如下：

 (1) 抽象工厂模式隔离了具体类的生成，使得客户并不需要知道什么被创建。由于这种隔离，更换一个具体工厂就变得相对容易，所有的具体工厂都实现了抽象工厂中定义的那些公共接口，因此只需改变具体工厂的实例，就可以在某种程度上改变整个软件系统的行为。

 (2) 当一个产品族中的多个对象被设计成一起工作时，它能够保证客户端始终只使用同一个产品族中的对象。

 (3) 增加新的产品族很方便，无须修改已有系统，符合“开闭原则”。

 主要缺点

 抽象工厂模式的主要缺点如下：

 增加新的产品等级结构麻烦，需要对原有系统进行较大的修改，甚至需要修改抽象层代码，这显然会带来较大的不便，违背了“开闭原则”。

 适用场景

 在以下情况下可以考虑使用抽象工厂模式：

 (1) 一个系统不应当依赖于产品类实例如何被创建、组合和表达的细节，这对于所有类型的工厂模式都是很重要的，用户无须关心对象的创建过程，将对象的创建和使用解耦。

 (2) 系统中有多于一个的产品族，而每次只使用其中某一产品族。可以通过配置文件等方式来使得用户可以动态改变产品族，也可以很方便地增加新的产品族。

 (3) 属于同一个产品族的产品将在一起使用，这一约束必须在系统的设计中体现出来。同一个产品族中的产品可以是没有任何关系的对象，但是它们都具有一些共同的约束，如同一操作系统下的按钮和文本框，按钮与文本框之间没有直接关系，但它们都是属于某一操作系统的，此时具有一个共同的约束条件：操作系统的类型。

 (4) 产品等级结构稳定，设计完成之后，不会向系统中增加新的产品等级结构或者删除已有的产品等级结构。
 * */


public class AbstractClient {
}











