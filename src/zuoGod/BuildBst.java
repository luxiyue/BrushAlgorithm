package zuoGod;




/**
 * 已知一个搜索二叉树后序遍历的数组posArr,
 * 请根据posArr重建出整颗树，返回新建树的头节点
 * @Author： Luzelong
 * @Created： 2022/7/27 18:59
 */
public class BuildBst {


    public static void main(String[] args) {
        Node node = postArrayToBST(new int[]{1, 3, 4, 6, 2, 9, 8});
        System.out.println("建议此行打个断点；node = "+node);
        Node node2 = postArrayToBST2(new int[]{1, 3, 4, 6, 2, 9, 8});
        System.out.println("建议此行打个断点；node = "+node2);

    }



    public static Node postArrayToBST(int[] posArr){
        return process(posArr,0,posArr.length -1);
    }

    /**
     *  核心思想：数组最后一个数字绝对是根结点
     *  从后往前看，第一个小于根结点的数字一定是根结点的左子节点，左子节点到根节点的区域肯定是右子树
     * @param posArr
     * @param L 构建的左起点
     * @param R 构建的右终点
     * @return
     */
    public static Node process(int[] posArr,int L,int R){
        if (L > R) {
            return null;
        }
        //L<=R
        Node head = new Node(posArr[R]);
        if (L == R) {
            return head;
        }
        int M = L - 1;
        for (int i =L;i < R;i++) {
            if (posArr[i] < posArr[R]) {
                M = i;
            }
        }
        head.left = process(posArr,L,M);
        head.right = process(posArr,M+1,R-1);
        return head;
    }




    public static Node postArrayToBST2(int[] posArr){
        return process2(posArr,0,posArr.length -1);
    }
    /**
     * 针对上面的方法进行二分查找的优化：
     * 当arr[mid] > arr[R] : 说明现在mid所在的数组区域还处于右子树范围 , 需要继续检查 【L  -  mid-1】范围
     * 当arr[mid] < arr[R] : 说明现在mid所在的数组区域已经处于左子树上了，但是不能保证是左子树的头节点，需要在 [mid+1 - R ]继续看看
     * @param posArr
     * @param L
     * @param R
     * @return
     */
    public static Node process2(int[] posArr,int L,int R){
        if (L > R) {
            return null;
        }
        //L<=R
        Node head = new Node(posArr[R]);
        if (L == R) {
            return head;
        }
        int M = L - 1;
        int left = L;
        int right = R -1;
        while (left <= right) {
            int mid = left + ((right-left) >> 1);
            if (posArr[mid] < posArr[R]) {
                M = mid;
                left = mid +1;
            }else {
                right = mid -1;
            }
        }
        head.left = process(posArr,L,M);
        head.right = process(posArr,M+1,R-1);
        return head;
    }


}


class Node{
    int value;
    Node left;
    Node right;
    public Node(int value){
        this.value = value;
    }
}
