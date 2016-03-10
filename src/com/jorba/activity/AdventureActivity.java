package com.jorba.activity;

import com.jorba.R;
import com.jorba.other.DatabaseManager;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AdventureActivity extends Activity {
	private ImageView logoImageView;
	private TextView textView1;
	private TextView textView2;
	private TextView textView3;
	private TextView textView4;
	private Button button1;
	private Button button2;
	private Button exitButton;
	private RelativeLayout relativeLayout;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adventure);
		relativeLayout=(RelativeLayout)findViewById(R.id.relativelayout_adventure);
		logoImageView=new ImageView(this);
		logoImageView.setBackgroundResource(R.drawable.game3);
		textView1=new TextView(this);
		TextPaint tp1 = textView1.getPaint(); 
		tp1.setFakeBoldText(true);
		textView1.setTextSize(25);

		textView2=new TextView(this);
		TextPaint tp2 = textView2.getPaint(); 
		tp2.setFakeBoldText(true);
		textView2.setTextSize(20);
		textView2.setTextColor(0xffFF0000);
		textView2.setBackgroundColor(0xffececec);

		textView3=new TextView(this);
		TextPaint tp3 = textView3.getPaint(); 
		tp3.setFakeBoldText(true);
		textView3.setTextSize(25);

		textView4=new TextView(this);
		TextPaint tp4 = textView4.getPaint(); 
		tp4.setFakeBoldText(true);
		textView4.setTextSize(20);
		textView4.setTextColor(0xffFF0000);
		textView4.setBackgroundColor(0xffececec);

		textView1.setText("真心话");
		textView2.setText("请按下一个选择真心话");
		textView3.setText("大冒险");
		textView4.setText("请安下一个选择大冒险");
		button1=new Button(this);
		button2=new Button(this);
		button1.setText("下一个");
		button2.setText("下一个");
		exitButton=new Button(this);
		exitButton = (Button)findViewById(R.id.button_exit4);
		relativeLayout.addView(logoImageView);
		relativeLayout.addView(textView1);
		relativeLayout.addView(textView2);
		relativeLayout.addView(textView3);
		relativeLayout.addView(textView4);
		relativeLayout.addView(button1);
		relativeLayout.addView(button2);

		exitButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent=new Intent(AdventureActivity.this,MainActivity.class);
				startActivity(intent);
				finish();
			}
		});

		button1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {

				DatabaseManager.initManager(view.getContext());
				DatabaseManager dm = DatabaseManager.getManager();
				db = dm.getDatabase("JorbaData.db");
				Cursor cursor =  db.rawQuery("select * from game3Data",null);

				int m = cursor.getCount();		                    	
				int random = (int)(Math.random()*m+1);                		
				for (int i = 0; i != random; i++) {
					cursor.moveToNext();
				}

				textView2.setText(cursor.getString(cursor.getColumnIndex("Content")));
				cursor.close();
				dm.closeDatabase("JorbaData");
			}
		});
		button2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				DatabaseManager.initManager(getApplicationContext());
				DatabaseManager dm = DatabaseManager.getManager();
				db = dm.getDatabase("JorbaData.db");
				Cursor cursor =  db.rawQuery("select * from game3Data",null);

				int m = cursor.getCount();		                    		
				int random = (int)(Math.random()*m+1);                		
				for (int i = 0; i != random; i++) {
					cursor.moveToNext();
				}

				textView4.setText(cursor.getString(cursor.getColumnIndex("Content")));
				cursor.close();
				dm.closeDatabase("JorbaData");
			}
		});

		ViewTreeObserver vtoRelativeLayoutMain = relativeLayout.getViewTreeObserver();
		vtoRelativeLayoutMain.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

			@Override
			public void onGlobalLayout() {

				logoImageView.getLayoutParams().width = (relativeLayout.getWidth()-10)/2;
				logoImageView.getLayoutParams().height =(relativeLayout.getWidth()-10)/2;

				textView2.getLayoutParams().width = (relativeLayout.getWidth()-textView1.getWidth()*2/5);
				textView2.getLayoutParams().height =(relativeLayout.getWidth())/10;

				textView4.getLayoutParams().width = (relativeLayout.getWidth()-textView1.getWidth()*2/5);
				textView4.getLayoutParams().height =(relativeLayout.getWidth())/10;

				logoImageView.setX(relativeLayout.getWidth()/2-logoImageView.getWidth()/2);
				logoImageView.setY(5);
				textView1.setX(0);
				textView1.setY(logoImageView.getY()+logoImageView.getHeight()+textView1.getHeight());

				textView2.setX(textView1.getWidth()/5);
				textView2.setY(textView1.getY()+textView1.getHeight()*2);

				button1.setX(relativeLayout.getWidth()/2-button1.getWidth()/2);
				button1.setY(textView2.getY()+button1.getHeight());

				textView3.setX(0);
				textView3.setY(button1.getY()+textView3.getHeight());

				textView4.setX(textView1.getWidth()/5);
				textView4.setY(textView3.getY()+textView4.getHeight()*2);

				button2.setX(relativeLayout.getWidth()/2-button1.getWidth()/2);
				button2.setY(textView4.getY()+button2.getHeight());	

			}
		});
	}

}
