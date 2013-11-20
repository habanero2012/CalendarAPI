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
	public abstract String getRRule();
	public abstract String getRDate();
	public abstract String getDuration();
	
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
		values.put(getRRule(), e.getRRule());
		values.put(getDuration(), e.getDuration());
		if(e.getRRule() != null && !e.getRRule().equals("")) {
			//values.remove(getDTEnd()); // rruleを設定するときはdtendを設定しない
			values.put(getDTEnd(), "");// durationを設定するときはdtendを設定しない
		}
		
		if(e.getDuration() != null  && !e.getDuration().equals("")) {
			//values.remove(getDTEnd()); // durationを設定するときはdtendを設定しない
			values.put(getDTEnd(), "");// durationを設定するときはdtendを設定しない
		}
		
		return values;
	}
	
	public ContentValues values(Event e, int calendar_id) {
		ContentValues values = values(e);
    	values.put(getCalendarId(), calendar_id);
    	return values;
	}
}
