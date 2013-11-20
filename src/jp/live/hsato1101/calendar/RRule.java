package jp.live.hsato1101.calendar;

import java.util.Calendar;

/**
 * 毎日、毎週、毎年などのルールを作成する
 * @author user
 *
 */
public class RRule {

	/**
	 * 毎日
	 */
	public static final String DAILY = "FREQ=DAILY;WKST=SU";
	
	/**
	 * 平日
	 */
	public static final String WEEKLY = "FREQ=WEEKLY;WKST=SU;BYDAY=MO,TU,WE,TH,FR";
	
	/**
	 * 毎週のフォーマット
	 * 
	 */
	public static final String EVERY_WEEK_FORMAT = "FREQ=WEEKLY;WKST=SU;BYDAY=%s";
	
	/**
	 * 毎月第何曜日のフォーマット
	 */
	public static final String MONTHLY_FORMAT = "FREQ=MONTHLY;WKST=SU;BYDAY=%s%s";
	
	/**
	 * 毎月何日のフォーマット
	 */
	public static final String BY_MONTHDAY_FORMAT = "FREQ=MONTHLY;WKST=SU;BYMONTHDAY=%s";
	
	/**
	 * 毎年
	 */
	public static final String YEAR = "FREQ=YEARLY;WKST=SU";
	
	/**
	 * duration
	 */
	public static final String DURATION = "P3600S";
	
	private static String getBYDAYStr(Calendar cal) {
		switch (cal.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.SUNDAY: return "SU";
		case Calendar.MONDAY: return "MO";
		case Calendar.TUESDAY: return "TU";
		case Calendar.WEDNESDAY: return "WE";
		case Calendar.THURSDAY: return "TH";
		case Calendar.FRIDAY: return "FR";
		case Calendar.SATURDAY: return "SA";
		}
		return "";
	}
	
	private static String getEveryWeek(Calendar cal) {
		return String.format(EVERY_WEEK_FORMAT, getBYDAYStr(cal));
	}
	
	/**
	 * RRuleを「毎日」にする
	 * @param e
	 */
	public static void setRRuleDaily(Event e) {
		e.setRRule(DAILY);
		e.setDuration(getDuration(e.getStart(), e.getEnd()));
	}
	
	/**
	 * RRuleを「平日」にする
	 * @param e
	 */
	public static void setRRuleWeekly(Event e) {
		e.setRRule(WEEKLY);
		e.setDuration(getDuration(e.getStart(), e.getEnd()));
	}
	
	/**
	 * RRuleを「毎週」にする
	 * @param e
	 */
	public static void setRRuleEveryWeek(Event e) {
		e.setRRule(getEveryWeek(e.getStart()));
		e.setDuration(getDuration(e.getStart(), e.getEnd()));
	}
	
	/**
	 * RRuleを「毎月」（第１水曜日など）にする
	 * @param e
	 */
	public static void setRRuleMonthly(Event e) {
		Calendar start = e.getStart();
		String weekStr = getBYDAYStr(start);
		int weekOfMonth = calcWeekOfMonth(start);
		e.setRRule(String.format(MONTHLY_FORMAT, weekOfMonth, weekStr));
		e.setDuration(getDuration(e.getStart(), e.getEnd()));
	}
	
	/**
	 * 第何週目か計算する
	 * @param cal
	 * @return
	 */
	public static int calcWeekOfMonth(Calendar cal) {
		int weekOfMonth = cal.get(Calendar.WEEK_OF_MONTH);
		Calendar tmp = (Calendar)cal.clone();
		tmp.add(Calendar.DATE, -7*(weekOfMonth-1));
		if(tmp.get(Calendar.MONTH) != cal.get(Calendar.MONTH)) {
			return weekOfMonth -1;
		}
		return weekOfMonth;
	}
	
	/**
	 * RRuleを「毎月」（毎月21日など）にする
	 */
	public static void setRRuleByMonthDay(Event e) {
		Calendar cal = e.getStart();
		e.setRRule(String.format(BY_MONTHDAY_FORMAT, cal.get(Calendar.DATE)));
		e.setDuration(getDuration(e.getStart(), e.getEnd()));
	}
	
	/**
	 * RRuleを「毎年」にする
	 * @param e
	 */
	public static void setRRuleYearly(Event e) {
		e.setRRule(YEAR);
		e.setDuration(getDuration(e.getStart(), e.getEnd()));
	}
	
	public static String getDuration(Calendar start, Calendar end) {
		long diff = (end.getTimeInMillis() - start.getTimeInMillis())/1000;
		return "P" + diff + "S";
	}
}
