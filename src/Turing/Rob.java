package Turing;

import java.nio.file.FileStore;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你不触动警报装置的情况下，一夜之内能够偷窃到的最高金额。
 */
public class Rob {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        System.out.println(maxMoney3(nums));//4
        System.out.println(maxMoney3(new int[]{2,7,9,3,1}));//12
    }


    /**
     * 方法1：递归
     * @param nums
     * @param index
     * @return
     */
    private static int maxMoney(int[] nums,int index) {
        if (nums == null || index < 0) {
            return 0;
        }
        if (index == 0) {
            return nums[0];
        }
        return Math.max(maxMoney(nums,index-1),maxMoney(nums,index-2) + nums[index]);
    }


    /**
     * 方法2：动态规划
     * 将方法一的递归结果保存在dp数组中
     * @param nums
     * @return
     */
    private static int maxMoney2(int[] nums){
        int length = nums.length;
        if (nums == null || length == 0 ) {
            return 0;
        }
        if (length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);
        for (int i=2;i<length;i++) {
            dp[i] = Math.max(dp[i-1],dp[i-2]+nums[i]);
        }
        return dp[length-1];
    }


    /**
     * 方法3：对方法2进行优化空间复杂度
     * @param nums
     * @return
     */
    private static int maxMoney3(int[] nums){
        int length = nums.length;
        if (nums == null || length == 0 ) {
            return 0;
        }
        if (length == 1) {
            return nums[0];
        }
        int first = nums[0],second = Math.max(nums[0],nums[1]);
        for (int i=2;i<length;i++) {
            int t = Math.max(first+nums[i],second);
            first = second;
            second = t;
        }
        return second;
    }

}


/**
 * 升级版，房屋会首尾相连
 */
class Rob2{
    public static void main(String[] args) {

    }

    private static int maxMoney(int[] nums){
        return 0;
    }

}
