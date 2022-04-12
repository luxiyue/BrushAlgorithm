package Turing;

import java.util.Arrays;

/**
 * 两数之和2
 * 给定一个升序排列！！的整数数组numbers,从数组中找出两个数满足相加之和等于目标数target。
 * 假设每个输入只对应唯一的答案，而且不可以重复使用相同的元素
 * 返回两数的下标值，以数组形式返回
 */
public class TwoMaxPlus2 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSearch(new int[]{1,2,3,4,5,6},10)));
        System.out.println(Arrays.toString(twoPoint(new int[]{1,2,3,4,5,6},10)));
    }
    //本题是上一题的变种，所以上面的两种方法都可以用于这一题


    //方法一：二分查找
    public static int[] twoSearch(int[] numbers,int target){
        if (numbers.length < 2){
            return new int[0];
        }
        for (int i = 0;i < numbers.length;i++){
            int low = i,high = numbers.length - 1;
            //二分查找，范围: low ~ high
            while (low <= high) {
                int mid = low + (high-low)/2;
                if (numbers[mid] == target - numbers[i] ){
                    return new int[]{i,mid};
                }else if (numbers[mid] > target - numbers[i] ){
                    high = mid - 1;
                }else {
                    low = mid + 1;
                }
            }
        }
        return new int[0];
    }



    //方法二：双指针

    /**
     * 核心思路：
     * 当左指针加右指针小于target，左指针右移
     * 当左指针加右指针大于target，右指针左移
     */
    public static int[] twoPoint(int[] numbers,int target){
        if (numbers.length < 2){
            return new int[0];
        }
        int low = 0,high = numbers.length -1 ;
        while (low <= target){
            if (numbers[low] + numbers[high] == target) {
                return new int[]{low,high};
            } else if (numbers[low] + numbers[high] < target) {
                low++;
            }else {
                high--;
            }
        }
        return new int[0];
    }

}
