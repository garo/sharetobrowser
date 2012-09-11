package net.juhonkoti.sharetobrowser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Log.d("MainActivity", "testing");
		//sendUrlToServer("http://www.google.com");
		
		TargetDatabase.instance().init(getPreferences(0));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}


	protected void sendUrlToServer(String url) {
		new SendUrlToServerTask(this).execute(url);
	
	}
	
	public void scanQRCode(View view) {
		Intent intent = new Intent(this, ScanQRCodeActivity.class);		
		startActivity(intent); 

	}
}
