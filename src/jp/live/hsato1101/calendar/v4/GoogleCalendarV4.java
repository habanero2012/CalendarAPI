package jp.live.hsato1101.calendar.v4;

import java.util.ArrayList;
import java.util.Calendar;

import jp.live.hsato1101.calendar.GoogleCalendar;
import jp.live.hsato1101.calendar.Schedule;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Instances;
import android.text.format.Time;

@TargetApi(14)
public class GoogleCalendarV4 implements GoogleCalendar {

	private static final Uri SELECT_URI = CalendarContract.Instances.CONTENT_BY_DAY_URI;
	private static final String[] PROJECTION = {
			Instances._ID,
			Instances.TITLE,
			Instances.DESCRIPTION,
			Instances.EVENT_LOCATION,
			Instances.BEGIN,
			Instances.END
			};
	private static final String SORT_ORDER = Instances.BEGIN + " ASC, " + 
			Instances.END + " DESC, " + 
			Instances.TITLE + " ASC";
	
	private final ContentResolver mResolver;
	
	public GoogleCalendarV4(Context context) {
		mResolver = context.getContentResolver();
	}

	public boolean insert(Schedule s) {
    	ContentValues values = new ContentValues();
    	values.put(CalendarContract.Events.DTSTART, s.getStartTimeInMillis());
    	values.put(CalendarContract.Events.DTEND, s.getEndTimeInMillis());
    	values.put(CalendarContract.Events.TITLE, s.getTitle());
    	values.put(CalendarContract.Events.DESCRIPTION, s.getDescription());
    	values.put(CalendarContract.Events.EVENT_TIMEZONE, "Asia/Tokyo");
    	values.put(CalendarContract.Events.CALENDAR_ID, 1);
    	mResolver.insert(CalendarContract.Events.CONTENT_URI, values);
		return true;
	}

	public boolean update(Schedule schedule) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean delete(Schedule schedule) {
		// TODO Auto-generated method stub
		return false;
	}

	public Schedule[] select(Calendar start, Calendar end) {
		Uri uri = buildQueryUri(toJulianDay(start), toJulianDay(end));
		Cursor c = mResolver.query(uri, PROJECTION, null, null, SORT_ORDER);
		ArrayList<Schedule> result = new ArrayList<Schedule>();
		
		if(c.moveToFirst()) {
		    int idColumn = c.getColumnIndex(Instances._ID);
		    int titleColumn = c.getColumnIndex(Instances.TITLE);
		    int descColumn = c.getColumnIndex(Instances.DESCRIPTION);
		    int eventLocationColumn = c.getColumnIndex(Instances.EVENT_LOCATION);
		    do {
		    	Schedule s = new Schedule(
		    			c.getInt(idColumn),
		    			c.getString(titleColumn),
		    			c.getString(descColumn),
		    			c.getString(eventLocationColumn),
		    			null, 
		    			null,
		    			"");
		    	result.add(s);
		    } while(c.moveToNext());
		}


		return result.toArray(new Schedule[0]);
	}
	
	private int toJulianDay(Calendar cal) {
		return Time.getJulianDay(cal.getTimeInMillis(), 0);
	}
	
	private Uri buildQueryUri(int start, int end) {
		StringBuilder path = new StringBuilder();
		path.append(start);
		path.append("/");
		path.append(end);
		return Uri.withAppendedPath(SELECT_URI, path.toString());
	}

}
