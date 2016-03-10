package com.jorba.asyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import com.jorba.data.Data;

import android.os.AsyncTask;
import android.widget.TextView;

public class MeAsyncTask extends AsyncTask<String, Void, String> {

	private String url;
	private String string;
	private TextView textViewName;
	private TextView textViewSex;
	private TextView textViewSchool;
	private TextView textViewRegion;
	private TextView textViewAbout;
	
	public MeAsyncTask(TextView textViewName,TextView textViewSex,TextView textViewSchool,TextView textViewRegion,TextView textViewAbout) {
		this.textViewName = textViewName;
		this.textViewSex = textViewSex;
		this.textViewSchool = textViewSchool;
		this.textViewRegion = textViewRegion;
		this.textViewAbout = textViewAbout;
	}
	
	@Override
	protected void onPostExecute(String result) {
		// TODO 自动生成的方法存根
		
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(result);
			textViewName.setText(jsonObject.getString("name")+"  >");
			textViewSex.setText(jsonObject.getString("sex")+">");
			textViewSchool.setText(jsonObject.getString("school")+"  >");
			textViewRegion.setText(jsonObject.getString("region")+"  >");
			textViewAbout.setText(jsonObject.getString("about")+"  >");
		} catch (JSONException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}	
	}

	@Override
	protected void onPreExecute() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	protected String doInBackground(String... arg0) {
		// TODO 自动生成的方法存根
		url = Data.getUrl()+"/JorbaServlet/MeServlet";
		try {
			URL httpUrl = new URL(url);
			
			HttpURLConnection httpURLConnection = (HttpURLConnection) httpUrl.openConnection();
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setReadTimeout(1000);
			
			OutputStream outputStream = httpURLConnection.getOutputStream();
			String content = "username="+Data.getUsername();
			outputStream.write(content.getBytes());

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
