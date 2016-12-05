package com.guce;

import sun.security.util.BitArray;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chengen.gu on 2016/11/16.
 */
public class SubClass extends ParentClass {
    private String substr1 = "ss1";
    private static String str = "str";
    private String substr = "sub";
    static{
        System.out.println("static{}");
    }
    public SubClass(){
        System.out.println(substr1);
        System.out.println(substr);
        System.out.println("sub class");
    }

    public static int lengthOfLongestSubstring(String s) {
        String currLongestStr;
        int length = 0,i=0,j=0,currLength = 0,currIndex = 0;
        boolean flag = false;
        StringBuffer sb = new StringBuffer();
        for(i = 0 ; i < s.length(); i++){

            char ch = s.charAt(i);
            flag = false;

            for(j = 0 ; j < sb.length(); j++){
                char ch1 = sb.charAt(j);
                if(ch == ch1){
                    flag = true;
                    break;
                }
            }

            if(flag){
                currLongestStr = sb.toString();
                currLength = sb.length();
                if(length < currLength){
                    length = currLength;
                }
                i = currIndex++;
                sb = new StringBuffer();

            }else{
                sb.append(ch);
            }

            currLength = sb.length();
            if(currLength > length){
                length = currLength;
            }

        }


        return length;

    }

    public int[] twoSum(int[] nums, int target) {

        int[] result = {0,0};
        Map<Integer,Integer> map = new HashMap();
        for(Integer i = 0 ; i < nums.length ; i++){
            map.put(nums[i],i);
        }
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            Integer key = entry.getKey();
            Integer src = target - key;
            if(map.containsValue(src)){
                result[0] = entry.getValue();
                result[1] = map.get(src);
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("main");
        new SubClass();
        char ch = 'a';
        int a = ch;
        String str = "dvdf";
        int i = lengthOfLongestSubstring(str);


        System.out.println(i);
    }
}
