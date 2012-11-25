package jp.live.hsato1101.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class GoogleCalendar {
	
	private final ContentResolver mResolver;
	private final EventColumns mEventColumns;
	private final CalendarContentURIs mURIs;
	private final int mCalendarId;

	public GoogleCalendar(Context context, EventColumns eventColumns, CalendarContentURIs uris, int calendar_id) {
		mResolver = context.getContentResolver();
		mEventColumns = eventColumns;
		mURIs = uris;
		mCalendarId = calendar_id;
	}

	public Uri insert(Event e) {
		ContentValues values = mEventColumns.values(e, mCalendarId);
		return mResolver.insert(mURIs.getEventUri(), values);
	}

	public int update(Event e) {
    	ContentValues values = mEventColumns.values(e);
    	return mResolver.update(mURIs.toUri(e), values, null, null);
	}

	public int delete(Event e) {
		return mResolver.delete(mURIs.toUri(e), null, null);
	}
	
	public Event[] select(Calendar start) {
		String[] selection = {"" + start.getTimeInMillis()};
		Cursor c = mResolver.query(mURIs.getEventUri(), null, mEventColumns.getDTStart() + ">= ?", selection, mEventColumns.getDTStart() + " ASC");
		Event[] events = fetchEvents(c, BaseColumns._ID);
		c.close();
		return events;
	}

	public Event[] selectInstance(Calendar start, Calendar end) {
		Uri uri = mURIs.buildByDayUri(start, end);
		Cursor c = mResolver.query(uri, null, null, null, mEventColumns.getSortOrder());
		Event[] events = fetchEvents(c, mEventColumns.getEventId());
		c.close();
		return events;
	}
	
	private Event[] fetchEvents(Cursor c, String eventIdColumnName) {
		ArrayList<Event> result = new ArrayList<Event>();

		if (c.moveToFirst()) {
			int eventIdColumn = c.getColumnIndex(eventIdColumnName);
			int titleColumn = c.getColumnIndex(mEventColumns.getTitle());
			int descColumn = c.getColumnIndex(mEventColumns.getDescription());
			int eventLocationColumn = c
					.getColumnIndex(mEventColumns.getEventLocation());
			int dtStartColumn = c.getColumnIndex(mEventColumns.getDTStart());
			int dtEndColumn = c.getColumnIndex(mEventColumns.getDTEnd());
			//int calendarIdColumn = c.getColumnIndex(mEventColumns.getCalendarId());
			int allDayColumn = c.getColumnIndex(mEventColumns.getAllDay());
			int lastDateColumn = c.getColumnIndex(mEventColumns.getLastDate());
			
			do {
				Calendar startTime = new GregorianCalendar();
				startTime.setTimeInMillis(c.getLong(dtStartColumn));
				Calendar endTime = new GregorianCalendar();
				endTime.setTimeInMillis(c.getLong(dtEndColumn));
				
				Calendar lastDate = new GregorianCalendar();
				lastDate.setTimeInMillis(c.getLong(lastDateColumn));
				
				Event s = new Event(c.getLong(eventIdColumn),
						c.getString(titleColumn), c.getString(descColumn),
						c.getString(eventLocationColumn), startTime, endTime,
						c.getInt(allDayColumn));
				s.setLastDate(lastDate);
				result.add(s);
			} while (c.moveToNext());
		}
		return result.toArray(new Event[0]);
	}
}
