package jp.live.hsato1101.calendar.v4;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import jp.live.hsato1101.calendar.Event;
import jp.live.hsato1101.calendar.GoogleCalendar;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Instances;
import android.text.format.Time;

@TargetApi(14)
public class GoogleCalendarV4 extends GoogleCalendar {

	private static final String[] PROJECTION = {
			Instances._ID,
			Instances.TITLE,
			Instances.DESCRIPTION,
			Instances.EVENT_LOCATION,
			Instances.BEGIN,
			Instances.END,
			Instances.EVENT_ID
			};
	private static final String SORT_ORDER = Instances.BEGIN + " ASC, " + 
			Instances.END + " DESC, " + 
			Instances.TITLE + " ASC";
	
	private final ContentResolver mResolver;
	private final int mCalendarId;
	
	public GoogleCalendarV4(Context context) {
		super(context, new EventColumnsV4(), new ContentURIsV4());
		mResolver = context.getContentResolver();
		mCalendarId = getCalendarId();
	}

	public boolean insert(Event e) {
    	ContentValues values = new ContentValues();
    	values.put(CalendarContract.Events.DTSTART, e.getStartTimeInMillis());
    	values.put(CalendarContract.Events.DTEND, e.getEndTimeInMillis());
    	values.put(CalendarContract.Events.TITLE, e.getTitle());
    	values.put(CalendarContract.Events.DESCRIPTION, e.getDescription());
    	values.put(CalendarContract.Events.EVENT_LOCATION, e.getEventLocation());
    	values.put(CalendarContract.Events.EVENT_TIMEZONE, "Asia/Tokyo");
    	values.put(CalendarContract.Events.CALENDAR_ID, mCalendarId);
    	mResolver.insert(CalendarContract.Events.CONTENT_URI, values);
		return true;
	}
	
	public int update(Event e) {
    	ContentValues values = new ContentValues();
    	values.put(CalendarContract.Events.DTSTART, e.getStartTimeInMillis());
    	values.put(CalendarContract.Events.DTEND, e.getEndTimeInMillis());
    	values.put(CalendarContract.Events.TITLE, e.getTitle());
    	values.put(CalendarContract.Events.DESCRIPTION, e.getDescription());
    	values.put(CalendarContract.Events.EVENT_TIMEZONE, "Asia/Tokyo");
    	return mResolver.update(toUri(e), values, null, null);
	}

	public int delete(Event e) {
		return mResolver.delete(toUri(e), null, null);
	}
	
	private Uri toUri(Event e) {
		return ContentUris.withAppendedId(CalendarContract.Events.CONTENT_URI, e.getId());
	}

	public Event[] select(Calendar start, Calendar end) {
		Uri uri = buildQueryUri(toJulianDay(start), toJulianDay(end));
		Cursor c = mResolver.query(uri, PROJECTION, null, null, SORT_ORDER);
		ArrayList<Event> result = new ArrayList<Event>();
		
		if(c.moveToFirst()) {
		    int titleColumn = c.getColumnIndex(Instances.TITLE);
		    int descColumn = c.getColumnIndex(Instances.DESCRIPTION);
		    int eventLocationColumn = c.getColumnIndex(Instances.EVENT_LOCATION);
		    int beginColumn = c.getColumnIndex(Instances.BEGIN);
		    int endColumn = c.getColumnIndex(Instances.END);
		    int eventIdColumn = c.getColumnIndex(Instances.EVENT_ID);
		    do {
		    	Calendar startTime = new GregorianCalendar();
		    	startTime.setTimeInMillis(c.getLong(beginColumn));
		    	Calendar endTime = new GregorianCalendar();
		    	endTime.setTimeInMillis(c.getLong(endColumn));
		    	
		    	Event s = new Event(
		    			c.getLong(eventIdColumn),
		    			c.getString(titleColumn),
		    			c.getString(descColumn),
		    			c.getString(eventLocationColumn),
		    			startTime, 
		    			endTime);
		    	result.add(s);
		    } while(c.moveToNext());
		}
		
		c.close();
		getCalendarId();
		return result.toArray(new Event[0]);
	}
	
	private int toJulianDay(Calendar cal) {
		return Time.getJulianDay(cal.getTimeInMillis(), 0);
	}
	
	private Uri buildQueryUri(int start, int end) {
		StringBuilder path = new StringBuilder();
		path.append(start);
		path.append("/");
		path.append(end);
		return Uri.withAppendedPath(CalendarContract.Instances.CONTENT_BY_DAY_URI, path.toString());
	}
	
	private int getCalendarId() {
		int calendarId = 1;
		Cursor c = mResolver.query(CalendarContract.Calendars.CONTENT_URI, null, null, null, CalendarContract.Calendars._ID + " ASC");
		if(c.moveToFirst()) {
			int idColumn = c.getColumnIndex(CalendarContract.Calendars._ID);
			calendarId = c.getInt(idColumn); // 最初の一つ目をカレンダーIDとして使用する
		}
		c.close();
		return calendarId;
	}

}
