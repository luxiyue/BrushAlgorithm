package Turing;

//统计素数
public class Sushu {

    public static void main(String[] args) {
        System.out.println(aisai(100));
    }


    //方法一：暴力法
    public static int bf(int n){
        int count = 0;
        //因为0和1不是素数
        for (int i=2;i<n;i++){
            count += isPrime(i)? 1:0;
        }
        return count;
    }

    private static boolean isPrime(int i) {
//        for (int j=2; j < i; j++){
        //下面是对上面的优化
        for (int j=2;j * j <= i; j++){
            if (i % j == 0){
                return false;
            }
        }
        return true;
    }


    //埃塞法,原理就是素数*2，素数*3，素数*4...都不是素数
    public static int aisai(int n){
        //这个数组默认都是false,都是素数
        boolean[] flag = new boolean[n];
        int count = 0;
        for (int i = 2;i < n; i++) {
            if (!flag[i]){
                count++;
//                for (int j=2*i;j < n;j+=i){
                for (int j=i*i;j < n;j+=i){//优化
                    flag[j] = true;
                }
            }
        }
        return count;
    }

}
