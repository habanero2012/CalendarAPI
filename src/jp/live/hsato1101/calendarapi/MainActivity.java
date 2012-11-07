package jp.live.hsato1101.calendarapi;

import java.util.Calendar;

import jp.live.hsato1101.calendar.GoogleCalendar;
import jp.live.hsato1101.calendar.GoogleCalendarFactory;
import jp.live.hsato1101.calendar.Schedule;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private GoogleCalendar mGoogleCalendar;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mGoogleCalendar = GoogleCalendarFactory.getInstance(this);
        
        final TextView textView = (TextView)findViewById(R.id.textView1);
        Button selectBtn = (Button)findViewById(R.id.select_btn);
        selectBtn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
		    	Calendar start = Calendar.getInstance();
		    	start.set(2012, 10, 1, 10, 00);
		    	Calendar end = Calendar.getInstance();
		    	end.set(2012, 10, 10, 12, 00);
		    	Schedule[] schedules = mGoogleCalendar.select(start, end);
		    	
		    	StringBuilder text = new StringBuilder();
		    	for(Schedule s : schedules) {
		    		text.append(s.toString() + "\n");
		    	}
		    	textView.setText(text.toString());
			}
		});
        
        Button insertBtn = (Button)findViewById(R.id.insert_btn);
        insertBtn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				Calendar start = Calendar.getInstance();
				Calendar end = Calendar.getInstance();
				end.add(Calendar.HOUR, 1);
				Schedule s = new Schedule(0, "titleです", "descです", "", start, end, "0");
				mGoogleCalendar.insert(s);
			}
		});
    }
    
    @TargetApi(14)
	@Override
    protected void onResume() {
    	super.onResume();
    	/*
    	String[] projection = new String[] { "_id", "name" };
    	Uri calendars = CalendarContract.Calendars.CONTENT_URI;
    	Cursor c = managedQuery(calendars, projection, null, null, null);
    	if (c.moveToFirst()) {
    	    String name;
    	    String id;
    	    int nameColumn = c.getColumnIndex("name");
    	    int idColumn = c.getColumnIndex("_id");
    	    do {
    	        name = c.getString(nameColumn);
    	        id = c.getString(idColumn);
    	        Log.d("Calendar Data", "id=" + id + ",name=" + name);
    	    } while (c.moveToNext());
    	}
    	
    	long startMillis = 0;
    	long endMillis = 0;
    	Calendar beginTime = Calendar.getInstance();
    	beginTime.set(2012, 10, 10, 10, 00);
    	startMillis = beginTime.getTimeInMillis();
    	Calendar endTime = Calendar.getInstance();
    	endTime.set(2012, 10, 10, 12, 00);
    	endMillis = endTime.getTimeInMillis();
    	 
    	//イベントデータを登録
    	ContentResolver cr = getContentResolver();
    	ContentValues values = new ContentValues();
    	values.put(CalendarContract.Events.DTSTART, startMillis);
    	values.put(CalendarContract.Events.DTEND, endMillis);
    	values.put(CalendarContract.Events.TITLE, "テストイベント");
    	values.put(CalendarContract.Events.DESCRIPTION, "これはテストイベントです。");
    	values.put(CalendarContract.Events.CALENDAR_ID, 1);
    	values.put(CalendarContract.Events.EVENT_TIMEZONE, "Asia/Tokyo");
    	Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);
    	*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
