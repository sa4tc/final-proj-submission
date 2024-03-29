package com.glTron;
import java.util.ArrayList;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.glTron.Database.Count;

public class TotalPieActivity extends Activity {

	Database db = new Database(this);
	Button btn1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_total_pie);
		 /* btn1=(Button) findViewById(R.id.button1);
	        btn1.setOnClickListener(new View.OnClickListener() {
			    public void onClick(View v) {
			    	Intent i=new Intent(TotalPieActivity.this,accuracy.class);
			        startActivity(i);
			    	}
				}); */
		openChart();
		

	}

	private void openChart() {

		// Pie Chart Section Names
		String[] code = new String[] { "Left Gesture", "Right Gesture",
				"Up Gesture", "Down Gesture" };

		ArrayList<Count> values = (ArrayList<Count>) db.getData();
		int size = values.size();
		int totalLeft = 0;
		int totalRight = 0;
		int totalUp = 0;
		int totalDown = 0;
		
		for (int i = 0; i < size; i++) {
			Count count1 = values.get(i);
			totalLeft += count1.left;
			totalRight += count1.right;
			totalUp += count1.up;
			totalDown += count1.down;
			
		}

		// Pie Chart Section Value

		double[] distribution1 = { totalLeft, totalRight, totalUp, totalDown};

		// Color of each Pie Chart Sections
		int[] colors = { Color.RED, Color.GREEN, Color.LTGRAY, Color.DKGRAY};

		// Instantiating CategorySeries to plot Pie Chart

		CategorySeries distributionSeries1 = new CategorySeries(
				"All Games Pie Chart");

		for (int i = 0; i < distribution1.length; i++) {
			distributionSeries1.add(code[i], distribution1[i]);
		}

		// Instantiating a renderer for the Pie Chart

		DefaultRenderer defaultRenderer1 = new DefaultRenderer();

		for (int i = 0; i < distribution1.length; i++) {
			SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();
			seriesRenderer.setColor(colors[i]);
			seriesRenderer.setDisplayChartValues(true);
			// Adding a renderer for a slice
			defaultRenderer1.addSeriesRenderer(seriesRenderer);
		}

		defaultRenderer1.setChartTitle("All Games Pie Chart");
		defaultRenderer1.setChartTitleTextSize(100);
		defaultRenderer1.setZoomButtonsVisible(true);
		defaultRenderer1.setLabelsTextSize(50);
		defaultRenderer1.setLabelsColor(Color.BLACK);
		// Creating an intent to plot bar chart using dataset and
		// multipleRenderer
		Intent intent = ChartFactory.getPieChartIntent(getBaseContext(),
				distributionSeries1, defaultRenderer1,
				"AChartEnginePieChartDemo");

		// Start Activity
		startActivity(intent);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.total_pie, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
