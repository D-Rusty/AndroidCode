package com.project.onepice.basicproject.javaBasic.algorithm.basic;

/**
 * Created by onepice2015 on 2016/11/23.
 */

public class Insert {
    /***
     *
     * 插入排序，在一个已经排序好的序列中，为下一个元素找合适的插入位置
     * */
    void InsertSort(int[] arry,int n){
        int i,j;
        int temp;
        for (i=1;i<n;i++){
            //为当前元素选择合适的位置
            temp=arry[i];
            for (j=i-1;j>=0;j--){
                if (arry[j]>temp){
                    arry[j+1]=arry[j];
                }else
                    break;;
                arry[j+1]=temp;
            }
        }
    }
}
