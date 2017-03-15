package com.project.onepice.basicproject.javaBasic.algorithm;

/**
 * Created by onepice2015 on 16/6/29.
 *
 * java_green 面试中的常用算法
 */
public class basicExample {

 /**
  *希尔排序
  *基本思想：算法先将要排序的一组数按某个增量d（n/2,n为要排序数的个数）分成若干组，每组中记录的下标相差d.对每组中全部元素进行直接插入排序，然后再用一个较小的增量（d/2）对它进行分组，在每组中再进行直接插入排序。当增量减到1时，
  *进行直接插入排序后，排序完成。
  *
  *
  *
  * */

public static int[] HillSort(int[] sort){
    double d1 = sort.length;  // 数组长度为 6

    int temp = 0;

    while (true) {

        d1 = Math.ceil(d1/2);//取中间数 当数组长度为奇数是 n/2+1

        int d=(int) d1;

        for (int x = 0; x < d; x++) {
            for (int i = x+d; i < sort.length; i+=d) {
                int j=i-d;
                for(temp = sort[i];j>=0&&temp<sort[j]; j-=d){
                    sort[j+d]=temp;
                }
            }
        }

        if (d == 1){
            break;
        }

        for (int i = 0; i < sort.length; i++) {
            System.out.println(sort[i]);
        }
    }

    return sort;
}




 /**
  * 冒泡排序
  *
  * 依次比较相邻的两个数，将小数放在前面，大数放在后面
  * 冒泡排序，具有稳定性
  * 时间复杂度为0,不及堆排序，快速排序
  *
  * */
 public static int[] bubbleSort(int[] sort){

        for (int i=1;i<sort.length;i++){
            for (int j=0;j<sort.length-i;j++){
                if (sort[j]>sort[j+1]){
                    int temp = sort[j+1];
                    sort[j+1]=sort[j];
                    sort[j]=temp;
                }
            }
        }
     return sort;
    }

  /**
   * 选择排序
   * * 每一趟从待排序的数据元素中选出最小或最大的一个元素
   * 顺序放在已排好序的数列的最后，直到全部待排序的数据元素排完
   * 选择排序是不稳定的排序方法
   *
   * */
  private static int[] selectSort(int[] sort){
      for (int i=0;i<sort.length-1;i++){
          for (int j=i+1;j<sort.length;j++){
              if (sort[j]<sort[i]){//前一位大于后一位
                  int temp = sort[j];
                  sort[j]=sort[i];//大的放后面
                  sort[i]=temp;//小的放前面
              }
          }
      }
      return sort;
  }

    /**
     * 快速排序
     *
     * 快速排序，通过一趟排序将要排序的数据分割成独立的两部分，其中
     * 一部分的所有数据都比另外一部分的所有数据都要小
     *
     * 然后在按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到
     * 整个数据变成有序序列
     * */

    /**
     *快速排序
     * @param sort 要排序的数组
     * @param start 排序的开始坐标
     * @param end 排序的结束坐标
     */

    public static void quickSort(int[] sort, int start,int end){
        //设置关键数据key为要排序数组的第一个元素
        //即第一趟排序后，key右边的数全部比key大，key左边的数全部比key小
        int key = sort[start];
        //设置数组左边的索引，往右移动判断比key大的数
        int i = start;
        //设置数组右边的索引，往左移动判断key小的数
        int j=end;
        //如果左边索引比右边索引小，则还有数据没有排序
        while (i<j){
            while (sort[j] > key && j> start){
                j--;
            }

            while (sort[i]<key && i<end){
                i++;
            }

            if (i<j){
                int temp = sort[i];
                sort[i]=sort[j];
                sort[j]=temp;
            }
        }
        /*
        * 如果左边索引比右边索引要大，说明第一次排序完成，将sort[j]与key对换
        * 即保持了key左边的数比key小，key右边的数比key大
        * */
        if (i>j){
            int temp = sort[j];
            sort[j]=sort[start];
            sort[start]=temp;
        }

        //递归调用
        if (j>start && j<end){
            quickSort(sort,start,j-1);
            quickSort(sort,j+1,end);
        }

    }

/**
 *直接插入排序
 *
 * 将一个数据插入到序数据中，从而得到一个新的，个数加一的有序数据
 *
 * 算法适用于少量数据的排序，时间复杂度为O 是稳定的排序方法
 *
 * */

private static void directInsertSort(int[] sort){
    for (int i=0; i<sort.length;i++){
        int index = i-1;
        int temp = sort[i];
        while (index>=0 &&sort[index]>temp){//比temp大的往后移
            sort[index+1]=sort[index];
            index--;
        }
        sort[index+1]=temp;
    }
}

/**
 * 二分搜索法，返回坐标，不存在返回-1
 *
 * */

private static int binarySearch(int[] sort,int data){
    if (data<sort[0]|| data>sort[sort.length-1]){
        return -1;
    }

    int begin=0;
    int end=sort.length;

    int mid=(begin+end)/2;

    while (begin<=end){
        mid=(begin+end)/2;
        if (data>sort[mid]){
            begin=mid+1;
        }else if (data<sort[mid]){
            end=mid-1;
        }else {
            return mid;
        }
    }
    return -1;
}






public static void main(String [] args){
//        Random random = new Random();
//        int[] sort = new int[10];
//        for (int i=0;i<10;i++){
//            sort[i]=random.nextInt(50);
//        }
//        System.out.print("排序前的数组为：");
//
//        for (int i:sort){
//            System.out.print(i+" ");
//        }
//
//        int[] sortSort=selectSort(sort);
//
//        System.out.print("排序后的数组为：");
//
//        for (int i:sortSort){
//           System.out.print(i+" ");
//        }

      /**
       * 快速排序
       * */

//    int[]sortSpeed={54,31,89,33,66,12,68,20};
//    System.out.print("排序前的数组为: ");
//    for (int data: sortSpeed){
//        System.out.print(data+" ");
//    }
//
//    System.out.println();;
//
//    quickSort(sortSpeed,0,sortSpeed.length-1);
//
//    System.out.print("排序后的数组为: ");
//
//    for (int data:sortSpeed){
//        System.out.print(data+" ");
//    }


//        Random random = new Random();
//        int[] sort = new int[10];
//        for (int i=0;i<10;i++){
//            sort[i]=random.nextInt(50);
//        }
//        System.out.print("排序前的数组为：");
//
//        for (int i:sort){
//            System.out.print(i+" ");
//        }
//
//        directInsertSort(sort);
//
//        System.out.print("排序后的数组为：");
//
//        for (int i:sort){
//           System.out.print(i+" ");
//        }


    int[] sort={1,2,3,4,5,6,7,8,9,10};
    int mask =binarySearch(sort,6);
    System.out.println(mask);
}






















}
