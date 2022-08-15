package org.brotao.breaks;

/**
 * @author luotao
 * @className BreakMultiLoop
 * @description 跳出多层循环, break/continue + [label]
 * @date 2021-12-28 14:12
 */

public class BreakMultiLoop {

	public static void main(String[] args) {
		test2();
	}

	public static void test1() {
		for (int i = 0; i < 3; i++) {
			for1:
			for (int j = 0; j < 3; j++) {
				for2:
				for (int m = 0; m < 3; m++) {
					for3:
					if (m == 1) {
						break for2;
					}
					System.out.println(i + "--" + j + "--" + m);
				}
			}
		}
	}

	public static void test2() {
		for1:
		for (int i = 0; i < 3; i++) {

			for2:
			for (int j = 0; j < 3; j++) {

				for3:
				for (int m = 0; m < 3; m++) {

					if (m == 1) {
						break for2;
					}
					System.out.println(i + "--" + j + "--" + m);
				}
			}
		}
	}

	public static void test3() {
		for1:
		for (int i = 0; i < 3; i++) {
			for2:
			for (int j = 0; j < 3; j++) {
				for3:
				for (int m = 0; m < 3; m++) {
					if (m == 1) {
						continue for2;
					}
					System.out.println(i + "--" + j + "--" + m);
				}
			}
		}
	}
}
