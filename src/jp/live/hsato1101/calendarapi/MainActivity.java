package jp.live.hsato1101.calendarapi;

import java.util.Calendar;
import java.util.List;

import jp.live.hsato1101.calendar.CalendarInfo;
import jp.live.hsato1101.calendar.GoogleCalendar;
import jp.live.hsato1101.calendar.GoogleCalendarFactory;
import jp.live.hsato1101.calendar.Event;
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
        
        List<CalendarInfo> list = GoogleCalendarFactory.getCalendarInfoHasGoogleSyncAccount(this);
        CalendarInfo info = list.get(0);
        mGoogleCalendar = GoogleCalendarFactory.getInstance(this, info.getId());
        
        final TextView textView = (TextView)findViewById(R.id.textView1);
        Button whenByBtn = (Button)findViewById(R.id.when_by_btn);
        whenByBtn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
		    	Calendar start = Calendar.getInstance();
		    	start.set(Calendar.DAY_OF_MONTH, 1);
		    	Calendar end = Calendar.getInstance();
		    	end.add(Calendar.MONTH, 1);
		    	Event[] events = mGoogleCalendar.selectInstance(start, end);
		    	
		    	StringBuilder text = new StringBuilder();
		    	for(Event e : events) {
		    		text.append(e.toString() + "\n");
		    	}
		    	textView.setText(text.toString());
			}
		});
        
        Button selectBtn = (Button)findViewById(R.id.select_btn);
        selectBtn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
		    	Calendar start = Calendar.getInstance();
		    	start.set(Calendar.DAY_OF_MONTH, 13);
		    	start.set(Calendar.HOUR_OF_DAY, 0);

		    	Event[] events = mGoogleCalendar.select(start);
		    	
		    	StringBuilder text = new StringBuilder();
		    	for(Event e : events) {
		    		text.append(e.toString() + "\n");
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
				Event e = new Event(0, "Insertです", "descです", "場所です", start, end, 0);
				mGoogleCalendar.insert(e);
			}
		});
        
        Button deleteBtn = (Button)findViewById(R.id.delete_btn);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
		    	Calendar start = Calendar.getInstance();
		    	start.set(Calendar.DAY_OF_MONTH, 1);
		    	Calendar end = Calendar.getInstance();
		    	end.add(Calendar.MONTH, 1);
		    	Event[] evetns = mGoogleCalendar.selectInstance(start, end);
		    	for(Event e: evetns) {
		    		mGoogleCalendar.delete(e);
		    		break; // 1件だけ削除する
		    	}
			}
		});
        
        Button updateBtn = (Button)findViewById(R.id.update_btn);
        updateBtn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
		    	Calendar start = Calendar.getInstance();
		    	start.set(Calendar.DAY_OF_MONTH, 1);
		    	Calendar end = Calendar.getInstance();
		    	end.add(Calendar.MONTH, 1);
		    	Event[] evetns = mGoogleCalendar.selectInstance(start, end);
		    	for(Event e: evetns) {
		    		e.setTitle("UPDATE!");
		    		
		    		Calendar e_start = e.getStart();
					e_start.add(Calendar.DATE, 1);
					e.setStart(e_start);
					Calendar e_end = e.getEnd();
					e_end.add(Calendar.DATE, 1);
					e.setEnd(e_end);
		    		
		    		mGoogleCalendar.update(e);
		    		break;
		    	}
			}
		});
    }
    
    @TargetApi(14)
	@Override
    protected void onResume() {
    	super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
