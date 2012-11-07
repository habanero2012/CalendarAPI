package jp.live.hsato1101.calendar;

import java.util.Calendar;

public interface GoogleCalendar {
	public boolean insert(Schedule schedule);
	public boolean update(Schedule schedule);
	public boolean delete(Schedule schedule);
	public Schedule[] select(Calendar start, Calendar end);
}
