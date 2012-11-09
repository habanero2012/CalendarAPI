package jp.live.hsato1101.calendar.v4;

import android.provider.CalendarContract.Events;
import android.provider.CalendarContract.Instances;
import jp.live.hsato1101.calendar.EventColumns;

public class EventColumnsV4 extends EventColumns {

	private static final String[] PROJECTION = { Instances._ID,
			Instances.TITLE, Instances.DESCRIPTION, Instances.EVENT_LOCATION,
			Instances.BEGIN, Instances.END, Instances.EVENT_ID };
	private static final String SORT_ORDER = Instances.BEGIN + " ASC, "
			+ Instances.END + " DESC, " + Instances.TITLE + " ASC";

	@Override
	public String getTitle() {
		return Instances.TITLE;
	}

	@Override
	public String getDescription() {
		return Instances.DESCRIPTION;
	}

	@Override
	public String getEventLocation() {
		return Instances.EVENT_LOCATION;
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
	public String[] getProjection() {
		return PROJECTION;
	}

	@Override
	public String getSortOrder() {
		return SORT_ORDER;
	}

}
