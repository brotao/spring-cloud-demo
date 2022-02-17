package org.brotao.leetcode;

import lombok.extern.slf4j.Slf4j;

/**
 * @author luotao
 * @className ListNode
 * @description 链表节点类
 * @date 2022-02-15 11:16
 */
@Slf4j
public class ListNode {
	int val;
	ListNode next;

	ListNode() {
	}

	ListNode(int val) {
		this.val = val;
	}

	ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}

	public static ListNode createLinkedListNode(int[] ints) {
		ListNode node = null;
		ListNode head = null;
		for (int i : ints) {
			if (head == null) {
				head = new ListNode(i);
				node = head;
				continue;
			}
			node.next = new ListNode(i);

			node = node.next;
		}
		return head;
	}
}

