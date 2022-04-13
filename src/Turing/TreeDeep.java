package Turing;


import javax.swing.tree.TreeNode;
import java.util.LinkedList;
import java.util.Queue;

/**
 *二叉树的最小深度
 * 给定一个二叉树,找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 */
public class TreeDeep {
    public static void main(String[] args) {
        TreeNode node7 = new TreeNode(7,null,null);////////////////////////////////////      1      /////////////////////////////
        TreeNode node6 = new TreeNode(6,node7,null);///////////////////////////////////   2    //////         3    //////////////////
        TreeNode node5 = new TreeNode(5,null,null);//////////////////////     4      ////  5///////  6    ////////////////
        TreeNode node4 = new TreeNode(4,null,null);//////////////////////////////////////////   7   ////////////////////
        TreeNode node3 = new TreeNode(3,node6,null);/////////////////////////////////////////////////////////////////////
        TreeNode node2 = new TreeNode(2,node4,node5);/////////////////////////////////////////////////////////////////////
        TreeNode node1 = new TreeNode(1,node2,node3);/////////////////////////////////////////////////////////////////////
        System.out.println(dfs(node1));
        System.out.println(bfs(node1));
    }

    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        int deep;//bfs算法加入的变量
        TreeNode(int val,TreeNode left,TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    //方法1：深度优先
    public static int dfs(TreeNode root){
        if (root == null){
            return 0;
        }
        if (root.left == null && root.right == null){
            return 1;
        }
        int min = Integer.MAX_VALUE;
        if (root.left != null) {
            min = Math.min(dfs(root.left),min);
        }
        if (root.right != null) {
            min = Math.min(dfs(root.right),min);
        }
        return min+1;
    }



    //方法2：广度优先
    public static int bfs(TreeNode root){
        if (root == null){
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        root.deep = 1;
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if (node.left == null && node.right == null) {
                return node.deep;
            }
            if (node.left != null) {
                node.left.deep = node.deep + 1;
                queue.offer(node.left);
            }
            if (node.right != null){
                node.right.deep = node.deep + 1;
                queue.offer(node.right);
            }
        }
        //压根不会走下面这一步
        return 0;
    }



}
