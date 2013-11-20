package jp.live.hsato1101.calendar.v2_1;

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
	public static final String ALL_DAY = "allDay";
	public static final String LAST_DATE = "lastDate";
	public static final String RRULE = "rrule";
	public static final String RDATE = "rdate";
	public static final String DURATION = "duration";

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
	public String getSortOrder() {
		return SORT_ORDER;
	}

	@Override
	public String getEventId() {
		return EVENT_ID;
	}

	@Override
	public String getAllDay() {
		return ALL_DAY;
	}

	@Override
	public String getLastDate() {
		return LAST_DATE;
	}

	@Override
	public String getRRule() {
		return RRULE;
	}

	@Override
	public String getRDate() {
		return RDATE;
	}

	@Override
	public String getDuration() {
		return DURATION;
	}

}
