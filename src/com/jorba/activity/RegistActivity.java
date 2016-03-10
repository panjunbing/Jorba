package com.jorba.activity;

import com.jorba.R;
import com.jorba.asyncTask.RegistAsyncTask;
import com.jorba.other.NavigationBar;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;


public class RegistActivity extends Activity {

	private NavigationBar navigationBar;
	private Button buttonRegist;
	private EditText editTextUserName;
	private EditText editTextPassword;
	private EditText editTextPassword2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regist);
		
		initView();
		
	}
	private void initView() {
		// TODO 自动生成的方法存根
		
		//导航栏及返回按钮
		navigationBar  = (NavigationBar)findViewById(R.id.navigationBar);
		navigationBar.getButtonLeft().setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				// TODO 自动生成的方法存根
				finish();
			}
		});
		
		editTextUserName = (EditText) findViewById(R.id.editText_regist_userName);
		editTextPassword = (EditText) findViewById(R.id.editText_regist_password);
		editTextPassword2 = (EditText) findViewById(R.id.editeText_regist_passworda2);
		
		
		//标题
		navigationBar.getTextViewTitle().setText("注册");
		
		//注册按钮
		buttonRegist =(Button)findViewById(R.id.button_regist);
		buttonRegist.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				regiset();
			}
		});
	}
	
	
	protected void regiset() {
		// TODO 自动生成的方法存根
		if(editTextPassword.getText().toString().equals(editTextPassword2.getText().toString())){
			RegistAsyncTask registAsyncTask = new RegistAsyncTask(this);
			registAsyncTask.execute(editTextUserName.getText().toString(),editTextPassword.getText().toString());
		}
		else {		
			Toast.makeText(this, "两次密码不一样！", Toast.LENGTH_SHORT).show();
		}
	}
	
	
}
