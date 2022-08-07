package Turing;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author： Luzelong
 * @Created： 2022/8/7 11:25
 * Dota2 参议院由来自两派的参议员组成。现在参议院希望对一个dota2游戏里的改变做出决定。他们以一个基于轮为过程的投票进行。在每一轮中，每位参议员都能行驶两项权利中的一项。
 * 禁止一名参议员的权利。参议员可以让另一位参议员在这一轮和随后的几轮中丧失所有的权利。宣布胜利：如果参议员发现有权利投票的参议员都是同一阵营的，他可以宣布胜利并决定在游戏中的有关变化
 * 给定一个字符串代表每个参议员的阵营。字母"R"和"D"，分别代表"Radiant（天辉）"和"Dire（夜糜）"，然后有n个参议员，给定字符串的大小将是n
 * 以轮为基础的过程从给定顺序的第一个参议员开始到最后一个参议员结束，这一过程将持续到投票结束。所有失去权利的参议员将在此过程中被跳过。
 * 假设每一位参议员都足够聪明，会为自己的政党做出最好的策略，你需要宣布哪一方最终会宣布胜利并在Dota2游戏中决定改变。输出应该是Radiant或者Dire
 */
public class Dota2 {

    public static void main(String[] args) {
        System.out.println(predictPartyVictory("RDD"));
    }


    /**
     * 贪心分析：比如给定的字符串是RDRDD
     * 第1个R应该优先禁用第1个D，不然第1个D会将第二个R给禁掉
     * 第一个R禁掉D之后，应该移到字符串尾部 --》队列
     * @param senate
     * @return
     */
    public static String predictPartyVictory(String senate) {
        Queue<Integer> r  = new LinkedList<>();
        Queue<Integer> d = new LinkedList<>();
        int length = senate.length();
        for (int i=0;i<length;i++) {
            if (senate.charAt(i) == 'R') {
                r.offer(i);
            }else {
                d.offer(i);
            }
        }
        while (!r.isEmpty() && !d.isEmpty()) {
            int rPoll = r.poll();
            int dPoll = d.poll();
            if (rPoll < dPoll) {
                //将r放在队列的尾部
                //rPoll一定要加一个比length更大的数字
                r.offer(rPoll+length);
            }else {
                d.offer(dPoll + length);
            }
        }
        return d.isEmpty() ? "R" : "D";
    }
}
