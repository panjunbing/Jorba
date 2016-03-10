package com.jorba.activity;

import com.jorba.R;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class WerewolfActivity2 extends Activity {
	private RelativeLayout relativeLayout;
	private TextView textView1;
	private TextView textView2;
	private EditText editText ;
	private Button nextButton;
	private Button previousButton;
	private Button thenextButton;
	private Button exitbButton;
	private String playername[];
	private int number;
	private int i=0;
    private int character[];
//    private int player[];
    private ImageView logoImageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		setContentView(R.layout.activity_langren2);
		relativeLayout=(RelativeLayout)findViewById(R.id.relativeLayoutMain2);
		exitbButton=(Button)findViewById(R.id.button_exit2);
		textView1 =new TextView(this);
		textView2 =new TextView(this);
		textView1.setText("③依次输入玩家的昵称：");
		
		TextPaint tp1 = textView1.getPaint(); 
		tp1.setFakeBoldText(true);
		textView1.setTextSize(25);
		
		editText=new EditText(this);
		editText.setHint("请输入玩家昵称");
		Intent intent = getIntent();
		number  = Integer.valueOf(intent.getIntExtra("number",-1));
		character=intent.getIntArrayExtra("character");
		playername = new String[number];
//		player=new int [number];
		nextButton =new Button(this);
		nextButton.setText("下一个");
		thenextButton = new Button(this);
		thenextButton.setText("开始游戏");
		thenextButton.setTextColor(0xffFF0000);
		thenextButton.setTextSize(20);
		previousButton=new Button(this);
		previousButton.setBackgroundResource(R.drawable.back_logo);
		textView2.setText("第一个人");
		
		TextPaint tp2 = textView2.getPaint(); 
		tp2.setFakeBoldText(true);
		textView2.setTextSize(20);
		
		logoImageView=new ImageView(this);
		logoImageView .setImageResource(R.drawable.langrenlogo);
		relativeLayout.addView(logoImageView);
		final DisplayMetrics dispalyMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dispalyMetrics);
		exitbButton.getLayoutParams().width = dispalyMetrics.widthPixels/10;
		exitbButton.getLayoutParams().height = dispalyMetrics.widthPixels/10;
		
		exitbButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {

				Intent intent=new Intent(WerewolfActivity2.this,MainActivity.class);
				startActivity(intent);
				finish();
				
			}
		});
		

		
		nextButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {

				
				playername[i]=(i+1)+"."+editText.getText().toString();
				i=i+1;
				textView2.setText("第"+(i+1)+"个人");
				editText.setText(null);
				if (i==number) {
					relativeLayout.removeView(textView2);
					relativeLayout.removeView(editText);
					relativeLayout.removeView(nextButton);
					relativeLayout.removeView(textView1);

					relativeLayout.addView(thenextButton);					
				}
				
				
				
				
				
				
			}
		});
		
	    previousButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {

			Intent intent=new Intent(WerewolfActivity2.this,WerewolfActivity.class);
			startActivity(intent);
				
			}
		});
	    thenextButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent=new Intent(WerewolfActivity2.this,WerewolfActivity3.class);
				Bundle bundle = new Bundle();
				bundle.putInt("number", number);
				bundle.putStringArray("playername", playername);
				bundle.putIntArray("character", character);
				intent.putExtras(bundle);
				startActivity(intent);
				
			}
		});
		relativeLayout.addView(textView1);
		relativeLayout.addView(textView2);
		relativeLayout.addView(editText);
		relativeLayout.addView(nextButton);
		relativeLayout.addView(previousButton);
		
		ViewTreeObserver vtoRelativeLayoutMain = relativeLayout.getViewTreeObserver();
		vtoRelativeLayoutMain.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

			@SuppressLint("NewApi")
			@Override
			public void onGlobalLayout() {
				logoImageView.getLayoutParams().width = (relativeLayout.getWidth()-10)/2;
				logoImageView.getLayoutParams().height =(relativeLayout.getWidth()-10)/2;
				previousButton.getLayoutParams().width = (relativeLayout.getWidth())/10;
				previousButton .getLayoutParams().height =(relativeLayout.getWidth())/10;
				editText.getLayoutParams().width=relativeLayout.getWidth()/2;
				logoImageView.setX(relativeLayout.getWidth()/2-logoImageView.getWidth()/2);
				logoImageView.setY(5);
				textView1.setX(0);
				textView1.setY(relativeLayout.getHeight()/2);
				
				textView2.setX(textView1.getWidth()/5);
				textView2.setY(textView1.getY()+textView1.getHeight()*2);
				
				editText.setX(textView2.getX()+textView2.getWidth());
				editText.setY(textView2.getY()-textView2.getHeight()/3);
				
				nextButton.setX(relativeLayout.getWidth()/2-nextButton.getWidth()/2);
				nextButton.setY(editText.getY()+editText.getHeight()*2);
				
				thenextButton.setWidth(relativeLayout.getWidth()/3);
				thenextButton.setHeight(relativeLayout.getWidth()/8);
				thenextButton.setX(relativeLayout.getWidth()/2-thenextButton.getWidth()/2);
				thenextButton .setY(relativeLayout .getHeight()-thenextButton.getHeight()*3);
				
				previousButton.setX(0);
				previousButton.setY(0);

			}
			
		});
	    
	}



}
