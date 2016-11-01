package io.rulai.core.utils;

import java.nio.charset.Charset;
import java.util.TimeZone;

import org.joda.time.DateTimeZone;

public class Constants {
	
	public static String FAQ2_DB_SERVER = "rulai.c0sm9aclkxrm.us-west-2.rds.amazonaws.com";

	public static final String FIREFOX_29_BROWSER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20120101 Firefox/29.0";

	public static final Charset UTF8 = Charset.forName("UTF-8");

	public static final long FIFTEEN_MINS = 15L * 60 * 1000L;

	public static final long FIVE_MINS = 5 * 60 * 1000L;

	public static final long FOUR_HOURS = 4L * 60L * 60L * 1000L;

	public static final TimeZone UTC = TimeZone.getTimeZone("UTC");

	public static final TimeZone TIMEZONE_PACIFIC = TimeZone.getTimeZone("America/Los_Angeles");
	
	public static final DateTimeZone DATETIMEZONE_PACIFIC = DateTimeZone.forTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));

	public static final DateTimeZone DATETIMEZONE_UTC = DateTimeZone.forTimeZone(TimeZone.getTimeZone("UTC"));

	public static final long ONE_DAY = 24L * 60L * 60L * 1000L;

	public static final long ONE_HOUR = 60L * 60L * 1000L;

	public static final long ONE_MIN = 60 * 1000L;

	public static final long ONE_MONTH = 30L * 24L * 60L * 60L * 1000L;

	public static final long ONE_SEC = 1000L;

	public static final long SIX_HOURS = 6L * 60L * 60L * 1000L;

	public static final long TWELVE_HOURS = 12L * 60L * 60L * 1000L;

	public static final long TEN_HOURS = 10L * 60L * 60L * 1000L;

	public static final long SIX_MONTHS = 6L * 30L * 24L * 60L * 60L * 1000L;

	public static final long SIXTEEN_HOURS = 16L * 60L * 60L * 1000L;

	public static final long TEN_MINS = 10 * 60 * 1000L;

	public static final long TEN_SECS = 10 * 1000L;

	public static final long THIRTY_MINS = 30 * 60 * 1000L;

	public static final long THIRTY_SECS = 30 * 1000L;

	public static final long THREE_HOURS = 3L * 60L * 60L * 1000L;

	public static final long THREE_MONTHS = 3L * 30L * 24L * 60L * 60L * 1000L;

	public static final long TWENTY_MINS = 20L * 60L * 1000L;

	public static final long TWENTY_SECS = 20 * 1000L;

	public static final long TWO_DAYS = 48L * 60L * 60L * 1000L;

	public static final long TWO_HOURS = 2L * 60L * 60L * 1000L;

	public static final long TWO_MINS = 2 * 60 * 1000L;

	public static final int ANDROID = 1;

	public static final int IOS = 2;
}
