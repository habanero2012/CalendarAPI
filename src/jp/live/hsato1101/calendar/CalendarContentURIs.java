package jp.live.hsato1101.calendar;

import java.util.Calendar;

import android.net.Uri;
import android.text.format.Time;

public abstract class CalendarContentURIs {
	public abstract Uri getEventUri();
	public abstract Uri getCalendarUri();
	public abstract Uri getEventByDayUri();
	
	public Uri toUri(Event e) {
		return Uri.withAppendedPath(getEventUri(), String.valueOf(e.getId()));
	}
	
	public Uri buildByDayUri(Calendar start, Calendar end) {
		StringBuilder path = new StringBuilder();
		path.append(toJulianDay(start));
		path.append("/");
		path.append(toJulianDay(end));
		return Uri.withAppendedPath(getEventByDayUri(), path.toString());
	}
	
	private int toJulianDay(Calendar cal) {
		return Time.getJulianDay(cal.getTimeInMillis(), 0);
	}
}
