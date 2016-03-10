package com.jorba.activity;

import com.jorba.R;
import com.jorba.data.Data;
import com.jorba.other.DatabaseManager;
import com.jorba.other.NavigationBar;
import com.jorba.other.SlideSwitch;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class SettingActivity extends Activity {

	private NavigationBar navigationBar; 
	private SlideSwitch slideSwitchMusic;
	private SlideSwitch slideSwitchSound;
	private RelativeLayout RelativeLayoutAboutUs;
	private Button buttonExit;
	
	private SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		
		initView();
	}
	
	private void initView() {
		
		//导航栏及标题
		navigationBar = (NavigationBar)findViewById(R.id.navigationBar);
		navigationBar.getTextViewTitle().setText("设置");
		
		//返回按钮
		navigationBar.getButtonLeft().setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				// TODO 自动生成的方法存根
				finish();
			}
		});
		
		//背景音乐按钮
		slideSwitchMusic = (SlideSwitch)findViewById(R.id.sideSwitch_music);
		
		//音效按钮
		slideSwitchMusic = (SlideSwitch)findViewById(R.id.sideSwitch_sound);
		
		//关于我们按钮
		RelativeLayoutAboutUs = (RelativeLayout)findViewById(R.id.RelativeLayout_aboutMe);
		RelativeLayoutAboutUs.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(SettingActivity.this,AboutUsActivity.class);
				SettingActivity.this.startActivity(intent);
			}
		});
		
		
		buttonExit = (Button)findViewById(R.id.button_exit);
		buttonExit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO 自动生成的方法存根
				DatabaseManager.initManager(view.getContext());
				DatabaseManager dm = DatabaseManager.getManager();
				db = dm.getDatabase("JorbaData.db");
				db.execSQL("update user set Login=0 where UserName='"+Data.getUsername()+"'");
				Data.setUsername(null);
				Toast.makeText(getApplicationContext(), "退出当前账号成功", Toast.LENGTH_SHORT).show();
				SettingActivity.this.finish();
			}
		});
	}
}
