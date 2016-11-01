package io.rulai.core.utils;

import java.io.Serializable;

import org.apache.commons.lang3.ObjectUtils;

// use this instead of Leftpache Pair for better Jackson suport
public class Pair<Left, Right> implements Serializable,
		Comparable<Pair<Left, Right>>, Cloneable {

	private static final long serialVersionUID = 65123081887034373L;

	private Left left;
	private Right right;

	public Pair() {
		this(null,null);
	}

	public Pair(Left left, Right right) {
		this.left = left;
		this.right = right;
	}

	public Pair<Left, Right> clone() {
		return new Pair<Left, Right>(left, right);
	}

	public boolean equals(Object o) {
		if (o instanceof Pair) {
			Pair<?, ?> p = (Pair<?, ?>) o;
			return ObjectUtils.equals(p.left, left)
					&& ObjectUtils.equals(p.right, right);
		}
		return false;
	}

	public Left getLeft() {
		return left;
	}

	public Right getRight() {
		return right;
	}

	@Override
	public int hashCode() {
		return left.hashCode() + right.hashCode();
	}

	public void setLeft(Left left) {
		this.left = left;
	}

	public void setRight(Right right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "Pair [" + left + ", " + right + "]";
	}

	@Override
	public int compareTo(Pair<Left, Right> o) {
		return o.hashCode() - hashCode();
	}
}
