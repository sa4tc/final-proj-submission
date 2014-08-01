/*
 * Copyright © 2012 Iain Churcher
 *
 * Based on GLtron by Andreas Umbach (www.gltron.org)
 *
 * This file is part of GL TRON.
 *
 * GL TRON is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GL TRON is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with GL TRON.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.glTron;


import com.glTron.Database;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class glTron extends Activity {
    /** Called when the activity is first created. */
	public OpenGLView _View;
	
	private Boolean _FocusChangeFalseSeen = false;
	private Boolean _Resume = false;
	private Button bul;
	private Button but2;
	private int countup;
	private int countdown;
	Database db = new Database(this);
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	
    	//startService(new Intent(this,ConnectionService.class));
    	
		WindowManager w = getWindowManager();
	    Display d = w.getDefaultDisplay();
	    int width = d.getWidth();
	    int height = d.getHeight();
	   
	    super.onCreate(savedInstanceState);
	    
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        _View = new OpenGLView(this, width, height);
        setContentView(_View);
       /* setContentView(R.layout.main);
        bul = (Button)findViewById(R.id.left);
        but2 = (Button)findViewById(R.id.right);
        
        bul.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				_View._renderer.Game.turnleft();
				Toast.makeText(glTron.this, "left", Toast.LENGTH_SHORT).show();
			}
		});
        
        but2.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				_View._renderer.Game.turnright();
				Toast.makeText(glTron.this, "right", Toast.LENGTH_SHORT).show();
				
			}
		});
        LinearLayout lay = (LinearLayout)findViewById(R.id.lay);
        
        
        lay.addView(_View);*/
        

    }
    
    
    @Override
    public void onPause() {
    	_View.onPause();
    	super.onPause();
    	unregisterReceiver(receiver);
    }
    
    @Override
    public void onResume() {
    	if(!_FocusChangeFalseSeen)
    	{
    		_View.onResume();
    	}
    	_Resume = true;
    	//registerReceiver(receiver, new IntentFilter("myproject"));
    	super.onResume();
    	registerReceiver(receiver, new IntentFilter("myproject"));
    }
    
    @Override
    public void onWindowFocusChanged(boolean focus) {
    	if(focus)
    	{
    		if(_Resume)
    		{
    			_View.onResume();
    		}
    		
    		_Resume = false;
    		_FocusChangeFalseSeen = false;
    	}
    	else
    	{
    		_FocusChangeFalseSeen = true;
    	}
    }   
    
    //open menu when key pressed
     public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
        	this.startActivity(new Intent(this, Preferences.class));
        }
        return super.onKeyUp(keyCode, event);
    }
     private BroadcastReceiver receiver = new BroadcastReceiver() {
    	 
 		@Override
 		public void onReceive(Context context, Intent intent) {
 			Log.i("In Broadcast recieve","test");
 			Bundle bundle = intent.getExtras();
 			if (bundle!=null) {
 				String data = bundle.getString("data");
 				Log.i("gestured recieved",data);
 				if(data.equalsIgnoreCase("left"))
 				{
 					_View._renderer.Game.turnleft();
 					Log.i("game", "Turned Left");
 					
 				}
 				else if(data.equalsIgnoreCase("right"))
 				{
 					_View._renderer.Game.turnright();
 					Log.i("game", "Turned Right");
 				}
 				else if(data.equalsIgnoreCase("fly")){
 					
					
 					/*end = System.currentTimeMillis();
 					diff = end - start;
					
 					db.storePlayTime(gameTime, diff);
 					
 					Log.i("saving Time", gameTime + "," + diff);*/
 					countup++;
 					finish();
 					//db.storeData(10, 13, 17, 18, 19);
 					int a=_View._renderer.Game.getCountLeft();
 					int b=_View._renderer.Game.getCountRight();
 					int count = countdown+countup+a+b;
 					Log.i("left count",String.valueOf(a));
 					Log.i("Right count",String.valueOf(b));
 					Log.i("Up count",String.valueOf(countdown));
 					Log.i("Up count",String.valueOf(countup));
 					db.storeData(_View._renderer.Game.getCountLeft(),_View._renderer.Game.getCountRight(),
 							countup,countdown,count);
 					
 					Log.i("saving count", "saving count");
 					
 				}
 				else if(data.equalsIgnoreCase("stomp")){
 					int a=_View._renderer.Game.getCountLeft();
 					int b=_View._renderer.Game.getCountRight();
 					int count = countdown+countup+a+b;
					countdown++;
					db.storeData(_View._renderer.Game.getCountLeft(),_View._renderer.Game.getCountRight(),
 							countup,countdown,count);
					Intent homeIntent = new Intent(Intent.ACTION_MAIN);
				    homeIntent.addCategory( Intent.CATEGORY_HOME );
				    homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
				    startActivity(homeIntent); 
					
 				}
 				
 			
 			
 				
 		//		if ("stomp".equalsIgnoreCase(data)) {
 					//view.flyCow();	
 			//	}
 						
 				//Toast.makeText(getApplicationContext(), "Ok", Toast.LENGTH_SHORT).show();
 			}else{
 				Log.i("data in main class", "bundle null");
 				//Toast.makeText(getApplicationContext(), "not", Toast.LENGTH_SHORT).show();
 			}
 			//handleResult(bundle);
 		}};
 		
}
     