package jp.live.hsato1101.calendar.v2_1;

import android.provider.BaseColumns;
import jp.live.hsato1101.calendar.EventColumns;

public class EventColumnsV2_1 extends EventColumns {

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

	public static final String[] PROJECTION = { BaseColumns._ID, TITLE, DESCRIPTION,
			EVENT_LOCATION, BEGIN, END, EVENT_ID};
	public static final String SORT_ORDER = BEGIN + " ASC, " + END + " DESC, "
			+ TITLE + " ASC";
	
	@Override
	public String getTitle() {
		return TITLE;
	}

	@Override
	public String getDescription() {
		return DESCRIPTION;
	}

	@Override
	public String getEventLocation() {
		return EVENT_LOCATION;
	}

	@Override
	public String getBegin() {
		return BEGIN;
	}

	@Override
	public String getEnd() {
		return END;
	}

	@Override
	public String getDTStart() {
		return DTSTART;
	}

	@Override
	public String getDTEnd() {
		return DTEND;
	}

	@Override
	public String getEventTimezone() {
		return EVENT_TIMEZONE;
	}

	@Override
	public String getCalendarId() {
		return CALENDAR_ID;
	}

	@Override
	public String[] getProjection() {
		return PROJECTION;
	}

	@Override
	public String getSortOrder() {
		return SORT_ORDER;
	}

	@Override
	public String getEventId() {
		return EVENT_ID;
	}

}
