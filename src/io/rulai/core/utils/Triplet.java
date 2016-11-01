package io.rulai.core.utils;

import java.io.Serializable;

import org.apache.commons.lang3.ObjectUtils;

public class Triplet<A, B, C> implements Serializable, Comparable, Cloneable {

	private static final long serialVersionUID = 6556668184499353748L;

	private A a;
	private B b;
	private C c;

	public Triplet(A a, B b, C c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public Triplet<A, B, C> clone() {
		return new Triplet<A, B, C>(a, b, c);
	}

	@Override
	public int compareTo(Object o) {
		return o.hashCode() - hashCode();
	}

	public boolean equals(Object o) {
		if (o instanceof Triplet) {
			Triplet<?, ?, ?> p = (Triplet<?, ?, ?>) o;
			return ObjectUtils.equals(p.a, a) && ObjectUtils.equals(p.b, b)
					&& ObjectUtils.equals(p.c, c);
		}
		return false;
	}

	public A getA() {
		return a;
	}

	public B getB() {
		return b;
	}

	public C getC() {
		return c;
	}

	@Override
	public int hashCode() {
		return a.hashCode() + b.hashCode() + c.hashCode();
	}

	public void setA(A a) {
		this.a = a;
	}

	public void setB(B b) {
		this.b = b;
	}

	public void setC(C c) {
		this.c = c;
	}

	@Override
	public String toString() {
		return "Triplet [" + a + ", " + b + ", " + c + "]";
	}

}
