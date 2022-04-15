package Turing;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 身份数量：
 * 有n个城市，其中一些彼此相连，另一些没有相连。如果城市a与城市b直接相连，且城市b与城市c直接相连，那么城市a与城市c间接相连。
 * 省份是一组直接或间接相连的城市,组内不含其他没有相连的城市。给你一个nx n的矩阵isConnected，
 * 其中 isConnected[i][l]=1表示第i个城市和第j个城市直接相连，而isConnected[i]li]=0表示二者不直接相连。
 * 返回矩阵中省份的数量。
 * 扩展：朋友圈问题，亲戚问题
 */
public class ProvinceNums {

    public static void main(String[] args) {
        System.out.println(bfs(new int[][]{{1,1,0},{1,1,0},{0,0,1}}));//2
        System.out.println(bfs(new int[][]{{1,0,0},{0,1,0},{0,0,1}}));//3
        System.out.println(getProvice(new int[][]{
                {1,1,1,0,0,0,0},
                {1,1,0,0,0,0,0},
                {1,0,1,1,1,0,0},
                {0,0,1,1,0,0,0},
                {0,0,1,0,1,0,0},
                {0,0,0,0,0,1,1},
                {0,0,0,0,0,1,1}
        }));//2
        System.out.println(getProvice(new int[][]{{1,0,0},{0,1,0},{0,0,1}}));//3
        System.out.println(binSearch(new int[][]{
                {1,1,1,0,0,0,0},
                {1,1,0,0,0,0,0},
                {1,0,1,1,1,0,0},
                {0,0,1,1,0,0,0},
                {0,0,1,0,1,0,0},
                {0,0,0,0,0,1,1},
                {0,0,0,0,0,1,1}
        }));//2

    }

    /**
     * 方法1：深度优先
     */
    private static int getProvice(int[][] citysConnected){
        //城市的数量
        int citys = citysConnected.length;
        boolean[] visited = new boolean[citys];
        //计数器
        int provinces = 0;
        for (int i = 0;i < citys;i++) {
            if (!visited[i]){
                //深度优先
                dfs(i,citys,visited,citysConnected);
                provinces++;
            }
        }
        return provinces;
    }
    private static void dfs(int i, int citys, boolean[] visited, int[][] citysConnected) {
        for (int j = 0;j < citys;j++){
            if (citysConnected[i][j] == 1 && !visited[j]){
                visited[j] = true;
                dfs(j,citys,visited,citysConnected);
            }
        }
    }





    /**
     * 方法2：广度优先
     * 先找与A城市直接关联的一圈城市，找完为止
     * 一圈一圈的找
     */
    private static int bfs(int[][] citysConnected){
        //城市的数量
        int citys = citysConnected.length;
        boolean[] visited = new boolean[citys];
        //计数器
        int provinces = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0;i < citys;i++) {
            if (!visited[i]) {
                queue.offer(i);
                while (!queue.isEmpty()) {
                    int k = queue.poll();
                    visited[k] = true;
                    //每次循环结束，就找完一圈
                    for (int j = 0; j < citys; j++) {
                        if (citysConnected[k][j] == 1 && !visited[j]) {
                            queue.offer(j);
                        }
                    }
                }
                provinces++;
            }
        }
        return provinces;
    }






    /**
     * 方法三：并查集
     * 将省份看做一颗树
     */
    public static int binSearch(int[][] citysConnected){
        int citys = citysConnected.length;
        //head中存放每个节点的 根节点 位置，如果根节点相同，说明在一个省份
        int[] head = new int[citys];
        int[] level = new int[citys];
        //初始化操作
        for (int i = 0;i < citys;i++) {
            head[i] = i;
            level[i] = i;
        }
        //下面操作主要是维护head数组中的元素
        for (int i = 0;i < citys; i++) {
            for (int j = i + 1;j < citys ;j++) {
                if (citysConnected[i][j] == 1) {
                    merge(i,j,head,level);
                }
            }
        }
        int count = 0;
        for (int i = 0; i < citys;i++){
            if (head[i] == i){
                count ++;
            }
        }
        return count;
    }
    //下面操作主要是维护head数组中的元素
    static void merge(int x,int y,int[] head,int[] level){
        //查找x和y的父节点
        int i = find(x,head);
        int j = find(y,head);
        if (i == j) {
            return;
        }
        if (level[i] <= level[j]) {
            head[i] = j;
        }else {
            head[j] = i;
        }
        //由于level数组不是很重要，所以直接都++
        if (level[i] == level[j]) {
            level[i]++;
            level[j]++;
        }
    }
    //查找根节点
    private static int find(int x, int[] head) {
        if (head[x] == x){
            return x;
        }
        head[x] = find(head[x],head);
        return head[x];
    }
}
