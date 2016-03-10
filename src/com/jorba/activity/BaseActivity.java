package com.jorba.activity;

import com.jorba.R;
import com.jorba.other.NavigationBar;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;


public class BaseActivity extends Activity {

	private NavigationBar navigationBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
		
		navigationBar  = (NavigationBar)findViewById(R.id.navigationBar);
		navigationBar.getButtonLeft().setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				// TODO 自动生成的方法存根
				finish();
			}
		});
		
		navigationBar.getButtonRight().setVisibility(View.INVISIBLE);
	}
	
	public Button getButtonLeft(){
		return navigationBar.getButtonLeft();
	}
	
	public Button getButtonRight(){
		return navigationBar.getButtonRight();
	}
	
	public TextView getTextViewTitle(){
		return navigationBar.getTextViewTitle();
	}
	
}
