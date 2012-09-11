package net.juhonkoti.sharetobrowser;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class PrepareToShareActivity extends Activity {

	String url = "";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare_to_share);
        
        Intent intent = getIntent();
        
        Log.d("PrepareToShareActivity", "type: " + intent.getType());
        if ("text/plain".equals(intent.getType())) {
            url = intent.getStringExtra(Intent.EXTRA_TEXT);
    		TextView t = (TextView) findViewById(R.id.sendToServerText);
    		t.setText(url);
        }              
        
        List<String> targetNames = TargetDatabase.instance().getTargetNames();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, targetNames);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       ((Spinner)findViewById(R.id.chooseTargetSpinner)).setAdapter(dataAdapter);        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_prepare_to_share, menu);
        return true;
    }
    
    public void onShareClick(View view) {
    	
    	String selectedName = (String) ((Spinner)findViewById(R.id.chooseTargetSpinner)).getSelectedItem();
    	Log.v("", "Selected name: " + selectedName);
    	String target = TargetDatabase.instance().getTargetByName(selectedName);
    	TextView t = (TextView) findViewById(R.id.sendToServerText);
    	new SendUrlToServerTask(this).execute(t.getText().toString(), target);
    }
}
