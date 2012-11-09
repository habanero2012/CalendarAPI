package jp.live.hsato1101.calendar.v2_2;

import android.content.ContentValues;
import jp.live.hsato1101.calendar.Event;
import jp.live.hsato1101.calendar.v2_1.EventColumnsV2_1;

public class EventColumnsV2_2 extends EventColumnsV2_1{
	
	private static final String _SYNC_ACCOUNT_TYPE = "_sync_account_type";
	
	@Override
	public ContentValues values(Event e) {
		ContentValues values = super.values(e);
		values.put(_SYNC_ACCOUNT_TYPE, "com.google");
		return values;
	}

}
