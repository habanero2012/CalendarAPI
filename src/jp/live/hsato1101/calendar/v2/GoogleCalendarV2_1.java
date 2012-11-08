package jp.live.hsato1101.calendar.v2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.format.Time;
import android.util.Log;
import jp.live.hsato1101.calendar.Event;
import jp.live.hsato1101.calendar.GoogleCalendar;

public class GoogleCalendarV2_1 implements GoogleCalendar {

	private final static String AUTHORITY = "calendar";
	private final static Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
			+ "/events");
	private final static Uri CONTENT_BY_DAY_URI = Uri.parse("content://"
			+ AUTHORITY + "/instances/whenbyday");
	
	private final static Uri CALENDAR_URI = Uri.parse("content://" + AUTHORITY
			+ "/calendars");



	private final ContentResolver mResolver;
	private final int mCalendarId;

	public GoogleCalendarV2_1(Context context) {
		mResolver = context.getContentResolver();
		mCalendarId = getCalendarId();
	}

	public boolean insert(Event e) {
		ContentValues values = new ContentValues();
		values.put(EventColumns.DTSTART, e.getStartTimeInMillis());
		values.put(EventColumns.DTEND, e.getEndTimeInMillis());
		values.put(EventColumns.TITLE, e.getTitle());
		values.put(EventColumns.DESCRIPTION, e.getDescription());
		values.put(EventColumns.EVENT_LOCATION, e.getEventLocation());
		values.put(EventColumns.EVENT_TIMEZONE, "Asia/Tokyo");
		values.put(EventColumns.CALENDAR_ID, mCalendarId);
		Uri res = mResolver.insert(CONTENT_URI, values);
		Log.i("testa", "insert success:" + res.toString());
		return true;
	}

	public int update(Event e) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delete(Event e) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Event[] select(Calendar start, Calendar end) {
		Uri uri = buildQueryUri(toJulianDay(start), toJulianDay(end));
		Cursor c = mResolver.query(uri, EventColumns.PROJECTION, null, null, EventColumns.SORT_ORDER);
		ArrayList<Event> result = new ArrayList<Event>();

		if (c.moveToFirst()) {
			int titleColumn = c.getColumnIndex(EventColumns.TITLE);
			int descColumn = c.getColumnIndex(EventColumns.DESCRIPTION);
			int eventLocationColumn = c
					.getColumnIndex(EventColumns.EVENT_LOCATION);
			int beginColumn = c.getColumnIndex(EventColumns.BEGIN);
			int endColumn = c.getColumnIndex(EventColumns.END);
			int eventIdColumn = c.getColumnIndex(EventColumns.EVENT_ID);
			
			do {
				Calendar startTime = new GregorianCalendar();
				startTime.setTimeInMillis(c.getLong(beginColumn));
				Calendar endTime = new GregorianCalendar();
				endTime.setTimeInMillis(c.getLong(endColumn));
				
				Event s = new Event(c.getLong(eventIdColumn),
						c.getString(titleColumn), c.getString(descColumn),
						c.getString(eventLocationColumn), startTime, endTime);
				result.add(s);
			} while (c.moveToNext());
		}

		c.close();
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
		return Uri.withAppendedPath(CONTENT_BY_DAY_URI, path.toString());
	}
	
	private int getCalendarId() {
		int calendarId = 1;
		Cursor c = mResolver.query(CALENDAR_URI, null, null, null, null);
		if (c.moveToFirst()) {
			int idColumn = c.getColumnIndex(CalendarColumns._ID);
			calendarId = c.getInt(idColumn);
		}
		c.close();
		return calendarId;
	}

}
