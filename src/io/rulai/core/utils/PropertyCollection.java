package io.rulai.core.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class PropertyCollection {

	public static class Builder {
		protected Set<String> properties;

		public Builder() {
			properties = new HashSet<String>();
		}

		public Builder add(@SuppressWarnings("rawtypes") Class cls, String property) {
			String fn = cls.getName() + "#" + property;
			this.properties.add(fn);
			return this;
		}

		public Builder add(PropertyCollection properties) {
			this.properties.addAll(properties.properties);
			return this;
		}

		public PropertyCollection build() {
			return new PropertyCollection(this.properties);
		}
	}

	protected Set<String> properties;

	public PropertyCollection(Set<String> properties) {
		this.properties = properties;
	}

	public PropertyCollection() {
		this.properties = new HashSet<String>();
	}

	@SuppressWarnings("rawtypes")
	public PropertyCollection(Pair<Class, String>[] properties) {
		this.properties = new HashSet<String>();
		for (Pair<Class, String> ip : properties) {
			String fn = ip.getLeft().getName() + "#" + ip.getRight();
			this.properties.add(fn);
		}
	}

	@SuppressWarnings("rawtypes")
	public PropertyCollection(Class cls, String property) {
		String fn = cls.getName() + "#" + property;
		this.properties = new HashSet<String>();
		this.properties.add(fn);
	}

	public boolean containsField(Field field) {
		String fn = field.getDeclaringClass().getName() + "#" + field.getName();
		return this.properties.contains(fn);
	}

	public boolean containsMethod(Method method) {
		if (method.getParameterTypes().length != 0) {
			return false;
		}

		String mn = method.getName();
		String clsName = method.getDeclaringClass().getName();
		String fn = clsName + "#" + mn;
		boolean contains = this.properties.contains(fn);
		if (!contains) {
			String propertyName = null;
			if (mn.length() > 3 && mn.startsWith("get")) {
				propertyName = mn.substring(3);
			} else if (mn.length() > 2 && mn.startsWith("is") && method.getReturnType() == boolean.class) {
				propertyName = mn.substring(2);
			}
			if (propertyName != null) {
				char ch = propertyName.charAt(0);
				if (ch >= 'A' && ch <= 'Z') {
					ch = (char) (ch - 'A' + 'a');
					propertyName = "" + ch + (propertyName.length() > 1 ? propertyName.substring(1) : "");
				}
				fn = clsName + "#" + propertyName;
				return this.properties.contains(fn);
			}
		}
		return contains;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((properties == null) ? 0 : properties.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PropertyCollection other = (PropertyCollection) obj;
		if (properties == null) {
			if (other.properties != null)
				return false;
		} else if (!properties.equals(other.properties))
			return false;
		return true;
	}

}
