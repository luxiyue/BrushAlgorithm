package zuoGod;

import java.util.HashSet;
import java.util.TreeSet;

/**
 * @Author： Luzelong
 * @Created： 2022/8/10 20:21
 * 给定一个非负数组arr，和一个正数m
 * 返回arr中所有子序列累加和和%m之后的最大值
 * 1）如果arr中每个数字不大，怎么做这道题 max2
 * 2）如果arr中m的值很小，怎么做这道题 max3
 * 3）如果arr的长度很短，但是arr每个数字比较大并且m比较大 max4
 */
public class Maxmold {

    public static void main(String[] args) {
        int arr[] = new int[]{1,6,4};
        int m = 6;
        System.out.println(max1(arr,m));
        System.out.println(max2(arr,m));
        System.out.println(max3(arr,m));
    }

    /**
     * 方法1：暴力法
     * 递归求出所有的子序列和
     * 然后挨个 % m 取得最大子
     * @param arr
     * @param m
     * @return
     */
    public static int max1(int[] arr , int m){
        HashSet<Integer> set = new HashSet<>();
        process(arr,0,0,set);
        int max = 0;
        for (Integer sum : set) {
            max = Math.max(max,sum % m);
        }
        return max;
    }
    public static void process(int[] arr, int index, int sum, HashSet<Integer> set) {
        if (index == arr.length) {
            set.add(sum);
        }else {
            process(arr,index + 1,sum,set);
            process(arr,index + 1 , sum + arr[index],set);
        }
    }


    /**
     * 动态规划：定义如下的表格，列数为arr的所有元素之和（缺点是累加和太大就计算不过来）
     * 比如arr[1][3] 代表了我使用arr的前两个元素（1，6） 能不能凑出 正数3？   很显然单独的1和6都不行，加起来的7也不行，所有arr[1][3]应该为false
     * 比如arr[1][7] 是可以用1和6 凑出7的，所以为true
     *
     * 看完上面的规则，我们很容易就能填完第一列和第一行
     * 我们发现下面的推导都能用到上面的数据，例如 arr[i][j] = arr[i-1][j] || arr[i-1][j - arr[i]]
     *                                     不使用当前行的元素能不能凑出j  ||  使用当前行的元素能不能凑出j
     *
     *    0  1  2  3  4  5  6  ... 11
     * 1  T  T  F  F  F  F  F  ...  F
     * 6  T
     * 4  T
     *
     * @param arr
     * @param m
     * @return
     */
    public static int max2(int[] arr , int m){
        int sum = 0;
        int N = arr.length;
        for (int i=0; i < N; i++) {
            sum += arr[i];
        }
        boolean [][]dp = new boolean[N][sum+1];

        for (int i = 0; i < N; i++) {
            dp[i][0] = true;
        }

        dp[0][arr[0]] = true;
        for (int i = 1; i < N; i++) {
            for (int j =1; j <= sum; j++) {
                dp[i][j] = dp[i-1][j];
                if ( j - arr[i] >= 0) {
                    dp[i][j] = dp[i][j] | dp[i - 1][j - arr[i]];
                }
            }
        }
        int ans = 0;
        for ( int j = 0; j <= sum; j++) {
            if (dp[N -1][j]) {
                ans = Math.max(ans , j % m);
            }
        }
        return ans;
    }


    /**
     * 动态规划2：和上面的动态规划相比，我们为了防止累加和过大就得改变列的含义（列代表 % m 的结果区间 0 ～ m-1）
     * arr[1][1] 代表了取arr的前两个数1和6进行组合，能不能凑出一个数，使其 % m = 1 ， 很显然可以！ 因为1 % 6 = 1
     *
     * 很显然，我们直接可以直接得出下面的数组：
     *
     *    0  1  2  3  4  5
     * 1  T  T  F  F  F  F
     * 6  T
     * 4  T
     *
     * @param arr
     * @param m
     * @return
     */
    public static int max3(int[] arr , int m) {
        int N = arr.length;
        boolean[][] dp = new boolean[N][m];
        for (int i = 0; i< N; i++) {
            dp[i][0] = true;
        }
        dp[0][arr[0] % m] = true;
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = dp[i - 1][j];
                int cur = arr[i] % m;
                if (j - cur >= 0) {
                    dp[i][j] = dp[i][j] | dp[i - 1][j - cur];
                }else if (m + j - cur < m) {
                    dp[i][j] = dp[i][j] | dp[i - 1][m + j - cur];
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            if (dp[N-1][i]) {
                ans = i;
            }
        }
        return ans;
    }


    /**
     * 如果arr的累加和很大，m也很大
     * 但是arr的长度相对不大，使用用这种拆分的思想
     *
     * 比如arr数组长度为35，拆分成长度为17 和 长度为18的数组，分别求累加和，放在数组a和数组b中
     * 比如m为10
     * 如果a数组找到元素为2，那么我们就希望在b数组中找一个接近7的元素，才能取得最大的模 -》 9
     * @param arr
     * @param m
     * @return
     */
    public static int max4(int[] arr,int m){
        if (arr.length == 1) {
            return arr[0] % m;
        }
        int mid = (arr.length -1) / 2;
        TreeSet<Integer> sortSet1 = new TreeSet<>();
        process4(arr,0,0,mid,m,sortSet1);
        TreeSet<Integer> sortSet2 = new TreeSet<>();
        process4(arr,mid + 1,0,arr.length -1, m, sortSet2);
        int ans = 0;
        for (Integer left : sortSet1) {
            ans = Math.max(ans, left + sortSet2.floor(m -1 -left));
        }
        return ans;
    }
    public static void process4(int[] arr, int index, int sum, int end, int m, TreeSet<Integer> sortSet){
        if (index == end +1) {
            sortSet.add(sum % m);
        }else {
            process4(arr, index + 1, sum, end, m, sortSet);
            process4(arr, index + 1, sum + arr[index], end, m, sortSet);
        }
    }







}
