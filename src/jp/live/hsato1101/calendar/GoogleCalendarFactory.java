package jp.live.hsato1101.calendar;

import jp.live.hsato1101.calendar.v4.GoogleCalendarV4;
import android.content.Context;

public class GoogleCalendarFactory {
	
	public static GoogleCalendar getInstance(Context context) {
		return new GoogleCalendarV4(context);
	}
}
