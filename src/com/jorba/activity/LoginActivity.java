package com.jorba.activity;

import com.jorba.R;
import com.jorba.asyncTask.LoginAsyncTask;
import com.jorba.other.NavigationBar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {

	private Button buttonLogin;
	private TextView textViewRegist;
	private EditText editTextUserName;
	private EditText editTextPassword;
	private NavigationBar navigationBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		initView();
		
	}

	private void initView() {
		// TODO 自动生成的方法存根
		
		navigationBar = (NavigationBar) findViewById(R.id.navigationBar);
		
		
		//返回按钮
		navigationBar.getButtonLeft().setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				finish();
			}
		});
		
		//标题
		navigationBar.getTextViewTitle().setText("登入");
		
		//登入按钮
		buttonLogin = (Button)findViewById(R.id.button_login);
		buttonLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO 自动生成的方法存根
				login();
			}
		});
		
		//注册按钮
		textViewRegist = (TextView)findViewById(R.id.textView_login_regist);
		textViewRegist.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(LoginActivity.this,RegistActivity.class);
				startActivity(intent);
			}
		});
		
		editTextUserName = (EditText)findViewById(R.id.editText_login_userName);
		editTextPassword = (EditText)findViewById(R.id.editText_login_password);
	}

	private void login() {
		// TODO 自动生成的方法存根

		LoginAsyncTask loginAsyncTask = new LoginAsyncTask(this);
		loginAsyncTask.execute(editTextUserName.getText().toString(),editTextPassword.getText().toString());
		
	}
	
}
