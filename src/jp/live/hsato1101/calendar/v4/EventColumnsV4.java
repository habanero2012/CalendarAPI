package jp.live.hsato1101.calendar.v4;

import android.provider.CalendarContract.Events;
import android.provider.CalendarContract.Instances;
import jp.live.hsato1101.calendar.EventColumns;

public class EventColumnsV4 extends EventColumns {

	private static final String SORT_ORDER = Instances.BEGIN + " ASC, "
			+ Instances.END + " DESC, " + Instances.TITLE + " ASC";

	@Override
	public String getTitle() {
		return Events.TITLE;
	}

	@Override
	public String getDescription() {
		return Events.DESCRIPTION;
	}

	@Override
	public String getEventLocation() {
		return Events.EVENT_LOCATION;
	}

	@Override
	public String getBegin() {
		return Instances.BEGIN;
	}

	@Override
	public String getEnd() {
		return Instances.END;
	}

	@Override
	public String getDTStart() {
		return Events.DTSTART;
	}

	@Override
	public String getDTEnd() {
		return Events.DTEND;
	}

	@Override
	public String getEventTimezone() {
		return Events.EVENT_TIMEZONE;
	}

	@Override
	public String getCalendarId() {
		return Events.CALENDAR_ID;
	}

	@Override
	public String getEventId() {
		return Instances.EVENT_ID;
	}

	@Override
	public String getSortOrder() {
		return SORT_ORDER;
	}

	@Override
	public String getAllDay() {
		return Events.ALL_DAY;
	}

}
