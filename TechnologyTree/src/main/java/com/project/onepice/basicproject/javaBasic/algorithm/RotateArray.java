package com.project.onepice.basicproject.javaBasic.algorithm;

/**
 * Created by onepice2015 on 16/6/24.
 */
public class RotateArray {
    /**
     * 从数组第n-k位元素开始调换
     * */
    public static void intermediateArray(int[] nums, int k){
        System.out.println("替换结果前：");
        for (int i : nums) {
            System.out.print(i);
        }
        System.out.println();
        if (k> nums.length)
            k=k%nums.length;
        int[] result = new int[nums.length];

        for (int i=0; i<k;i++){
            result[i] = nums[nums.length-k+i];
        }

        int j=0;

        for (int i=k; i<nums.length; i++){
            result[i] = nums[j];
            j++;
        }

        System.arraycopy(result,0,nums,0,nums.length);

        System.out.println("替换结果后：");
        for (int i : result) {
            System.out.print(i);
        }
        System.out.println();
    }


    public static void bubbleRotate(int[]arr, int order){
        if (arr == null || order <0){
            throw new IllegalArgumentException("Illegal argument! ");
        }

        System.out.println("转换前的结果");
        for (int i : arr) {
            System.out.print(i);
        }
        System.out.println();
        for (int i=0; i<order; i++){
            for (int j=arr.length -1 ;j>0; j--){
                int temp = arr[j];
                arr[j]=arr[j-1];
                arr[j-1]=temp;
            }
        }

        System.out.println("转换后的结果");
        for (int i : arr) {
            System.out.print(i);
        }
        System.out.println();
    }



    public static void main(String[] args){
        int[] number=new int[]{1, 2, 3, 4, 5, 6, 7};
        System.out.println("IntermediateArray");
        RotateArray.intermediateArray(number,6);
        System.out.println("BubbleRote");
        int[] bubbleRotate=new int[]{1, 2, 3, 4, 5, 6, 7};
        RotateArray.bubbleRotate(bubbleRotate,6);
    }
}


















