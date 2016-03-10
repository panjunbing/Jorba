package com.jorba.activity;

import com.jorba.R;
import com.jorba.other.NavigationBar;

import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public  class DatingActivity extends FragmentActivity  {

	private NavigationBar navigationBar;
	private Button buttonRight;
	private Button buttonLeft;
	private TextView textViewTitle;
	private ImageView imageViewMain;
	private ImageView imageViewFriend;
	private ImageView imageViewInvitations;

	
	// 定义Fragment页面
	private DatingMainActivity datingMainActivity;
	private DatingFriendsActivity datingFriendActivity;
	private DatingInvitationsActivity  datingInvitationsActivity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dating);
		
		initeView();
		
		//默认在主界面
		mainClick();
	}


	private void initeView() {
		// TODO 自动生成的方法存根
		navigationBar = (NavigationBar)findViewById(R.id.navigationBar_dating);
		buttonLeft = navigationBar.getButtonLeft();
		buttonRight = navigationBar.getButtonRight();
		textViewTitle = navigationBar.getTextViewTitle();
		
		buttonLeft.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				// TODO 自动生成的方法存根
				finish();
			}
		});
		
		imageViewMain = (ImageView)findViewById(R.id.image_main);
		imageViewFriend = (ImageView)findViewById(R.id.image_friends);
		imageViewInvitations = (ImageView)findViewById(R.id.image_invitations);
		
		imageViewMain.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				mainClick();
			}
		});
		
		imageViewFriend.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				friendsClick();
			}
		});
		
		imageViewInvitations.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				invitationsClick();
			}
		});
	}
	
	private void mainClick() {
		// TODO 自动生成的方法存根
		// 实例化Fragment页面
		datingMainActivity = new DatingMainActivity();
		// 得到Fragment事务管理器
		FragmentTransaction fragmentTransaction = this
				.getSupportFragmentManager().beginTransaction();
		// 替换当前的页面
		fragmentTransaction.replace(R.id.frame_content, datingMainActivity);
		// 事务管理提交
		fragmentTransaction.commit();
		
		textViewTitle.setText("约吧");
		buttonRight = navigationBar.getButtonRight();
		buttonRight.setText("发起邀请");
		buttonRight.setVisibility(View.VISIBLE);
		buttonRight.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				// TODO 自动生成的方法存根  
				Intent intent = new Intent(DatingActivity.this,DatingSendActivity.class);
				startActivity(intent);
			}
		});
	}
	
	private void friendsClick() {
		// TODO 自动生成的方法存根
		// 实例化Fragment页面
		datingFriendActivity = new DatingFriendsActivity();
		// 得到Fragment事务管理器
		FragmentTransaction fragmentTransaction = this
				.getSupportFragmentManager().beginTransaction();
		// 替换当前的页面
		fragmentTransaction.replace(R.id.frame_content, datingFriendActivity);
		// 事务管理提交
		fragmentTransaction.commit();
		textViewTitle.setText("联系人");
		buttonRight.setText("+");
		buttonRight.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				Toast.makeText(getApplicationContext(), "+++!", Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	private void invitationsClick() {
		// TODO 自动生成的方法存根
		// 实例化Fragment页面
		datingInvitationsActivity = new DatingInvitationsActivity();
		// 得到Fragment事务管理器
		FragmentTransaction fragmentTransaction = this
				.getSupportFragmentManager().beginTransaction();
		// 替换当前的页面
		fragmentTransaction.replace(R.id.frame_content, datingInvitationsActivity);
		// 事务管理提交
		fragmentTransaction.commit();
		textViewTitle.setText("收到邀请");
		buttonRight.setVisibility(View.INVISIBLE);
	}


}
