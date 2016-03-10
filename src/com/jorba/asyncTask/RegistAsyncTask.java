package com.jorba.asyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.jorba.activity.RegistActivity;
import com.jorba.data.Data;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;


public class RegistAsyncTask extends AsyncTask<String, Void,Integer> {

	private String username;
	private String password;
	private String url;
	private int result;
	private Context context;
	
	public RegistAsyncTask(Context context) {
		// TODO 自动生成的构造函数存根
		this.context = context;
	}

	@Override
	protected Integer doInBackground(String... params) {
		// TODO 自动生成的方法存根
		username = params[0];
		password = params[1];
		
		return 	doPost();
	}

	@Override
	protected void onPreExecute() {
		// TODO 自动生成的方法存根
		super.onPreExecute();
	}

	protected void onPostExecute(Integer result) {
		// TODO 自动生成的方法存根
		
		switch (result) {
		case 0:
			Toast.makeText(context, "账号已经存在", Toast.LENGTH_SHORT).show();
			break;
		case 1:
			Toast.makeText(context, "注册成功！", Toast.LENGTH_SHORT).show();
			((RegistActivity) context).finish();
			break;
		default:
			break;
		}
		
	}

	private Integer doPost() {
		// TODO 自动生成的方法存根
		try {

			url =  Data.getUrl()+"/JorbaServlet/RegistServlet";
			URL httpUrl = new URL(url);
			
			HttpURLConnection httpURLConnection = (HttpURLConnection) httpUrl.openConnection();
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setReadTimeout(1000);
			
			OutputStream outputStream = httpURLConnection.getOutputStream();
			String content = "username="+username+"&password="+password;
			outputStream.write(content.getBytes());

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
			StringBuffer stringBuffer = new StringBuffer();
			String str;

			while ((str = bufferedReader.readLine()) != null ) {
				stringBuffer.append(str);
			}
			
			return ( Integer.valueOf(stringBuffer.toString()) ) ;
			
		} catch (MalformedURLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return null;

	}

	public int getResult() {
		return result;
	}
}
