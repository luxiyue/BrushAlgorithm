package Turing;

/**
 * 香槟塔：
 * 把玻璃杯摆成金字塔的形状，其中第一层有1个玻璃杯，第二层有2个，依次类推到第100层。
 * 从顶层的第一个玻璃杯开始倾倒一些香槟，当顶层的杯子满了，任何溢出的香槟都会立刻等流量的流向左右两侧的玻璃杯。
 * 当左右两边的杯子也满了，就会等流量的流向它们左右两边的杯子，依次类推。（当最底层的玻璃杯满了，香槟会流到地板上)
 * 例如，在倾倒一杯香槟后，最顶层的玻璃杯满了。倾倒了两杯香槟后，第二层的两个玻璃杯各自盛放一半的香槟。
 * 在倒三杯香槟后，第二层的香槟满了–此时总共有三个满的玻璃杯。在倒第四杯后第三层中间的玻璃杯盛放了一半的香槟，他两边的玻璃杯各自盛放了四分之一的香槟
 * 现在当倾倒了非负整数杯香槟后，返回第i行j个玻璃杯所盛放的香槟占玻璃杯容积的比例(i和j都从0开始)
 */
public class ChampagneTower {
    public static void main(String[] args) {
        System.out.println(champagneTower(5,2,1));//1
        System.out.println(champagneTower(5,2,2));//0.5
    }

    /**
     *
     * @param poured 倒了几杯香槟
     * @param query_row   行
     * @param query_glass 列
     * @return
     */
    public static double champagneTower(int poured,int query_row,int query_glass) {
        //数组中存放承载容量（现存的  +  溢出的 ）
        double[][] ca = new double[100][100];
        ca[0][0] = poured;
        for (int r = 0; r < query_row; r ++ ) {
            for (int l=0;l <= r;l++) {
                double d = (ca[r][l] - 1.0) / 2;
                if (d > 0) {//如果溢出，就给左下角和右下角的杯子放水
                   ca[r+1][l] += d ;
                   ca[r+1][l+1] += d;
                }
            }
        }
        return Math.min(1,ca[query_row][query_glass]);
    }
}
