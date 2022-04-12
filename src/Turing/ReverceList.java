package Turing;

//链表反转
public class ReverceList {

    static class ListNode{
        int val;
        ListNode next;

        public ListNode(int val,ListNode next){
            this.val = val;
            this.next = next;
        }
    }


    //方法一：new一个新的头结点,将原链表的第一个节点不断的插在新链表头部的下一个位置
    public static ListNode function1(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode newhead = new ListNode(0,null);
        ListNode cur = head;
        while(cur != null) {
            ListNode next = cur.next;
            cur.next = newhead.next;
            newhead.next = cur;
            cur = next;
        }
        return newhead.next;
    }

    //方法2：迭代方法
    public static ListNode function2(ListNode head){
        if(head == null || head.next == null) {
            return head;
        }
        ListNode prev=null, next;
        ListNode cur = head;
        while(cur != null){
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    //方法3：从后往前进行递归
    public static ListNode function3(ListNode head){
        if(head == null || head.next == null) {
            return head;
        }
        ListNode newHead = function3(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    //验证
    public static void main(String[] args) {
        ListNode node5  = new ListNode(5,null);
        ListNode node4  = new ListNode(4,node5);
        ListNode node3  = new ListNode(3,node4);
        ListNode node2  = new ListNode(2,node3);
        ListNode node1  = new ListNode(1,node2);
        ListNode rhead = function3(node1);
        System.out.print(rhead);
    }

}
