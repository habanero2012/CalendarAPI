package jp.live.hsato1101.calendar.v2_1;

import android.content.Context;
import jp.live.hsato1101.calendar.CalendarAccountChecker;
import jp.live.hsato1101.calendar.CalendarContentURIs;

public class CalendarAccountCheckerV2 extends CalendarAccountChecker{

	public CalendarAccountCheckerV2(Context context, CalendarContentURIs uris) {
		super(context, uris);
	}

	@Override
	public String getCalendarColumnAccountName() {
		return "_sync_account";
	}

	@Override
	public String getCalendarColumnAccountType() {
		return "_sync_account_type";
	}

	@Override
	public String getCalendarColumnAccessLevel() {
		return "access_level";
	}

	@Override
	public String getCalendarColumnSyncEvents() {
		return "sync_events";
	}

}
