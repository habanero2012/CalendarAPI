package jp.live.hsato1101.calendar.v2;

import android.provider.BaseColumns;

public interface EventColumns extends BaseColumns {
	public static final String TITLE = "title";
	public static final String DESCRIPTION = "description";
	public static final String EVENT_LOCATION = "eventLocation";
	public static final String BEGIN = "begin";
	public static final String END = "end";
	public static final String EVENT_ID = "event_id";
	public static final String DTSTART = "dtstart";
	public static final String DTEND = "dtend";
	public static final String EVENT_TIMEZONE = "eventTimezone";
	public static final String CALENDAR_ID = "calendar_id";

	public static final String[] PROJECTION = { _ID, TITLE, DESCRIPTION,
			EVENT_LOCATION, BEGIN, END, EVENT_ID};
	public static final String SORT_ORDER = BEGIN + " ASC, " + END + " DESC, "
			+ TITLE + " ASC";
}
