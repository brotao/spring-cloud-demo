package org.brotao;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.net.URI;
import java.util.*;

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

	@Test
	public void testSplit() {
		/*String str= "/aaa/20220218/TestCSV.csv.zip";
		for (String s : str.split("/")) {
			System.out.println(s);
		}*/

		String s = "";
		String[] qws = s.split("qw");
		System.out.println(qws.length);
		System.out.println(Arrays.toString(qws));
	}

	@Test
	public void testMaxWord() {
		String[] words = new String[] {"apple","iOS","dog","nana","man","good","goodman"};

		List<String> list = new ArrayList<>(Arrays.asList(words));
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if (o1.length() == o2.length()) {
					return o1.compareTo(o2);
				}
				return o1.length()-o2.length();
			}
		});

		int size = list.size();
		for (int i = 0; i < size-1; i++) {
			boolean res = find(list.get(i), list, i+1, size-1);
		}



	}

	private boolean find(String word, List<String> list, int start , int end) {


		for (int i = start; i <= end; i++) {
			int i1 = word.indexOf(list.get(i));
			if (i1 < 0) {
				continue;
			}
			String[] split = word.split(list.get(i));
			for (int i2 = 0; i2 < split.length; i2++) {
//				split[i2].split()
			}

		}
		
		return false;
	}


}
