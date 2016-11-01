package io.rulai.core.utils;

public class Tuple<A, B> {

	private A a;
	private B b;

	public Tuple(A a, B b) {
		this.a = a;
		this.b = b;
	}

	public boolean equals(Object o) {
		if (o instanceof Tuple) {
			Tuple<?, ?> p = (Tuple<?, ?>) o;
			return (p.a.equals(this.a) && p.b.equals(this.b));
		}
		return false;
	}
	
	public int hashCode() {
		if (a == null && b == null) {
			return 0;
		}
		if (a == null) {
			return b.hashCode();
		}
		if (b == null) {
			return a.hashCode();
		}
		return a.hashCode() + b.hashCode();
	}

	public A getA() {
		return a;
	}

	public B getB() {
		return b;
	}

	public void setA(A a) {
		this.a = a;
	}

	public void setB(B b) {
		this.b = b;
	}

	@Override
	public String toString() {
		return "Tuple [a=" + a + ", b=" + b + "]";
	}
}
