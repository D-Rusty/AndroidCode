package com.project.onepice.basicproject.javaBasic.DesignPatterns.Single;

import java.util.HashMap;
import java.util.Map;

/**
 * 登记式，单例模式
 *
 * Created by onepice2015 on 16/6/20.
 */
public class register {
    private  static Map<String,register> map = new HashMap<String ,register>();

    static {
        register sign=new register();
        map.put(sign.getClass().getName(),sign);
    }

    //保护的默认构造例子
    protected  register(){}

    //静态工厂方法，返回此类唯一实例
    public  static register getInstance(String name){
        if (name == null){
            name = register.class.getName();
            System.out.println("name == null"+"--->name="+name);
        }

        if (map.get(name) ==null) {
            try {
                map.put(name, (register) Class.forName(name).newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return map.get(name);
    }

    //一个示意性的商业方法
    public String about() {
        return "Hello, I am RegSingleton.";
    }
    public static void main(String[] args) {
        register single = register.getInstance(null);
        System.out.println(single.about());
    }
}
