package zuoGod;

/**
 * 京东面试原题:
 * 整型数组arr长度为n（3 <= n <= 10^4）
 * 最初每个数字是<= 200的正数且满足如下条件
 * 1.arr[0] <= arr[1]
 * 2.arr[n-1] <= arr[n-2]
 * 3.arr[i] <= max(arr[i-1],arr[i+1])
 * 但是arr有些数字丢失了
 * 比如K位置的数字之前是正数，丢失之后k位置的数字为0
 * 计算可能有多少种不同的arr可以满足以上的条件
 * 比如[6,0,9]只有还原成[6,9,9]满足全部三个条件
 * 所以返回1种
 *
 * @Author： Luzelong
 * @Created： 2022/9/4 19:07
 */
public class JinDongArray {

    public static void main(String[] args) {
        System.out.println(way1(new int[]{0,9,9}));//9种
        System.out.println(way1(new int[]{6,0,9}));//1种
    }


    public static int way1(int arr[]){
        int N = arr.length;
        if (arr[N -1] != 0) {
            return process1(arr, N -1, arr[N - 1], 2);
        }else {
            int ways = 0;
            for (int v = 1; v < 201; v++) {
                ways += process1(arr,N - 1, v, 2);
            }
            return ways;
        }
    }


    /**
     * 如果i位置的数字变成了v
     * 并且arr[i] 和 arr[i+1]的关系为s
     * s==0:代表arr[index] < arr[index+1]
     * s==1:代表arr[index] = arr[index+1]
     * s==2:代表arr[index] > arr[index+1]
     * @return arr[0 ... i]有多少种转化方式
     */
    public static int process1(int[] arr,int i, int v, int s){
        if (i == 0) {
            return ((s == 0 || s == 1) && (arr[i] == 0 || v == arr[i])) ? 1 : 0;
        }
        if (arr[i] != 0 && v != arr[i]) {
            return 0;
        }
        int ways = 0;
        if (s == 0 || s == 1){
            for (int pre = 1; pre < 201; pre++) {
                ways += process1(arr,i-1,pre,pre < v ? 0 : (pre == v ? 1 : 2));
            }
        } else {
            for (int pre = v; pre < 201; pre++) {
                ways += process1(arr, i - 1,pre,pre < v ? 0 : (pre == v ? 1 : 2));
            }
        }
        return ways;
    }





    //上面的方法可以改成三维数组的dp，TODO


}
