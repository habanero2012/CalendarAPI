package jp.live.hsato1101.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import jp.live.hsato1101.calendar.v2.ContentURIsV2_1;
import jp.live.hsato1101.calendar.v2.EventColumnsV2_1;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class GoogleCalendar {
	private static final int NULL_CALENDAR_ID = -9999;
	
	private final ContentResolver mResolver;
	private final EventColumns mEventColumns;
	private final CalendarContentURIs mURIs;
	
	private  int mCalendarId = NULL_CALENDAR_ID; // mCalendarIdを直接使わずgetCalendarIdを呼ぶこと

	public GoogleCalendar(Context context, EventColumns eventColumns, CalendarContentURIs uris) {
		mResolver = context.getContentResolver();
		mEventColumns = eventColumns;
		mURIs = uris;
	}

	public boolean insert(Event e) {
		ContentValues values = mEventColumns.values(e, getCalendarId());
		mResolver.insert(mURIs.getEventUri(), values);
		return true;
	}

	public int update(Event e) {
    	ContentValues values = mEventColumns.values(e);
    	return mResolver.update(mURIs.toUri(e), values, null, null);
	}
	

	public int delete(Event e) {
		return mResolver.delete(mURIs.toUri(e), null, null);
	}

	public Event[] select(Calendar start, Calendar end) {
		Uri uri = mURIs.buildByDayUri(start, end);
		Cursor c = mResolver.query(uri, mEventColumns.getProjection(), null, null, mEventColumns.getSortOrder());
		ArrayList<Event> result = new ArrayList<Event>();

		if (c.moveToFirst()) {
			int titleColumn = c.getColumnIndex(mEventColumns.getTitle());
			int descColumn = c.getColumnIndex(mEventColumns.getDescription());
			int eventLocationColumn = c
					.getColumnIndex(mEventColumns.getEventLocation());
			int beginColumn = c.getColumnIndex(mEventColumns.getBegin());
			int endColumn = c.getColumnIndex(mEventColumns.getEnd());
			int eventIdColumn = c.getColumnIndex(mEventColumns.getEventId());
			
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
	
	private int getCalendarId() {
		if(mCalendarId != NULL_CALENDAR_ID) {
			return mCalendarId;
		}
		
		Cursor c = mResolver.query(mURIs.getCalendarUri(), null, null, null, null);
		if (c.moveToFirst()) {
			int idColumn = c.getColumnIndex(BaseColumns._ID);
			mCalendarId = c.getInt(idColumn);
		}
		c.close();
		return mCalendarId;
	}
}
