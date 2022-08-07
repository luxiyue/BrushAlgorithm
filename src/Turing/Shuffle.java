package Turing;

import java.util.*;

/**
 * @Author： Luzelong
 * @Created： 2022/8/7 15:30
 * 优势洗牌：（田忌赛马）
 * 给定两个大小相等的数组A和B，A相对B的优势可以用满足A[i] > B[i]的索引i的数目来描述。
 * 返回A的任意排列，使其相对与b的优势最大化
 */
public class Shuffle {

    public static void main(String[] args) {
        int[] A = new int[] {10,24,8,32};
        int[] B = new int[] {13,25,25,11};
        System.out.println(Arrays.toString(bestRefresh(A,B)));
    }


    /**
     * 用贪心思维：
     * 比如A：2，5，7，9，8
     * 比如B：10，2，5，6，7
     * 看B第二个元素，A应该找一个比2大，但又在A中排名最小的元素
     * @return
     */
    public static int[] bestRefresh(int[] A,int[] B) {
        if (A == null || B == null || A.length != B.length) {
            return null;
        }
        //先排序
        int[] sortB = B.clone();
        Arrays.sort(sortB);
        Arrays.sort(A);

        Map<Integer, Deque<Integer>> bMap = new HashMap<>();
        for (int b : B) {
            bMap.put(b,new LinkedList<>());
        }
        //垃圾桶
        Deque<Integer> dq = new LinkedList<>();
        int j = 0;
        for (int a : A) {
            if (a > sortB[j]) {
                bMap.get(sortB[j++]).add(a);
            }else {
                //丢垃圾桶里面
                dq.add(a);
            }
        }

        //结果
        int[] ans = new int[A.length];
        for (int i = 0 ;i < B.length ; i++) {
            if (bMap.get(B[i]).size() > 0) {
                ans[i] = bMap.get(B[i]).removeLast();
            }else {
                //从垃圾桶来进行匹配
                ans[i] = dq.removeLast();
            }
        }

        return ans;
    }

}
