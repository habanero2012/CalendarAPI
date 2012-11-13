package jp.live.hsato1101.calendar;

import android.content.ContentValues;

public abstract class EventColumns {
	
	public abstract String getTitle();
	public abstract String getDescription();
	public abstract String getEventLocation();
	public abstract String getBegin();
	public abstract String getEnd();
	public abstract String getDTStart();
	public abstract String getDTEnd();
	public abstract String getEventTimezone();
	public abstract String getCalendarId();
	public abstract String getEventId();
	public abstract String getAllDay();
	public abstract String getLastDate();
	
	public abstract String getSortOrder();
	
	public ContentValues values(Event e) {
    	ContentValues values = new ContentValues();
    	values.put(getDTStart(), e.getStartTimeInMillis());
    	values.put(getDTEnd(), e.getEndTimeInMillis());
    	values.put(getTitle(), e.getTitle());
    	values.put(getDescription(), e.getDescription());
    	values.put(getEventLocation(), e.getEventLocation());
    	values.put(getEventTimezone(), "Asia/Tokyo");
    	values.put(getAllDay(), e.getAllDay());
    	return values;
	}
	
	public ContentValues values(Event e, int calendar_id) {
		ContentValues values = values(e);
    	values.put(getCalendarId(), calendar_id);
    	return values;
	}
}
