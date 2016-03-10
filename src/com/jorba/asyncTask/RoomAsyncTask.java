package com.jorba.asyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jorba.R;
import com.jorba.adapter.AdapterDating;
import com.jorba.contacts.ContactsDating;
import com.jorba.data.Data;
import com.jorba.data.Room;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

public class RoomAsyncTask extends AsyncTask<String, Void, String> {

	private String url;
	private String string;
	private Room[] room;
	private ListView listView; 
	private Context context;
	
	public RoomAsyncTask(Room[] room,ListView listView,Context context) {
		this.room = room;
		this.listView = listView;
		this.context = context;
	}
	
	@Override
	protected void onPostExecute(String result) {
		// TODO 自动生成的方法存根

		if(result == null){
			Toast.makeText(context, "连接超时= =", 1).show();
		}

		else{
			try {
				JSONObject jsonObject = new JSONObject(result);
				JSONArray room = jsonObject.getJSONArray("room");
				for (int i = 0; i < room.length(); i++) {
					this.room[i] = new Room();
					this.room[i].setRoomName(room.getJSONObject(i).getString("RoomName"));
					this.room[i].setName(room.getJSONObject(i).getString("Name"));
					this.room[i].setContent(room.getJSONObject(i).getString("Content"));
				}
			} catch (JSONException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}	
			ArrayList<ContactsDating> list = new ArrayList<ContactsDating>();
			for (int i = 0;room[i] != null; i++) {
				list.add(new ContactsDating(R.drawable.ic_launcher,room[i].getRoomName(),"现在","目前人数1",room[i].getContent()));
			}
			listView.setAdapter(new AdapterDating(context, list));
		}
	}
	@Override
	protected void onPreExecute() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	protected String doInBackground(String... arg0) {
		// TODO 自动生成的方法存根
		url =  Data.getUrl()+"/JorbaServlet/RoomServlet";
		try {
			URL httpUrl = new URL(url);
			
			HttpURLConnection httpURLConnection = (HttpURLConnection) httpUrl.openConnection();
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setReadTimeout(5000);
			
//			OutputStream outputStream = httpURLConnection.getOutputStream();
//			String content = "username="+Data.getUsername();
//			outputStream.write(content.getBytes());

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
			StringBuffer stringBuffer = new StringBuffer();
			String str;

			while ((str = bufferedReader.readLine()) != null ) {
				stringBuffer.append(str);
			}
			
			string = stringBuffer.toString();
			return string;
			
		} catch (MalformedURLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		
		return null;
	}


}
