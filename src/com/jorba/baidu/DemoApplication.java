package com.jorba.baidu;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

public class DemoApplication extends Application {
	private static DemoApplication application = null;

	@Override
	public void onCreate() {
		super.onCreate();
		application = this;
		// 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
		SDKInitializer.initialize(this);
	}

	public static DemoApplication getApplication() {
		return application;
	}

}