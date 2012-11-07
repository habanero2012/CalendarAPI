package jp.live.hsato1101.calendar;

import java.util.Calendar;

public class Schedule {

	private int mId;
	private String mTitle;
	private String mDescription;
	private String mEventLocation;
	private Calendar mStart;
	private Calendar mEnd;
	private String mEventId;
	
	public Schedule(int id, String title, String description,
			String eventLocation, Calendar start, Calendar end, String eventId) {
		mId = id;
		mTitle = title;
		mDescription = description;
		mEventLocation = eventLocation;
		mStart = start;
		mEnd = end;
		mEventId = eventId;
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
	
	public String getEventId() {
		return mEventId;
	}
	
	@Override
	public String toString() {
		return "ID:" + mId + " Title:" + mTitle + " DESC:" + mDescription;
	}
}
