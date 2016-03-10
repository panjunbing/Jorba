package com.jorba.asyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.jorba.activity.DatingSendActivity;
import com.jorba.data.Data;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;


public class SendDatingAsyncTask extends AsyncTask<String,Void,String> {

	private String username;
	private String content;
	private Context context;
	
	public SendDatingAsyncTask(Context context) {
		// TODO 自动生成的构造函数存根
		this.context = context;
		content = null;
	}

	@Override
	protected String doInBackground(String... params) {
		// TODO 自动生成的方法存根
		username = params[0];
		content = params[1];
		return 	doPost();
	}

	@Override
	protected void onPreExecute() {
		// TODO 自动生成的方法存根
		super.onPreExecute();
	}

	protected void onPostExecute(String result) {
		// TODO 自动生成的方法存根
		((DatingSendActivity)context).finish();
//		if(result.equals("1"))
//			Toast.makeText(context, "发送成功",1).show();
//		else
//			Toast.makeText(context, "发送失败", 1).show();
	}

	private String doPost() {
		// TODO 自动生成的方法存根
		try {
//			url = InetAddress.getLocalHost()+"/JorbaServlet/LoginServlet";
			String url =  Data.getUrl()+"/JorbaServlet/SendDatingServlet";
			URL httpUrl = new URL(url);
			
			HttpURLConnection httpURLConnection = (HttpURLConnection) httpUrl.openConnection();
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setReadTimeout(1000);

			OutputStream outputStream = httpURLConnection.getOutputStream();
			String string = "username="+username+"&content="+content;
			outputStream.write(string.getBytes());

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
			StringBuffer stringBuffer = new StringBuffer();
			string = null;

			while ((string = bufferedReader.readLine()) != null ) {
				stringBuffer.append(string);
			}
			
			return stringBuffer.toString();
			
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
