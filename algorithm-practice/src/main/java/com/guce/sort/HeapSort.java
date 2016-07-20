package com.guce.sort;

/**
 * 堆排序
 * Created by gucheng_en on 2016/5/5.
 */

public class HeapSort {

    public static void minHeapFixup(Integer[] arr,int length){

        int rightNode ,leftNode;
        //建堆  数据小的节点往上走
        for(int i = length ; i >= 0 ; i--){
            leftNode = 2*i + 1;
            rightNode = 2*i + 2;
            //判断左子节点 和右子节点是否存在
            //左节点不存在的话  右节点一定不存在
            if(leftNode > length){
                continue;
            }

            check_left_right_node:{

                //若左节点存在  右节点不存在
                if(rightNode > length){

                    break check_left_right_node;
                }
                //右节点存在的话 左节点一定存
                if(arr[leftNode] >= arr[rightNode]){
//                    swapValue(arr[leftNode],arr[rightNode]);
                    int tmp = arr[leftNode];
                    arr[leftNode] = arr[rightNode];
                    arr[rightNode] = tmp;

                    break check_left_right_node;
                }

            }

            if(arr[i] >= arr[leftNode]) {
                int tmp = arr[i];
                arr[i] = arr[leftNode];
                arr[leftNode] = tmp;
            }
        }
    }

    private static void swapValue(Integer left,Integer right){
        int tmp = left;
        left = right;
        right = tmp;
        System.out.println(left + "  " + right);
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[] {2,5,6,4,4,145,8,9,8,7,5,123};
//        Integer[] arr = new Integer[] {6,8,5};

        for(int i = arr.length -1 ; i> 0 ; i--){
               minHeapFixup(arr,i);
               int tmp = arr[0];
               arr[0] = arr[i];
               arr[i] = tmp;

        }
        for(int j = 0 ; j < arr.length ; j++){
            System.out.println("arr" + j + " :" + arr[j] + ";");
        }
        System.out.println("");
    }
}
