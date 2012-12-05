package jp.live.hsato1101.calendar;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.util.Log;

public abstract class CalendarAccountChecker {

	public abstract String getCalendarColumnAccountName();
	public abstract String getCalendarColumnAccountType();
	public abstract String getCalendarColumnAccessLevel();
	public abstract String getCalendarColumnSyncEvents();
	
	private final ContentResolver mResolver;
	private final CalendarContentURIs mURIs;
	
	public CalendarAccountChecker(Context context, CalendarContentURIs uris) {
		mResolver = context.getContentResolver();
		mURIs = uris;
	}
	
	/**
	 * 同期可能なCalendar情報のリストを取得する
	 * 
	 * @return
	 */
	public List<CalendarInfo> getGoogleSyncAccount() {
		List<CalendarInfo> list = new ArrayList<CalendarInfo>();
		Cursor c = mResolver.query(mURIs.getCalendarUri(), null, null, null, BaseColumns._ID + " ASC");
		if(c == null) {
			return list;
		}
		
		try {
			if (c.moveToFirst()) {
				int idColumn = c.getColumnIndex(BaseColumns._ID);
				int accountNameColumn = c.getColumnIndex(getCalendarColumnAccountName());
				int accountTypeColumn = c.getColumnIndex(getCalendarColumnAccountType());
				int accountAccessLevel = c.getColumnIndex(getCalendarColumnAccessLevel());
				int accountSyncEvents = c.getColumnIndex(getCalendarColumnSyncEvents());
				
				do {
					CalendarInfo tmp = new CalendarInfo(c.getInt(idColumn), 
							c.getString(accountNameColumn), 
							c.getString(accountTypeColumn),
							c.getString(accountAccessLevel),
							c.getString(accountSyncEvents));
					Log.i("CalendarInfo", tmp.toString());
					if(tmp.isGoogleSyncAccount()) {
						list.add(tmp);
					}
				} while(c.moveToNext());
			}
		} finally {
			c.close();
		}
		
		
		return list;
	}
}
