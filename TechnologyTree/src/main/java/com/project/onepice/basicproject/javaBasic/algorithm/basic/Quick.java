package com.project.onepice.basicproject.javaBasic.algorithm.basic;

/**
 * Created by onepice2015 on 2016/11/23.
 *
 * 快速排序:
 *
 */

public class Quick {
    //基准点划分子数组
    void  quickSort(int[]array,int left,int right){
        if (left<right){
            //k基准点下标
            int k=parition(array,left,right);
            quickSort(array,left,k-1);
            quickSort(array,k+1,right);
        }
    }


    int parition(int[]array,int left,int right){
        int key=array[right];//以最后一个元素为基准点
        int i=left-1;
        int temp;
        for (int j=left;j<right;j++){//小于key的就越小，大于key的就越大
            if (array[j]<key){
                i++;//得到比key大的位数的下标，如果持续小于k的话，先对小的这一部分进行一次值替换
                temp=array[j];//j=0
                array[j]=array[i];//i=0
                array[i]=temp;
            }
        }
        temp=array[i+1];
        array[i+1]=array[right];//key   temp >key
        array[right]=temp;
        return i+1;
    }

}








































