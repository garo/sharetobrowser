package net.juhonkoti.sharetobrowser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

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
	
	public void addTarget(String targetAndName) {
	
		
		HashSet<String> targets = (HashSet<String>) settings.getStringSet("targets", new HashSet<String>());
		targets.add(targetAndName);
		
		SharedPreferences.Editor editor = settings.edit();
		editor.putStringSet("targets", targets);
		editor.putString("target", targetAndName);
		editor.commit();
		Log.d("TargetDatabase", "Added new target: " + targetAndName);
	}
	
	public String getDefaultTarget() {
		String target = settings.getString("target", "");
		Log.d("TargetDatabase", "Returning default target: " + target);
		return target;
	}
	
	public String[] getTargets() {
		HashSet<String> targets = (HashSet<String>) settings.getStringSet("targets", new HashSet<String>());
		String[] targetsArray = new String[targets.size()];
		int i = 0;
		for (Iterator<String> iter = targets.iterator(); iter.hasNext();) {
			String target = iter.next();
			Log.v("TargetDatabase", "Target " + target);
			targetsArray[i++] = target;
		}

		return targetsArray;
	}
	
	public List<String> getTargetNames() {
		HashSet<String> targets = (HashSet<String>) settings.getStringSet("targets", new HashSet<String>());
		ArrayList<String> targetsArray = new ArrayList<String>();
		int i = 0;
		for (Iterator<String> iter = targets.iterator(); iter.hasNext();) {
			String target = iter.next();
			Log.v("TargetDatabase", "Target " + target);
			String parts[] = target.substring(7).split("/");
			if (parts.length == 2) {
				Log.v("", "Added: " + parts[1]);
				targetsArray.add(parts[1]);				
			}			
		}

		return targetsArray;
	}	

	public String getTargetByName(String name) {
		HashSet<String> targets = (HashSet<String>) settings.getStringSet("targets", new HashSet<String>());
		ArrayList<String> targetsArray = new ArrayList<String>();
		int i = 0;
		for (Iterator<String> iter = targets.iterator(); iter.hasNext();) {
			String target = iter.next();
			Log.v("getTargetByName", "target: " + target);
			String parts[] = target.substring(7).split("/");
			if (parts.length == 2) {
				if (parts[1].equals(name)) {
					Log.v("", "Return target: " + parts[0] + " for name: " + parts[1]);
					return parts[0];
				}
			}
		}
		return "";		
	}		
}
