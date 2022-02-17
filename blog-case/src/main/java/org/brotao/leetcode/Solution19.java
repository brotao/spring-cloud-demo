package org.brotao.leetcode;

import lombok.extern.slf4j.Slf4j;

/**
 * @author luotao
 * @className Solution19
 * @description 删除链表的倒数第 N 个结点
 * @date 2022-02-15 11:16
 */
@Slf4j
public class Solution19 {
	public static void main(String[] args) {
		ListNode linkedListNode = ListNode.createLinkedListNode(new int[]{1, 2});
		linkedListNode = removeNthFromEnd(linkedListNode, 2);

		while (linkedListNode != null) {
			log.info(String.valueOf(linkedListNode.val));
			linkedListNode = linkedListNode.next;
		}

	}

	/**
	 * 递归 return 计数法
	 * @param head
	 * @param n
	 * @return
	 */
	public static ListNode removeNthFromEnd(ListNode head, int n) {
		return removeNode(head, n) == n ? head.next : head;
	}

	public static int removeNode(ListNode node, int n) {
		if (node.next == null) return 1;
		int m = removeNode(node.next, n);
		if (m == n)
			if (m == 1) node.next = null;
			else node.next = node.next.next;
		return m + 1;
	}

	/**
	 * 快慢指针
	 * @param head
	 * @param n
	 * @return
	 */
	public static ListNode removeNthFromEndI(ListNode head, int n) {
		ListNode ahead = new ListNode(-1, head);
		ListNode front = ahead, behind = ahead;
		for (int i = 0; i < n; i++) {
			if (front != null) {
				front = front.next;
			}
		}

		while (front.next != null) {
			front = front.next;
			behind = behind.next;
		}
		behind.next = behind.next.next;
		return ahead.next;
	}
}
