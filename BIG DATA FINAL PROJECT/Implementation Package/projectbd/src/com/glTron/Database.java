package com.glTron;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {

	private static final String CHART = "CHART";
	private static final String COL_LEFT = "LEFT";
	private static final String COL_RIGHT = "RIGHT";
	private static final String COL_UP = "UP";
	private static final String COL_DOWN = "DOWN";
	
	
	private static final String COL_COUNT = "COUNT";
	private static final String TIME = "TIME";
	private static final String DATE = "DATE";
	private static final String SECONDS = "PLAY_TIME";
	private static final String GPS = "GPS";
	private static final String LAT = "LATITUDE";
	private static final String LONG = "LONGITUDE";

	public Database(Context context) {
		super(context, "datastore.db", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		String sql = String
				.format("create table %s (_id INTEGER PRIMARY KEY AUTOINCREMENT, %s INTEGER NOT NULL, %s INTEGER NOT NULL, %s INTEGER NOT NULL, %s INTEGER NOT NULL, %s INTEGER NOT NULL)",
						CHART, COL_LEFT, COL_RIGHT, COL_UP, COL_DOWN, COL_COUNT);
		String sql2 = String
				.format("create table %s (_id INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL, %s INTEGER NOT NULL)",
						TIME, DATE, SECONDS);
		String sql3 = String.format("create table %s (_id INTEGER PRIMARY KEY, %s REAL NOT NULL, %s REAL NOT NULL)", GPS, LAT, LONG);
		db.execSQL(sql);
		db.execSQL(sql2);
		db.execSQL(sql3);
		

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public void storeData(int left, int right, int up, int down, int total) {
		SQLiteDatabase db = getReadableDatabase();

		ContentValues values = new ContentValues();

		values.put(COL_LEFT, left);
		values.put(COL_RIGHT, right);
		values.put(COL_UP, up);
		values.put(COL_DOWN, down);

		values.put(COL_COUNT, total);

		db.insert(CHART, null, values);

		db.close();

	}

	public List<Count> getData() {
		List<Count> values = new ArrayList<Count>();
		SQLiteDatabase db = getWritableDatabase();

		String sql = String.format(
				"SELECT %s, %s, %s, %s,%s FROM %s ORDER BY _id", COL_LEFT,
				COL_RIGHT, COL_UP, COL_DOWN,COL_COUNT, CHART);
		Cursor cursor = db.rawQuery(sql, null);
		cursor.moveToFirst();
		while (cursor.moveToNext()) {
			
				int left = cursor.getInt(0);
				Log.i("left",String.valueOf(left));
				int right = cursor.getInt(1);
				Log.i("right",String.valueOf(right));
				int up = cursor.getInt(2);
				Log.i("up",String.valueOf(up));
				int down = cursor.getInt(3);
				Log.i("down",String.valueOf(down));
				int total = cursor.getInt(4);
				Count count = new Count(left, right, up, down, total);
				values.add(count);

			
		}

		
		db.close();
		return values;
	}
	

	
	
	
	
	
	
	
	
	class Time{
		String time;
		long playTime;
		public Time(String time, long playTime){
			this.time = time;
			this.playTime = playTime;
			
		}
	}
	
	class Count{
		int left;
		int right;
		int up;
		int down;
		
		int count;
		public Count(int left, int right, int up, int down, int count){
			this.left = left;
			this.right = right;
			this.up = up;
			this.down = down;
			
			this.count = count;
			
		}
	}

}
