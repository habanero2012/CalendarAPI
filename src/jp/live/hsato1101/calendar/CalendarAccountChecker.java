package jp.live.hsato1101.calendar;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;

public abstract class CalendarAccountChecker {

	public abstract String getCalendarColumnAccountName();
	public abstract String getCalendarColumnAccountType();
	
	private final ContentResolver mResolver;
	private final CalendarContentURIs mURIs;
	
	public CalendarAccountChecker(Context context, CalendarContentURIs uris) {
		mResolver = context.getContentResolver();
		mURIs = uris;
	}
	
	public CalendarInfo getGoogleSyncAccount() {
		Cursor c = mResolver.query(mURIs.getCalendarUri(), null, null, null, BaseColumns._ID + " ASC");
		if (c.moveToFirst()) {
			int idColumn = c.getColumnIndex(BaseColumns._ID);
			int accountNameColumn = c.getColumnIndex(getCalendarColumnAccountName());
			int accountTypeColumn = c.getColumnIndex(getCalendarColumnAccountType());
			
			do {
				CalendarInfo info = new CalendarInfo(c.getInt(idColumn), 
						c.getString(accountNameColumn), 
						c.getString(accountTypeColumn));
				if(info.isGoogleSyncAccount()) {
					return info;
				}
			} while(c.moveToNext());
		}
		return null;
	}
}
