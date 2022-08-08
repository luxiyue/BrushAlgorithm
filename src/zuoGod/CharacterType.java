package zuoGod;

import java.util.HashSet;

/**
 * @Author： Luzelong
 * @Created： 2022/8/7 17:50
 * 快手一面：
 * 如果两个字符串，所含字符种类完全一样，就算作一类。
 * 只由小写字母（a-z）组成的一批字符串
 * 都放在字符类型的数组String[] arr中，返回arr中一共有多少类
 */
public class CharacterType {

    public static void main(String[] args) {
        System.out.println(countType(new String[]{"eeeabace","abce","abc"}));
        System.out.println(countType2(new String[]{"eeeabace","abce","abc"}));
    }


    /**
     * 摘要法：
     * 将类似 eeeabace  -》 抽象成abce,放在set中统计最后set集合的大小
     * @param arr
     * @return
     */
    public static int countType(String[] arr){
        HashSet<String> types = new HashSet<>();
        for (String str : arr) {
            char[] chs = str.toCharArray();
            boolean[] map = new boolean[26];
            for (int i =0 ;i< chs.length; i++) {
                map[chs[i] - 'a'] = true;
            }
            StringBuilder key = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (map[i]) {
                    key.append( String.valueOf((char) (i + 'a')) );
                }
            }
            types.add(key.toString());
        }
        return types.size();
    }


    /**
     * 用二进制的000000000000000的每个位代表对应的字符是否存在
     * @param arr
     * @return
     */
    public static int countType2(String[] arr) {
        // int 代表一种摘要
        HashSet<Integer> types = new HashSet<>();
        for (String str : arr) {
            char[] chars = str.toCharArray();
            int key = 0;
            for (int i = 0; i < chars.length;i++) {
                key |= (1 << (chars[i] - 'a'));
            }
            types.add(key);
        }
        return types.size();
    }



}
