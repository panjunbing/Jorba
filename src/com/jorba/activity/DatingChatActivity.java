package com.jorba.activity;

import java.util.ArrayList;

import com.jorba.R;
import com.jorba.activity.util.FaceMessageUtil;
import com.jorba.activity.util.Util;
import com.jorba.adapter.AdapterDatingChat;
import com.jorba.contacts.ContactsDatingChat;
import com.jorba.other.DatabaseManager;
import com.jorba.other.FaceSelectHelper;
import com.jorba.other.NavigationBar;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.SpannableString;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class DatingChatActivity extends Activity {

	private String lastTime;																								//上一条消息的时间（用来判断是否需要输出时间）
	
	private NavigationBar navigationBar;
	private Button buttonLeft;
	private Button buttonRight;
	private Button buttonSend;
	private Button buttonFace;
	private EditText editText;
	private TextView textViewTitle;
	private TextView textViewChat;
	
	private ListView listView;
	private AdapterDatingChat adapter;
	private ArrayList<ContactsDatingChat> list;
	
	private FaceSelectHelper mFaceHelper;
	private View addFaceToolView;
	private boolean isVisbilityFace;
	com.jorba.other.FaceSelectHelper.OnFaceOprateListener mOnFaceOprateListener;

	DatabaseManager dm;
	private SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dating_chat);
		
		initView();
		
		//数据库初始化
		DatabaseManager.initManager(this);
		dm = DatabaseManager.getManager();
		db = dm.getDatabase("JorbaData.db");
		
		//表情选择删除监听器
		mOnFaceOprateListener = new com.jorba.other.FaceSelectHelper.OnFaceOprateListener() {
			@Override
			public void onFaceSelected(SpannableString spanEmojiStr) {
				// TODO 自动生成的方法存根
				if (null != spanEmojiStr) {
					editText.append(spanEmojiStr);
				}
			}
			@Override
			public void onFaceDeleted() {
				// TODO 自动生成的方法存根
				int selection = editText.getSelectionStart();
				String text = editText.getText().toString();
				if (selection > 0) {
					String text2 = text.substring(selection - 1);
					if ("]".equals(text2)) {
						int start = text.lastIndexOf("[");
						int end = selection;
						editText.getText().delete(start, end);
						return;
					}
					editText.getText().delete(selection - 1, selection);
				}
			}
		};
		
		//聊天窗口
		list = new ArrayList<ContactsDatingChat>();
		adapter = new AdapterDatingChat(this, list);
		initData();
		listView = (ListView)findViewById(R.id.listView_dating_chat);
		listView.setAdapter(new AdapterDatingChat(this,list));
		listView.setSelection(adapter.getCount()-1);																									//显示最后一条
	}

	private void initView() {
		// TODO 自动生成的方法存根
		
		//表情列表弹出框
		addFaceToolView = (View) findViewById(R.id.add_tool);
		
		//导航栏及标题
		navigationBar = (NavigationBar)findViewById(R.id.navigationBar_dating_chat);
		textViewTitle = navigationBar.getTextViewTitle();
		Intent intent = getIntent();
		textViewTitle.setText(intent.getStringExtra("roomName"));

		
		//返回按钮
		buttonLeft = navigationBar.getButtonLeft();
		buttonLeft.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				close();
			}
		});
		
		//房间信息按钮
		buttonRight = navigationBar.getButtonRight();
		buttonRight.setVisibility(View.VISIBLE);
		buttonRight.setText("房间信息");
		buttonRight.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(DatingChatActivity.this,DatingChatInformationActivity.class);
				startActivity(intent);
			}
		});

		//发送按钮
		buttonSend = (Button)findViewById(R.id.button_dating_chat_send);
		buttonSend.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				// TODO 自动生成的方法存根
				send();
			}
		});
		
		//表情按钮
		buttonFace = (Button)findViewById(R.id.button_dating_chat_face);
		buttonFace.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				if (null == mFaceHelper) {
					mFaceHelper = new FaceSelectHelper(DatingChatActivity.this, addFaceToolView);
					mFaceHelper.setFaceOpreateListener(mOnFaceOprateListener);
				}
				if (isVisbilityFace) {
					isVisbilityFace = false;
					addFaceToolView.setVisibility(View.GONE);
				} else {
					isVisbilityFace = true;
					addFaceToolView.setVisibility(View.VISIBLE);
					hideInputManager(DatingChatActivity.this);
				}
			}
		});
		
		//文本输入框
		editText = (EditText)findViewById(R.id.edit_dating_chat);
		editText.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				isVisbilityFace = false;
				addFaceToolView.setVisibility(View.GONE);
				return false;
			}
		});
		
		//消息的文本框
//		textViewChat = (TextView)findViewById(R.id.textView_dating_chat);
//		textViewChat.setOnLongClickListener(new OnLongClickListener() {
//			@Override
//			public boolean onLongClick(View view) {
//				// TODO 自动生成的方法存根
//				Toast.makeText(DatingChatActivity.this, "!!", Toast.LENGTH_SHORT).show();
//				return false;
//			}
//		});
		
	}
	
	private void initData() {
		// 从数据库中读取内容
		Cursor cursor  = db.rawQuery("select message,type from chatData where chatName ='"+textViewTitle.getText().toString()+"'", null);
		while (cursor.moveToNext()) {
			list.add(new ContactsDatingChat(R.drawable.ic_launcher,cursor.getString(0),cursor.getInt(1)));
		}
		cursor.close();
	}

	private void send() {
		// 发送响应事件
		if(null !=editText.getText()){
			String message = FaceMessageUtil.convertToMsg(editText.getText(), DatingChatActivity.this);
			list.add(new ContactsDatingChat(R.drawable.ic_launcher,message,1));
			adapter.notifyDataSetChanged();
			listView.setSelection(listView.getCount()-1);
			editText.setText(null);
			
			//更新本地数据库内容
			ContentValues contentValues = new ContentValues();
			contentValues.put("chatname", textViewTitle.getText().toString());
			contentValues.put("message",message);
			contentValues.put("type", 1);
			contentValues.put("time", Util.getDate());
			db.insert("chatData", null, contentValues);
			
		}
	}
	
	private void delete(String message){
		//在窗口中删除


		//在本地数据库中删除
		db.execSQL("delete from chatData where message'="+message+"'");
		
	}
	
	// 隐藏软键盘
	public void hideInputManager(Context ct) {
		try {
			((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(((Activity) ct)
					.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		} catch (Exception e) {
		}
	}
	
	public void onBackPressed() {
		// 隐藏表情菜单的退出
		if (isVisbilityFace) {												
			isVisbilityFace = false;
			addFaceToolView.setVisibility(View.GONE);
			return;
		}
		close();
	}

	private void close() {
		// TODO 自动生成的方法存根
		dm.closeDatabase("JorbaData.db");
		finish();
	};
}
