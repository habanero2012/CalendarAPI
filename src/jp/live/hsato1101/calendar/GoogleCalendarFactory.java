package jp.live.hsato1101.calendar;

import jp.live.hsato1101.calendar.v2.GoogleCalendarV2_1;
import jp.live.hsato1101.calendar.v2.GoogleCalendarV2_2;
import jp.live.hsato1101.calendar.v4.GoogleCalendarV4;
import android.content.Context;
import android.os.Build;

public class GoogleCalendarFactory {
	
	private static final int ICE_CREAM_SANDWICH = 14;
	private static final int FROYO = 8;
	
	public static GoogleCalendar getInstance(Context context) {
		if(Build.VERSION.SDK_INT >= ICE_CREAM_SANDWICH) {
			return new GoogleCalendarV4(context);
		} else if(Build.VERSION.SDK_INT >= FROYO){
			return new GoogleCalendarV2_2(context);
		} else {
			return new GoogleCalendarV2_1(context);
		}
	}
}
