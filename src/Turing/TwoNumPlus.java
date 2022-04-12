package Turing;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 两数之和
 * 给定一个整数数组numbers,从数组中找出两个数满足相加之和等于目标数target。
 * 假设每个输入只对应唯一的答案，而且不可以重复使用相同的元素
 * 返回两数的下标值，以数组形式返回
 */
public class TwoNumPlus {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(bf(new int[]{1,2,3,4,5,6},10)));
        System.out.println(Arrays.toString(mapSolution(new int[]{1,2,3,4,5,6},10)));
    }


    //暴力法
    public static int[] bf(int[] nums,int target){
        if (nums.length < 2){
            return new int[0];
        }
        for (int i=0;i<nums.length-1;i++){
            for (int j=i+1;j< nums.length;j++){
                if (nums[i] + nums[j] == target){
                    return new int[]{i,j};
                }
            }
        }
        return new int[0];
    }


    //使用map减少时间复杂度
    public static int[] mapSolution(int[] nums,int target){
        if (nums.length < 2){
            return new int[0];
        }
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i=0;i<nums.length;i++){
            if (map.containsKey(target-nums[i])){
                return new int[]{i,map.get(target-nums[i])};
            }
            map.put(nums[i],i);
        }
        return new int[0];
    }


}
