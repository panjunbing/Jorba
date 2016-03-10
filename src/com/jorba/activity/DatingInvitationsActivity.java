package com.jorba.activity;

import java.util.ArrayList;

import com.jorba.R;
import com.jorba.adapter.AdapterDatingInvitations;
import com.jorba.contacts.ContactsDatingInvitations;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class DatingInvitationsActivity extends Fragment {

	private ListView listView; 

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view  = inflater.inflate(R.layout.activity_dating_base, container, false);

		listView = (ListView)view.findViewById(R.id.listView_dating_base);
		listView.setAdapter(new AdapterDatingInvitations(view.getContext(),getDatas()));

		return view;
	}

	private ArrayList<ContactsDatingInvitations> getDatas() {
		ArrayList<ContactsDatingInvitations> list = new ArrayList<ContactsDatingInvitations>();
		list.add(new ContactsDatingInvitations(R.drawable.ic_launcher,"hey嘉浩","30分钟前"));
		list.add(new ContactsDatingInvitations(R.drawable.ic_launcher,"SuperXia浩","45分钟前"));
		list.add(new ContactsDatingInvitations(R.drawable.ic_launcher,"ZsmYyyyyy","58分钟前"));

		return list;

	}
}

