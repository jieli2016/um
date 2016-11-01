package io.rulai.core.utils;

import org.apache.commons.lang3.StringUtils;

public class OStringUtils {

	/**
	 * <p>
	 * Find the Jaro Winkler Distance which indicates the similarity score
	 * between two Strings.
	 * </p>
	 * 
	 * <p>
	 * The Jaro measure is the weighted sum of percentage of matched characters
	 * from each file and transposed characters. Winkler increased this measure
	 * for matching initial characters.
	 * </p>
	 * 
	 * <p>
	 * This implementation is based on the Jaro Winkler similarity algorithm
	 * from <a
	 * href="http://en.wikipedia.org/wiki/Jaro%E2%80%93Winkler_distance">
	 * http://en.wikipedia.org/wiki/Jaro%E2%80%93Winkler_distance</a>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.getJaroWinklerDistance(null, null)          = IllegalArgumentException
	 * StringUtils.getJaroWinklerDistance("","")               = 0.0
	 * StringUtils.getJaroWinklerDistance("","a")              = 0.0
	 * StringUtils.getJaroWinklerDistance("aaapppp", "")       = 0.0
	 * StringUtils.getJaroWinklerDistance("frog", "fog")       = 0.93
	 * StringUtils.getJaroWinklerDistance("fly", "ant")        = 0.0
	 * StringUtils.getJaroWinklerDistance("elephant", "hippo") = 0.44
	 * StringUtils.getJaroWinklerDistance("hippo", "elephant") = 0.44
	 * StringUtils.getJaroWinklerDistance("hippo", "zzzzzzzz") = 0.0
	 * StringUtils.getJaroWinklerDistance("hello", "hallo")    = 0.88
	 * StringUtils.getJaroWinklerDistance("ABC Corporation", "ABC Corp") = 0.91
	 * StringUtils.getJaroWinklerDistance("D N H Enterprises Inc", "D &amp; H Enterprises, Inc.") = 0.93
	 * StringUtils.getJaroWinklerDistance("My Gym Children's Fitness Center", "My Gym. Childrens Fitness") = 0.94
	 * StringUtils.getJaroWinklerDistance("PENNSYLVANIA", "PENNCISYLVNIA")    = 0.9
	 * </pre>
	 * 
	 * @param first
	 *            the first String, must not be null
	 * @param second
	 *            the second String, must not be null
	 * @return result distance
	 * @throws IllegalArgumentException
	 *             if either String input {@code null}
	 * @since 3.3
	 */
	public static double getJaroWinklerDistance(final CharSequence first,
			final CharSequence second) {
		final double DEFAULT_SCALING_FACTOR = 0.1;

		if (first == null || second == null) {
			throw new IllegalArgumentException("Strings must not be null");
		}

		final double jaro = score(first, second);
		final int cl = commonPrefixLength(first, second);
		final double matchScore = Math.round((jaro + (DEFAULT_SCALING_FACTOR
				* cl * (1.0 - jaro))) * 100.0) / 100.0;

		return matchScore;
	}

	/**
	 * This method returns the Jaro-Winkler score for string matching.
	 * 
	 * @param first
	 *            the first string to be matched
	 * @param second
	 *            the second string to be machted
	 * @return matching score without scaling factor impact
	 */
	private static double score(final CharSequence first,
			final CharSequence second) {
		String shorter;
		String longer;

		// Determine which String is longer.
		if (first.length() > second.length()) {
			longer = first.toString().toLowerCase();
			shorter = second.toString().toLowerCase();
		} else {
			longer = second.toString().toLowerCase();
			shorter = first.toString().toLowerCase();
		}

		// Calculate the half length() distance of the shorter String.
		final int halflength = (shorter.length() / 2) + 1;

		// Find the set of matching characters between the shorter and longer
		// strings. Note that
		// the set of matching characters may be different depending on the
		// order of the strings.
		final String m1 = getSetOfMatchingCharacterWithin(shorter, longer,
				halflength);
		final String m2 = getSetOfMatchingCharacterWithin(longer, shorter,
				halflength);

		// If one or both of the sets of common characters is empty, then
		// there is no similarity between the two strings.
		if (m1.length() == 0 || m2.length() == 0) {
			return 0.0;
		}

		// If the set of common characters is not the same size, then
		// there is no similarity between the two strings, either.
		if (m1.length() != m2.length()) {
			return 0.0;
		}

		// Calculate the number of transposition between the two sets
		// of common characters.
		final int transpositions = transpositions(m1, m2);

		// Calculate the distance.
		final double dist = (m1.length() / ((double) shorter.length())
				+ m2.length() / ((double) longer.length()) + (m1.length() - transpositions)
				/ ((double) m1.length())) / 3.0;
		return dist;
	}

	/**
	 * Gets a set of matching characters between two strings.
	 * 
	 * <p>
	 * <Two characters from the first string and the second string are
	 * considered matching if the character's respective positions are no
	 * farther than the limit value.
	 * </p>
	 * 
	 * @param first
	 *            The first string.
	 * @param second
	 *            The second string.
	 * @param limit
	 *            The maximum distance to consider.
	 * @return A string contain the set of common characters.
	 */
	private static String getSetOfMatchingCharacterWithin(
			final CharSequence first, final CharSequence second, final int limit) {
		final StringBuilder common = new StringBuilder();
		final StringBuilder copy = new StringBuilder(second);

		for (int i = 0; i < first.length(); i++) {
			final char ch = first.charAt(i);
			boolean found = false;

			// See if the character is within the limit positions away from the
			// original position of that character.
			for (int j = Math.max(0, i - limit); !found
					&& j < Math.min(i + limit, second.length()); j++) {
				if (copy.charAt(j) == ch) {
					found = true;
					common.append(ch);
					copy.setCharAt(j, '*');
				}
			}
		}
		return common.toString();
	}

	/**
	 * Calculates the number of transposition between two strings.
	 * 
	 * @param first
	 *            The first string.
	 * @param second
	 *            The second string.
	 * @return The number of transposition between the two strings.
	 */
	private static int transpositions(CharSequence first, CharSequence second) {
		int transpositions = 0;
		for (int i = 0; i < first.length(); i++) {
			if (first.charAt(i) != second.charAt(i)) {
				transpositions++;
			}
		}
		return transpositions / 2;
	}

	/**
	 * Calculates the number of characters from the beginning of the strings
	 * that match exactly one-to-one, up to a maximum of four (4) characters.
	 * 
	 * @param first
	 *            The first string.
	 * @param second
	 *            The second string.
	 * @return A number between 0 and 4.
	 */
	private static int commonPrefixLength(CharSequence first,
			CharSequence second) {
		final int result = StringUtils.getCommonPrefix(first.toString(),
				second.toString()).length();

		// Limit the result to 4.
		return result > 4 ? 4 : result;
	}

}
