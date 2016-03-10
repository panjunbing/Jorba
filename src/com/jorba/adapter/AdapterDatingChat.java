package com.jorba.adapter;

import java.util.List;

import com.jorba.R;
import com.jorba.activity.util.FaceMessageUtil;
import com.jorba.contacts.ContactsDatingChat;
import com.jorba.other.FaceParser;

import android.content.Context;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * 消息ListView的Adapter
 * 
 * @author way
 */
public class AdapterDatingChat extends BaseAdapter {

	public static interface IMsgViewType {
		int IMVT_COM_MSG = 0;// 收到对方的消息
		int IMVT_TO_MSG = 1;// 自己发送出去的消息
		int IMVT_TIME=2;       //时间
	}

	private static final int ITEMCOUNT = 3;// 消息类型的总数
	private List<ContactsDatingChat> coll;// 消息对象数组

	private LayoutInflater mInflater;
	private TextView message;
	private ImageView head;
	private TextView time;
	private Context context;


	public AdapterDatingChat(Context context, List<ContactsDatingChat> coll) {
		this.coll = coll;
		this.context = context;
		mInflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return coll.size();
	}

	public Object getItem(int position) {
		return coll.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	/**
	 * 得到Item的类型，是对方发过来的消息，还是自己发送出去的
	 */
	public int getItemViewType(int position) {
		ContactsDatingChat c = coll.get(position);

		switch (c.getType())  {
		case 0:
			return IMsgViewType.IMVT_COM_MSG;
		case 1:
			return IMsgViewType.IMVT_TO_MSG;
		case 2:
			return IMsgViewType.IMVT_TIME;
		default:
			return -1;
		}
	}

	/**
	 * Item类型的总数
	 */
	public int getViewTypeCount() {
		return ITEMCOUNT;
	}

	public View getView(int position, View view, ViewGroup parent) {

		ContactsDatingChat c = coll.get(position);
		int type = getItemViewType(position);
		
		switch (type)  {
		case 0:
			view = mInflater.inflate(R.layout.listview_dating_chat_left, null);
			break;
		case 1:
			view = mInflater.inflate(R.layout.listview_dating_chat_right, null);
			break;
		case 2:
			view = mInflater.inflate(R.layout.listview_dating_chat_time, null);
			time = (TextView)view.findViewById(R.id.textView_dating_chat_time);
			time.setText(c.getTime());
			return view;	
		}
		
		head = (ImageView)view.findViewById(R.id.imageView_dating_chat_head);
		message = (TextView) view.findViewById(R.id.textView_dating_chat);

		head.setImageResource(c.getHead());
		String unicode = FaceParser.getInstance(context).parseEmoji(c.getMessage());
		SpannableString spannableString = FaceMessageUtil.getExpressionString(this.context, unicode);
		message.setText(spannableString);
		
		return view;
	}
}
