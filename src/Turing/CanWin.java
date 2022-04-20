package Turing;

import java.util.Arrays;

/**
 * 预测赢家：
 * 给定一个表示分数的非负整数数组。玩家1从数组任意一端拿取一个分数，
 * 随后玩家⒉继续从剩余数组任意一端拿取分数，然后玩家1拿，……。
 * 每次一个玩家只能拿取一个分数，分数被拿取之后不再可取。直到没有剩余分数可取时游戏结束。最终获得分数总和最多的玩家获胜。
 * ===》
 * 给定一个表示分数的数组,预测玩家1是否会成为赢家。你可以假设每个玩家的玩法都会使他的分数最大化。
 */
public class CanWin {
    /**
     * 分析过程： 比如有 {5,200,1,3,6} 这个数组
     * ====== 当player1第一个元素取5时 ======
     * player1 : 5  ==》 player2为了胜利肯定把200取走  ==> player1第二次可以选择取{1,6}
     *      player1 ： 6  ==》  player2为了胜利肯定把3取走  ==》player1第三次只能选 1
     *      结果： 5 + 6 + 1 = 12
     * ======= 当player1第一个元素取6时 ========
     * player1 : 6  ==》 player2从 {5,3} 中选择，为了游戏胜利，player2肯定选3  ==》 player1第二次可以选 {5,1}
     *      player1 ： 5  ==》player2取走200  ===》 player1取走1
     *      结果：  6 + 5 + 1
     *
     */
    public static void main(String[] args) {
        int[] arr = new int[]{5,200,2,3,6};
        int sum = Arrays.stream(arr).sum();
        int max = maxScore(arr, 0, arr.length - 1);
        System.out.println(max > (sum-max));

        System.out.println(maxScore2(arr,0,arr.length-1) > 0);
        System.out.println(dp(arr));
    }


    /**
     * 方法一： 递归法
     * 先手无论从左边取还是从右边取，聪明的对手都会把分数少的情况留下来
     */
    static int maxScore(int[] arr,int l,int r) {
        if (l == r) {
            return arr[l];
        }
        int sLeft = 0,rRight = 0;
        if (r - l == 1) {
            sLeft = arr[l];
            rRight = arr[r];
        }
        if (r - l >= 2) {
//            sLeft = arr[l] + Math.min(maxScore(arr,l+2,r),maxScore(arr,l+1,r-1));
//            rRight = arr[r] + Math.min(maxScore(arr,l+1,r-1),maxScore(arr,l,r-2));
            int num = maxScore(arr, l + 1, r - 1);
            sLeft = arr[l] + Math.min(maxScore(arr,l+2,r),num);
            rRight = arr[r] + Math.min(num,maxScore(arr,l,r-2));
        }

        return Math.max(sLeft,rRight);
    }


    /**
     * 方法2： 统计每次 先手 和 后手 的差值，取和
     */
    public static int maxScore2(int[] arr,int l,int r){
        if (r == l) {
            return arr[l];
        }
        int sLeft  = arr[l] - maxScore2(arr,l+1,r);//arr[l] - (arr[l+1]  -  (arr[l+2]  -  XXX )    )
        int rRight = arr[r] - maxScore2(arr,l,r-1);
        return Math.max(sLeft,rRight);
    }


    /**
     * 方法三：动态规划
     * 将方法2每一步的差值存起来
     * dp【i】[j] 代表 arr从i到j的最大差值
     */
    public static boolean dp(int[] arr){
        int length = arr.length;
        int[][] dp = new int[length][length];
        //初始化dp数组
        for (int i=0;i<length;i++){
            dp[i][i] = arr[i];
        }
        for (int i = length-2;i >= 0;i--) {
            for (int j=i+1;j<length;j++) {
                dp[i][j] = Math.max(arr[i] - dp[i+1][j],arr[j] - dp[i][j-1]);
            }
        }
        return dp[0][length-1] >= 0;
    }

}
