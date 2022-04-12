package Turing;

/**
 * 总共有n枚硬币，将它们摆成一个阶梯形状，第k行就必须正好有k枚硬币
 * 给定一个数字n，找出可形成完整阶梯行数的总行数
 * n是一个非负整数，并且在32位有符号整型的范围内
 */
public class ArrangeCoin {

    public static void main(String[] args) {
        System.out.println(arrangeCoins(10));
        System.out.println(mathLogic(10));
        System.out.println(binarySearch(10));
        System.out.println(newTonSearch(10));
    }


    /**
     * 方法1： 迭代
     * 用n不断的减去层数，将结果和当前层数对比
     */
    public static int arrangeCoins(int n){
        //i代表每一行
        for (int i=1;i <= n;i++){
            n = n - i;
            if (n <= i) {
                return i;
            }
        }
        return 0;
    }


    /**
     *方法二:数学公式  直到((1+n)n)/2 》 目标
     */
    public static int mathLogic(int n){
        for (int i=1;i <= n;i++){
            int res = (i + i*i) / 2;
            if (res >= n){
                return  i;
            }
        }
        return 0;
    }




    /**
     * 方法三： 二分查找，对方法二上面的优化
     */
    public static int binarySearch(int n){
        int low = 0,high = n;
        while (low < high) {
            int mid = low + (high - low) / 2;
            int cost = ((mid + 1) * mid) / 2;
            if (cost == n){
                return mid;
            } else if (cost > n) {
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }
        return high;
    }



    //方法4：牛顿迭代
    /**
     * x(x+1) = 2n  ====> x^2 = 2n-x
     */

    public static int newTonSearch(int n){
        if (n == 0){
            return 0;
        }
        return (int)newTow(n,n);
    }
    public static double newTow(double i,int n) {
        double res = (i + (2*n-i)/i) / 2 ;
        if( i == res ){
            return i;
        }else {
            return newTow(res,n);
        }
    }





}
