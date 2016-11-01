package io.rulai.core.utils;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterOutputStream;

import org.apache.commons.lang3.StringUtils;

import gnu.trove.list.array.TDoubleArrayList;
import gnu.trove.list.array.TFloatArrayList;
import gnu.trove.list.array.TIntArrayList;
import gnu.trove.list.array.TLongArrayList;
import gnu.trove.map.hash.TDoubleDoubleHashMap;
import gnu.trove.map.hash.TDoubleIntHashMap;
import gnu.trove.map.hash.TDoubleLongHashMap;
import gnu.trove.map.hash.TFloatDoubleHashMap;
import gnu.trove.map.hash.TFloatIntHashMap;
import gnu.trove.map.hash.TFloatLongHashMap;
import gnu.trove.map.hash.TIntDoubleHashMap;
import gnu.trove.map.hash.TIntFloatHashMap;
import gnu.trove.map.hash.TIntIntHashMap;
import gnu.trove.map.hash.TIntLongHashMap;
import gnu.trove.map.hash.TLongDoubleHashMap;
import gnu.trove.map.hash.TLongIntHashMap;
import gnu.trove.map.hash.TLongLongHashMap;
import gnu.trove.map.hash.TObjectIntHashMap;

public class CoreUtils {

	// http://howtodoinjava.com/2014/11/11/java-regex-validate-email-address/
	private static final Pattern EMAIL_PATTERN = Pattern
			.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");

	public static double[] add(double[] array, int a) {
		double[] array2 = new double[array.length + 1];
		for (int i = 0; i < array.length; i++) {
			array2[i] = array[i];
		}
		array2[array2.length - 1] = a;
		return array2;
	}

	public static float[] add(float[] array, int a) {
		float[] array2 = new float[array.length + 1];
		for (int i = 0; i < array.length; i++) {
			array2[i] = array[i];
		}
		array2[array2.length - 1] = a;
		return array2;
	}

	public static int[] add(int[] array, int a) {
		int[] array2 = new int[array.length + 1];
		for (int i = 0; i < array.length; i++) {
			array2[i] = array[i];
		}
		array2[array2.length - 1] = a;
		return array2;
	}

	public static long[] add(long[] array, int a) {
		long[] array2 = new long[array.length + 1];
		for (int i = 0; i < array.length; i++) {
			array2[i] = array[i];
		}
		array2[array2.length - 1] = a;
		return array2;
	}

	public static <T> T[] add(T[] array, T a) {
		T[] array2 = Arrays.copyOf(array, array.length + 1);
		array2[array.length] = a;
		return array2;
	}

	public static Set<Double> addAll(Set<Double> set, double[] array) {
		for (double i : array) {
			set.add(i);
		}
		return set;
	}

	public static Set<Float> addAll(Set<Float> set, float[] array) {
		for (float i : array) {
			set.add(i);
		}
		return set;
	}

	public static Set<Integer> addAll(Set<Integer> set, int[] array) {
		for (int i : array) {
			set.add(i);
		}
		return set;
	}

	public static Set<Long> addAll(Set<Long> set, long[] array) {
		for (long i : array) {
			set.add(i);
		}
		return set;
	}

	public static <K, V> Map<K, List<V>> addMapList(Map<K, List<V>> map1, Map<K, List<V>> map2) {
		Map<K, List<V>> map = new HashMap<>();
		for (K k : map1.keySet()) {
			putMapList(map, k, map1.get(k));
		}
		for (K k : map2.keySet()) {
			putMapList(map, k, map2.get(k));
		}
		return map;
	}

	public static <K, V> Map<K, Set<V>> addMapSet(Map<K, Set<V>> map1, Map<K, Set<V>> map2) {
		Map<K, Set<V>> map = new HashMap<>();
		for (K k : map1.keySet()) {
			putMapSet(map, k, map1.get(k));
		}
		for (K k : map2.keySet()) {
			putMapSet(map, k, map2.get(k));
		}
		return map;
	}

	public static int byteArrayToInt(byte[] bytes) {
		return ByteBuffer.wrap(bytes).getInt();
	}

	public static boolean byteArrayToBool(byte[] bytes) {
		BitSet bits = BitSet.valueOf(bytes);
		return bits.nextSetBit(0) == 0;
	}

	public static short byteArrayToShort(byte[] b) {
		final ByteBuffer bb = ByteBuffer.wrap(b);
		return bb.getShort();
	}

	public static void close(InputStream w) {
		try {
			if (w != null) {
				w.close();
			}
		} catch (Exception e) {
		}
	}

	public static void close(OutputStream w) {
		try {
			if (w != null) {
				w.close();
			}
		} catch (Exception e) {
		}
	}

	public static void close(Reader w) {
		try {
			if (w != null) {
				w.close();
			}
		} catch (Exception e) {
		}
	}

	public static void close(Writer w) {
		try {
			if (w != null) {
				w.close();
			}
		} catch (Exception e) {
		}
	}

	public static byte[] compress(byte[] data) {
		if (data == null) {
			return null;
		}
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			// create deflater without header
			DeflaterOutputStream def = new DeflaterOutputStream(out, new Deflater(Deflater.DEFAULT_COMPRESSION));
			def.write(data);
			def.close();
			byte[] compressed = out.toByteArray();
			out.close();
			return compressed;
		} catch (IOException e) {
			// this should NOT happen
			return null;
		}
	}

	public static byte[] compress(String s) {
		return compress(s.getBytes(Constants.UTF8));
	}

	public static boolean contains(double[] a, int b) {
		for (double o : a) {
			if (o == b) {
				return true;
			}
		}
		return false;
	}

	public static boolean contains(float[] a, int b) {
		for (float o : a) {
			if (o == b) {
				return true;
			}
		}
		return false;
	}

	public static boolean contains(int[] a, int b) {
		for (int o : a) {
			if (o == b) {
				return true;
			}
		}
		return false;
	}

	public static boolean contains(long[] a, long b) {
		for (long o : a) {
			if (o == b) {
				return true;
			}
		}
		return false;
	}

	public static <T> boolean contains(T[] a, T b) {
		if (a == null) {
			return false;
		}
		for (T o : a) {
			if ((o == null && b == null) || o.equals(b)) {
				return true;
			}
		}
		return false;
	}

	public static boolean contains(Tuple<Date, Date> daterange, Date d) {
		return d.getTime() >= daterange.getA().getTime() && d.getTime() > daterange.getB().getTime();
	}

	public static boolean containsAll(int[] bigarray, int[] smallarray) {
		for (int o : smallarray) {
			if (!contains(bigarray, o)) {
				return false;
			}
		}
		return true;
	}

	public static boolean containsAll(Set<byte[]> bigarray, Set<byte[]> smallarray) {
		for (byte[] bb : smallarray) {
			boolean found = false;
			for (byte[] aa : bigarray) {
				if (Arrays.equals(aa, bb)) {
					found = true;
					break;
				}
			}
			if (!found) {
				return false;
			}
		}
		return true;
	}

	public static <T> boolean containsAll(T[] bigarray, T[] smallarray) {
		if (smallarray == null) {
			return false;
		}
		for (T o : smallarray) {
			if (!contains(bigarray, o)) {
				return false;
			}
		}
		return true;
	}

	public static String convertColorToRgb(final Color color) {
		if (color != null) {
			return "rgb(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ")";
		}
		return null;
	}

	public static String convertRgbToHex(final String colorString) {
		String[] rgbs = colorString.split(",");
		int r = Integer.parseInt(rgbs[0].substring(4));
		int g = Integer.parseInt(rgbs[1]);
		int b = Integer.parseInt(rgbs[2].substring(0, rgbs[2].length() - 1));
		String ret = "#" + toBrowserHexValue(r) + toBrowserHexValue(g) + toBrowserHexValue(b);
		return ret;
	}

	public static Color convertStringToColor(final String colorString) {
		Color color = null;
		if (StringUtils.isNotBlank(colorString)) {
			String colorStringLower = colorString.trim().toLowerCase();
			if (colorStringLower.startsWith("rgb")) {
				String rgbValues = colorStringLower.replace("rgb(", "").replace(")", "");
				String[] rgbValuesArray = rgbValues.split(",");
				if (rgbValuesArray.length == 3) {
					int r = Integer.parseInt(rgbValuesArray[0].trim());
					int g = Integer.parseInt(rgbValuesArray[1].trim());
					int b = Integer.parseInt(rgbValuesArray[2].trim());
					color = new Color(r, g, b);
				}
			} else if (colorStringLower.startsWith("#")) {
				if (7 == colorStringLower.length()) {
					int r = Integer.parseInt(colorStringLower.substring(1, 3), 16);
					int g = Integer.parseInt(colorStringLower.substring(3, 5), 16);
					int b = Integer.parseInt(colorStringLower.substring(5, 7), 16);
					color = new Color(r, g, b);
				} else if (4 == colorStringLower.length()) {
					int r = Integer.parseInt(colorStringLower.substring(1, 2) + colorStringLower.substring(1, 2), 16);
					int g = Integer.parseInt(colorStringLower.substring(2, 3) + colorStringLower.substring(2, 3), 16);
					int b = Integer.parseInt(colorStringLower.substring(3, 4) + colorStringLower.substring(3, 4), 16);
					color = new Color(r, g, b);
				}
			}
		}
		return color;
	}

	public static String decodeFrmUrlQuery(String txt) {
		try {
			return URLDecoder.decode(txt, "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
		return txt;
	}

	public static byte[] decompress(byte[] compressed) throws IOException {
		if (compressed == null) {
			return null;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		InflaterOutputStream ios = new InflaterOutputStream(out);
		ios.write(compressed);
		ios.close();
		byte[] decompressed = out.toByteArray();
		out.close();
		return decompressed;
	}

	public static String decompressToString(byte[] compressed) throws IOException {
		byte[] decompressed = decompress(compressed);
		return new String(decompressed, Constants.UTF8);
	}

	public static String encodeForUrlQuery(String txt) {
		try {
			return URLEncoder.encode(txt, "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
		return txt;
	}

	public static <T> boolean equal(T a, T b) {
		if (a == null) {
			if (b != null)
				return false;
		} else if (!a.equals(b))
			return false;

		return true;
	}

	// Just add the missing StringEscapeUtils.escapeSql
	// Escapes the characters in a String to be suitable to pass to an SQL
	// query.
	//
	// For example,
	//
	// statement.executeQuery("SELECT * FROM MOVIES WHERE TITLE='" +
	// StringEscapeUtils.escapeSql("McHale's Navy") +
	// "'");
	// At present, this method only turns single-quotes into doubled
	// single-quotes ("McHale's Navy" => "McHale''s Navy"). It does not handle
	// the cases of percent (%) or underscore (_) for use in LIKE clauses.
	//
	// see http://www.jguru.com/faq/view.jsp?EID=8881
	public static String escapeSql(String text) {
		if (text == null || text.isEmpty()) {
			return text;
		}

		text = text.replace("'", "''");

		return text;
	}

	public static Number format(Number n, int decimals) {
		BigDecimal d = new BigDecimal(n.doubleValue());
		if (decimals >= 0) {
			return d.setScale(decimals, BigDecimal.ROUND_HALF_UP);
		} else {
			return d;
		}
	}

	public static String genRandomString(int length) {
		char[] chars = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			char c = chars[Randomizer.nextInt(chars.length)];
			sb.append(c);
		}
		return sb.toString();
	}

	/**
	 * Get an key from the collection, or return NULL if it is empty.
	 */
	public static <K, V> K getFirstKey(Map<K, V> map) {
		for (K k : map.keySet()) {
			return k;
		}
		return null;
	}

	/**
	 * Get an element from the collection, or return NULL if it is empty.
	 */
	public static <K> K getFirstValue(Collection<K> set) {
		for (K k : set) {
			return k;
		}
		return null;
	}

	/**
	 * Get an value from the collection, or return NULL if it is empty.
	 */
	public static <K, V> V getFirstValue(Map<K, V> map) {
		for (V v : map.values()) {
			return v;
		}
		return null;
	}

	public static String getStacktace(Throwable e, boolean removeNewline) {
		StringWriter sw = new StringWriter(1024);
		PrintWriter ppw = new PrintWriter(sw);
		e.printStackTrace(ppw);
		ppw.flush();
		sw.flush();
		ppw.close();
		try {
			sw.close();
			String s = sw.toString();
			if (removeNewline) {
				s = s.replaceAll("\n", " :: ");
			}
			return s;
		} catch (IOException e1) {
			return "" + e;
		}
	}

	public static byte[] intToByteArray(int value) {
		return ByteBuffer.allocate(4).putInt(value).array();
	}

	public static byte[] boolToByteArray(boolean value) {
		BitSet bits = new BitSet(1);
		if (value) {
			bits.set(0);
		}
		return bits.toByteArray();
	}

	public static boolean isAlphaNum(char ch) {
		return ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9'));
	}

	public static boolean isEmail(String s) {
		if (s == null || s.trim().isEmpty()) {
			return false;
		}
		Matcher match = EMAIL_PATTERN.matcher(s);
		return match.matches();
	}

	public static <E extends Comparable> boolean isSorted(E[] array, boolean ascending) {
		for (int i = 0; i < array.length - 1; i++) {
			int cmp = array[i].compareTo(array[i + 1]);
			boolean notSorted = (ascending && cmp > 0) || (!ascending && cmp < 0);
			if (notSorted) {
				return false;
			}
		}
		return true;
	}

	public static double max(double[] array) {
		if (array.length == 0) {
			throw new IllegalArgumentException("array size is zero");
		}
		double n = array[0];
		for (int i = 1; i < array.length; i++) {
			double e = array[i];
			if (e > n) {
				n = e;
			}
		}
		return n;
	}

	public static int max(int[] array) {
		if (array.length == 0) {
			throw new IllegalArgumentException("array size is zero");
		}
		int n = array[0];
		for (int i = 1; i < array.length; i++) {
			int e = array[i];
			if (e > n) {
				n = e;
			}
		}
		return n;
	}

	public static Integer max(List<Integer> list) {
		if (list.size() == 0) {
			throw new IllegalArgumentException("List size is zero");
		}
		Integer n = list.get(0);
		for (Integer e : list) {
			if (e.intValue() > n.intValue()) {
				n = e;
			}
		}
		return n;
	}

	public static Integer max(Set<Integer> set) {
		if (set.size() == 0) {
			throw new IllegalArgumentException("Set size is zero");
		}
		Integer n = Integer.MIN_VALUE;
		for (Integer e : set) {
			if (e.intValue() > n.intValue()) {
				n = e;
			}
		}
		return n;
	}

	public static double mean(double[] data) {
		// double v = 0;
		// for (double i : data) {
		// why performing an additional flop here?
		// v += ((i) / data.length);
		// }
		// return v;
		int l = data.length;
		double accumulator = 0.d;
		for (int i = 0; i < l; ++i) {
			// TODO high percision summing via divide conquer or Kahan's
			// algorithm
			// also, could just call sum(data) / data.length ?
			accumulator += data[i];
		}
		return accumulator / data.length;
	}

	public static double mean(int[] data) {
		// double v = 0;
		// for (int i : data) {
		// v += ((i * 1.0d) / data.length);
		// }
		// return v;
		long accumulator = 0L;
		int l = data.length;
		for (int i = 0; i < l; ++i) {
			accumulator += data[i];
		}
		return ((double) accumulator) / ((double) data.length);
	}

	public static double median(double[] vec) {
		double[] sorted = Arrays.copyOf(vec, vec.length);
		Arrays.sort(sorted);
		int index = (int) Math.ceil((double) vec.length / 2);
		if (vec.length % 2 == 0 && vec.length > 1) {
			return ((sorted[index] + sorted[index - 1]) / 2);
		} else {
			return sorted[index - 1];
		}
	}

	public static int median(int[] vec) {
		int[] sorted = Arrays.copyOf(vec, vec.length);
		Arrays.sort(sorted);
		int index = (int) Math.ceil((double) vec.length / 2);
		if (vec.length % 2 == 0 && vec.length > 1) {
			return ((sorted[index] + sorted[index - 1]) / 2);
		} else {
			return sorted[index - 1];
		}
	}

	public static int min(int[] array) {
		if (array.length == 0) {
			throw new IllegalArgumentException("array size is zero");
		}
		int n = array[0];
		for (int i = 1; i < array.length; i++) {
			int e = array[i];
			if (e < n) {
				n = e;
			}
		}
		return n;
	}

	public static Integer min(List<Integer> list) {
		if (list.size() == 0) {
			throw new IllegalArgumentException("List size is zero");
		}
		Integer n = list.get(0);
		for (Integer e : list) {
			if (e.intValue() < n.intValue()) {
				n = e;
			}
		}
		return n;
	}

	public static Integer min(Set<Integer> set) {
		if (set.size() == 0) {
			throw new IllegalArgumentException("Set size is zero");
		}
		Integer n = Integer.MAX_VALUE;
		for (Integer e : set) {
			if (e.intValue() < n.intValue()) {
				n = e;
			}
		}
		return n;
	}

	public static double[] parseArrayDouble(String s, boolean suppressException) {
		if (s == null) {
			return null;
		}
		String[] f = s.split(",");
		TDoubleArrayList list = new TDoubleArrayList();
		for (String istr : f) {
			istr = istr.trim();
			if (!istr.isEmpty()) {
				try {
					double i = Double.parseDouble(istr);
					list.add(i);
				} catch (NumberFormatException e) {
					if (!suppressException) {
						throw e;
					}
				}
			}
		}
		return list.toArray();
	}

	public static float[] parseArrayFloat(String s, boolean suppressException) {
		return parseArrayFloat(s, ",", suppressException);
	}

	public static float[] parseArrayFloat(String s, String delimiter, boolean suppressException) {
		if (s == null) {
			return null;
		}
		String[] f = s.split(delimiter);
		TFloatArrayList list = new TFloatArrayList();
		for (String istr : f) {
			istr = istr.trim();
			if (!istr.isEmpty()) {
				try {
					float i = Float.parseFloat(istr);
					list.add(i);
				} catch (NumberFormatException e) {
					if (!suppressException) {
						throw e;
					}
				}
			}
		}
		return list.toArray();
	}

	public static int[] parseArrayInt(String s, boolean suppressException) {
		return parseArrayInt(s, ",", suppressException);
	}

	public static int[] parseArrayInt(String s, String delimiter, boolean suppressException) {
		if (s == null) {
			return null;
		}
		String[] f = s.split(delimiter);
		TIntArrayList list = new TIntArrayList();
		for (String istr : f) {
			istr = istr.trim();
			if (!istr.isEmpty()) {
				try {
					int i = Integer.parseInt(istr);
					list.add(i);
				} catch (NumberFormatException e) {
					if (!suppressException) {
						throw e;
					}
				}
			}
		}
		return list.toArray();
	}

	public static long[] parseArrayLong(String s, boolean suppressException) {
		if (s == null) {
			return null;
		}
		String[] f = s.split(",");
		TLongArrayList list = new TLongArrayList();
		for (String istr : f) {
			istr = istr.trim();
			if (!istr.isEmpty()) {
				try {
					long i = Long.parseLong(istr);
					list.add(i);
				} catch (NumberFormatException e) {
					if (!suppressException) {
						throw e;
					}
				}
			}
		}
		return list.toArray();
	}

	public static boolean parseBoolean(String s, boolean defaultVal) {
		if (s == null) {
			return defaultVal;
		}
		try {
			return Boolean.parseBoolean((s + "").toLowerCase());
		} catch (NumberFormatException e) {
			return defaultVal;
		}
	}

	public static double parseDouble(String s, double defaultVal) {
		if (s == null) {
			return defaultVal;
		}
		try {
			return Double.parseDouble(s);
		} catch (NumberFormatException e) {
			return defaultVal;
		}
	}

	public static float parseFloat(String s, float defaultVal) {
		if (s == null) {
			return defaultVal;
		}
		try {
			return Float.parseFloat(s);
		} catch (NumberFormatException e) {
			return defaultVal;
		}
	}

	public static int parseInt(String s, int defaultVal) {
		if (s == null) {
			return defaultVal;
		}
		try {
			return Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return defaultVal;
		}
	}

	public static <E extends Enum<E>> List<E> parseListEnum(String s, Class<E> enumClass, boolean suppressException) {
		if (s == null) {
			return null;
		}
		String[] f = s.split(",");
		List<E> list = new ArrayList<>();
		for (String istr : f) {
			istr = istr.trim();
			if (!istr.isEmpty()) {
				try {
					E e = (E) Enum.valueOf(enumClass, istr.toUpperCase());
					list.add(e);
				} catch (IllegalArgumentException e) {
					if (!suppressException) {
						throw e;
					}
				}
			}
		}
		return list;
	}

	public static long parseLong(String s, long defaultVal) {
		if (s == null) {
			return defaultVal;
		}
		try {
			return Long.parseLong(s);
		} catch (NumberFormatException e) {
			return defaultVal;
		}
	}

	public static <K, V> void putMapList(Map<K, List<V>> map, K k, Collection<V> collection) {
		if (!map.containsKey(k)) {
			map.put(k, new ArrayList<V>());
		}
		map.get(k).addAll(collection);
	}

	public static <K, V> void putMapList(Map<K, List<V>> map, K k, V v) {
		if (!map.containsKey(k)) {
			map.put(k, new ArrayList<V>());
		}
		map.get(k).add(v);
	}

	public static <K, V> void putMapList(Map<K, List<V>> map, K k, V[] collection) {
		if (!map.containsKey(k)) {
			map.put(k, new ArrayList<V>());
		}
		List<V> list = map.get(k);
		for (V v : collection) {
			list.add(v);
		}
	}

	public static <K, V> void putMapSet(Map<K, Set<V>> map, K k, Collection<V> collection) {
		if (!map.containsKey(k)) {
			map.put(k, new HashSet<V>());
		}
		map.get(k).addAll(collection);
	}

	public static <K, V> void putMapSet(Map<K, Set<V>> map, K k, V v) {
		if (!map.containsKey(k)) {
			map.put(k, new HashSet<V>());
		}
		map.get(k).add(v);
	}

	public static <K, V> void putMapSet(Map<K, Set<V>> map, K k, V[] collection) {
		if (!map.containsKey(k)) {
			map.put(k, new HashSet<V>());
		}
		Set<V> set = map.get(k);
		for (V v : collection) {
			set.add(v);
		}
	}

	public static int[] randInt(int n, int sum) {
		int[] nums = new int[n];
		int upperbound = Long.valueOf(Math.round(sum * 1.0 / n)).intValue();
		int offset = Long.valueOf(Math.round(0.5 * upperbound)).intValue();

		int cursum = 0;
		for (int i = 0; i < n; i++) {
			int rand = Randomizer.nextInt(upperbound) + offset;
			if (cursum + rand > sum || i == n - 1) {
				rand = sum - cursum;
			}
			cursum += rand;
			nums[i] = rand;
			if (cursum == sum) {
				break;
			}
		}
		return nums;
	}

	public static int readInt(byte[] data, int from) {
		byte[] bb = data;
		int readback = (((int) (bb[from + 0] & 0xff)) << 24) | (((int) (bb[from + 1] & 0xff)) << 16)
				| (((int) (bb[from + 2] & 0xff)) << 8) | (((int) (bb[from + 3] & 0xff)) << 0);
		return readback;
	}

	public static long readLong(byte[] bb, int from) {
		long c = (((long) (bb[from + 0] & 0xff)) << 56) | (((long) (bb[from + 1] & 0xff)) << 48)
				| (((long) (bb[from + 2] & 0xff)) << 40) | (((long) (bb[from + 3] & 0xff)) << 32)
				| (((long) (bb[from + 4] & 0xff)) << 24) | (((long) (bb[from + 5] & 0xff)) << 16)
				| (((long) (bb[from + 6] & 0xff)) << 8) | (((long) (bb[from + 7]) & 0xff) << 0);
		return c;
	}

	public static double[] remove(double[] array, double item) {
		TDoubleArrayList list = new TDoubleArrayList();
		for (double v : array) {
			if (item != v) {
				list.add(v);
			}
		}
		return list.toArray();
	}

	public static float[] remove(float[] array, float item) {
		TFloatArrayList list = new TFloatArrayList();
		for (float v : array) {
			if (item != v) {
				list.add(v);
			}
		}
		return list.toArray();
	}

	public static int[] remove(int[] array, int item) {
		TIntArrayList list = new TIntArrayList();
		for (int v : array) {
			if (item != v) {
				list.add(v);
			}
		}
		return list.toArray();
	}

	public static long[] remove(long[] array, long item) {
		TLongArrayList list = new TLongArrayList();
		for (long v : array) {
			if (item != v) {
				list.add(v);
			}
		}
		return list.toArray();
	}

	public static String[] removeBlankStrings(String[] inp) {
		List<String> ret = new ArrayList<String>();
		for (String s : inp) {
			if (s != null && !s.trim().isEmpty()) {
				ret.add(s);
			}
		}
		return ret.toArray(new String[0]);
	}

	public static byte[] shortToByteArray(short s) {
		final ByteBuffer bb = ByteBuffer.allocate(2);
		bb.putShort(s);
		bb.flip();
		return bb.array();
	}

	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}
	}

	public static <K, V> LinkedHashMap<K, V> sortMapByValue(Map<K, V> map) {
		List list = new LinkedList<>(map.entrySet());
		Collections.sort(list, new Comparator() {

			@Override
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o2)).getValue()).compareTo(((Map.Entry) (o1)).getValue());
			}
		});

		LinkedHashMap result = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

	public static <K, V> LinkedHashMap<K, V> sortMapByValue(Map<K, V> map, final boolean desc) {
		List list = new LinkedList<>(map.entrySet());
		Collections.sort(list, new Comparator() {

			@Override
			public int compare(Object o1, Object o2) {
				return desc ? ((Comparable) ((Map.Entry) (o2)).getValue()).compareTo(((Map.Entry) (o1)).getValue())
						: ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
			}
		});

		LinkedHashMap result = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

	public static double std(double[] data) {
		return Math.sqrt(variance(data));
	}

	public static double std(int[] data) {
		return Math.sqrt(variance(data));
	}

	public static double sum(double[] arr) {
		double d = 0;
		for (double a : arr) {
			d += a;
		}
		return d;
	}

	public static double sum(Double[] arr) {
		double d = 0;
		for (double a : arr) {
			d += a;
		}
		return d;
	}

	public static float sum(float[] arr) {
		double d = 0;
		for (float a : arr) {
			d += a;
		}
		return (float) d;
	}

	public static float sum(Float[] arr) {
		double d = 0;
		for (float a : arr) {
			d += a;
		}
		return (float) d;
	}

	public static int sum(int[] arr) {
		int d = 0;
		for (int a : arr) {
			d += a;
		}
		return d;
	}

	public static long sum(Integer[] arr) {
		long l = 0;
		for (int a : arr) {
			l += a;
		}
		return l;
	}

	public static float sum(List<Number> list) {
		float d = 0;
		for (Number a : list) {
			d += a.floatValue();
		}
		return d;
	}

	public static long sum(long[] arr) {
		long l = 0;
		for (long a : arr) {
			l += a;
		}
		return l;
	}

	public static double sumHighPrecision(double[] arr) {
		double sum = 0.0;
		double c = 0.0;
		int l = arr.length;
		for (int i = 0; i < l; ++i) {
			double y = arr[i] - c;
			double t = sum + y;
			c = (t - sum) - y;
			sum = t;
		}
		return sum;
	}

	public static float sumHighPrecision(float[] arr) {
		float sum = 0.0f;
		float c = 0.0f;
		int l = arr.length;
		for (int i = 0; i < l; ++i) {
			float y = arr[i] - c;
			float t = sum + y;
			c = (t - sum) - y;
			sum = t;
		}
		return sum;
	}

	public static boolean[] toBooleanArray(String[] array) {
		boolean[] vals = new boolean[array.length];
		for (int i = 0; i < array.length; i++) {
			vals[i] = Boolean.parseBoolean(array[i]);
		}
		return vals;
	}

	private static String toBrowserHexValue(int number) {
		StringBuilder builder = new StringBuilder(Integer.toHexString(number & 0xff));
		while (builder.length() < 2) {
			builder.append("0");
		}
		return builder.toString().toUpperCase();
	}

	public static Date[] toDateArray(String[] array) {
		long[] longs = toLongArray(array);
		Date[] vals = new Date[array.length];
		for (int i = 0; i < array.length; i++) {
			vals[i] = new Date(longs[i]);
		}
		return vals;
	}

	public static double[] toDoubleArray(Collection<Double> data) {
		double[] arr = new double[data.size()];
		int i = 0;
		for (double f : data) {
			arr[i] = f;
			i++;
		}
		return arr;
	}

	public static double[] toDoubleArray(int[] arr) {
		double[] d = new double[arr.length];
		for (int i = 0; i < d.length; i++) {
			d[i] = arr[i];
		}
		return d;
	}

	public static double[] toDoubleArray(String[] array) {
		double[] vals = new double[array.length];
		for (int i = 0; i < array.length; i++) {
			vals[i] = Double.parseDouble(array[i]);
		}
		return vals;
	}

	public static float[] toFloatArray(Collection<Float> data) {
		float[] arr = new float[data.size()];
		int i = 0;
		for (float f : data) {
			arr[i] = f;
			i++;
		}
		return arr;
	}

	public static float[] toFloatArray(int[] arr) {
		float[] d = new float[arr.length];
		for (int i = 0; i < d.length; i++) {
			d[i] = arr[i];
		}
		return d;
	}

	public static float[] toFloatArray(String[] array) {
		float[] vals = new float[array.length];
		for (int i = 0; i < array.length; i++) {
			vals[i] = Float.parseFloat(array[i]);
		}
		return vals;
	}

	public static String toHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		Formatter formatter = new Formatter(sb);
		for (byte b : bytes) {
			formatter.format("%02x", b);
		}
		formatter.close();
		return sb.toString();
	}

	public static int[] toIntArray(Collection<Integer> data) {
		int[] arr = new int[data.size()];
		int i = 0;
		for (Integer a : data) {
			arr[i] = a;
			i++;
		}
		return arr;
	}

	public static int[] toIntArray(String[] array) {
		int[] vals = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			vals[i] = Integer.parseInt(array[i]);
		}
		return vals;
	}

	public static long[] toLongArray(Collection<Long> data) {
		long[] arr = new long[data.size()];
		int i = 0;
		for (long f : data) {
			arr[i] = f;
			i++;
		}
		return arr;
	}

	public static long[] toLongArray(String[] array) {
		long[] vals = new long[array.length];
		for (int i = 0; i < array.length; i++) {
			vals[i] = Long.parseLong(array[i]);
		}
		return vals;
	}

	/**
	 * Underlying bytes must be UTF-8 strings
	 */
	public static HashMap<String, byte[]> toMapStringBytes(Map<String, String> map) {
		HashMap<String, byte[]> ret = new HashMap<String, byte[]>();
		for (String s : map.keySet()) {
			String val = map.get(s);
			ret.put(s, val.getBytes(Constants.UTF8));
		}
		return ret;
	}

	public static HashMap<String, byte[]> toMapStringBytesFromMapStringInt(Map<String, Integer> map) {
		HashMap<String, byte[]> ret = new HashMap<String, byte[]>();
		for (String s : map.keySet()) {
			Integer val = map.get(s);
			ret.put(s, intToByteArray(val));
		}
		return ret;
	}

	public static HashMap<String, byte[]> toMapStringBytesFromMapStringBool(Map<String, Boolean> map) {
		HashMap<String, byte[]> ret = new HashMap<String, byte[]>();
		for (String s : map.keySet()) {
			boolean val = map.get(s);
			ret.put(s, boolToByteArray(val));
		}
		return ret;
	}

	public static HashMap<String, byte[]> toMapStringBytesFromMapIntInt(Map<Integer, Integer> map) {
		HashMap<String, byte[]> ret = new HashMap<String, byte[]>();
		for (Integer s : map.keySet()) {
			Integer val = map.get(s);
			ret.put(s + "", intToByteArray(val));
		}
		return ret;
	}

	public static HashMap<String, Integer> toMapStringInteger(Map<String, byte[]> map) {
		HashMap<String, Integer> ret = new HashMap<String, Integer>();
		for (String s : map.keySet()) {
			byte[] valbytes = map.get(s);
			ret.put(s, byteArrayToInt(valbytes));
		}
		return ret;
	}

	public static HashMap<String, Boolean> toMapStringBoolean(Map<String, byte[]> map) {
		HashMap<String, Boolean> ret = new HashMap<String, Boolean>();
		for (String s : map.keySet()) {
			byte[] valbytes = map.get(s);
			ret.put(s, byteArrayToBool(valbytes));
		}
		return ret;
	}

	public static HashMap<Integer, Integer> toMapIntegerInteger(Map<String, byte[]> map) {
		HashMap<Integer, Integer> ret = new HashMap<Integer, Integer>();
		for (String s : map.keySet()) {
			byte[] valbytes = map.get(s);
			ret.put(Integer.parseInt(s), byteArrayToInt(valbytes));
		}
		return ret;
	}

	public static HashMap<String, String> toMapStringString(Map<String, byte[]> map) {
		HashMap<String, String> ret = new HashMap<String, String>();
		for (String s : map.keySet()) {
			byte[] valbytes = map.get(s);
			ret.put(s, new String(valbytes, Constants.UTF8));
		}
		return ret;
	}

	public static String toString(boolean[] l) {
		return "[" + toString(l, ", ") + "]";
	}

	public static String toString(boolean[] l, String seperator) {
		if (l == null) {
			return "NULL_ARRAY";
		}
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (boolean k : l) {
			if (!isFirst) {
				sb.append(seperator);
			}
			sb.append(k);
			isFirst = false;
		}
		return sb.toString();
	}

	public static String toString(double[] l) {
		return "[" + toString(l, ", ") + "]";
	}

	public static String toString(double[] l, String seperator) {
		if (l == null) {
			return "NULL_ARRAY";
		}
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (double k : l) {
			if (!isFirst) {
				sb.append(seperator);
			}
			sb.append(k);
			isFirst = false;
		}
		return sb.toString();
	}

	public static String toString(float[] l) {
		return "[" + toString(l, ", ") + "]";
	}

	public static String toString(float[] l, String seperator) {
		if (l == null) {
			return "NULL_ARRAY";
		}
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (float k : l) {
			if (!isFirst) {
				sb.append(seperator);
			}
			sb.append(k);
			isFirst = false;
		}
		return sb.toString();
	}
	
	public static String toString(byte[] l) {
		return "[" + toString(l, ", ") + "]";
	}

	public static String toString(byte[] l, String seperator) {
		if (l == null) {
			return "NULL_ARRAY";
		}
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (int k : l) {
			if (!isFirst) {
				sb.append(seperator);
			}
			sb.append(k);
			isFirst = false;
		}
		return sb.toString();
	}

	public static String toString(int[] l) {
		return "[" + toString(l, ", ") + "]";
	}

	public static String toString(int[] l, String seperator) {
		if (l == null) {
			return "NULL_ARRAY";
		}
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (int k : l) {
			if (!isFirst) {
				sb.append(seperator);
			}
			sb.append(k);
			isFirst = false;
		}
		return sb.toString();
	}

	public static String toString(long[] l) {
		return "[" + toString(l, ", ") + "]";
	}

	public static <T> String toString(List<T> l) {
		return "[" + toString(l, ", ") + "]";
	}

	public static <T> String toString(List<T> l, String seperator) {
		if (l == null) {
			return "NULL_MAP";
		}
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (T k : l) {
			if (!isFirst) {
				sb.append(seperator);
			}
			sb.append("" + k);
			isFirst = false;
		}
		return sb.toString();
	}

	public static String toString(long[] l, String seperator) {
		if (l == null) {
			return "NULL_ARRAY";
		}
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (long k : l) {
			if (!isFirst) {
				sb.append(seperator);
			}
			sb.append(k);
			isFirst = false;
		}
		return sb.toString();
	}

	public static <K, V> String toString(Map<K, V> m) {
		return toString(m, ", ", ": ");
	}

	public static <K, V> String toString(Map<K, V> m, String s, String d) {
		if (m == null) {
			return "NULL_MAP";
		}
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (K key : m.keySet()) {
			if (!isFirst) {
				sb.append(s);
			}
			sb.append(key.toString() + d + m.get(key).toString());
			isFirst = false;
		}
		return sb.toString();
	}

	public static <T> String toString(Set<T> l) {
		return "[" + toString(l, ", ") + "]";
	}

	public static <T> String toString(Set<T> s, String seperator) {
		if (s == null) {
			return "NULL_SET";
		}
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (T k : s) {
			if (!isFirst) {
				sb.append(seperator);
			}
			sb.append(k.toString());
			isFirst = false;
		}
		return sb.toString();
	}

	public static <T> String toString(T[] l) {
		return "[" + toString(l, ", ") + "]";
	}

	public static <T> String toString(T[] l, String seperator) {
		if (l == null) {
			return "NULL_ARRAY";
		}
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (T k : l) {
			if (!isFirst) {
				sb.append(seperator);
			}
			sb.append("" + k);
			isFirst = false;
		}
		return sb.toString();
	}

	public static String toString(TDoubleDoubleHashMap m) {
		if (m == null) {
			return "NULL_MAP";
		}
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (double key : m.keys()) {
			if (!isFirst) {
				sb.append(", ");
			}
			sb.append(key + ": " + m.get(key));
			isFirst = false;
		}
		return sb.toString();
	}

	public static String toString(TDoubleIntHashMap m) {
		if (m == null) {
			return "NULL_MAP";
		}
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (double key : m.keys()) {
			if (!isFirst) {
				sb.append(", ");
			}
			sb.append(key + ": " + m.get(key));
			isFirst = false;
		}
		return sb.toString();
	}

	public static String toString(TDoubleLongHashMap m) {
		if (m == null) {
			return "NULL_MAP";
		}
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (double key : m.keys()) {
			if (!isFirst) {
				sb.append(", ");
			}
			sb.append(key + ": " + m.get(key));
			isFirst = false;
		}
		return sb.toString();
	}

	public static String toString(TFloatDoubleHashMap m) {
		if (m == null) {
			return "NULL_MAP";
		}
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (float key : m.keys()) {
			if (!isFirst) {
				sb.append(", ");
			}
			sb.append(key + ": " + m.get(key));
			isFirst = false;
		}
		return sb.toString();
	}

	public static String toString(TFloatIntHashMap m) {
		if (m == null) {
			return "NULL_MAP";
		}
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (float key : m.keys()) {
			if (!isFirst) {
				sb.append(", ");
			}
			sb.append(key + ": " + m.get(key));
			isFirst = false;
		}
		return sb.toString();
	}

	public static String toString(TFloatLongHashMap m) {
		if (m == null) {
			return "NULL_MAP";
		}
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (float key : m.keys()) {
			if (!isFirst) {
				sb.append(", ");
			}
			sb.append(key + ": " + m.get(key));
			isFirst = false;
		}
		return sb.toString();
	}

	public static String toString(TIntDoubleHashMap m) {
		if (m == null) {
			return "NULL_MAP";
		}
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (int key : m.keys()) {
			if (!isFirst) {
				sb.append(", ");
			}
			sb.append(key + ": " + m.get(key));
			isFirst = false;
		}
		return sb.toString();
	}

	public static String toString(TIntFloatHashMap m) {
		if (m == null) {
			return "NULL_MAP";
		}
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (int key : m.keys()) {
			if (!isFirst) {
				sb.append(", ");
			}
			sb.append(key + ": " + m.get(key));
			isFirst = false;
		}
		return sb.toString();
	}

	public static String toString(TIntIntHashMap m) {
		if (m == null) {
			return "NULL_MAP";
		}
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (int key : m.keys()) {
			if (!isFirst) {
				sb.append(", ");
			}
			sb.append(key + ": " + m.get(key));
			isFirst = false;
		}
		return sb.toString();
	}

	public static String toString(TIntLongHashMap m) {
		if (m == null) {
			return "NULL_MAP";
		}
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (int key : m.keys()) {
			if (!isFirst) {
				sb.append(", ");
			}
			sb.append(key + ": " + m.get(key));
			isFirst = false;
		}
		return sb.toString();
	}

	public static String toString(TLongDoubleHashMap m) {
		if (m == null) {
			return "NULL_MAP";
		}
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (long key : m.keys()) {
			if (!isFirst) {
				sb.append(", ");
			}
			sb.append(key + ": " + m.get(key));
			isFirst = false;
		}
		return sb.toString();
	}

	public static String toString(TLongIntHashMap m) {
		if (m == null) {
			return "NULL_MAP";
		}
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (long key : m.keys()) {
			if (!isFirst) {
				sb.append(", ");
			}
			sb.append(key + ": " + m.get(key));
			isFirst = false;
		}
		return sb.toString();
	}

	public static String toString(TLongLongHashMap m) {
		if (m == null) {
			return "NULL_MAP";
		}
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (long key : m.keys()) {
			if (!isFirst) {
				sb.append(", ");
			}
			sb.append(key + ": " + m.get(key));
			isFirst = false;
		}
		return sb.toString();
	}

	public static String toString(TObjectIntHashMap m) {
		if (m == null) {
			return "NULL_MAP";
		}
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (Object key : m.keys()) {
			if (!isFirst) {
				sb.append(", ");
			}
			sb.append(key + ": " + m.get(key));
			isFirst = false;
		}
		return sb.toString();
	}

	public static String trim(String string, int maxLen) {
		if (string == null || string.length() <= maxLen) {
			return string;
		}
		return string.substring(0, maxLen);
	}

	public static String[] trimArray(String[] array) {
		if (array == null) {
			return array;
		}
		ArrayList<String> arrlist = new ArrayList<String>();
		for (String s : array) {
			if (s != null && s.trim() != "") {
				arrlist.add(s.trim());
			}
		}
		return arrlist.toArray(new String[arrlist.size()]);
	}

	/**
	 * Mean of top-50% elements of the array after sorting
	 */
	public static double upperQuartileMean(int[] vec) {
		if (vec.length == 1) {
			return vec[0];
		}
		int[] sorted = Arrays.copyOf(vec, vec.length);
		Arrays.sort(sorted);
		double sum = 0;
		int index = (int) Math.ceil(vec.length / 2.0d);
		for (int i = 0; i < index; i++) {
			sum += sorted[sorted.length - i - 1];
		}
		return sum / index;
	}

	public static double variance(double[] data) {
		// TODO
		// this is a 2 pass algorithm, we can do it in 1 pass using
		// VAR(X)=E(X^2)-E(X)^2
		// http://en.wikipedia.org/wiki/Algebraic_formula_for_the_variance
		double m = mean(data);
		double v = 0;
		for (double i : data) {
			double diff = (i - m);
			v += ((diff * diff) / data.length);
		}
		return v;
	}

	public static double variance(int[] data) {
		double m = mean(data);
		double v = 0;
		for (int i : data) {
			double diff = (i - m);
			v += ((diff * diff) / data.length);
		}
		return v;
	}

	public static void waitForever(boolean exitOnInterrupt) {
		while (true) {
			try {
				Thread.sleep(Constants.ONE_DAY);
			} catch (InterruptedException e) {
				if (exitOnInterrupt) {
					throw new SimpleRuntimeException(e);
				}
			}
		}
	}
}
