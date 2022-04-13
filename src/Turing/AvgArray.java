package Turing;

/**
 * 子数组最大平均数
 * 给一个整数数组，找出平均数最大且长度为k的下标连续的子数组，并输出该最大平均数
 * 例子：输入【1，12，-5，-6,50,3】，k=4
 * 输出：12.75  =》》》》》 因为 （12-5-6+50）/4
 */
public class AvgArray {

    public static void main(String[] args) {
        System.out.println(ScollWindow(new int[]{1,12,-5,-6,50,3},4));
        System.out.println(findMaxAverage(new int[]{1,12,-5,-6,50,3},4));
    }



    //方法一：滑动窗口(自己写的)
    public static double ScollWindow(int[] nums,int k){
        int max = 0;
        for (int i = 0; i < nums.length - k; i++) {
            int count = 0;
            for (int j = 0;j < k;j++){
                count += nums[i+j];
            }
            if (count > max){
                max = count;
            }
        }
        return (max*1.0)/k;
    }




    /**
     * 滑动窗口：图灵官方写的
     * 相比之下与前面自己写的来说，官方优化了时间复杂度
     * @param nums
     * @param k
     * @return
     */
    public static double findMaxAverage(int[] nums,int k) {
        int sum = 0;
        int n = nums.length;
        //统计第一个窗口的和
        for (int i = 0;i < k;i++){
            sum += nums[i];
        }
        int max = sum;
        for (int i = k;i < n; i++){
            sum = sum + nums[i] - nums[i-k];
            max = Math.max(sum,max);
        }
        return 1.0 * max / k;
    }
}
