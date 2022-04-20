package Turing;

/**
 * !!!!!!!!!!!!!!!!!!!!  有误的题解  !!!!!!!!!!!!!!!!!!!!
 * 井字游戏:(个人觉得下面的解法有误！！！！！！！！！！！！)
 * 用字符串数组作为井字游戏的游戏板board，判断该游戏板有没有可能最终形成
 * 游戏板是一个3x3数组，由字符""，"X"和"O"组成。字符""代表一个空位。
 * 两个玩家轮流将字符放入空位，一个玩家执X棋，另一个玩家执O棋
 * “X”和“O”只允许放置在空位中，不允许对已放有字符的位置进行填充。
 * 当有3个相同(且非空）的字符填充任何行、列或对角线时，游戏结束，board生成
 */
public class TicTacToe {
    public static void main(String[] args) {
        //因为X赢的话，X的数量一定大于O的数量
        System.out.println(validBoard(new String[]{"XXX","OXO","O O"}));//false
        System.out.println(validBoard(new String[]{"XOX","XXO","OXO"}));//false
        //XOX
        //XXO
        //O O
    }

    public static boolean validBoard(String[] board){
        //X赢了  因为X是先手，意味着 X - O = 1
        //O赢了  意味着 X - O == 0
        //胜负未分,X - O = 1 或 X - O == 0
        int xCount = 0,oCount = 0;
        for (String row : board) {
            for (char c : row.toCharArray()) {
                if (c == 'X') {
                    xCount++;
                }
                if (c == 'O') {
                    oCount++;
                }
            }
        }
        if (xCount != oCount && xCount - oCount != 1) {
            //数组有误，这种情况不可能出现啊
            return false;
        }
        if (win(board,"XXX") && xCount - oCount != 1) {
            return false;
        }
        if (win(board,"OOO") && xCount - oCount != 0) {
            return false;
        }
        if (!win(board,"XXX") && !win(board,"OOO") && ((xCount + oCount) == 9 )) {
            return false;
        }
        return true;
    }


    static boolean win(String[] board,String flag) {
        for (int i=0;i<3;i++) {
            //纵向3连
            if (flag.equals("" + board[0].charAt(i) + board[1].charAt(i) + board[2].charAt(i) )) {
                return true;
            }
            //横向3连
            if (flag.equals(board[i])) {
                return true;
            }
        }
        //   \对角线 3连
        if (flag.equals("" + board[0].charAt(0) + board[1].charAt(1) +  board[2].charAt(2) )){
            return true;
        }
        //   /对角线 3连
        if (flag.equals("" + board[2].charAt(0) + board[1].charAt(1) + board[0].charAt(2))) {
            return true;
        }
        return false;
    }


}
