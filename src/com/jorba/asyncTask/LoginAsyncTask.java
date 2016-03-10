package com.jorba.asyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.jorba.activity.LoginActivity;
import com.jorba.data.Data;
import com.jorba.other.DatabaseManager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.Toast;


public class LoginAsyncTask extends AsyncTask<String, Void,Integer> {

	private String username;
	private String password;
	private String url;
	private int result;
	private Context context;
	private SQLiteDatabase db;
	
	public LoginAsyncTask(Context context) {
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
			Toast.makeText(context, "账号不存在", Toast.LENGTH_SHORT).show();
			break;
		case 1:
			Toast.makeText(context, "密码错误", Toast.LENGTH_SHORT).show();
			break;
		case 2:
			Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
			((LoginActivity) context).finish();
			
			DatabaseManager.initManager(context);
			DatabaseManager dm = DatabaseManager.getManager();
			db = dm.getDatabase("JorbaData.db");
			db.execSQL("update user set Login=1 where UserName='"+username+"'");
			Data.setUsername(username);
			
			
			break;
		default:
			break;
		}

	}

	private Integer doPost() {
		// TODO 自动生成的方法存根
		try {
//			url = InetAddress.getLocalHost()+"/JorbaServlet/LoginServlet";
			url = Data.getUrl()+"/JorbaServlet/LoginServlet";
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
