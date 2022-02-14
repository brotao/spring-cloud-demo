package org.brotao;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {
	/**
	 * Rigorous Test :-)
	 */
	@Test
	public void shouldAnswerWithTrue() {
		assertTrue(true);
	}

	@Test
	public void testArray() {
		List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);

//        Integer[] a = new Integer[0];
		Integer[] integers1 = integers.toArray(new Integer[0]);

		System.out.println(Arrays.toString(integers1));
	}

	@Test
	public void testIterator() {
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			String item = iterator.next();
			if ("1".equals(item)) {
				iterator.remove();
			}
		}
		System.out.println(list);

		List<String> list2 = new ArrayList<>();
		list2.add("1");
		list2.add("2");

		for (String item : list2) {
			if ("2".equals(item)) {
				list2.remove(item);
			}
		}

		System.out.println(list2);

	}

	@Test
	public void testSwitchString() {
		// throw NullPointerException
		String str = null;
		/*switch (str) {
			// 肯定不是进入这里
			case "sth":
				System.out.println("it's sth");
				break;
			// 也不是进入这里
			case "null":
				System.out.println("it's null");
				break;
			// 也不是进入这里
			default:
				System.out.println("default");
		}*/
	}

	@Test
	public void testForEach() {
		Integer[] ints = new Integer[0];
		for (Integer anInt : ints) {
			System.out.println(anInt);
		}
	}

}
