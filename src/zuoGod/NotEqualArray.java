package zuoGod;

import java.util.Arrays;

/**
 * 输入一个int类型的值N，构造一个长度为N的数组Arr并返回
 * 要求：
 * 对任意对i < k < j ,都满足arr[i] + arr[j] != arr[k]*2
 * @Author： Luzelong
 * @Created： 2022/7/28 10:14
 */
public class NotEqualArray {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(makeNo(9)) );
    }


    /**
     * 核心思想：
     * 比如： [2,3,1] 是满足条件的，因为 2+1 ！= 3*2
     * 那么我们可以得出，[第二个奇数，第三个奇数，第一个奇数] =》[3,5,1] 也是符合要求的
     * 得出 [第二个偶数，第三个偶数，第一个偶数] =》[4，6，2] 也是符合要求的
     * 可以自己推断得出：上面两个结果的拼接也是符合要求的 ： [第二个奇数，第三个奇数，第一个奇数， 第二个偶数，第三个偶数，...]，就算只有偶数的一部分，那也是合法的
     * 下面的算法就是用到了这种思想：比如需要长度为4的数组，可以由两个偶数，两个奇数拼接。比如长度为5的数组，可以由长度为3的奇数，和长度为3（只取其前两个数）的偶数拼接
     * @param size
     * @return
     */
    public static int[] makeNo(int size) {
        if (size == 1) {
            return new int[]{1};
        }

        int halfSize = (size + 1) / 2;
        int[] base = makeNo(halfSize);
        int[] ans = new int[size];
        int index = 0;
        for (;index < halfSize;index++) {
            ans[index] = base[index] * 2 + 1;
        }
        for (int i=0 ; index < size ; index++,i++) {
            ans[index] = base[i] * 2;
        }
        return ans;
    }
}
