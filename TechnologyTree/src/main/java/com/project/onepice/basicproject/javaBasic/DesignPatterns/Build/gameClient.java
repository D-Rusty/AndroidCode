package com.project.onepice.basicproject.javaBasic.DesignPatterns.Build;

/**
 * Created by onepice2015 on 16/6/21.
 */



class Actor
{
    private  String type; //角色类型
    private  String sex; //性别
    private  String face; //脸型
    private  String costume; //服装
    private  String hairstyle; //发型

    public  void setType(String type) {
        this.type  = type;
    }
    public  void setSex(String sex) {
        this.sex  = sex;
    }
    public  void setFace(String face) {
        this.face  = face;
    }
    public  void setCostume(String costume) {
        this.costume  = costume;
    }
    public  void setHairstyle(String hairstyle) {
        this.hairstyle  = hairstyle;
    }
    public  String getType() {
        return  (this.type);
    }
    public  String getSex() {
        return  (this.sex);
    }
    public  String getFace() {
        return  (this.face);
    }
    public  String getCostume() {
        return  (this.costume);
    }
    public  String getHairstyle() {
        return  (this.hairstyle);
    }
}

//角色建造器：抽象建造者
abstract class ActorBuilder
{
    protected  Actor actor = new Actor();

    public  abstract void buildType();
    public  abstract void buildSex();
    public  abstract void buildFace();
    public  abstract void buildCostume();
    public  abstract void buildHairstyle();

    //工厂方法，返回一个完整的游戏角色对象
    public Actor createActor()
    {
        return actor;
    }
}

//英雄角色建造器：具体建造者
class HeroBuilder extends ActorBuilder
{
    public  void buildType()
    {
        actor.setType("英雄");
    }
    public  void buildSex()
    {
        actor.setSex("男");
    }
    public  void buildFace()
    {
        actor.setFace("英俊");
    }
    public  void buildCostume()
    {
        actor.setCostume("盔甲");
    }
    public  void buildHairstyle()
    {
        actor.setHairstyle("飘逸");
    }
}

//天使角色建造器：具体建造者
class AngelBuilder extends ActorBuilder
{
    public  void buildType()
    {
        actor.setType("天使");
    }
    public  void buildSex()
    {
        actor.setSex("女");
    }
    public  void buildFace()
    {
        actor.setFace("漂亮");
    }
    public  void buildCostume()
    {
        actor.setCostume("白裙");
    }
    public  void buildHairstyle()
    {
        actor.setHairstyle("披肩长发");
    }
}

//恶魔角色建造器：具体建造者
class DevilBuilder extends ActorBuilder
{
    public  void buildType()
    {
        actor.setType("恶魔");
    }
    public  void buildSex()
    {
        actor.setSex("妖");
    }
    public  void buildFace()
    {
        actor.setFace("丑陋");
    }
    public  void buildCostume()
    {
        actor.setCostume("黑衣");
    }
    public  void buildHairstyle()
    {
        actor.setHairstyle("光头");
    }
}
   /* 指挥者类 ActorController 定义了 construct() 方法，该方法拥有一个抽象建造者 ActorBuilder 类型的参数，在该方法内部实现了游戏角色对象的逐步构建，代码如下所示：
*/
        //游戏角色创建控制器：指挥者
class ActorController
{
    //逐步构建复杂产品对象
    public Actor construct(ActorBuilder ab)
    {
        Actor actor;
        ab.buildType();
        ab.buildSex();
        ab.buildFace();
        ab.buildCostume();
        ab.buildHairstyle();
        actor=ab.createActor();
        return actor;
    }
}

public class gameClient {
}



/**
 * 在建造者模式中，客户端只需实例化指挥者类，指挥者类针对抽象建造者编程，客户端根据需要传入具体的建造者类型，指挥者将指导具体建造者一步一步构造一个完整的产品（逐步调用具体建造者的 buildX() 方法），相同的构造过程可以创建完全不同的产品。在游戏角色实例中
 * ，如果需要更换角色，只需要修改配置文件，更换具体角色建造者类即可；如果需要增加新角色，可以增加一个新的具体角色建造者类作为抽象角色建造者的子类，再修改配置文件即可，原有代码无须修改，完全符合“开闭原则”。
 *
 *
 *
 *
 *
 * 建造者模式总结

 建造者模式的核心在于如何一步步构建一个包含多个组成部件的完整对象，使用相同的构建过程构建不同的产品，在软件开发中，如果我们需要创建复杂对象并希望系统具备很好的灵活性和可扩展性可以考虑使用建造者模式。

 主要优点

 建造者模式的主要优点如下：

 (1) 在建造者模式中，客户端不必知道产品内部组成的细节，将产品本身与产品的创建过程解耦，使得相同的创建过程可以创建不同的产品对象。

 (2) 每一个具体建造者都相对独立，而与其他的具体建造者无关，因此可以很方便地替换具体建造者或增加新的具体建造者，用户使用不同的具体建造者即可得到不同的产品对象。由于指挥者类针对抽象建造者编程，增加新的具体建造者无须修改原有类库的代码，系统扩展方便，符合“开闭原则”

 (3) 可以更加精细地控制产品的创建过程。将复杂产品的创建步骤分解在不同的方法中，使得创建过程更加清晰，也更方便使用程序来控制创建过程。

 主要缺点

 建造者模式的主要缺点如下：

 (1) 建造者模式所创建的产品一般具有较多的共同点，其组成部分相似，如果产品之间的差异性很大，例如很多组成部分都不相同，不适合使用建造者模式，因此其使用范围受到一定的限制。

 (2) 如果产品的内部变化复杂，可能会导致需要定义很多具体建造者类来实现这种变化，导致系统变得很庞大，增加系统的理解难度和运行成本。

 适用场景

 在以下情况下可以考虑使用建造者模式：

 (1) 需要生成的产品对象有复杂的内部结构，这些产品对象通常包含多个成员属性。

 (2) 需要生成的产品对象的属性相互依赖，需要指定其生成顺序。

 (3) 对象的创建过程独立于创建该对象的类。在建造者模式中通过引入了指挥者类，将创建过程封装在指挥者类中，而不在建造者类和客户类中。

 (4) 隔离复杂对象的创建和使用，并使得相同的创建过程可以创建不同的产品。
 * */


















