package Turing;

/**
 * 柃檬水找零：
 * 在柠檬水摊上，每一杯柠檬水的售价为5美元。顾客排队购买你的产品，一次购买一杯。
 * 每位顾客只买一杯柠檬水,然后向你付5美元、10美元或20美元。必须给每个顾客正确找零
 * 注意,一开始你手头没有任何零钱。
 * 如果你能给每位顾客正确找零，返回 true ，否则返回false
 */
public class LemonChange {
    public static void main(String[] args) {
        System.out.println(change(new int[]{5,10,5,20}));
    }

    /**
     * 分析：
     * 看到5块直接收
     * 看到10块，需要找零5块
     * 看到20，需要找零 三个五块 或者 一个十块+一个五块   ==》》》》  贪心算法得出 尽量选择第二个方法，这样更适合生存
     *
     */
    public static boolean change(int[] bills){
        int five = 0;//5元的数量
        int ten = 0;//10元的数量
        for (int bill : bills){
            if (bill == 5) {
                five++;
            }else if (bill == 10){
                if (five == 0){
                    return false;
                }
                five--;
                ten++;
            }else {
                if (five > 0 && ten > 0){
                    five--;
                    ten--;
                }else if(five >= 3) {
                    five -= 3;
                }else {
                    return false;
                }
            }
        }
        return true;
    }


}
