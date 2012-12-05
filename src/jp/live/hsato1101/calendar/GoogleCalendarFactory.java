package jp.live.hsato1101.calendar;

import java.util.List;

import jp.live.hsato1101.calendar.v2_1.CalendarAccountCheckerV2;
import jp.live.hsato1101.calendar.v2_1.ContentURIsV2_1;
import jp.live.hsato1101.calendar.v2_1.EventColumnsV2_1;
import jp.live.hsato1101.calendar.v2_2.ContentURIsV2_2;
import jp.live.hsato1101.calendar.v2_2.EventColumnsV2_2;
import jp.live.hsato1101.calendar.v4.CalendarAccountCheckerV4;
import jp.live.hsato1101.calendar.v4.ContentURIsV4;
import jp.live.hsato1101.calendar.v4.EventColumnsV4;
import android.content.Context;
import android.os.Build;

public class GoogleCalendarFactory {
	
	
	private static final int ICE_CREAM_SANDWICH = 14;
	private static final int FROYO = 8;
	
	/**
	 * GoogleCalendarクラスを作成する
	 * 
	 * 使用できるgoogleアカウントが無い場合はnullを返す
	 * 
	 * @param context
	 * @return GoogleCalendar
	 */
	public static GoogleCalendar getInstance(Context context, int calendarId) {
		if(Build.VERSION.SDK_INT >= ICE_CREAM_SANDWICH) {
			return new GoogleCalendar(context, new EventColumnsV4(), new ContentURIsV4(), calendarId);
		} else if(Build.VERSION.SDK_INT >= FROYO){
			return new GoogleCalendar(context, new EventColumnsV2_2(), new ContentURIsV2_2(), calendarId);
		} else {
			return new GoogleCalendar(context, new EventColumnsV2_1(), new ContentURIsV2_1(), calendarId);
		}
	}
	
	/**
	 * 同期可能なCalendarの一覧を取得する
	 * 
	 * @param context
	 * @return
	 */
	public static List<CalendarInfo> getCalendarInfoHasGoogleSyncAccount(Context context) {
		CalendarAccountChecker checker;
		if(Build.VERSION.SDK_INT >= ICE_CREAM_SANDWICH) {
			checker = new CalendarAccountCheckerV4(context, new ContentURIsV4());
		} else if(Build.VERSION.SDK_INT >= FROYO){
			checker = new CalendarAccountCheckerV2(context, new ContentURIsV2_2());
		} else {
			checker = new CalendarAccountCheckerV2(context, new ContentURIsV2_1());
		}

		return checker.getGoogleSyncAccount();
	}
	
	/**
	 * Android Versionごとの適切なEventColumnを取得する
	 * @return
	 */
	public static EventColumns getEventColumns() {
		if(Build.VERSION.SDK_INT >= ICE_CREAM_SANDWICH) {
			return new EventColumnsV4();
		} else if(Build.VERSION.SDK_INT >= FROYO){
			return new EventColumnsV2_2();
		} else {
			return new EventColumnsV2_1();
		}
	}
}
