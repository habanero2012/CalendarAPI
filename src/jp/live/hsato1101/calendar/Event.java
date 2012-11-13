package jp.live.hsato1101.calendar;

import java.text.DateFormat;
import java.util.Calendar;

public class Event {

	private final DateFormat mDateFormat = DateFormat.getInstance();
	
	private long mId;
	private String mTitle;
	private String mDescription;
	private String mEventLocation;
	private Calendar mStart;
	private Calendar mEnd;
	private int mAllDay;
	private Calendar mLastDate;
	
	public Event(long id, String title, String description,
			String eventLocation, Calendar start, Calendar end, int allday) {
		mId = id;
		mTitle = title;
		mDescription = description;
		mEventLocation = eventLocation;
		mStart = (Calendar)start.clone();
		mEnd = (Calendar)end.clone();
		mAllDay = allday;
	}

	public long getId(){
		return mId;
	}
	
	public String getTitle(){
		return mTitle;
	}
	
	public void setTitle(String title) {
		mTitle = title;
	}
	
	public String getDescription(){
		return mDescription;
	}
	
	public void setDescription(String desc) {
		mDescription = desc;
	}
	
	public String getEventLocation() {
		return mEventLocation;
	}
	
	public void setEventLocation(String eventLocation) {
		mEventLocation = eventLocation;
	}
	
	public Calendar getStart() {
		return (Calendar)mStart.clone();
	}
	
	public void setStart(Calendar start) {
		mStart = (Calendar) start.clone();
	}
	
	public long getStartTimeInMillis() {
		return mStart.getTimeInMillis();
	}
	
	public Calendar getEnd() {
		return (Calendar)mEnd.clone();
	}
	
	public void setEnd(Calendar end) {
		mEnd = (Calendar) end.clone();
	}
	
	public long getEndTimeInMillis() {
		return mEnd.getTimeInMillis();
	}
	
	public int getAllDay() {
		return mAllDay;
	}
	
	public void setLastDate(Calendar lastDate) {
		mLastDate = (Calendar)lastDate.clone();
	}
	
	public Calendar getLastDate() {
		return (Calendar) mLastDate.clone();
	}
	
	
	@Override
	public String toString() {
		return "ID:" + mId + " Title:" + mTitle + " DESC:" + mDescription +
				" EventLocation:" + mEventLocation + 
				" Start:" + mDateFormat.format(mStart.getTime()) +
				" End:" + mDateFormat.format(mEnd.getTime()) +
				" AllDay:" + mAllDay +
				" LastDate:" + mDateFormat.format(mLastDate.getTime());
	}
}
