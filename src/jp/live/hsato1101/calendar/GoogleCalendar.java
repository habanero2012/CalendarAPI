package jp.live.hsato1101.calendar;

import java.util.Calendar;

public interface GoogleCalendar {
	public boolean insert(Event e);
	public int update(Event e);
	public int delete(Event e);
	public Event[] select(Calendar start, Calendar end);
}
