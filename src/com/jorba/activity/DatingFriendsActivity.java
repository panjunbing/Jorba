package com.jorba.activity;

import java.util.ArrayList;
import java.util.List;

import com.jorba.R;
import com.jorba.other.ChineseToPinyin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

	
public class DatingFriendsActivity extends Fragment implements OnItemClickListener{

	String[] letter={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","#"};
	String[] nameLetter;
	String[] name={"罗嘉浩","夏浩","潘俊冰","薛大喧","梁晓涛","王东帅","周思敏","刘丙乙","@da"};
	List<String> letterToName=new ArrayList<String>();
	int count;
	ListView listView;
	ListView listViewRight;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view =  inflater.inflate(R.layout.activity_dating_friends, container, false);
		
		ChineseToPinyin objChineseToPinyin = new ChineseToPinyin();
		nameLetter = new String[name.length];
		for(int i = 0;i<nameLetter.length;i++)
			nameLetter[i] = objChineseToPinyin.getPinyin(name[i]);
			
		
		String str="";
		for(int i=0;i<letter.length;i++){
			str=letter[i];
			boolean isAddLetter=false;
			for(int j=0;j<nameLetter.length;j++){
				if(str.equals(nameLetter[j])){
					if(!isAddLetter){
						letterToName.add(str);
						isAddLetter=true;
					}
					letterToName.add(name[j]);
				}
			}
		}
		
		listView=(ListView)view.findViewById(R.id.listView1);
		listView.setAdapter(new AdapterDatingFriends());
		listView.setOnItemClickListener(this);
		
		listViewRight=(ListView)view.findViewById(R.id.listView2);
		listViewRight.setAdapter(new AdapterDatingFriends2());
		listViewRight.setOnItemClickListener(this);
		
		return view;
	}
	
	class AdapterDatingFriends2 extends BaseAdapter{
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return letter.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return letter[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view=LayoutInflater.from(DatingFriendsActivity.this.getActivity()).inflate(R.layout.activity_dating_friends_letter_list, null);
			TextView textView=(TextView)view.findViewById(R.id.letterListTextView);
			textView.setText(letter[position]);
			textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,view.getHeight()/27);
			return view;
		}
		
	}
	
	class AdapterDatingFriends extends BaseAdapter{

		final static int TYPE_1=1;
		final static int TYPE_2=2;
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return letterToName.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return letterToName.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}
		
		@Override
		public int getItemViewType(int position) {
			for(int i=0;i<letter.length;i++){
				if(letterToName.get(position).equals(letter[i])){
					return TYPE_1;
				}
			}
			return TYPE_2;
		}
		
		@Override
		public int getViewTypeCount() {
			return 3;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder1 vh1=null;
			ViewHolder2 vh2=null;
			int type=getItemViewType(position);
			if(convertView==null){
				switch (type) {
				case TYPE_1:
					convertView=LayoutInflater.from(DatingFriendsActivity.this.getActivity()).inflate(R.layout.activity_dating_friends_letter, null);
					vh1=new ViewHolder1();
					vh1.tv=(TextView)convertView.findViewById(R.id.letterTextView);
					convertView.setTag(vh1);
					break;
				case TYPE_2:
					convertView=LayoutInflater.from(DatingFriendsActivity.this.getActivity()).inflate(R.layout.activity_dating_friends_name, null);
					vh2=new ViewHolder2();
					vh2.tv=(TextView)convertView.findViewById(R.id.cityTextView);
					convertView.setTag(vh2);
					break;
				default:
					break;
				}
			}else{
				switch (type) {
				case TYPE_1:
					vh1=(ViewHolder1)convertView.getTag();
					break;
				case TYPE_2:
					vh2=(ViewHolder2)convertView.getTag();
					break;
				default:
					break;
				}
			}
			switch (type) {
			case TYPE_1:
				vh1.tv.setText(letterToName.get(position));
				break;
			case TYPE_2:
				vh2.tv.setText(letterToName.get(position));
				break;
			default:
				break;
			}
			return convertView;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		switch (parent.getId()) {
		case R.id.listView1:
			boolean  isLetter=false;
			for(int i=0;i<letter.length;i++){
				if(letter[i].equals(letterToName.get(position))){
					isLetter=true;
					break;
				}
			}
			if(!isLetter){
				Toast.makeText(view.getContext(), letterToName.get(position), Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.listView2:
			for(int i=0;i<letterToName.size();i++){
				if(letter[position].equals(letterToName.get(i))){
					listView.setSelection(i);
					break;
				}
			}
			break;
		default:
			break;
		}
	}
}

class ViewHolder{
	TextView tv;
	LinearLayout ll;
}
class ViewHolder1{
	TextView tv;
}
class ViewHolder2{
	TextView tv;
}
