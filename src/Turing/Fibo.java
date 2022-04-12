package Turing;

/**
 * 求取斐波那契数列第n位的值
 * 斐波那契数列：0,1,1,2,3,5,8,13....
 */
public class Fibo {
    public static void main(String[] args) {
        System.out.println(caculate(10));
        System.out.println(caculate2(10));
        System.out.println(twoPoint(10));
    }

    /**
     * 方法1：暴力递归
     */
    public static int caculate(int n){
        if (n == 0){
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return caculate(n-1) + caculate(n-2);
    }


    /**
     * 方法2：去重递归
     * 核心：将递归的中间过程的结果存起来
     */
    public static int caculate2(int n){
        int[] arr = new int[n+1];
        int res = recurse(arr, n);
        return res;
    }
    public static int recurse(int[] arr,int n){
        if (n == 0){
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (arr[n] != 0){
            return arr[n];
        }
        arr[n] = recurse(arr,n-1) + recurse(arr,n-2);
        return arr[n];
    }





    /**
     * 方法3：双指针法
     */
    public static int twoPoint(int n) {
        if (n == 0){
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int prev = 0,next = 1;
        int index = 2;
        while(index++ <= n){
            int temp = prev + next;
            prev = next;
            next = temp;
        }

        return next;
    }

}
