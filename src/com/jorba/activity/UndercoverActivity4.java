package com.jorba.activity;

import com.jorba.R;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class UndercoverActivity4 extends Activity {
    private TextView playernametTextViews[];
	private String playername[];
	private String charactername[];
	private ImageView backView[];
	private TextView nameTextView[];
	private int number;
	private int player[];
    private Button voteButton;
    private Button forgetButton;
	private RelativeLayout relativeLayout;
	private TextView textView1;
	private Button button;
	private AlertDialog.Builder builder[];
	private AlertDialog.Builder resultBuilder[];
	private int undercovernumber=0;
	private int undercover=0;
	private int otherpeople=0;
	private int other=0;
	private Button cancelButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wodi4);
		Intent intent = getIntent();
		number  = Integer.valueOf(intent.getIntExtra("number",-1));
		playername  =intent.getStringArrayExtra("playername");
		player=intent.getIntArrayExtra("player");
		charactername=intent.getStringArrayExtra("charactername");
		relativeLayout=(RelativeLayout)findViewById(R.id.relativelayout_wodi4);
		backView = new ImageView[number];
		nameTextView=new TextView[number];
		textView1=new TextView(this);
		forgetButton=new Button(this);
		voteButton=new Button(this);
		forgetButton.setText("忘记角色");
		voteButton.setText("投死一名玩家");

		TextPaint tp1 = textView1.getPaint(); 
		tp1.setFakeBoldText(true);
		textView1.setTextSize(15);

		TextPaint tp2 = textView1.getPaint(); 
		tp2.setFakeBoldText(true);

		button=new Button(this);
		button.setText("退出游戏");
		button.setTextColor(0xffFF0000);
		playernametTextViews=new TextView[number];
		relativeLayout.addView(button);
		relativeLayout.addView(forgetButton);
		relativeLayout.addView(voteButton);
		builder=new AlertDialog.Builder[number];
		resultBuilder=new AlertDialog.Builder[2];
		resultBuilder[0] = new AlertDialog.Builder(this); 
		resultBuilder[1] = new AlertDialog.Builder(this); 
		cancelButton=new Button(this);
		cancelButton.setText("取消");
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent=new Intent(UndercoverActivity4.this,MainActivity.class);
				startActivity(intent);
			}
		});
		for (int i = 0; i < number; i++) {
			if (player[i]==1) {
				undercovernumber=undercovernumber+1;
			}
			
		}
		otherpeople=number-undercovernumber;
		
		
		for (int i = 0; i <number; i++) {
			
			backView[i]=new ImageView(this); 
			backView[i].setImageResource(R.drawable.game2);
			relativeLayout.addView(backView[i]);
			nameTextView[i]=new TextView(this);
			nameTextView[i].setText(playername[i]);
			relativeLayout.addView(nameTextView[i]);
			playernametTextViews[i]=new TextView(this);
			playernametTextViews[i].setText(null);
			relativeLayout.addView(playernametTextViews[i]);
			builder[i]=new AlertDialog.Builder(this);
			
		}
		forgetButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				forgetButton.setVisibility(View.INVISIBLE);
				voteButton.setVisibility(View.INVISIBLE);
				textView1.setText("点击一张牌查看角色");
				relativeLayout.addView(textView1);
				relativeLayout.addView(cancelButton);
			    
				for (int j = 0; j < number; j++) {
					
					final int a=j;
					
					
					backView[j].setOnClickListener(new View.OnClickListener() {
					
						@Override
						public void onClick(View arg0) {
							
							
							backView[a].setImageResource(R.drawable.white);
							playernametTextViews[a].setText(charactername[player[a]]);
					
							new Handler().postDelayed(new Runnable() {
								@Override
								public void run() {
									backView[a].setImageResource(R.drawable.game2);
									forgetButton.setVisibility(View.VISIBLE);
									voteButton.setVisibility(View.VISIBLE);
									relativeLayout.removeView(textView1);
									relativeLayout.removeView(cancelButton);
									playernametTextViews[a].setText(null);
									for (int i = 0; i <number; i++) {
										backView[i].setOnClickListener(null);
										
									}
									
																	}
							},150);
						}
					});
					
				}
				
			}
		});
		
		voteButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				voteButton.setVisibility(View.INVISIBLE);
				forgetButton.setVisibility(View.INVISIBLE);
				textView1.setText("点击一张牌投死一名玩家");
				relativeLayout.addView(textView1);
				relativeLayout.addView(cancelButton);
				
				for (int j = 0; j < number; j++) {
					
					final int a=j;
					backView[j].setOnClickListener(new View.OnClickListener() {
					
						@Override
						public void onClick(View arg0) {
							
							builder[a].setMessage("确定要投死这位玩家么？") .setPositiveButton("是", new DialogInterface.OnClickListener() { 
						           public void onClick(DialogInterface dialog, int id) { 
						        	   
						        	   relativeLayout.removeView(backView[a]);
										playernametTextViews[a].setText("死亡");
										 voteButton.setVisibility(View.VISIBLE);
											forgetButton.setVisibility(View.VISIBLE);
											relativeLayout.removeView(textView1);
											relativeLayout.removeView(cancelButton);
										
										if (player[a]==1) {
											undercover=undercover+1;
										}else {
											other=other +1;
										}
										
										if (undercover==undercovernumber) {
											resultBuilder[0].setTitle("游戏结束" )  

											.setMessage("卧底全部死亡，其他人获胜！" )  

											.setPositiveButton("退出游戏", 
													new DialogInterface.OnClickListener() { 
												public void onClick(DialogInterface dialog, int id) 
												{ 
													Intent intent=new Intent(UndercoverActivity4.this,MainActivity.class);
													startActivity(intent);
												} 
											}) 
											.show();  	
							}
										
										if (other==otherpeople) {

											resultBuilder[1].setTitle("游戏结束" )  

											.setMessage("卧底获胜" )  

											.setPositiveButton("退出游戏", 
													new DialogInterface.OnClickListener() { 
												public void onClick(DialogInterface dialog, int id) { 
													Intent intent=new Intent(UndercoverActivity4.this,MainActivity.class);
													startActivity(intent);
												} 
											}) 
											.show();  
										}
						           } 
						       }) 
						       .setNegativeButton("否", new DialogInterface.OnClickListener() { 
						           public void onClick(DialogInterface dialog, int id) { 
						                dialog.cancel(); 
						           } 
						       }); 
						AlertDialog alert = builder[a].create(); 
							alert.show();
						}
					});
				}
			}
		});
		cancelButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				voteButton.setVisibility(View.VISIBLE);
				forgetButton.setVisibility(View.VISIBLE);
				relativeLayout.removeView(cancelButton);
				relativeLayout.removeView(textView1);
				for (int i = 0; i <number; i++) {
					backView[i].setOnClickListener(null);
				}
			}
		});

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
					cancelButton.setX(relativeLayout.getWidth()/2-cancelButton.getWidth()/2);
					cancelButton.setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*3+textView1.getHeight()*4);
					button.setX(relativeLayout.getWidth()/2-button.getWidth()/2);
					button.setY(relativeLayout.getHeight()-button.getHeight());
					forgetButton.setX(0);
					forgetButton.setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*3+nameTextView[i].getHeight()*2+forgetButton.getHeight());
					voteButton.setX(relativeLayout.getWidth()-voteButton.getWidth());
					voteButton.setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*3+nameTextView[i].getHeight()*2+voteButton.getHeight());
					switch (i) {
					case 0:{
						backView[i].setX(0);
						backView[i].setY(backView[i].getLayoutParams().width/10);
						nameTextView[i].setX(0);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight());
                        playernametTextViews[i].setX(backView[i].getX()+backView[i].getWidth()/2-playernametTextViews[i].getWidth()/2);
                        playernametTextViews[i].setY(backView[i].getY()+backView[i].getHeight()/2-playernametTextViews[i].getHeight()/2);
						break;
					}
					case 1:{
						backView[i].setX(relativeLayout.getWidth()/2-backView[i].getLayoutParams().width/2);
						backView[i].setY(backView[i].getLayoutParams().width/10);
						nameTextView[i].setX(relativeLayout.getWidth()/2-backView[i].getLayoutParams().width/2);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight());
						playernametTextViews[i].setX(backView[i].getX()+backView[i].getWidth()/2-playernametTextViews[i].getWidth()/2);
                        playernametTextViews[i].setY(backView[i].getY()+backView[i].getHeight()/2-playernametTextViews[i].getHeight()/2);
						break;
					}
					case 2:{
						backView[i].setX(relativeLayout.getWidth()-backView[i].getLayoutParams().width);
						backView[i].setY(backView[i].getLayoutParams().width/10);
						nameTextView[i].setX(relativeLayout.getWidth()-backView[i].getLayoutParams().width);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight());
						playernametTextViews[i].setX(backView[i].getX()+backView[i].getWidth()/2-playernametTextViews[i].getWidth()/2);
                        playernametTextViews[i].setY(backView[i].getY()+backView[i].getHeight()/2-playernametTextViews[i].getHeight()/2);
						break;
					}
					case 3:{
						backView[i].setX(0);
						backView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getLayoutParams().height+nameTextView[i].getHeight());
						nameTextView[i].setX(0);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*2+nameTextView[i].getHeight());
						playernametTextViews[i].setX(backView[i].getX()+backView[i].getWidth()/2-playernametTextViews[i].getWidth()/2);
                        playernametTextViews[i].setY(backView[i].getY()+backView[i].getHeight()/2-playernametTextViews[i].getHeight()/2);
						break;
					}
					case 4:{
						backView[i].setX(relativeLayout.getWidth()/2-backView[i].getLayoutParams().width/2);
						backView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getLayoutParams().height+nameTextView[i].getHeight());
						nameTextView[i].setX(relativeLayout.getWidth()/2-backView[i].getLayoutParams().width/2);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*2+nameTextView[i].getHeight());
						playernametTextViews[i].setX(backView[i].getX()+backView[i].getWidth()/2-playernametTextViews[i].getWidth()/2);
                        playernametTextViews[i].setY(backView[i].getY()+backView[i].getHeight()/2-playernametTextViews[i].getHeight()/2);
						break;
					}
					case 5:{
						backView[i].setX(relativeLayout.getWidth()-backView[i].getLayoutParams().width);
						backView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getLayoutParams().height+nameTextView[i].getHeight());
						nameTextView[i].setX(relativeLayout.getWidth()-backView[i].getLayoutParams().width);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*2+nameTextView[i].getHeight());
						playernametTextViews[i].setX(backView[i].getX()+backView[i].getWidth()/2-playernametTextViews[i].getWidth()/2);
                        playernametTextViews[i].setY(backView[i].getY()+backView[i].getHeight()/2-playernametTextViews[i].getHeight()/2);
						
						break;
					}
					case 6:{
						backView[i].setX(0);
						backView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getLayoutParams().height*2+nameTextView[i].getHeight()*2);
						nameTextView[i].setX(0);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*3+nameTextView[i].getHeight()*2);
						playernametTextViews[i].setX(backView[i].getX()+backView[i].getWidth()/2-playernametTextViews[i].getWidth()/2);
                        playernametTextViews[i].setY(backView[i].getY()+backView[i].getHeight()/2-playernametTextViews[i].getHeight()/2);
						break;
					}
					case 7:{
						backView[i].setX(relativeLayout.getWidth()/2-backView[i].getLayoutParams().width/2);
						backView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getLayoutParams().height*2+nameTextView[i].getHeight()*2);
						nameTextView[i].setX(relativeLayout.getWidth()/2-backView[i].getLayoutParams().width/2);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*3+nameTextView[i].getHeight()*2);
						playernametTextViews[i].setX(backView[i].getX()+backView[i].getWidth()/2-playernametTextViews[i].getWidth()/2);
                        playernametTextViews[i].setY(backView[i].getY()+backView[i].getHeight()/2-playernametTextViews[i].getHeight()/2);
						break;
					}
					case 8:{
						backView[i].setX(relativeLayout.getWidth()-backView[i].getLayoutParams().width);
						backView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getLayoutParams().height*2+nameTextView[i].getHeight()*2);
						nameTextView[i].setX(relativeLayout.getWidth()-backView[i].getLayoutParams().width);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*3+nameTextView[i].getHeight()*2);
						playernametTextViews[i].setX(backView[i].getX()+backView[i].getWidth()/2-playernametTextViews[i].getWidth()/2);
                        playernametTextViews[i].setY(backView[i].getY()+backView[i].getHeight()/2-playernametTextViews[i].getHeight()/2);
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
					cancelButton.setX(relativeLayout.getWidth()/2-cancelButton.getWidth()/2);
					cancelButton.setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*4+textView1.getHeight()*5);
					button.setX(relativeLayout.getWidth()/2-button.getWidth()/2);
					button.setY(relativeLayout.getHeight()-button.getHeight());
					forgetButton.setX(0);
					forgetButton.setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*4+nameTextView[i].getHeight()*3+forgetButton.getHeight());
					voteButton.setX(relativeLayout.getWidth()-voteButton.getWidth());
					voteButton.setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*4+nameTextView[i].getHeight()*3+voteButton.getHeight());
					

					switch (i) {
					case 0:{
						backView[i].setX(0);
						backView[i].setY(backView[i].getLayoutParams().width/10);
						nameTextView[i].setX(0);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight());
						playernametTextViews[i].setX(backView[i].getX()+backView[i].getWidth()/2-playernametTextViews[i].getWidth()/2);
                        playernametTextViews[i].setY(backView[i].getY()+backView[i].getHeight()/2-playernametTextViews[i].getHeight()/2);
						break;
					}
					case 1:{
						backView[i].setX(backView[i].getWidth()+relativeLayout.getWidth()/15);
						backView[i].setY(backView[i].getLayoutParams().width/10);
						nameTextView[i].setX(backView[i].getWidth()+relativeLayout.getWidth()/15);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight());
						playernametTextViews[i].setX(backView[i].getX()+backView[i].getWidth()/2-playernametTextViews[i].getWidth()/2);
                        playernametTextViews[i].setY(backView[i].getY()+backView[i].getHeight()/2-playernametTextViews[i].getHeight()/2);
						break;
					}
					case 2:{
						backView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15)*2);
						backView[i].setY(backView[i].getLayoutParams().width/10);
						nameTextView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15)*2);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight());
						playernametTextViews[i].setX(backView[i].getX()+backView[i].getWidth()/2-playernametTextViews[i].getWidth()/2);
                        playernametTextViews[i].setY(backView[i].getY()+backView[i].getHeight()/2-playernametTextViews[i].getHeight()/2);
						break;
					}
					case 3:{
						backView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15)*3);
						backView[i].setY(backView[i].getLayoutParams().width/10);
						nameTextView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15)*3);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight());
						playernametTextViews[i].setX(backView[i].getX()+backView[i].getWidth()/2-playernametTextViews[i].getWidth()/2);
                        playernametTextViews[i].setY(backView[i].getY()+backView[i].getHeight()/2-playernametTextViews[i].getHeight()/2);
						break;
					}
					case 4:{
						backView[i].setX(0);
						backView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getLayoutParams().height+nameTextView[i].getHeight());
						nameTextView[i].setX(0);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*2+nameTextView[i].getHeight());
						playernametTextViews[i].setX(backView[i].getX()+backView[i].getWidth()/2-playernametTextViews[i].getWidth()/2);
                        playernametTextViews[i].setY(backView[i].getY()+backView[i].getHeight()/2-playernametTextViews[i].getHeight()/2);
						break;
					}
					case 5:{
						backView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15));
						backView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getLayoutParams().height+nameTextView[i].getHeight());
						nameTextView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15));
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*2+nameTextView[i].getHeight());
						playernametTextViews[i].setX(backView[i].getX()+backView[i].getWidth()/2-playernametTextViews[i].getWidth()/2);
                        playernametTextViews[i].setY(backView[i].getY()+backView[i].getHeight()/2-playernametTextViews[i].getHeight()/2);
						break;
					}
					case 6:{
						backView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15)*2);
						backView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getLayoutParams().height+nameTextView[i].getHeight());
						nameTextView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15)*2);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*2+nameTextView[i].getHeight());
						playernametTextViews[i].setX(backView[i].getX()+backView[i].getWidth()/2-playernametTextViews[i].getWidth()/2);
                        playernametTextViews[i].setY(backView[i].getY()+backView[i].getHeight()/2-playernametTextViews[i].getHeight()/2);
						break;
					}
					case 7:{
						backView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15)*3);
						backView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getLayoutParams().height+nameTextView[i].getHeight());
						nameTextView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15)*3);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*2+nameTextView[i].getHeight());
						playernametTextViews[i].setX(backView[i].getX()+backView[i].getWidth()/2-playernametTextViews[i].getWidth()/2);
                        playernametTextViews[i].setY(backView[i].getY()+backView[i].getHeight()/2-playernametTextViews[i].getHeight()/2);
						break;
					}
					case 8:{
						backView[i].setX(0);
						backView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getLayoutParams().height*2+nameTextView[i].getHeight()*2);
						nameTextView[i].setX(0);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*3+nameTextView[i].getHeight()*2);
						playernametTextViews[i].setX(backView[i].getX()+backView[i].getWidth()/2-playernametTextViews[i].getWidth()/2);
                        playernametTextViews[i].setY(backView[i].getY()+backView[i].getHeight()/2-playernametTextViews[i].getHeight()/2);
						break;
					}
					case 9:{
						backView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15));
						backView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getLayoutParams().height*2+nameTextView[i].getHeight()*2);
						nameTextView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15));
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*3+nameTextView[i].getHeight()*2);
						playernametTextViews[i].setX(backView[i].getX()+backView[i].getWidth()/2-playernametTextViews[i].getWidth()/2);
                        playernametTextViews[i].setY(backView[i].getY()+backView[i].getHeight()/2-playernametTextViews[i].getHeight()/2);
						break;
					}
					case 10:{
						backView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15)*2);
						backView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getLayoutParams().height*2+nameTextView[i].getHeight()*2);
						nameTextView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15)*2);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*3+nameTextView[i].getHeight()*2);
						playernametTextViews[i].setX(backView[i].getX()+backView[i].getWidth()/2-playernametTextViews[i].getWidth()/2);
                        playernametTextViews[i].setY(backView[i].getY()+backView[i].getHeight()/2-playernametTextViews[i].getHeight()/2);
						break;
					}
					case 11:{
						backView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15)*3);
						backView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getLayoutParams().height*2+nameTextView[i].getHeight()*2);
						nameTextView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15)*3);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*3+nameTextView[i].getHeight()*2);
						playernametTextViews[i].setX(backView[i].getX()+backView[i].getWidth()/2-playernametTextViews[i].getWidth()/2);
                        playernametTextViews[i].setY(backView[i].getY()+backView[i].getHeight()/2-playernametTextViews[i].getHeight()/2);
						break;
					}
					case 12:{
						backView[i].setX(0);
						backView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getLayoutParams().height*3+nameTextView[i].getHeight()*3);
						nameTextView[i].setX(0);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*4+nameTextView[i].getHeight()*3);
						playernametTextViews[i].setX(backView[i].getX()+backView[i].getWidth()/2-playernametTextViews[i].getWidth()/2);
                        playernametTextViews[i].setY(backView[i].getY()+backView[i].getHeight()/2-playernametTextViews[i].getHeight()/2);
						break;
					}
					case 13:{
						backView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15));
						backView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getLayoutParams().height*3+nameTextView[i].getHeight()*3);
						nameTextView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15));
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*4+nameTextView[i].getHeight()*3);
						playernametTextViews[i].setX(backView[i].getX()+backView[i].getWidth()/2-playernametTextViews[i].getWidth()/2);
                        playernametTextViews[i].setY(backView[i].getY()+backView[i].getHeight()/2-playernametTextViews[i].getHeight()/2);
						break;
					}
					case 14:{
						backView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15)*2);
						backView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getLayoutParams().height*3+nameTextView[i].getHeight()*3);
						nameTextView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15)*2);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*4+nameTextView[i].getHeight()*3);
						playernametTextViews[i].setX(backView[i].getX()+backView[i].getWidth()/2-playernametTextViews[i].getWidth()/2);
                        playernametTextViews[i].setY(backView[i].getY()+backView[i].getHeight()/2-playernametTextViews[i].getHeight()/2);
						break;
					}
					case 15:{
						backView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15)*3);
						backView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getLayoutParams().height*3+nameTextView[i].getHeight()*3);
						nameTextView[i].setX((backView[i].getWidth()+relativeLayout.getWidth()/15)*3);
						nameTextView[i].setY(backView[i].getLayoutParams().width/10+backView[i].getHeight()*4+nameTextView[i].getHeight()*3);
						playernametTextViews[i].setX(backView[i].getX()+backView[i].getWidth()/2-playernametTextViews[i].getWidth()/2);
                        playernametTextViews[i].setY(backView[i].getY()+backView[i].getHeight()/2-playernametTextViews[i].getHeight()/2);
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
