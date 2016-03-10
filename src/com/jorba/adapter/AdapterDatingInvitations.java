package com.jorba.adapter;

import java.util.ArrayList;

import com.jorba.R;
import com.jorba.contacts.ContactsDatingInvitations;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterDatingInvitations extends BaseAdapter{
	
	private ArrayList<ContactsDatingInvitations> list;
	private Context context;
	
	public AdapterDatingInvitations(Context context,ArrayList<ContactsDatingInvitations> list) {
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
		ContactsDatingInvitations c = list.get(index);
		if (view == null) {
			//设置布局
			view = LayoutInflater.from(context).inflate(R.layout.listview_invitations,null);
		}
		
		ImageView imageView_invitations_head = (ImageView) view.findViewById(R.id.imageView_invitations_head);
		TextView  TextView_invitations_name = (TextView) view.findViewById(R.id.TextView_invitations_name);
		TextView  textView_invitations_time = (TextView) view.findViewById(R.id.textView_invitations_time);

		imageView_invitations_head.setImageResource(c.getHead());
		TextView_invitations_name.setText(c.getName());
		textView_invitations_time.setText(c.getTime());

		
		// TODO 自动生成的方法存根
		return view;
	}
}