package com.project.onepice.basicproject.javaBasic.DesignPatterns.Single;

/**
 * 懒汉式单例模式
 *
 * 懒汉式单例类，在第一次调用的时候实例化自己
 *
 * Created by onepice2015 on 16/6/20.
 */
public class Lazy {
//    private Lazy(){}
//    private static Lazy single=null;
//
//    //静态工厂方法
//
//    public static Lazy getInstance(){
//        if (single ==null){
//            single = new Lazy();
//        }
//        return  single;
//    }

    /**
     * 关于懒汉线程安全的考虑 --在getInstance方法上加同步
     * */
//    public static synchronized  Lazy getInstance(){
//        if (single == null){
//            single = new Lazy();
//        }
//        return  single;
//    }

    /**
     * 关于懒汉线程安全的考虑 --双重检查锁定
     * */

//    public static Lazy getInstance(){
//        if (single ==null){
//            synchronized (Lazy.class){
//                if (single ==null){
//                    single = new Lazy();
//                }
//            }
//        }
//        return  single;
//    }

    /**
     * 关于懒汉线程安全的考虑 --静态内部类 ?
     * */

    private  static  class  LazyHolder {
        private static final  Lazy INSTANNCE = new Lazy();
    }

    private Lazy(){}

    public static final  Lazy getInstance(){
        return LazyHolder.INSTANNCE;
    }


}
