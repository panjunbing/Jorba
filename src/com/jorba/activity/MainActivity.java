package com.jorba.activity;


import com.jorba.R;
import com.jorba.activity.WerewolfActivity;
import com.jorba.data.Data;
import com.jorba.other.DatabaseManager;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;




public class MainActivity extends Activity {
	
	private ImageView ImageButton1;
	private ImageView ImageButton2;
	private ImageView ImageButton3;
	private ImageView ImageButton4;
	private ImageView logoImageView;
	private Button buttonMe;
	private Button buttonSetting;
	private SQLiteDatabase db;
	private DatabaseManager dm;
	private RelativeLayout relativeLayout;
	

	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		DatabaseManager.initManager(this);
		dm = DatabaseManager.getManager();
		db = dm.getDatabase("JorbaData.db");
		Cursor cursor = db.rawQuery("select userName from user where login = 1", null);
		if(cursor.moveToNext()) {
		}
		dm.closeDatabase("JorbaData.db");
		
		relativeLayout=new RelativeLayout(this);
		relativeLayout=(RelativeLayout)findViewById(R.id.relativeLayout1);
		ImageButton1 = new ImageView(this);
		ImageButton2 =  new ImageView(this);
		ImageButton3 =  new ImageView(this);
		ImageButton4 = new ImageView(this);
		logoImageView=new ImageView(this);
		ImageButton1.setImageResource(R.drawable.langrenlogo);
		ImageButton2.setImageResource(R.drawable.game2logo);
		ImageButton3.setImageResource(R.drawable.game3logo);
		ImageButton4.setImageResource(R.drawable.datinglogo);
		logoImageView.setImageResource(R.drawable.logo);
		relativeLayout.addView(ImageButton1);
		relativeLayout.addView(ImageButton2);
		relativeLayout.addView(ImageButton3);
		relativeLayout.addView(ImageButton4);
		relativeLayout.addView(logoImageView);		
		
		//获取屏幕宽度	
//		final DisplayMetrics dispalyMetrics = new DisplayMetrics();
//		getWindowManager().getDefaultDisplay().getMetrics(dispalyMetrics);
//		ImageButton1.getLayoutParams().width = dispalyMetrics.widthPixels/2-20;
//		ImageButton2.getLayoutParams().width = dispalyMetrics.widthPixels/2-20;
//		ImageButton3.getLayoutParams().width = dispalyMetrics.widthPixels/2-20;
//		ImageButton4.getLayoutParams().width = dispalyMetrics.widthPixels/2-20;
		
		//

	
		//
		ImageButton1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent();
				intent.setClass(MainActivity.this,WerewolfActivity.class);
				overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
				MainActivity.this.startActivity(intent);

			}
		});
		

		ImageButton2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent();
				intent.setClass(MainActivity.this,UndercoverActivity.class);
				overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
				MainActivity.this.startActivity(intent);
			}
		});

		//

		ImageButton3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent();
				intent.setClass(MainActivity.this,AdventureActivity.class);
				overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
				MainActivity.this.startActivity(intent);
			}
		});
		
		//
		ImageButton4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent();
				intent.setClass(MainActivity.this,DatingActivity.class);
				overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
				MainActivity.this.startActivity(intent);
			}
		});
		
		//
		buttonMe = (Button)findViewById(R.id.button_me);

		buttonMe.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				// TODO 自动生成的方法存根
				if(Data.getUsername() != null){
					Intent intent = new Intent(MainActivity.this,MeActivity.class);
					overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
					view.getContext().startActivity(intent);
				}
				else {
					Intent intent = new Intent(MainActivity.this,LoginActivity.class);
					overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
					view.getContext().startActivity(intent);
				}
			}
		});
		
		//
		buttonSetting = (Button)findViewById(R.id.button_setting);
		buttonSetting.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(MainActivity.this,SettingActivity.class);
				overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
				startActivity(intent);
			}
		});
		ViewTreeObserver vtoRelativeLayoutMain = relativeLayout.getViewTreeObserver();
		vtoRelativeLayoutMain.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

			@SuppressLint("NewApi")
			@Override
			public void onGlobalLayout() {
				// TODO 自动生成的方法存根
				logoImageView.getLayoutParams().width=relativeLayout.getWidth()/3;
				logoImageView.getLayoutParams().height=relativeLayout.getWidth()/3;
				
				ImageButton1.getLayoutParams().width = (relativeLayout.getWidth())/2-10;
				ImageButton1.getLayoutParams().height =(relativeLayout.getWidth()-10)/2-10;
				ImageButton2.getLayoutParams().width = (relativeLayout.getWidth())/2-10;
				ImageButton2.getLayoutParams().height =(relativeLayout.getWidth()-10)/2-10;
				ImageButton3.getLayoutParams().width = (relativeLayout.getWidth())/2-10;
				ImageButton3.getLayoutParams().height =(relativeLayout.getWidth()-10)/2-10;
				ImageButton4.getLayoutParams().width = (relativeLayout.getWidth())/2-10;
				ImageButton4.getLayoutParams().height =(relativeLayout.getWidth()-10)/2-10;
				
	            logoImageView.setX(relativeLayout.getWidth()/2-logoImageView.getWidth()/2);
	            logoImageView.setY(40);
	            
	            ImageButton1.setX(5);
	            ImageButton1.setY(logoImageView.getY()+logoImageView.getHeight()+10);
	            
	            ImageButton2.setX(relativeLayout.getWidth()/2);
	            ImageButton2.setY(ImageButton1.getY());
	            
	            ImageButton3.setX(5);
	            ImageButton3.setY(ImageButton1.getY()+ImageButton1.getHeight()+5);
	            
	            ImageButton4.setX(relativeLayout.getWidth()/2);
	            ImageButton4.setY(ImageButton3.getY());

			}
			
		});
	}
	
	
}
	

	



