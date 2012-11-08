package jp.live.hsato1101.calendar;

import java.text.DateFormat;
import java.util.Calendar;

public class Event {

	private final DateFormat mDateFormat = DateFormat.getInstance();
	
	private int mId;
	private String mTitle;
	private String mDescription;
	private String mEventLocation;
	private Calendar mStart;
	private Calendar mEnd;
	
	public Event(int id, String title, String description,
			String eventLocation, Calendar start, Calendar end) {
		mId = id;
		mTitle = title;
		mDescription = description;
		mEventLocation = eventLocation;
		mStart = start;
		mEnd = end;
	}

	public int getId(){
		return mId;
	}
	
	public String getTitle(){
		return mTitle;
	}
	
	public String getDescription(){
		return mDescription;
	}
	
	public String getEventLocation() {
		return mEventLocation;
	}
	
	public Calendar getStart() {
		return mStart;
	}
	
	public long getStartTimeInMillis() {
		return mStart.getTimeInMillis();
	}
	
	public Calendar getEnd() {
		return mEnd;
	}
	
	public long getEndTimeInMillis() {
		return mEnd.getTimeInMillis();
	}
	
	@Override
	public String toString() {
		return "ID:" + mId + " Title:" + mTitle + " DESC:" + mDescription +
				" EventLocation:" + mEventLocation + 
				" Start:" + mDateFormat.format(mStart.getTime()) +
				" End:" + mDateFormat.format(mEnd.getTime());
	}
}
