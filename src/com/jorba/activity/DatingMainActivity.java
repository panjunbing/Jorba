package com.jorba.activity;

import com.jorba.R;
import com.jorba.asyncTask.RoomAsyncTask;
import com.jorba.data.Room;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class DatingMainActivity extends Fragment {

	private ListView listView; 
	private Room[] room;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view =  inflater.inflate(R.layout.activity_dating_base, container, false);

		listView = (ListView)view.findViewById(R.id.listView_dating_base);
		room = new Room[10];
		
		RoomAsyncTask roomAsyncTask = new RoomAsyncTask(room,listView,this.getActivity());
		roomAsyncTask.execute();

		return view;
	}
}

