package com.glTron;

import java.util.ArrayList;

import com.glTron.Database.Count;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class accuracy extends Activity {
	Database db = new Database(this);	
	Button btn1;
	EditText edt1;
	Spinner spn1;
	String str1;
	
	int totalLeft = 0;
	int totalRight = 0;
	int totalUp = 0;
	int totalDown = 0;
	int val;
	TextView txtv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accuracy);
		btn1=(Button) findViewById(R.id.button1);	 
		edt1=(EditText) findViewById(R.id.editText1);
		 spn1 = (Spinner) findViewById(R.id.spinner1);
	       txtv=(TextView) findViewById(R.id.textView2);
	        btn1.setOnClickListener(new View.OnClickListener() {
			    public void onClick(View v) {
			    	Log.i("in","Onclick");
			    	String str=edt1.getText().toString();
			  str1=spn1.getSelectedItem().toString();
			  Log.i("str1",str1);
			  val=Integer.parseInt(str);			  
			  ArrayList<Count> values = (ArrayList<Count>) db.getData();
				
			  int size = values.size();
				for (int i = 0; i < 1; i++) {
					Count count1 = values.get(i);
					totalLeft += count1.left;
					totalRight += count1.right;
					totalUp += count1.up;
					totalDown += count1.down;
					
				}
			       
				 if("Right".equals(str1)){
			        	Log.i("in","Right Accuracy");
			        	Log.i("totalright",String.valueOf(totalRight));
			        	float per=(val-totalRight)/val;
			        	
			        	float per1=per*100;
			        	if(val==totalRight){
			        		per1=100;
			        	}
			        	
			        	String str2=String.valueOf(per1);
			        	Log.i("str2",str2);
			        	txtv.setText(str2);
			        //	totalRight = 0;
			        	
			        }
				 if("Left".equals(str1)){
			        	Log.i("in","Right Accuracy");
			        	
			        	float per=(val-totalLeft)/val;
			        	Log.i("totalleft",String.valueOf(totalLeft));
			        	float per1=per*100;
			        	if(val==totalLeft){
			        		per1=100;
			        	}
			        	
			        	String str2=String.valueOf(per1);
			        	Log.i("str2",str2);
			        	txtv.setText(str2);
			        	// totalLeft = 0;
			        	
			        }
				 if("Up".equals(str1)){
			        	Log.i("in","Right Accuracy");
			        	
			        	float per=(val-totalUp)/val;
			        	Log.i("totalUp",String.valueOf(totalUp));
			        	float per1=per*100;
			        	if(val==totalUp){
			        		per1=100;
			        	}
			        	
			        	String str2=String.valueOf(per1);
			        	Log.i("str2",str2);
			        	txtv.setText(str2);
			        //	totalUp = 0;
			        	
			        }
				 if("Down".equals(str1)){
			        	Log.i("in","Dwon Accuracy");
			        	
			        	float per=(val-totalDown)/val;
			        	Log.i("totalright",String.valueOf(totalDown));
			        	float per1=per*100;
			        	if(val==totalDown){
			        		per1=100;
			        	}
			        	
			        	String str2=String.valueOf(per1);
			        	Log.i("str2",str2);
			        	txtv.setText(str2);
			        	//totalDown = 0;
			        	
			        }
			    	}
				});
	       
	    	
	    	
	    	
	       
	        	
}
}
