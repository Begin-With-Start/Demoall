package demo.minifly.com.algorithms_demo;

/**
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4) Output: 7 -> 0 -> 8
 * 
 * @author xiaofei.he You are given two non-empty linked lists representing two
 *         non-negative integers. The digits are stored in reverse order and
 *         each of their nodes contain a single digit. Add the two numbers and
 *         return it as a linked list. You may assume the two numbers do not
 *         contain any leading zero, except the number 0 itself.
 */
public class Solution {
	public static void main(String arg[]) {
		ListNode l1 = new ListNode(1);
		ListNode l11 = new ListNode(8);
		l1.next = l11;


		ListNode l2 = new ListNode(0);
		
		ListNode returnNode = addTwoNumbers(l1,l2);
		
		
		boolean loop = true;
		ListNode thisNode = returnNode;
		for (; loop;) {
			if (thisNode == null) {
				loop = false;
				break;
			}else{
				System.out.print( thisNode.val + "->");
			}
			thisNode = thisNode.next;
		}
		
	}
	
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		 ListNode c1 = l1;
	        ListNode c2 = l2;
	        ListNode sentinel = new ListNode(0);
	        ListNode d = sentinel;
	        int sum = 0;
	        while (c1 != null || c2 != null) {
	            sum /= 10;
	            if (c1 != null) {
	                sum += c1.val;
	                c1 = c1.next;
	            }
	            if (c2 != null) {
	                sum += c2.val;
	                c2 = c2.next;
	            }
	            d.next = new ListNode(sum % 10);
	            d = d.next;
	        }
	        if (sum / 10 == 1)
	            d.next = new ListNode(1);
	        return sentinel.next;
	}
}
class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}

	public String toString() {
		return "[" + next + ", " + val + "]";
	}
}
