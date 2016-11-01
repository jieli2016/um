package io.rulai.core.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;

public class TimeUtils {

	public static final DateTime EPOCH = new DateTime(0);

	public static Timestamp earlier(long millis) {
		return new Timestamp(System.currentTimeMillis() - millis);
	}

	/**
	 * Returns midnight in UTC for start of the specified dayId.
	 */
	public static Date getDate(int dayId) {
		return new Date(dayId * Constants.ONE_DAY);
	}

	/**
	 * Returns time for start of the specified hour in UTC.
	 */
	public static Date getDateFromHourId(int hourId) {
		return new Date(hourId * Constants.ONE_HOUR);
	}

	/**
	 * Returns time for start of the specified minute in UTC.
	 */
	public static Date getDateFromMinuteId(int minuteId) {
		return new Date(minuteId * Constants.ONE_MIN);
	}

	public static Date getDateStartOfDay(long millis, TimeZone tz) {
		DateTime tzDate = new DateTime(millis, DateTimeZone.forTimeZone(tz));
		DateTime tzStartOfDay = tzDate.withTimeAtStartOfDay();
		return new Date(tzStartOfDay.getMillis());
	}

	/**
	 * Unique day-id based on UTC.
	 */
	public static int getDayId(Date date) {
		return getDayId(date.getTime());
	}

	/**
	 * Unique day-id based on condensed UTC date string like 20141201
	 */
	public static int getDayId(String text) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		sdf.setTimeZone(Constants.UTC);
		try {
			return getDayId(sdf.parse(text).getTime());
		} catch (ParseException e) {
			return 0;
		}
	}

	/**
	 * Unique day-id based on UTC.
	 */
	public static int getDayId(long time) {
		return (int) (time / Constants.ONE_DAY);
	}

	/**
	 * Hour-id based on UTC
	 */
	public static int getHourId(Date time) {
		return getHourId(time.getTime());
	}

	/**
	 * Hour-id based on UTC
	 */
	public static int getHourId(long time) {
		return (int) (time / Constants.ONE_HOUR);
	}

	/**
	 * Minute-id based on UTC.
	 */
	public static int getMinuteId(long time) {
		return (int) (time / Constants.ONE_MIN);
	}

	/**
	 * Second-id based on UTC.
	 */
	public static long getSecondId(long time) {
		return (int) (time / Constants.ONE_SEC);
	}

	/**
	 * Returns midnight of Monday just preceding d as per UTC timezone.
	 */
	public static int getStartOfWeekDayId(Date d) {
		LocalDate ld = new LocalDate(d, Constants.DATETIMEZONE_UTC);
		LocalDate weekStart = ld.withDayOfWeek(DateTimeConstants.MONDAY);
		Date weekStartDate = weekStart.toDate();
		return getDayId(weekStartDate);
	}

	public static Timestamp getTimestamp(int dayId) {
		return new Timestamp(dayId * Constants.ONE_DAY);
	}

	/**
	 * America/Toronto timezone; Example: parseET(2013, 3, 31, 3, 22, 11) will
	 * return "Sun Mar 31 03:22:11 EDT 2013" NOTE: month is between 1-12
	 */
	public static Timestamp parseET(int year, int month, int dayOfMonth,
			int hourOfDay, int minute, int second) {
		Calendar cal = Calendar.getInstance(Constants.TIMEZONE_PACIFIC);
		cal.set(year, month - 1, dayOfMonth, hourOfDay, minute, second);
		return new Timestamp(cal.getTimeInMillis());
	}

	/**
	 * America/Toronto timezone; Format YYYY-MM-dd HH:mm:ss
	 */
	public static Timestamp parseET(String text) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(Constants.TIMEZONE_PACIFIC);
		try {
			return new Timestamp(sdf.parse(text).getTime());
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * America/Toronto timezone; Format YYYYMMdd (20141201)
	 */
	public static Timestamp parseETFromCondensedDateString(String text) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		sdf.setTimeZone(Constants.TIMEZONE_PACIFIC);
		try {
			return new Timestamp(sdf.parse(text).getTime());
		} catch (java.text.ParseException e) {
			return null;
		}
	}

	/**
	 * America/Toronto timezone; Format YYYYMMddHHmmss (20141201205959)
	 */
	public static Timestamp parseETFromCondensedString(String text) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		sdf.setTimeZone(Constants.TIMEZONE_PACIFIC);
		try {
			return new Timestamp(sdf.parse(text).getTime());
		} catch (java.text.ParseException e) {
			return null;
		}
	}

	/**
	 * America/Toronto timezone; Example: parseUTC(2013, 3, 31, 3, 22, 11) will
	 * return "Sat Mar 30 23:22:11 EDT 2013" NOTE: month is between 1-12
	 */
	public static Timestamp parseUTC(int year, int month, int dayOfMonth,
			int hourOfDay, int minute, int second) {
		Calendar cal = Calendar.getInstance(Constants.UTC);
		cal.set(year, month - 1, dayOfMonth, hourOfDay, minute, second);
		return new Timestamp(cal.getTimeInMillis());
	}

	/**
	 * UTC timezone; Format YYYY-MM-dd HH:mm:ss
	 */
	public static Timestamp parseUTC(String text) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(Constants.UTC);
		try {
			return new Timestamp(sdf.parse(text).getTime());
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * UTC timezone; Format YYYYMMdd (20141201)
	 */
	public static Timestamp parseUTCFromCondensedDateString(String text) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		sdf.setTimeZone(Constants.UTC);
		try {
			return new Timestamp(sdf.parse(text).getTime());
		} catch (java.text.ParseException e) {
			return null;
		}
	}

	/**
	 * UTC timezone; Format YYYYMMddHHmmss (20141201205959)
	 */
	public static Timestamp parseUTCFromCondensedString(String text) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		sdf.setTimeZone(Constants.UTC);
		try {
			return new Timestamp(sdf.parse(text).getTime());
		} catch (java.text.ParseException e) {
			return null;
		}
	}
	
	public static String toCondensedDateString(int dayId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");		
		sdf.setTimeZone(Constants.UTC);
		Date d = getDate(dayId);
		return sdf.format(d);
	}

	public static String toString(TimeZone tz, String format, Date d) {
		if (format == null) {
			format = "yyyy-MM-dd HH:mm:ss z";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if (tz == null) {
			tz = Constants.TIMEZONE_PACIFIC;
		}
		sdf.setTimeZone(tz);
		return sdf.format(d);
	}

	public static String toString(TimeZone tz, String format, long time) {
		return toString(tz, format, new Date(time));
	}

	public static String toStringDuration(long duration) {
		if (duration < Constants.ONE_SEC * 5) {
			return ""
					+ new java.text.DecimalFormat("0.#")
							.format(duration / 1000.0f) + "s";
		} else if (duration < Constants.ONE_MIN) {
			return (duration / Constants.ONE_SEC) + "s";
		} else if (duration < Constants.FIVE_MINS) {
			int numMin = (int) (duration / Constants.ONE_MIN);
			return numMin
					+ "m "
					+ ((duration - (numMin * Constants.ONE_MIN)) / Constants.ONE_SEC)
					+ "s";
		} else if (duration < Constants.ONE_HOUR * 3) {
			int numMin = (int) (duration / Constants.ONE_MIN);
			return numMin + "m";
		} else if (duration < Constants.ONE_DAY * 3) {
			int numHr = (int) (duration / Constants.ONE_HOUR);
			return numHr + "h";
		} else {
			int numDay = (int) (duration / Constants.ONE_DAY);
			return numDay + "d";
		}
	}

	public static String toShortStringDuration(long duration) {
		if (duration < Constants.ONE_SEC * 5) {
			return ""
					+ new java.text.DecimalFormat("0.#")
							.format(duration / 1000.0f) + "s";
		} else if (duration < Constants.ONE_MIN) {
			return (duration / Constants.ONE_SEC) + "s";
		} else if (duration < Constants.ONE_MIN * 100) {
			int numMin = (int) (Math.round(duration
					/ (1.0f * Constants.ONE_MIN)));
			return numMin + "m";
		} else if (duration <= Constants.ONE_DAY * 4) {
			int numMin = (int) (duration / Constants.ONE_HOUR);
			return numMin + "h";
		} else {
			int numDay = (int) (duration / Constants.ONE_DAY);
			return numDay + "d";
		}

	}

	public static String toStringUTC(Date d) {
		return toString(Constants.UTC, "yyyy-MM-dd HH:mm:ss", d);
	}

}
