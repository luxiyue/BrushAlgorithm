package zuoGod;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 今日头条题目
 * 数组为{3,2,2,3,1},查询为（0，3，2）。
 * 意思是在数组里下标0-3这个范围上，有几个2？返回2。
 * 假设给你一个数组arr，对这个数组的查询非常频繁，
 * 请返回所有的查询的结果
 * @Author： Luzelong
 * @Created： 2022/8/14 10:35
 */
public class ArrayFind {

    public static void main(String[] args) {
        int[] ints = {3, 2, 2, 3, 1};
        System.out.println(find(ints,0,3,2));
        System.out.println(find2(ints,0,3,2));
    }

    /**
     * 暴力法
     * @return
     */
    public static int find(int[] arr,int L,int R,int v) {
        int ans = 0;
        for (;L <= R; L++) {
            if (arr[L] == v) {
                ans++;
            }
        }
        return ans;
    }


    /**
     * map法：
     * 比如数组{3,2,2,3,1} ， 我会先构建这个map：
     *
     * key(value)   :    value（list<index>）
     *     3        :     0,3
     *     2        :    1,2
     *     1        :      4
     *
     *然后找（0，3，2），我只需看map的key为2的list，值在（0，3）范围上的
     */
    public static int find2(int[] arr,int L,int R,int v) {
        //构建map
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i=0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i],new ArrayList<>());
            }
            map.get(arr[i]).add(i);
        }

        //query
        if (!map.containsKey(v)) {
            return 0;
        }
        ArrayList<Integer> indexArr = map.get(v);
        //查询 < L 的下标有几个
        int a = countLess(indexArr,L);
        //查询 < R+1的下标有几个
        int b = countLess(indexArr,R+1);
        return b-a;
    }

    //有序数组arr中，用二分的方法数出<limit的数有几个
    // 也就是用二分法，找到<limit的数中最右的位置
    private static int countLess(ArrayList<Integer> arr, int limit) {
        int L = 0;
        int R = arr.size() -1 ;
        int mostRight = -1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1) ;
            if (arr.get(mid) < limit) {
                mostRight = mid;
                L = mid + 1;
            }else {
                R = mid - 1;
            }
        }
        return mostRight + 1;
    }


}
