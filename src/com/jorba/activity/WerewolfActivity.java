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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class WerewolfActivity extends Activity {
	private SeekBar seekBar;
	private TextView textView1 ;
	private TextView textView2;
	private TextView textView3 ;
	private TextView textView4 ;
	private TextView langrenTextView;
	private TextView cunminTextView;
	private RelativeLayout relativeLayoutMain;
	private CheckBox qiuBox;
	private CheckBox nvwuBox;
	private CheckBox yuyanBox;
	private CheckBox lierenBox;
	private Button button;
	private Button buttonexit;
	private int character[];            
	private RadioButton lang1;
	private RadioButton lang2;
	private RadioGroup radioGroup;
	private int member=8;
	private ImageView logoImageView;
	
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_langren);
		seekBar =new SeekBar (this);
		textView1 =new TextView (this);
		textView2=new TextView (this);
		textView3 =new TextView (this);
		textView4 =new TextView (this);
		langrenTextView  =new TextView (this);
		cunminTextView  =new TextView (this);
		radioGroup =new RadioGroup(this);
		logoImageView=new ImageView(this);
		logoImageView.setImageResource(R.drawable.game1);
		buttonexit = (Button)findViewById(R.id.button_exit);
		final DisplayMetrics dispalyMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dispalyMetrics);
		buttonexit.getLayoutParams().width = dispalyMetrics.widthPixels/10;
		buttonexit.getLayoutParams().height = dispalyMetrics.widthPixels/10;


		textView1.setText("①选择人数（8~16）:");
		textView2.setText("（拖动选择，不包括上帝）");
		textView3.setText("玩家人数13人");
		textView4.setText("②选择添加的角色：");
		langrenTextView.setText("狼人");
		cunminTextView.setText("村民");
		cunminTextView.setTextColor(0xff171717);
		
		TextPaint tp1 = textView1.getPaint(); 
		tp1.setFakeBoldText(true);
		textView1.setTextSize(25);
		
		TextPaint tp3 = textView3.getPaint(); 
		tp3.setFakeBoldText(true);
		textView3.setTextSize(20);
		
		TextPaint tp4 = textView4.getPaint(); 
		tp4.setFakeBoldText(true);
		textView4.setTextSize(25);
		
		TextPaint tp5 = langrenTextView.getPaint(); 
		tp5.setFakeBoldText(true);
		
		
		TextPaint tp6 = cunminTextView.getPaint(); 
		tp6.setFakeBoldText(true);
		
		seekBar.setMax(8);
		seekBar.setProgress(0);
		
			lang1= new RadioButton(this);       
		    lang1 .setPadding(60, 0, 0, 0);                
		    lang1.setText("2人");  
		    radioGroup .addView(lang1);  
		    
		    lang2= new RadioButton(this);       
		    lang2 .setPadding(60, 0, 0, 0);                
		    lang2.setText("3人");  
		    radioGroup .addView(lang2);  
		
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {        //����seekbar
			
			@Override
			public void onProgressChanged(SeekBar seekbar,int progress,boolean fromUser){
				textView3.setText("玩家人数"+(progress+8)+"人");
				member=seekbar.getProgress()+8;
			}
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
		        
		    }

		    
		    @Override
			public void onStartTrackingTouch(SeekBar seekBar) {

		    }
		   
		    
		});
		qiuBox=new CheckBox(this);
		nvwuBox =new CheckBox(this);
		yuyanBox=new CheckBox(this);
		lierenBox =new CheckBox(this);
		button =new Button(this);
//	    button.setBackgroundColor(0xffFFFF00);
		qiuBox.setText("丘比特");
		nvwuBox.setText("女巫");
		yuyanBox.setText("预言家");
		lierenBox.setText("猎人");
		button.setText("下一步");
		
		
		
		relativeLayoutMain=(RelativeLayout)findViewById(R.id.relativeLayoutMain1);
		relativeLayoutMain.addView(seekBar);
		relativeLayoutMain.addView(textView1);
		relativeLayoutMain.addView(textView2);
		relativeLayoutMain.addView(textView3);
		relativeLayoutMain.addView(textView4);
		relativeLayoutMain.addView(qiuBox);
		relativeLayoutMain.addView(nvwuBox);
		relativeLayoutMain.addView(yuyanBox);
		relativeLayoutMain.addView(lierenBox);
//		relativeLayoutMain.addView(langrenTextView);
		relativeLayoutMain.addView(cunminTextView);
		relativeLayoutMain.addView(button);
		relativeLayoutMain.addView(logoImageView);
		relativeLayoutMain.addView(radioGroup);

		
		
	
		character=new int[6];
		for (int i = 0; i < 6; i++) {
			character[i]=0;			
		}
		
		qiuBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
				if (isChecked) 
					character[0]=1; 
				else 
					character[0]=0; 
				
				
			}
		}); 
		nvwuBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
				if (isChecked)
					character[1]=1; 
				else
					character[1]=0; 
				
			}
		}); 
		yuyanBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
				if (isChecked) 
					character[2]=1; 
				else 
					character[2]=0; 
				

				
			}
		}); 
		
		lierenBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
				if (isChecked) 
					character[3]=1; 
				else
					character[3]=0; 
			}
		}); 
		 radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {  //���õ�ѡ������¼�
	            @Override  
	            public void onCheckedChanged(RadioGroup group, int checkedId)  
	            {  
	                if (checkedId == lang1.getId())  
	                {  
	                    character[4]=2;
	                }  
	                else  
	                {  
	                   character[4]=3;
	                }  
	            }  
	        });  
		
		
		

		

		
	button.setOnClickListener(new View.OnClickListener() {        
		
		@Override
		public void onClick(View arg0) {
			
			character[5] =member-character[0]-character[1]-character[2]-character[3]-character[4];
			

			
			Intent intent =new Intent(WerewolfActivity.this,WerewolfActivity2.class);
			Bundle bundle = new Bundle();
			bundle.putInt("number", member);
			bundle.putIntArray("character", character);
			intent.putExtras(bundle);
			startActivity(intent);
			
		}
	});
	
buttonexit.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		Intent intent =new Intent(WerewolfActivity.this,MainActivity.class);
		startActivity(intent);
		finish();
		
	}
});
		
		ViewTreeObserver vtoRelativeLayoutMain = relativeLayoutMain.getViewTreeObserver();
		vtoRelativeLayoutMain.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

			@SuppressLint("NewApi")
			@Override
			public void onGlobalLayout() {
				logoImageView.getLayoutParams().width = (relativeLayoutMain.getWidth())/4;
				logoImageView.getLayoutParams().height =(relativeLayoutMain.getWidth())/4;
				logoImageView.setX(relativeLayoutMain.getWidth()/2-logoImageView.getWidth()/2);
				logoImageView.setY(5);
				seekBar.setX(relativeLayoutMain.getWidth()/2-seekBar.getWidth()/2);
				seekBar.setY(textView1.getY()+textView1.getHeight());
				seekBar.getLayoutParams().width = relativeLayoutMain.getWidth()/2;
				
				textView1.setX(0);
				textView1.setY(relativeLayoutMain.getHeight()/5);
				
				textView2.setX(seekBar.getX());
				textView2.setY(seekBar.getY()+seekBar.getHeight());
				
				textView3.setX(relativeLayoutMain.getWidth()/2-textView3.getWidth()/2);
				textView3.setY(textView2.getY()+textView2.getHeight());

				textView4.setX(0);
				textView4.setY(textView3.getY()+textView3.getHeight()*2);

				qiuBox.setX(0);
				qiuBox.setY(textView4.getY()+textView4.getHeight());
				
				nvwuBox.setX(qiuBox.getX()+qiuBox.getWidth());
				nvwuBox.setY(qiuBox.getY());
				
				yuyanBox.setX(nvwuBox.getX()+nvwuBox.getWidth());
				yuyanBox.setY(nvwuBox.getY());
				
				lierenBox.setX(0);
				lierenBox.setY(qiuBox.getY()+qiuBox.getHeight());
				
//				langrenTextView.setX(nvwuBox.getX());
//				langrenTextView.setY(lierenBox.getY());
				
				radioGroup.setX(nvwuBox.getX());
				radioGroup .setY(lierenBox.getY());
//				radioGroup.getLayoutParams().width=qiuBox.getWidth();
//				radioGroup .getLayoutParams().height=qiuBox.getHeight()*2;
				
				cunminTextView.setX(radioGroup.getX()+radioGroup.getWidth()+10);
				cunminTextView.setY(lierenBox.getY()+20);
				
				button.setX(relativeLayoutMain.getWidth()/2-button.getWidth()/2);
				button .setY(relativeLayoutMain.getHeight()-button.getHeight()*2);
			}
			
		});
	}
	




	

}
