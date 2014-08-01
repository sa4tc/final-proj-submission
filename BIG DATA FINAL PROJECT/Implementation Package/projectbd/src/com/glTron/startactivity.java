package com.glTron;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class startactivity extends Activity{
	 
	@Override
    public void onCreate(Bundle savedInstanceState) {
		int state=R.layout.start;
		setContentView(state);
        super.onCreate(savedInstanceState);
        Button btn1=(Button) findViewById(R.id.button1);
        Button btn2=(Button) findViewById(R.id.button2);
        btn1.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		   // tomain(v); 
		    	Toast.makeText(startactivity.this, "Data Training", Toast.LENGTH_LONG).show();
		        startService(new Intent(startactivity.this,ConnectionService.class));
		        Toast.makeText(startactivity.this, "Data Trained", Toast.LENGTH_SHORT).show();
		       
		    	}
			}); 
        btn2.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		   // tomain(v); 
		    	Intent i=new Intent(startactivity.this,TotalPieActivity.class);
		    	startActivity(i);
		    	
		       
		    	}
			}); 
    }

	protected void tomain() {
		// TODO Auto-generated method stub
		Intent intent=new Intent(this,glTron.class);
		startActivity(intent);
	
		
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		unregisterReceiver(receiver);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		registerReceiver(receiver, new IntentFilter("myproject"));
	}
	
	  private BroadcastReceiver receiver = new BroadcastReceiver() {
	    	 
	 		@Override
	 		public void onReceive(Context context, Intent intent) {
	 			Log.i("In Broadcast recieve","test");
	 			Bundle bundle = intent.getExtras();
	 			if (bundle!=null) {
	 				String data = bundle.getString("data");
	 				Log.i("gestured recieved",data);
	 				if(data.equalsIgnoreCase("start"))
	 				{
	 					
	 					tomain();
	 					
	 				}
	 				
	 			}
	 		}};

}



