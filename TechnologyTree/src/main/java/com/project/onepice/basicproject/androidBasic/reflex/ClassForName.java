package com.project.onepice.basicproject.androidBasic.reflex;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by onepice2015 on 16/6/22.
 *
 * 本类用于测试反射API，利用用户输入类的全路径，
 * 找到该类所有的成员方法和属性
 */
public class ClassForName {

    public ClassForName(){
        String classInfo="";
//                classInfo=JOptionPane.showInputDialog(null,"输入类全路径");//要求用户输入类的全路径

        try{
            Class<?> cla =Class.forName(classInfo);
            Method[] method=cla.getDeclaredMethods();
            System.out.println("forName:"+cla);

            for (Method me:method){//
                System.out.println("方法有："+me.toString());
            }

            System.out.println("*****************************************************");
            Field[] field=cla.getDeclaredFields();//利用得到的Class对象的自审，返回属性对象集合

            for(Field me:field){ //遍历该类属性的集合

                System.out.println("属性有:"+me.toString());//打印属性信息

            }

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        }

    }

    public static void main(String[] args) {

        new ClassForName();

    }


}
