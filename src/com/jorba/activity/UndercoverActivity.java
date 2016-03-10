package com.jorba.activity;

import com.jorba.R;

import android.os.Bundle;
import android.text.TextPaint;
import android.util.DisplayMetrics;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class UndercoverActivity extends Activity {
	private SeekBar seekBar;
	private TextView textView1 ;
	private TextView textView2 ;
	private TextView textView3 ;
	private TextView textView4;
	private RelativeLayout relativeLayoutMain;

	private Button button;
	private Button buttonexit;
	private int character[];            
	private RadioButton baiButton0;
	private RadioButton baiButton1;
	private RadioButton baiButton2;
	private RadioButton wodiButton1;
	private RadioButton wodiButton2;
	private RadioButton wodiButton3;
	private RadioGroup radioGroup;
	private RadioGroup radioGroup2;
	private int member=6;
	private ImageView logoImageView;
	
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wodi);
		seekBar =new SeekBar (this);
		textView1 =new TextView (this);
		textView2=new TextView (this);
		textView3 =new TextView (this);
		textView4=new TextView(this);
		radioGroup =new RadioGroup(this);
		radioGroup2=new RadioGroup(this);
		logoImageView=new ImageView(this);
		logoImageView.setImageResource(R.drawable.game2);
		buttonexit = (Button)findViewById(R.id.button_exit2);
		final DisplayMetrics dispalyMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dispalyMetrics);
		buttonexit.getLayoutParams().width = dispalyMetrics.widthPixels/10;
		buttonexit.getLayoutParams().height = dispalyMetrics.widthPixels/10;


		textView1.setText("①选择人数（6~16人）:");
		textView2.setText("玩家人数6人");
		textView3.setText("②选择白板个数：");
		textView4.setText("③选择卧底个数：");
		
		TextPaint tp1 = textView1.getPaint(); 
		tp1.setFakeBoldText(true);
		textView1.setTextSize(25);
		
		TextPaint tp2 = textView2.getPaint(); 
		tp2.setFakeBoldText(true);
		textView2.setTextSize(20);
		
		TextPaint tp3 = textView3.getPaint(); 
		tp3.setFakeBoldText(true);
		textView3.setTextSize(25);
		

		TextPaint tp4 = textView4.getPaint(); 
		tp4.setFakeBoldText(true);
		textView4.setTextSize(25);
		

		seekBar.setMax(10);
		seekBar.setProgress(0);
		
			baiButton0= new RadioButton(this);   
			baiButton1= new RadioButton(this); 
			baiButton2= new RadioButton(this); 
		    baiButton0 .setPadding(60, 0, 0, 0);     
		    baiButton1 .setPadding(60, 0, 0, 0); 
		    baiButton2 .setPadding(60, 0, 0, 0); 
		    baiButton0.setText("0个");
		    baiButton1.setText("1个");
		    baiButton2.setText("2个");
		    radioGroup.setOrientation(RadioGroup.HORIZONTAL);
		    radioGroup .addView(baiButton0);  
		    radioGroup .addView(baiButton1);  
		    radioGroup .addView(baiButton2);  
		    
		    wodiButton1= new RadioButton(this);   
			wodiButton2= new RadioButton(this); 
			wodiButton3= new RadioButton(this); 
		    wodiButton1 .setPadding(60, 0, 0, 0);     
		    wodiButton2 .setPadding(60, 0, 0, 0); 
		    wodiButton3 .setPadding(60, 0, 0, 0); 
		    wodiButton1.setText("1个");
		    wodiButton2.setText("2个");
		    wodiButton3.setText("3个");
		    radioGroup2.setOrientation(RadioGroup.HORIZONTAL);
		    radioGroup2 .addView(wodiButton1);  
		    radioGroup2 .addView(wodiButton2);  
		    radioGroup2.addView(wodiButton3);  
		    
		    
		
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {        //设置seekbar
			
			@Override
			public void onProgressChanged(SeekBar seekbar,int progress,boolean fromUser){
				textView2.setText("玩家人数"+(progress+6)+"人");
				member=seekbar.getProgress()+6;
			}
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
		        
		    }

		    
		    @Override
			public void onStartTrackingTouch(SeekBar seekBar) {

		    }
		   
		    
		});

		button =new Button(this);

		button.setText("下一步");
		
		
		
		relativeLayoutMain=(RelativeLayout)findViewById(R.id.relativeLayout_wodi);
		relativeLayoutMain.addView(seekBar);
		relativeLayoutMain.addView(textView1);
		relativeLayoutMain.addView(textView2);
		relativeLayoutMain.addView(textView3);
		relativeLayoutMain.addView(textView4);
		relativeLayoutMain.addView(button);
		relativeLayoutMain.addView(logoImageView);
		relativeLayoutMain.addView(radioGroup);
		relativeLayoutMain.addView(radioGroup2);

		
		
	
		character=new int[3];         //0是白板个数，1是卧底个数，2是平民个数
		for (int i = 0; i < 3; i++) {
			character[i]=0;			
		}
		
		
		 radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {  //设置单选框监听事件
	            @Override  
	            public void onCheckedChanged(RadioGroup group, int checkedId)  
	            {  
	            	 //找到ID为checkedID的RadioButton  
	                // TODO Auto-generated method stub 
	            	
	                if (checkedId == baiButton0 .getId())  
	                {  
	                    character[0]=0;
	                }  
	                if (checkedId == baiButton1 .getId())  
	                {  
	                    character[0]=1;
	                }  
	                if (checkedId == baiButton2 .getId())  
	                {  
	                    character[0]=2;
	                }  
	            }  
	        });  
		
		
		 radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {  //设置单选框监听事件
	            @Override  
	            public void onCheckedChanged(RadioGroup group, int checkedId)  
	            {  
	            	//找到ID为checkedID的RadioButton  
	                // TODO Auto-generated method stub  
	                if (checkedId == wodiButton1 .getId())  
	                {  
	                    character[1]=1;
	                }  
	                if (checkedId == wodiButton2 .getId())  
	                {  
	                    character[1]=2;
	                }  
	                if (checkedId == wodiButton3 .getId())  
	                {  
	                    character[1]=3;
	                }  
	            }  
	        });  

		

		
	button.setOnClickListener(new View.OnClickListener() {        //button的监听器，将数据存到intent里
		
		@Override
		public void onClick(View arg0) {
			// TODO 自动生成的方法存根
			
			character[2] =member-character[0]-character[1];
			
	
			
			Intent intent =new Intent(UndercoverActivity.this,UndercoverActivity2.class);
			Bundle bundle = new Bundle();
			bundle.putInt("number", member);
			bundle.putIntArray("character", character);
			intent.putExtras(bundle);
			startActivity(intent);
			finish();
		}
	});
	
buttonexit.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		// TODO 自动生成的方法存根
		Intent intent =new Intent(UndercoverActivity.this,MainActivity.class);
		startActivity(intent);
		finish();
		
	}
});
		
		ViewTreeObserver vtoRelativeLayoutMain = relativeLayoutMain.getViewTreeObserver();
		vtoRelativeLayoutMain.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

			@SuppressLint("NewApi")
			@Override
			public void onGlobalLayout() {
				// TODO 自动生成的方法存根
				logoImageView.getLayoutParams().width = (relativeLayoutMain.getWidth())/2;
				logoImageView.getLayoutParams().height =(relativeLayoutMain.getWidth())/2;
				logoImageView.setX(relativeLayoutMain.getWidth()/2-logoImageView.getWidth()/2);
				logoImageView.setY(5);
				
				
				textView1.setX(0);
				textView1.setY(logoImageView.getHeight()+textView1.getHeight()/3);
				
				seekBar.setX(relativeLayoutMain.getWidth()/2-seekBar.getWidth()/2);
				seekBar.setY(textView1.getY()+textView1.getHeight());
				seekBar.getLayoutParams().width = relativeLayoutMain.getWidth()/2;
				
				textView2.setX(relativeLayoutMain.getWidth()/2-textView2.getWidth()/2);
				textView2.setY(seekBar.getY()+seekBar.getHeight());
				
				textView3.setX(0);
				textView3.setY(textView2.getY()+textView2.getHeight());
				
				radioGroup.setX(textView3.getX());
				radioGroup .setY(textView3.getY()+textView3.getHeight());
				
				textView4.setX(0);
				textView4.setY(radioGroup.getY()+radioGroup.getHeight());
				
				radioGroup2.setX(textView4.getX());
				radioGroup2 .setY(textView4.getY()+textView4.getHeight());

				
				button.setX(relativeLayoutMain.getWidth()/2-button.getWidth()/2);
				button .setY(relativeLayoutMain.getHeight()-button.getHeight()*2);
				
	

				

			}
			
		});
	}
	




	

}
