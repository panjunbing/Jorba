package com.jorba.activity;

import com.jorba.R;
import com.jorba.other.DatabaseManager;

import android.os.Bundle;
import android.os.Handler;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class UndercoverActivity3 extends Activity {
	private TextView textView1;
	private ImageView backImageView;
	private TextView nameTextView;
	private String charactername[];
	private TextView characterTextView;
	private Button nextButton;
	private Button beginButton;
	private Button buttonExit;
	private Button buttonBack;
	private String playername[];
	private RelativeLayout relativeLayout;
	private int number;
	private int character[];
	private int player[];
	private int random;
	private SQLiteDatabase db;
//	private int a=0;
	private int b;
	private int i=1;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wodi3);
		relativeLayout=(RelativeLayout)findViewById(R.id.relativelayout_wodi3);
		Intent intent = getIntent();
		number  = Integer.valueOf(intent.getIntExtra("number",-1));
		playername  =intent.getStringArrayExtra("playername");
		character=new int[number];
		character=intent.getIntArrayExtra("character");
		player=new int[number];
		backImageView=new ImageView(this);
		backImageView.setBackgroundResource(R.drawable.game2);
		nameTextView=new TextView(this);
		charactername=new String[3];
		characterTextView=new TextView(this);
		characterTextView.setText(null);
		nextButton=new Button(this);
		nextButton.setText("下一个");
		beginButton=new Button(this);
		beginButton.setText("开始游戏");
		textView1=new TextView(this);
		textView1.setText("查看角色：");
		
		TextPaint tp1 = textView1.getPaint(); 
		tp1.setFakeBoldText(true);
		textView1.setTextSize(25);
		
		TextPaint tp2 = nameTextView.getPaint(); 
		tp2.setFakeBoldText(true);
		nameTextView.setTextSize(20);
		
		TextPaint tp3 = characterTextView.getPaint(); 
		tp3.setFakeBoldText(true);
		characterTextView.setTextSize(30);
		characterTextView.setTextColor(0xffFF0000);
		
		
		buttonBack = (Button)findViewById(R.id.button_back3);
		buttonExit = (Button)findViewById(R.id.button_exit3);
		final DisplayMetrics dispalyMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dispalyMetrics);
		buttonBack.getLayoutParams().width = dispalyMetrics.widthPixels/7;
		buttonBack.getLayoutParams().height = dispalyMetrics.widthPixels/7;

		
		

		
		
	
		buttonExit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent=new Intent(UndercoverActivity3.this,MainActivity.class);
				startActivity(intent);
				finish();
			}
		});

		
		buttonBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent=new Intent(UndercoverActivity3.this,UndercoverActivity2.class);
				Bundle bundle = new Bundle();
				bundle.putInt("number", number);
				bundle.putIntArray("character", character);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
		


for (int i = 0; i < number; i++) {      
	player[i]=3;

}
for (int i = 0; i < 3; i++) {      
	if (character[i]!=0) {
		for (int j = 0; j <character[i]; j++) {
			do {
				random = (int)(Math.random()*number);   
			} while (player[random]!=3);
			player[random]=i;       

		}		
	}

}

DatabaseManager.initManager(getApplicationContext());
DatabaseManager dm = DatabaseManager.getManager();
db = dm.getDatabase("JorbaData.db");
Cursor cursor =  db.rawQuery("select * from game2Data",null);

int m = cursor.getCount();		                    	
int random = (int)(Math.random()*m+1);                		
for (int i = 0; i != random; i++) {
	cursor.moveToNext();
}

        charactername[0] = "白板";
		charactername[1] = cursor.getString(cursor.getColumnIndex("Undercover"));
		charactername[2] = cursor.getString(cursor.getColumnIndex("Civilian"));
		
		cursor.close();
		dm.closeDatabase("JorbaData");
		
		


		nextButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				if (i==number) {
					relativeLayout.removeView(nameTextView);
					relativeLayout.removeView(backImageView);
					relativeLayout.removeView(nextButton);
					relativeLayout.addView(beginButton);
				}
				if (i<number) {
					nameTextView.setText(playername[i]);
					b=i;
					i=i+1;
				}
				
				
				
			}
		});
		backImageView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				
				backImageView.setImageResource(R.drawable.white);
				if (i==1) {
					characterTextView.setText(charactername[player[0]]);
					
				}else {
					characterTextView.setText(charactername[player[b]]);
				}
				

			
			
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						backImageView.setImageResource(R.drawable.game2);
						characterTextView.setText(null);
					}
				},500);
			}
		});
		
		beginButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent=new Intent(UndercoverActivity3.this,UndercoverActivity4.class);
				Bundle bundle = new Bundle();
				bundle.putInt("number", number);
				bundle.putStringArray("playername", playername);
				bundle.putIntArray("player", player);
				bundle.putStringArray("charactername", charactername);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
		relativeLayout .addView(backImageView);
		relativeLayout.addView(nameTextView);
		relativeLayout.addView(textView1);
		nameTextView.setText(playername[0]);
		relativeLayout.addView(characterTextView);
		relativeLayout.addView(nextButton);
		

		
		ViewTreeObserver vtoRelativeLayoutMain = relativeLayout.getViewTreeObserver();
		vtoRelativeLayoutMain.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

			@SuppressLint("NewApi")
			@Override
			public void onGlobalLayout() {
				
				nextButton.setX(relativeLayout.getWidth()/2-nextButton.getWidth()/2);
				nextButton.setY(relativeLayout.getHeight()-nextButton.getHeight()*2);
				
				backImageView.getLayoutParams().width=relativeLayout.getWidth()/2;
				backImageView.getLayoutParams().height=relativeLayout.getWidth()/2;
				backImageView.setX(relativeLayout.getWidth()/2-backImageView.getWidth()/2);
				backImageView.setY(relativeLayout.getHeight()/2-backImageView.getHeight()/2);
				
				nameTextView.setX(backImageView.getX());
				nameTextView.setY(backImageView.getY()-nameTextView.getHeight());
				
				textView1.setX(0);
				textView1.setY(nameTextView.getY()-textView1.getHeight()*2);
				
				beginButton.setX(relativeLayout.getWidth()/2-beginButton.getWidth()/2);
				beginButton.setY(relativeLayout.getHeight()/2-beginButton.getHeight()/2);
				
				characterTextView.setX(backImageView.getX()+backImageView.getWidth()/2-characterTextView.getWidth()/2);
				characterTextView.setY(backImageView.getY()+backImageView.getHeight()/2-characterTextView.getHeight()/2);

				
			}
			
		});
	    
	}

}
