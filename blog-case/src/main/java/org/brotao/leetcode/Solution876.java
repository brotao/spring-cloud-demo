package org.brotao.leetcode;

import lombok.extern.slf4j.Slf4j;

/**
 * @author luotao
 * @className Solution876
 * @description 链表的中间结点
 * @date 2022-02-15 10:41
 */
@Slf4j
public class Solution876 {
	public static void main(String[] args) {
		int[] arg = new int[] {1,2,3,4,5,6};
		ListNode node = null;
		ListNode head = null;
		for (int i : arg) {
			if (head == null) {
				head = new ListNode(i);
				node = head;
				continue;
			}
			node.next = new ListNode(i);

			node = node.next;
		}

		ListNode node1 = middleNode(head);

		while (node1 != null) {
			log.info(String.valueOf(node1.val));
			node1 = node1.next;
		}
	}

	public static ListNode middleNode(ListNode head) {
		ListNode quick = head, slow = head;
		while (quick.next != null) {
			quick = quick.next;
			if (quick.next != null) {
				quick = quick.next;
			}
			slow = slow.next;
		}

		return slow;
	}
}

