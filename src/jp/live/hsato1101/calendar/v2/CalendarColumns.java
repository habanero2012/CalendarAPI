package jp.live.hsato1101.calendar.v2;

import android.provider.BaseColumns;

public interface CalendarColumns extends BaseColumns{
	public static final String ACCESS_LEVEL = "access_level";
	public static final String SYNC_EVENTS = "sync_events";
	public static final String NAME = "name";
	public static final String DISPLAY_NAME = "displayName";
	public static final String HIDDEN = "hidden";
	public static final String OWNER_ACCOUNT = "ownerAccount";
	
	public static final String[] PROJECTION = {
		_ID,
		ACCESS_LEVEL,
		SYNC_EVENTS,
		NAME,
		DISPLAY_NAME,
		HIDDEN,
		OWNER_ACCOUNT
	};
}
