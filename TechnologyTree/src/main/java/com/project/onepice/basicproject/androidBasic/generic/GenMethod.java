package com.project.onepice.basicproject.androidBasic.generic;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by onepice2015 on 16/6/23.
 */
public class GenMethod {

    public static <T> void  fromArrayToCollection(T[] a, Collection<T> c){
        for (T t: a){
            c.add(t);
        }
    }

    public static void main(String[] args){
        Object[] oa = new Object[100];

        Collection<Object> co =new ArrayList<>();

        GenMethod.<Object>fromArrayToCollection(oa,co);

        String[] sa = new String[100];

        Collection<String> cs = new ArrayList<String>();

        //T 推断为String
        fromArrayToCollection(sa,cs);

        //T 推断为Object
        fromArrayToCollection(sa,co);

        Integer[] ia = new Integer[100];

        Float[] fa=new Float[100];
        Number[] na=new Number[100];
        Collection<Number> cn=new ArrayList<Number>();

        //T 推断为Number
        fromArrayToCollection(ia,cn);

        String someString = new String();
        Object someObject = new Object();

        someObject=someString;


    }

    public static<T extends Comparable<T>> int countGreater(T[] array, T elem){
        int count = 0;
        for (T t: array){
            if (t.compareTo(elem)>0){//编译错误
                ++ count;
            }
        }

        return count;
    }



}


/**
 * 第一、定义方法所用的泛型参数需要在修饰符之后添加，public static<T>,如果有多个泛型参数，可如此定义<T1,T2>
 * 第二、不建议在泛型变量里添加其它类型
 * 第三、
 * 4.泛型的方法与构造函数
 5.泛型参数的界限
 <T extends BoundingType>
 此定义表示 T 应该是BoundingType的子类型(subtype),T和BoundingType可以是类，也可以是接口，另外注意的的是，此处的
 extends表示的子类型，不等同于继承。
 6.<T extends A & B & C>
 一个泛型参数可以有多重限制范围，使用 &分隔，且限制范围中最多有一个类，如果用一个类作为限定
 ，它必须是限定列表中的第一个

 7.泛型方法与泛参界限的综合
   如果说泛型方法是一个有用的工具，哪泛参的界限就应该这个工具的灵魂，为这个工具添加了一些行为准则


 8.泛型，集成与子类型
 如果两个类之间相互兼容（继承与被继承），那么便可以将一个类对象赋值给另一个类对象，比如：你可以将一个String对象赋值给Object，String是Object的子类，

 * */































































//for (T t: a){
//        c.add(t);
//        c.add(new Object());
//        }


class A { /* ... */ }
interface B { /* ... */ }
interface C { /* ... */ }

class D <T extends A & B & C> { /* ... */ }





























