package Turing;

//X的平方根

/**
 * 在不适用sqrt(x)函数的情况下，得到x的平方根的整数部分
 */
public class X2 {
    public static void main(String[] args) {
        //4*4=16<24  5*5=25>24 所以24的平方根=4
        System.out.println((int)Math.sqrt(24));//2*12
        System.out.println(binarySearch(24));
        System.out.println(newton(24));
    }

    //方法一：采用二分查找
    public static int binarySearch(int x){
        int index = -1,l = 0,r = x;
        while (l <= r){
            int mid = l + (r - l) / 2;
            if (mid * mid <= x){
                index = mid;
                l = mid + 1;
            }else {
                r = mid - 1;
            }
        }
        return index;
    }


    //方法二：牛顿迭代
    public static int newton(int x){
        if (x == 0){
            return 0;
        }
        return (int)newtonSqrt(x,x);
    }

    /**
     * 原理如下：
     * 24 = 2 * 12
     * 但我们发现两个因子的平均数 （2+12）/2 =7，比这两个因子更加接近这个sqrt结果
     */
    public static double newtonSqrt(double i ,int x){
        double res = (i + x/i) / 2;
        if (res == i) {
            return i;
        }else {
            //递归调用
            return newtonSqrt(res,x);
        }
    }

}
