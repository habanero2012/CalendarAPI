package jp.live.hsato1101.calendar;

import java.util.Calendar;

public interface GoogleCalendar {
	public boolean insert(Event schedule);
	public boolean update(Event schedule);
	public boolean delete(Event schedule);
	public Event[] select(Calendar start, Calendar end);
}
