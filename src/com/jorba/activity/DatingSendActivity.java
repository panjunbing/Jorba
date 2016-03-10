package com.jorba.activity;

import com.jorba.R;
import com.jorba.asyncTask.SendDatingAsyncTask;
import com.jorba.baidu.LocationDemo;
import com.jorba.data.Data;
import com.jorba.other.NavigationBar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class DatingSendActivity extends Activity {

	private NavigationBar navigationBar;
	private LinearLayout linearLayout;
	private EditText editText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dating_send);
		
		navigationBar = (NavigationBar) findViewById(R.id.navigationBar);
		navigationBar.getButtonRight().setText("取消");
		
		editText = (EditText) findViewById(R.id.editText);
		
		Button buttonRightButton = navigationBar.getButtonRight();
		buttonRightButton.setText("发送");
		buttonRightButton.setVisibility(View.VISIBLE);
		buttonRightButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				// TODO 自动生成的方法存根
				SendDatingAsyncTask sendDatingAsyncTask = new SendDatingAsyncTask(view.getContext());
				sendDatingAsyncTask.execute(Data.getUsername(),editText.getText().toString());
			}
		});
		
		navigationBar.getTextViewTitle().setText("发送邀请");
		
		linearLayout = (LinearLayout) findViewById(R.id.linearLayout_location);
		linearLayout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(v.getContext(),LocationDemo.class);
				startActivity(intent);
				finish();
			}
		});
		
	}

}
