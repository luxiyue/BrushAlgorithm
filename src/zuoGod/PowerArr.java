package zuoGod;

import java.util.Arrays;

/**
 * 腾讯原题：
 * 给定整数power,给定一个数组arr，给定一个数组reverse。含义如下：
 * arr的长度一定是2的power次方，reverse每个值一定都在0～power范围。
 * 例如power = 2，arr={3,1,4,2}, reverse = {0,1,0,2}
 * 任何一个在前的数字可以和任意一个在后的数组，构成一对数。可能是生序关系、相等关系或者降序关系
 * 比如arr开始时有如下的降序对：（3，1）、（3，2）、（4，2） 一共3个
 * 接下来根据reverse对arr进行调整：
 * reverse[0]=0,表示在arr中，划分每1（2的0次方）个数为一组，然后每个小组内部逆序，那么arr变成【3，1，4，2】（无变化），此时有3个逆序对
 * reverse[1]=1,表示在arr中，划分每2（2的1次方）个数为一组，然后每个小组内部逆序，那么arr变成【1，3，2，4】，此时有1个逆序对
 * 剩余的操作同理，得出reverse[2]操作结束后，有1个逆序对
 * reverse[3]操作结束后有5个逆序对
 * .....
 *
 * 所以最后返回：【3，1，1，5】
 *
 * @Author： Luzelong
 * @Created： 2022/8/14 10:27
 */
public class PowerArr {


    public static void main(String[] args) {
        int power = 2;
        int[] arr = new int[]{3,1,4,2};
        int reverseArr[] = new int[]{0,1,0,2};
        System.out.println(Arrays.toString(reversePair1(arr.clone(),reverseArr.clone(),power)));
        System.out.println(Arrays.toString(reversePair2(arr.clone(),reverseArr.clone(),power)));
    }


    /**
     * 暴力法
     * @param originArr
     * @param reverseArr
     * @param power
     * @return
     */
    public static int[] reversePair1(int[] originArr, int[] reverseArr, int power){
        int[] ans = new int[reverseArr.length];
        for (int i = 0; i < reverseArr.length; i++) {
            //（1 << x） 相当于 2的x次方，以下的操作会将originArr进行分区逆序
            reverseArray(originArr,1 << (reverseArr[i]));
            //统计originArr的逆序对
            ans[i] = countReversePair(originArr);
        }
        return ans;
    }
    private static void reverseArray(int[] originArr, int teamSize) {
        if (teamSize < 2) {
            return;
        }
        for (int i=0; i < originArr.length; i += teamSize) {
            reversePart(originArr,i,i+teamSize-1);
        }
    }
    private static void reversePart(int[] arr, int L, int R) {
        while(L < R) {
            int temp = arr[L];
            arr[L++] = arr[R];
            arr[R--] = temp;
        }
    }
    private static int countReversePair(int[] originArr) {
        int ans = 0;
        for (int i=0; i<originArr.length; i++) {
            for (int j=i+1; j < originArr.length; j++) {
                if (originArr[i] > originArr[j]) {
                    ans++;
                }
            }
        }
        return ans;
    }






    public static int[] reversePair2(int[] originArr, int[] reverseArr, int power) {
        int[] originReverse = originArr.clone();
        reversePart(originReverse, 0 ,originReverse.length - 1);
        //recordDown[i] 2的i次方个数一组的划分中，降序的数量
        int[] recordDown = new int[power + 1];
        //recordUp[i] 2的i次方个数一组的划分中，升序的数量
        int[] recordUp = new int[power + 1];
        process(originArr, 0, originArr.length - 1, power, recordDown);
        process(originReverse, 0, originReverse.length - 1,power,recordUp);

        //后序操作
        int ans[] = new int[reverseArr.length];
        for (int i = 0; i < reverseArr.length; i++) {
            int curPower = reverseArr[i];
            for (int p = 1; p <= curPower; p++) {
                int tmp = recordDown[p];
                recordDown[p] = recordUp[p];
                recordUp[p] = tmp;
            }
            for (int p = 1; p <= power; p++) {
                ans[i] += recordDown[p];
            }
        }
        return ans;
    }

    /**
     * 归并排序 求降序对的个数
     */
    private static void process(int[] originArr, int L, int R, int power, int[] record) {
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(originArr, L, mid,power -1, record);
        process(originArr,mid+1, R,power-1,record);
        record[power] += merge(originArr,L,mid,R);
    }

    private static int merge(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        int ans = 0;
        while (p1 <= m && p2 <= r) {
            ans += arr[p1] > arr[p2] ? (m - p1 + 1) : 0;
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[l+i] = help[i];
        }
        return ans;
    }


}
