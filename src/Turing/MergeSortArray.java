package Turing;

import java.util.Arrays;

/**
 * 两个有序整数数组nums1和nums2，将nums2合并到nums1中，使nums1成为一个有序数组
 * 初始化nums1和nums2的元素数量分别为m和n。假设nums1的空间大小等于m + n，这样他就有足够的空间保存来自nums2的元素
 */
public class MergeSortArray {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1,3,5,7,9,0,0,0,0};
        int[] nums2 = new int[]{2,4,6,8};
//        System.out.println(Arrays.toString(merge1(nums1,5,nums2,4)));
        System.out.println(Arrays.toString(merge3(nums1,5,nums2,4)));
    }


    /**
     * 方法一：先将nums2的所有元素拼接到nums1后面，然后再对nums1进行排序
     */
    public static int[] merge1(int[] nums1,int m,int[] nums2,int n){
        //将nums2放在nums1后面
        System.arraycopy(nums2,0,nums1,m,n);
        Arrays.sort(nums1);
        return nums1;
    }


    //方法二：双指针法
    public static int[] merge2(int[] nums1,int m,int[] nums2,int n){
        int[] nums1_copy = new int[m];
        System.arraycopy(nums1,0,nums1_copy,0,m);
        int p1 = 0;//指向nums1_copy
        int p2 = 0;//指向nums2
        int p = 0;//指向nums1

        while (p1 < m && p2 < n) {
            nums1[p++] = nums1_copy[p1] < nums2[p2] ? nums1_copy[p1++] : nums2[p2++];
        }

        if (p1 < m) {
            System.arraycopy(nums1_copy,p1,nums1,p1+p2,m+n-p1-p2);
        }
        if (p2 < n) {
            System.arraycopy(nums2,p2,nums1,p1+p2,m+n-p1-p2);
        }


        return nums1;
    }




    //方法三：减少方法二的空间复杂度，直接在nums1中合并，从后往前遍历
    public static int[] merge3(int[] nums1,int m,int[] nums2,int n){
        int p1 = m-1;//指向nums1
        int p2 = n-1;//指向nums2
        int p = m + n - 1 ; //指向排序末尾
        while (p1 >= 0 && p2 >= 0) {
            nums1[p--] = nums1[p1] > nums2[p2] ? nums1[p1--] : nums2[p2--];
        }
        System.arraycopy(nums2,0,nums1,0,p2+1);
        return nums1;
    }



}
