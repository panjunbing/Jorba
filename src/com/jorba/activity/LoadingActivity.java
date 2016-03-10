package com.jorba.activity;

import com.jorba.R;
import com.jorba.data.Data;
import com.jorba.other.DatabaseManager;

import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class LoadingActivity extends Activity {
	
private ImageView imageView;
private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
//		relativeLayout=(RelativeLayout)findViewById(R.id.relativelayout_loading);
		imageView = (ImageView)findViewById(R.id.imageView_loading);
		final DisplayMetrics dispalyMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dispalyMetrics);
		imageView.getLayoutParams().width=dispalyMetrics.widthPixels/3;
		imageView.getLayoutParams().height=dispalyMetrics.widthPixels/3;
		
		
		DatabaseManager.initManager(this);
		DatabaseManager dm = DatabaseManager.getManager();
		db = dm.getDatabase("JorbaData.db");
		Cursor cursor = db.rawQuery("select UserName from user where Login=1",null);
		if(cursor.moveToNext()){
			Data.setUsername(cursor.getString(0));
			Toast.makeText(getApplicationContext(), cursor.getString(0), Toast.LENGTH_SHORT).show();
		}
		else {
			Toast.makeText(getApplicationContext(), "欢迎使用桌吧", Toast.LENGTH_SHORT).show();
		}
		
//		延时
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO 自动生成的方法存根
				Intent intent = new Intent();
				intent.setClass(LoadingActivity.this, MainActivity.class);
				LoadingActivity.this.startActivity(intent);	
				overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
				LoadingActivity.this.finish();
			}
		},3000);
	}

}
