package com.jorba.activity;

import com.jorba.R;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class WerewolfActivity3 extends Activity {
private int character[];
private String playername[];
private ImageView backView[];
private TextView nameTextView[];
private int number;
private int player[];
private int random;
private RelativeLayout relativeLayout;
private int imageId[];
private TextView textView1;
private TextView textView2;
private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_langren3);
		Intent intent = getIntent();
		number  = Integer.valueOf(intent.getIntExtra("number",-1));
		playername  =intent.getStringArrayExtra("playername");
		character=new int[number];
		character=intent.getIntArrayExtra("character");
		relativeLayout=(RelativeLayout)findViewById(R.id.relativelayoutMain3);
		backView = new ImageView[number];
		nameTextView=new TextView[number];
		imageId=new int [number];
		textView1=new TextView(this);
		textView1.setText("点击各自的牌查看角色");
		TextPaint tp1 = textView1.getPaint(); 
		tp1.setFakeBoldText(true);
		textView1.setTextSize(15);
		textView2=new TextView(this);
		textView2.setText("所有人查看完后，上帝则可开始主持游戏！");
		textView2.setTextColor(0xffEEEE00);
		TextPaint tp2 = textView1.getPaint(); 
		tp2.setFakeBoldText(true);
		textView2.setTextSize(20);
		relativeLayout.addView(textView1);
		relativeLayout.addView(textView2);
		button=new Button(this);
		button.setText("退出游戏");
		button.setTextColor(0xffFF0000);
		relativeLayout.addView(button);
		
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent=new Intent(WerewolfActivity3.this,MainActivity.class);
			
				startActivity(intent);
			}
		});
		
		
		for (int i = 0; i <number; i++) {
			
			backView[i]=new ImageView(this); 
			backView[i].setImageResource(R.drawable.backview);
			relativeLayout.addView(backView[i]);
			nameTextView[i]=new TextView(this);
			nameTextView[i].setText(playername[i]);
			relativeLayout.addView(nameTextView[i]);
			
		}

		
		imageId[0]=R.drawable.qiubite;
		imageId[1]=R.drawable.nvwu;
		imageId[2]=R.drawable.yuyanjia;
		imageId[3]=R.drawable.lieren;
		imageId[4]=R.drawable.langren;
		imageId[5]=R.drawable.cunmin;
		
		player=new int [number];
		for (int i = 0; i < number; i++) {         
			player[i]=6;
			
		}
		for (int i = 0; i < 6; i++) {           
			if (character[i]!=0) {
			for (int j = 0; j <character[i]; j++) {
			do {
				random = (int)(Math.random()*number);   
			} while (player[random]!=6);
			player[random]=i;       
				   
			}		
			}
			
		}
		for (int j = 0; j < number; j++) {
			
			final int a=j;
			backView[j].setOnClickListener(new View.OnClickListener() {
			
				@Override
				public void onClick(View arg0) {
					
					
					backView[a].setImageResource(imageId[player[a]]);
				
					new Handler().postDelayed(new Runnable() {
						@Override
						public void run() {
							backView[a].setImageResource(R.drawable.backview);
						}
					},500);
				}
			});
			
		}
	
		
		
		
		
		ViewTreeObserver vtoRelativeLayoutMain = relativeLayout.getViewTreeObserver();
		vtoRelativeLayoutMain.addOnGlobalLayoutListener(new OnGlobalLayoutListener(){

		@Override
		public void onGlobalLayout() {
	
			for (int i = 0; i < number; i++) {
				 
				if(number<10){							
					backView[i].getLayoutParams().width = (relativeLayout.getWidth()-10)/4;
					backView[i].getLayoutParams().height =(relativeLayout.getWidth()-10)/4;
					textView1.setX(relativeLayout.getWidth()/2-textView1.getWidth()/2);
					textView1.setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*3+textView1.getHeight()*3);
					textView2.setX(relativeLayout.getWidth()/2-textView2.getWidth()/2);
					textView2.setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*3+textView1.getHeight()*4);
					button.setX(relativeLayout.getWidth()/2-button.getWidth()/2);
					button.setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*3+textView1.getHeight()*4+button.getHeight());
					switch (i) {
					case 0:{
						backView[i].setX(0);
						backView[i].setY(backView[i].getLayoutParams().width/10);
						nameTextView[i].setX(0);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight());

						break;
					}
					case 1:{
						backView[i].setX(relativeLayout.getWidth()/2-backView[i].getLayoutParams().width/2);
						backView[i].setY(backView[i].getLayoutParams().width/10);
						nameTextView[i].setX(relativeLayout.getWidth()/2-backView[i].getLayoutParams().width/2);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight());
						break;
					}
					case 2:{
						backView[i].setX(relativeLayout.getWidth()-backView[i].getLayoutParams().width);
						backView[i].setY(backView[i].getLayoutParams().width/10);
						nameTextView[i].setX(relativeLayout.getWidth()-backView[i].getLayoutParams().width);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight());
						break;
					}
					case 3:{
						backView[i].setX(0);
						backView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getLayoutParams().height+nameTextView[i].getHeight());
						nameTextView[i].setX(0);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*2+textView1.getHeight());
						break;
					}
					case 4:{
						backView[i].setX(relativeLayout.getWidth()/2-backView[i].getLayoutParams().width/2);
						backView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getLayoutParams().height+nameTextView[i].getHeight());
						nameTextView[i].setX(relativeLayout.getWidth()/2-backView[i].getLayoutParams().width/2);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*2+textView1.getHeight());
						break;
					}
					case 5:{
						backView[i].setX(relativeLayout.getWidth()-backView[i].getLayoutParams().width);
						backView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getLayoutParams().height+nameTextView[i].getHeight());
						nameTextView[i].setX(relativeLayout.getWidth()-backView[i].getLayoutParams().width);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*2+textView1.getHeight());
						
						break;
					}
					case 6:{
						backView[i].setX(0);
						backView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getLayoutParams().height*2+nameTextView[i].getHeight()*2);
						nameTextView[i].setX(0);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*3+textView1.getHeight()*2);
						break;
					}
					case 7:{
						backView[i].setX(relativeLayout.getWidth()/2-backView[i].getLayoutParams().width/2);
						backView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getLayoutParams().height*2+nameTextView[i].getHeight()*2);
						nameTextView[i].setX(relativeLayout.getWidth()/2-backView[i].getLayoutParams().width/2);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*3+textView1.getHeight()*2);
						break;
					}
					case 8:{
						backView[i].setX(relativeLayout.getWidth()-backView[i].getLayoutParams().width);
						backView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getLayoutParams().height*2+nameTextView[i].getHeight()*2);
						nameTextView[i].setX(relativeLayout.getWidth()-backView[i].getLayoutParams().width);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*3+textView1.getHeight()*2);
						break;
					}
					default:
						break;
					}
				}
				else {
					backView[i].getLayoutParams().width = (relativeLayout.getWidth()-10)/5;
					backView[i].getLayoutParams().height = (relativeLayout.getWidth()-10)/5;
					textView1.setX(relativeLayout.getWidth()/2-textView1.getWidth()/2);
					textView1.setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*4+textView1.getHeight()*4);
					textView2.setX(relativeLayout.getWidth()/2-textView2.getWidth()/2);
					textView2.setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*4+textView1.getHeight()*5);
					button.setX(relativeLayout.getWidth()/2-button.getWidth()/2);
					button.setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*4+textView1.getHeight()*5+button.getHeight());

					switch (i) {
					case 0:{
						backView[i].setX(0);
						backView[i].setY(backView[i].getLayoutParams().width/10);
						nameTextView[i].setX(0);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight());
						break;
					}
					case 1:{
						backView[i].setX(backView[i].getWidth()+relativeLayout.getWidth()/15);
						backView[i].setY(backView[i].getLayoutParams().width/10);
						nameTextView[i].setX(backView[i].getWidth()+relativeLayout.getWidth()/15);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight());
						break;
					}
					case 2:{
						backView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15)*2);
						backView[i].setY(backView[i].getLayoutParams().width/10);
						nameTextView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15)*2);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight());
						break;
					}
					case 3:{
						backView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15)*3);
						backView[i].setY(backView[i].getLayoutParams().width/10);
						nameTextView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15)*3);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight());
						break;
					}
					case 4:{
						backView[i].setX(0);
						backView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getLayoutParams().height+nameTextView[i].getHeight());
						nameTextView[i].setX(0);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*2+textView1.getHeight());
						break;
					}
					case 5:{
						backView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15));
						backView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getLayoutParams().height+nameTextView[i].getHeight());
						nameTextView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15));
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*2+textView1.getHeight());
						break;
					}
					case 6:{
						backView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15)*2);
						backView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getLayoutParams().height+nameTextView[i].getHeight());
						nameTextView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15)*2);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*2+textView1.getHeight());
						break;
					}
					case 7:{
						backView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15)*3);
						backView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getLayoutParams().height+nameTextView[i].getHeight());
						nameTextView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15)*3);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*2+textView1.getHeight());
						break;
					}
					case 8:{
						backView[i].setX(0);
						backView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getLayoutParams().height*2+nameTextView[i].getHeight()*2);
						nameTextView[i].setX(0);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*3+textView1.getHeight()*2);
						break;
					}
					case 9:{
						backView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15));
						backView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getLayoutParams().height*2+nameTextView[i].getHeight()*2);
						nameTextView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15));
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*3+textView1.getHeight()*2);
						break;
					}
					case 10:{
						backView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15)*2);
						backView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getLayoutParams().height*2+nameTextView[i].getHeight()*2);
						nameTextView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15)*2);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*3+textView1.getHeight()*2);
						break;
					}
					case 11:{
						backView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15)*3);
						backView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getLayoutParams().height*2+nameTextView[i].getHeight()*2);
						nameTextView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15)*3);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*3+textView1.getHeight()*2);
						break;
					}
					case 12:{
						backView[i].setX(0);
						backView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getLayoutParams().height*3+nameTextView[i].getHeight()*3);
						nameTextView[i].setX(0);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*4+textView1.getHeight()*3);
						break;
					}
					case 13:{
						backView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15));
						backView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getLayoutParams().height*3+nameTextView[i].getHeight()*3);
						nameTextView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15));
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*4+textView1.getHeight()*3);
						break;
					}
					case 14:{
						backView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15)*2);
						backView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getLayoutParams().height*3+nameTextView[i].getHeight()*3);
						nameTextView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15)*2);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*4+textView1.getHeight()*3);
						break;
					}
					case 15:{
						backView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15)*3);
						backView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getLayoutParams().height*3+nameTextView[i].getHeight()*3);
						nameTextView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15)*3);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*4+textView1.getHeight()*3);
						break;
					}
					
					default:
						break;
					}
				}
				
			}
		}
	});

		
	}
	
}
	
	





