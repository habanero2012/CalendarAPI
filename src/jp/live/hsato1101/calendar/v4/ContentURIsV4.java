package jp.live.hsato1101.calendar.v4;

import android.annotation.TargetApi;
import android.net.Uri;
import android.provider.CalendarContract;
import jp.live.hsato1101.calendar.CalendarContentURIs;

@TargetApi(14)
public class ContentURIsV4 extends CalendarContentURIs {

	@Override
	public Uri getEventUri() {
		return CalendarContract.Events.CONTENT_URI;
	}

	@Override
	public Uri getCalendarUri() {
		return CalendarContract.Calendars.CONTENT_URI;
	}

	@Override
	public Uri getEventByDayUri() {
		return CalendarContract.Instances.CONTENT_BY_DAY_URI;
	}

}
