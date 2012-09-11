package net.juhonkoti.sharetobrowser;

import android.content.SharedPreferences;
import android.util.Log;

public class TargetDatabase {
	private SharedPreferences settings;
	
	private TargetDatabase() {

	}
	
	private static TargetDatabase _instance = null;
	public static TargetDatabase instance() {
		if (_instance == null) {
			_instance = new TargetDatabase();
		}
		
		return _instance;		
	}
	
	public void init(SharedPreferences settings) {
		this.settings = settings;
	}
	
	public void addTarget(String target) {
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("target", target);
		editor.commit();
		Log.d("TargetDatabase", "Added new target: " + target);
	}
	
	public String getDefaultTarget() {
		String target = settings.getString("target", "");
		Log.d("TargetDatabase", "Returning default target: " + target);
		return target;
	}

}
