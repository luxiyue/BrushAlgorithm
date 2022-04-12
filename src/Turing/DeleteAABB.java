package Turing;//删除排序数组中的重复项

/**
 * 一个有序数组nums，原地删除重复出现的元素，使每个元素只出现一次，返回删除后数组的新长度
 * 不能使用额外的数组空间，必须在原地修改输入数组并在使用O（1）额外空间的条件下完成
 * 例如输入【0,1,2，2,3,3,4】
 * 输出：5
 */
public class DeleteAABB {

    public static void main(String[] args) {
        int[] a = new int[]{0,1,2,2,3,3,4};
        System.out.println(delRepeat(a));
    }

    //双指针算法
    /**
     * 两个指针所在元素不相等时，一起往后移动，（并让前指针移动后的值等于 后面指针移动前的值）
     * 两个指针所在元素相等时，只有快指针往后移动
     *
     * @param nums
     * @return
     */
    private static int delRepeat(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 0;
        for (int j = 1;j < nums.length;j++) {
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i+1;
    }

}
