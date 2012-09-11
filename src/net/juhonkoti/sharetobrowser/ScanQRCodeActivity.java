package net.juhonkoti.sharetobrowser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ScanQRCodeActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scan_qrcode);

		IntentIntegrator integrator = new IntentIntegrator(this);
		integrator.initiateScan(IntentIntegrator.QR_CODE_TYPES);
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		if (scanResult != null) {
			// handle scan result
			Log.d("ScanQRCodeActivity", "Got results:" + scanResult.getContents());
			
			TextView t = (TextView) findViewById(R.id.sendToServerText);
			t.setText(scanResult.getContents());
			
			new SendUrlToServerTask(this).execute(scanResult.getContents());
		}
		// else continue with any other code you need in the method

	}

}
