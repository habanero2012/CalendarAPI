package jp.live.hsato1101.calendar;

import jp.live.hsato1101.calendar.v2.ContentURIsV2_1;
import jp.live.hsato1101.calendar.v2.EventColumnsV2_1;
import jp.live.hsato1101.calendar.v4.ContentURIsV4;
import jp.live.hsato1101.calendar.v4.EventColumnsV4;
import android.content.Context;
import android.os.Build;

public class GoogleCalendarFactory {
	
	private static final int ICE_CREAM_SANDWICH = 14;
	private static final int FROYO = 8;
	
	public static GoogleCalendar getInstance(Context context) {
		if(Build.VERSION.SDK_INT >= ICE_CREAM_SANDWICH) {
			return new GoogleCalendar(context, new EventColumnsV4(), new ContentURIsV4());
		} else if(Build.VERSION.SDK_INT >= FROYO){
			return new GoogleCalendar(context, new EventColumnsV2_1(), new ContentURIsV2_1());
		} else {
			return new GoogleCalendar(context, new EventColumnsV2_1(), new ContentURIsV2_1());
		}
	}
}
