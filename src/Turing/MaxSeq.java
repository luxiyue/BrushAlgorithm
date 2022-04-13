package Turing;

/**
 * 最长连续递增序列：
 * 给定一个未知排序的整数数组，找到最长且连续递增的子序列，并返回该序列的长度
 * 序列的下标是连续的
 */
public class MaxSeq {

    public static void main(String[] args) {
        System.out.println(findLength(new int[]{1,2,3,2,3,4,3,4,5,6,7}));
    }

    private static int findLength(int[] nums) {
        int start = 0;
        int max = 0;
        for (int i = 1; i < nums.length; i++){
            if (nums[i] <= nums[i-1]){
                start = i;
            }
            max = Math.max(max,i-start+1);
        }
        return max;
    }

}
