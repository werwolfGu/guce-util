package com.guce.sort;

/**
 * 快速排序
 * Created by gucheng_en on 2016/5/5.
 */
public class QuickSort {

    /**
     * 升序
     * @param arr
     * @param left
     * @param right
     */
    public static void quickSortA2Z(int[] arr, int left, int right){

        int midl = 0;
        int tmp = arr[left];
        int lefttmp = left;
        int righttmp = right;
        int i = 0 , j = 0;
        while(left < right){
            //从右边找比tmp小的数
            for( i = right ; i > left ; i--){
                if(arr[i] < tmp){
                    arr[left] = arr[i];
                    right = i;
                    break;
                }
            }
            right = i;

            //从左边找比tmp大的数
            for(j = left ;j < right ; j++){
                if(arr[j] > tmp){
                    arr[right] = arr[j];
                    left = j;
                    break;
                }
            }
            left = j;

        }

        midl = left;
        arr[midl] = tmp;

        if(lefttmp < midl -1)
            quickSortA2Z(arr,lefttmp,midl -1);
        if(midl +1 < righttmp)
            quickSortA2Z(arr,midl + 1,righttmp);

    }

    /**
     * 降序
     * @param arr
     * @param left
     * @param right
     */
    public static void quickSortZ2A(int[] arr, int left, int right){

        int midl = 0;
        int tmp = arr[left];
        int lefttmp = left;
        int righttmp = right;
        int i = 0 , j = 0;
        while(left < right){
            //从右边找比tmp大的数
            for( i = right ; i > left ; i--){
                if(arr[i] > tmp){
                    arr[left] = arr[i];
                    right = i;
                    break;
                }
            }
            right = i;

            //从左边找比tmp小的数
            for(j = left ;j < right ; j++){
                if(arr[j] < tmp){
                    arr[right] = arr[j];
                    left = j;
                    break;
                }
            }
            left = j;

        }

        midl = left;
        arr[midl] = tmp;

        if(lefttmp < midl -1)
            quickSortZ2A(arr,lefttmp,midl -1);
        if(midl +1 < righttmp)
            quickSortZ2A(arr,midl + 1,righttmp);

    }

    public static void main(String[] args) {
        QuickSort q = new QuickSort();
        int[] arr = {2,14,5,6,3,23,1,75,4,8,2,7,543,8};
        q.quickSortA2Z(arr,0,arr.length - 1);

        for(int i = 0 ; i < arr.length ; i++){
            System.out.println(String.format("arr[%s]:%s",i,arr[i]));
        }
        System.out.println("*****************************************");
        q.quickSortZ2A(arr,0,arr.length - 1);
        for(int i = 0 ; i < arr.length ; i++){
            System.out.println(String.format("arr[%s]:%s",i,arr[i]));
        }
    }
}
