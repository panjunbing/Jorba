package com.jorba.adapter;

import java.util.ArrayList;

import com.jorba.R;
import com.jorba.activity.DatingChatActivity;
import com.jorba.contacts.ContactsDating;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterDating extends BaseAdapter{
	
	private ArrayList<ContactsDating> list;
	private Context context;
	
	public AdapterDating(Context context,ArrayList<ContactsDating> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO 自动生成的方法存根
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public long getItemId(int index) {
		// TODO 自动生成的方法存根
		return index;
	}

	@Override
	public View getView(int index, View view, ViewGroup viewGroup) {
		ContactsDating c = list.get(index);
		if (view == null) {
			//设置布局
			view = LayoutInflater.from(context).inflate(R.layout.listview_dating,null);
		}
		
		ImageView imageView_daingHead = (ImageView) view.findViewById(R.id.imageView_daingHead);
		final TextView  textView_datingUserName = (TextView) view.findViewById(R.id.textView_datingUserName);
		TextView  textView_datingTime = (TextView) view.findViewById(R.id.textView_datingTime);
		TextView  textView_datingPeopleCount = (TextView) view.findViewById(R.id.textView_datingPeopleCount);
		TextView  textView_datingContent = (TextView) view.findViewById(R.id.textView_datingContent);
		Button button_joinRoom = (Button)view.findViewById(R.id.button_joinRoom);
		
		imageView_daingHead.setImageResource(c.getHead());
		textView_datingUserName.setText(c.getDatingUserName());
		textView_datingTime.setText(c.getDatingTime());
		textView_datingPeopleCount.setText(c.getDatingPeopleCount());
		textView_datingContent.setText(c.getDatingContent());
		button_joinRoom.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				Bundle bundle = new Bundle();
				bundle.putString("roomName", textView_datingUserName.getText().toString()+"的房间");
				Intent intent = new Intent(context,DatingChatActivity.class);
				intent.putExtras(bundle);
				context.startActivity(intent);
			}
		});
		
		// TODO 自动生成的方法存根
		return view;
	}


}
