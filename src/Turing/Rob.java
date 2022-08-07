package Turing;

import java.nio.file.FileStore;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你不触动警报装置的情况下，一夜之内能够偷窃到的最高金额。
 */
public class Rob {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        System.out.println(maxMoney(nums,nums.length-1));
        System.out.println(maxMoney3(nums));//4
        System.out.println(maxMoney3(new int[]{2,7,9,3,1}));//12
    }


    /**
     * 方法1：递归
     * @param nums
     * @param index
     * @return
     */
    private static int maxMoney(int[] nums,int index) {
        if (nums == null || index < 0) {
            return 0;
        }
        if (index == 0) {
            return nums[0];
        }
        return Math.max(maxMoney(nums,index-1),maxMoney(nums,index-2) + nums[index]);
    }


    /**
     * 方法2：动态规划
     * 将方法一的递归结果保存在dp数组中
     * @param nums
     * @return
     */
    private static int maxMoney2(int[] nums){
        int length = nums.length;
        if (nums == null || length == 0 ) {
            return 0;
        }
        if (length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);
        for (int i=2;i<length;i++) {
            dp[i] = Math.max(dp[i-1],dp[i-2]+nums[i]);
        }
        return dp[length-1];
    }


    /**
     * 方法3：对方法2进行优化空间复杂度
     * @param nums
     * @return
     */
    private static int maxMoney3(int[] nums){
        int length = nums.length;
        if (nums == null || length == 0 ) {
            return 0;
        }
        if (length == 1) {
            return nums[0];
        }
        int first = nums[0],second = Math.max(nums[0],nums[1]);
        for (int i=2;i<length;i++) {
            int t = Math.max(first+nums[i],second);
            first = second;
            second = t;
        }
        return second;
    }

}


/**
 * 升级版，房屋会首尾相连，环形的状态
 */
class Rob2{
    public static void main(String[] args) {
        int[] nums = new int[]{2,7,9,3,1};
        System.out.println(max(nums));
    }



    public static int max(int[] nums){
        return Math.max(maxMoney(nums,0, nums.length-2),maxMoney(nums,1,nums.length-1));
    }


    /**
     **@see Turing.Rob#maxMoney3(int[])
     * 该升级版可以抽象出普通版：
     * 1。取0这个位置，不取index这个位置，即不存在首位相连
     * 2。取index这个位置，不取0这个位置，也不存在首位相连
     */
    private static int maxMoney(int[] nums,int start,int end){
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (start > end) {
            return -1;
        }
        //下面才是核心
        int first = nums[start],second = Math.max(nums[start],nums[start+1]);

        for (int i=start+2;i <= end;i++) {
            int temp = second;
            second = Math.max(first+nums[i],second);
            first = temp;
        }

        return second;
    }

}


/**
 * 升级版2：
 * 在上次打劫完一条街道之后h和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为"根"。
 * 除了根之外，每个房子有且只有一个父房子与之相连，一反侦察之后，聪明的小偷意识到：这个地区所有房子的排列类似一颗二叉树。
 * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 * 计算在不触动警报的情况下，小偷一晚上能盗取的最高金额！
 */
class Rob3{
    public static void main(String[] args) {
        TreeNode treeNode5 = new TreeNode(1, null, null);
        TreeNode treeNode4 = new TreeNode(3, null, null);
        TreeNode treeNode3 = new TreeNode(3, null, treeNode5);
        TreeNode treeNode2 = new TreeNode(2, null, treeNode4);
        TreeNode treeNode1 = new TreeNode(3, treeNode2, treeNode3);

        int[] dfs = dfs(treeNode1);
        System.out.println(Math.max(dfs[0],dfs[1]));

    }


    /**
     * 本题的突破口在于叶子节点挂载的null节点
     * @return int[] {selected最优解，noselect最优解}
     */
    private static int[] dfs(TreeNode node){
        if (node == null) {
            return new int[] {0,0};
        }
        int[] l = dfs(node.left);
        int[] r = dfs(node.right);
        int select =  node.val + l[1] + r[1];
        int noselect = Math.max(l[0],l[1]) + Math.max(r[0],r[1]);
        return new int[]{select,noselect};
    }




    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        int deep;//bfs算法加入的变量
        TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
