package com.project.onepice.basicproject.javaBasic.algorithm.basic;

/**
 * Created by onepice2015 on 2016/11/23.
 *
 * 冒泡排序从大到小:
 *
 *  后面一个和前面一个比，大的放前面
 */
public class Bubble {

    /**
     *  冒泡排序从大到小:
     *
     *  后面一个和前面一个比，大的放前面
     * */
   void Bulldle(int[]array,int num){
       int temp;
       for (int i=0;i<array.length-1;i++){
           for (int j=i+1;j<num;j++){
               if (array[i]<array[j]){
                   temp=array[i];
                   array[i]=array[j];
                   array[j]=temp;
               }
           }
       }
   }

    /**
     *  冒泡排序从小到大:
     *
     *
     * */
    void Bulldle2(int[]array,int num){
        int temp;
        boolean flag=true;
        for (int i=0;i<array.length-1 && flag;i++){
            for (int j=i+1;j<num ;j++){
                if (array[i]>array[j]){
                    temp=array[i];
                    array[i]=array[j];
                    array[j]=temp;
                    flag=true;
                }
            }
        }
    }
}
