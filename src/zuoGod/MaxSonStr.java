package zuoGod;

/**
 * 给定一个只由小写字母（a-z）组成的字符串str
 * 返回其中最长无重复字符的子串长度
 * @Author： Luzelong
 * @Created： 2022/7/28 20:35
 */
public class MaxSonStr {
    public static void main(String[] args) {
        System.out.println(maxLength("abcabcdabcdaaaa"));
    }


    /**
     * 核心思想：
     * 77 78 79...... 99
     * 比如99是a，77也是a，！！！！那么如果包含99来看，最长的一段是【78，99】
     * 但是如果我们明确知道 98只能数到80，因为79在【80,98】存在重复字符,！！！那么如果包含99来看，最长的一段是【80，99】
     * @param s
     * @return
     */
    public static int maxLength(String s){
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        //last[0] -> a字符上次出现的位置
        //last[1] -> b字符上次出现的位置
        //.....
        int[] last = new int[26];
        for (int i=0;i<26;i++) {
            last[i] = -1;
        }
        //dp[0] = 1，子串必须以0位置结尾，最长无重复字符的子串长度
        last[str[0] - 'a'] = 0;
        int max = 1;
        //dp[i-1]的值
        int preMaxLen = 1;
        for (int i=1;i<N;i++) {
            preMaxLen = Math.min( i - last[str[i]-'a'] , preMaxLen + 1);
            max = Math.max(max,preMaxLen);
            last[str[i] - 'a'] = i;
        }
        return max;
    }


}
