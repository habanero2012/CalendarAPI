package jp.live.hsato1101.calendar.v4;

import android.content.Context;
import android.provider.CalendarContract;
import jp.live.hsato1101.calendar.CalendarAccountChecker;
import jp.live.hsato1101.calendar.CalendarContentURIs;

public class CalendarAccountCheckerV4 extends CalendarAccountChecker {

	public CalendarAccountCheckerV4(Context context, CalendarContentURIs uris) {
		super(context, uris);
	}

	@Override
	public String getCalendarColumnAccountName() {
		return CalendarContract.Calendars.ACCOUNT_NAME;
	}

	@Override
	public String getCalendarColumnAccountType() {
		return CalendarContract.Calendars.ACCOUNT_TYPE;
	}

	@Override
	public String getCalendarColumnAccessLevel() {
		return CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL;
	}

	@Override
	public String getCalendarColumnSyncEvents() {
		return CalendarContract.Calendars.SYNC_EVENTS;
	}

}
