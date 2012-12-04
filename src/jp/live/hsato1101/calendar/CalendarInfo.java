package jp.live.hsato1101.calendar;


public class CalendarInfo {
	private int mId;
	private String mAccountName;
	private String mAccountType;
	private String mAccessLevel;
	private String mSyncEvents;

	public CalendarInfo(int id, String accountName, String accountType, String accessLevel, String syncEvents) {
		mId = id;
		mAccountName = accountName;
		mAccountType = accountType;
		mAccessLevel = accessLevel;
		mSyncEvents = syncEvents;
	}
	
	public int getId() {
		return mId;
	}
	
	public boolean isGoogleSyncAccount() {
		if(isGoogleAccount() && 
			isSyncAccount() &&
			isAccessLevelOwner() &&
			isSyncEvents()) {
			return true;
		}
		return false;
	}
	
	private boolean isSyncEvents() {
		return mSyncEvents.equals("1");
	}
	
	private boolean isAccessLevelOwner() {
		return mAccessLevel.equals("700");
	}
	
	private boolean isGoogleAccount() {
		// 独自ドメイン対応のため条件をはずす
		//return mAccountName.matches(".*@gmail\\.com$");
		return true;
	}
	
	private boolean isSyncAccount() {
		if(mAccountType.matches("com.google") || mAccountType.matches("com.android.exchange")) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return super.toString() + " Calendar ID:" + mId + 
				" AccountName:" + mAccountName + 
				" AccountType:" + mAccountType +
				" AccessLevel:" + mAccessLevel +
				" SyncEvents:" + mSyncEvents;
	}
}
