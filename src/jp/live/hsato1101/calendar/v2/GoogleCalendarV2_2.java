package jp.live.hsato1101.calendar.v2;

import java.util.Calendar;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;

import jp.live.hsato1101.calendar.Event;
import jp.live.hsato1101.calendar.GoogleCalendar;

public class GoogleCalendarV2_2 implements GoogleCalendar {

	private final static String AUTHORITY = "com.android.calendar";
	private final static Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/events");
	private final static Uri CONTENT_BY_DAY_URI = Uri.parse("content://" + AUTHORITY + "/instances/whenbyday");
	
	private final ContentResolver mResolver;
	
	public GoogleCalendarV2_2(Context context) {
		mResolver = context.getContentResolver();
	}

	public boolean insert(Event e) {
		// TODO Auto-generated method stub
		return false;
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
		// TODO Auto-generated method stub
		return null;
	}

}
