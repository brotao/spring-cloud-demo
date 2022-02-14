package org.brotao;

import java.util.Objects;

/**
 * @author luotao
 * @className Man
 * @description
 * @date 2022-02-08 14:50
 */
public class Man {
	String name;
	String gender;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Man man = (Man) o;
		return Objects.equals(name, man.name) && Objects.equals(gender, man.gender);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, gender);
	}
}
