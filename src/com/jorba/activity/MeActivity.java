package com.jorba.activity;

import com.jorba.R;
import com.jorba.asyncTask.MeAsyncTask;
import com.jorba.data.Data;
import com.jorba.other.NavigationBar;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.app.Activity;


public class MeActivity extends Activity {

	private NavigationBar navigationBar;
	private TextView textViewUserName;
	private TextView textViewName;
	private TextView textViewSex;
	private TextView textViewSchool;
	private TextView textViewRegion;
	private TextView textViewAbout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_me);
		
		initView();
		
		MeAsyncTask meAsyncTask = new MeAsyncTask(textViewName,textViewSex,textViewSchool,textViewRegion,textViewAbout);
		meAsyncTask.execute();
		
			
	}
	
	private void initView() {
		// TODO 自动生成的方法存根
		
		//
		navigationBar  = (NavigationBar)findViewById(R.id.navigationBar);
		navigationBar.getButtonLeft().setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				// TODO 自动生成的方法存根
				finish();
			}
		});
		textViewUserName = (TextView)findViewById(R.id.textView_username);
		textViewUserName.setText(Data.getUsername());
		textViewName = (TextView)findViewById(R.id.textView_name);
		textViewSex = (TextView)findViewById(R.id.textView_sex);
		textViewSchool = (TextView)findViewById(R.id.textView_school);
		textViewRegion = (TextView)findViewById(R.id.textView_region);
		textViewAbout = (TextView)findViewById(R.id.textView_about);
		
		
	}
	
}
