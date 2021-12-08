package org.brotao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) {
		List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		List<Integer> collect = integers.stream().flatMap(integer -> {
			List<Integer> b = new ArrayList<>();
			b.add(integer);
			return b.stream();
		}).filter(i -> true).collect(Collectors.toList());

		System.out.println(collect);
	}
}
