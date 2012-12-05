package jp.live.hsato1101.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.provider.BaseColumns;

/**
 * 
 * Eventのselectを補助するクラス
 *
 */
public class EventSelector {

	private static long NULL_VALUE = -1;
	
	private EventColumns mColumnsName;
	private long mEventId;
	private long mCalendarId;
	private long mStartFromTimeMillis;
	private long mStartToTimeMillis;
	
	
	public EventSelector() {
		mColumnsName = GoogleCalendarFactory.getEventColumns();
		mEventId = NULL_VALUE;
		mCalendarId = NULL_VALUE;
		mStartFromTimeMillis = NULL_VALUE;
		mStartToTimeMillis = NULL_VALUE;
	}
	
	public EventSelector id(long eventId) {
		mEventId = eventId;
		return this;
	}
	
	public EventSelector calendarId(long calendarId) {
		mCalendarId = calendarId;
		return this;
	}
	
	public EventSelector startFrom(Calendar c) {
		mStartFromTimeMillis = c.getTimeInMillis();
		return this;
	}
	
	public EventSelector startTo(Calendar c) {
		mStartToTimeMillis = c.getTimeInMillis();
		return this;
	}
	
	public Event[] select(GoogleCalendar calendar) {
		List<String> selection = new ArrayList<String>();
		List<String> selectionArgs = new ArrayList<String>();
		if(mEventId != NULL_VALUE) {
			selection.add(BaseColumns._ID + " = ?");
			selectionArgs.add(String.valueOf(mEventId));
		}
		
		if(mCalendarId != NULL_VALUE) {
			selection.add(mColumnsName.getCalendarId() + " = ?");
			selectionArgs.add(String.valueOf(mCalendarId));
		}
		
		if(mStartFromTimeMillis != NULL_VALUE) {
			selection.add(mColumnsName.getDTStart() + " >= ?");
			selectionArgs.add(String.valueOf(mStartFromTimeMillis));
		}
		
		if(mStartToTimeMillis != NULL_VALUE) {
			selection.add(mColumnsName.getDTStart() + " <= ?");
			selectionArgs.add(String.valueOf(mStartToTimeMillis));
		}
		
		return calendar.select(null, join(selection, " AND "), selectionArgs.toArray(new String[]{}), null);
	}
	
	/**
	 * リストの文字列をsepで結合した文字列を作る
	 * 
	 * @param list
	 * @param sep
	 * @return
	 */
	public static String join(List<String> list, String sep) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<list.size(); i++) {
			sb.append(list.get(i));
			if(i != list.size()-1) {
				sb.append(sep);
			}
		}
		return sb.toString();
	}
}
