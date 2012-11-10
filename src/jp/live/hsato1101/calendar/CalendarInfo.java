package jp.live.hsato1101.calendar;


public class CalendarInfo {
	private int mId;
	private String mAccountName;
	private String mAccountType;

	public CalendarInfo(int id, String accountName, String accountType) {
		mId = id;
		mAccountName = accountName;
		mAccountType = accountType;
	}
	
	public int getId() {
		return mId;
	}
	
	public boolean isGoogleSyncAccount() {
		if(isGoogleAccount() && isSyncAccount()) {
			return true;
		}
		return false;
	}
	
	private boolean isGoogleAccount() {
		return mAccountName.matches(".*@gmail\\.com$");
	}
	
	private boolean isSyncAccount() {
		if(mAccountType.matches("com.google") || mAccountType.matches("com.android.exchange")) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return super.toString() + " Calendar ID:" + mId + " AccountName:" + mAccountName + " AccountType:" + mAccountType;
	}
}
