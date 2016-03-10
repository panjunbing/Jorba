package com.jorba.other;

import com.jorba.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NavigationBar extends RelativeLayout {  

	private Button buttonLeft;  
	private Button buttonRight;  
	private TextView textViewTitle;  
	private int drawableRight;  
	public NavigationBar(Context context) {  
		super(context);  
		initContent();  
	}  

	public NavigationBar(Context context, AttributeSet attrs) {  
		super(context, attrs);  
		initAttributes(attrs);  
		initContent();  
	}  

	private void initAttributes(AttributeSet attributeSet) {  

		if (null != attributeSet) {  

			final int attrIds[] = new int[] { R.attr.btn_leftText,  
					R.attr.btn_rightText, R.attr.tv_title,  
					R.attr.left_drawable, R.attr.right_drawable };  

			Context context = getContext();  

			TypedArray array = context.obtainStyledAttributes(attributeSet,  
					attrIds);  

			CharSequence t1 = array.getText(0);  
			CharSequence t2 = array.getText(1);  
			CharSequence t3 = array.getText(2);  
			drawableRight = array.getResourceId(3, 0);  
			array.getResourceId(4, 0);  

			array.recycle();  

			if (null != t1) {  
				t1.toString();  
			}  
			if (null != t2) {  
				t2.toString();  

			}  
			if (null != t3) {  
				t3.toString();  
			}  

			Log.i("coder", "t1 = " + t1);  
			Log.i("coder", "t2 = " + t2);  
			Log.i("coder", "t3 = " + t3);  
			Log.i("coder", "left_res = " + drawableRight);  
		}  

	}  

	private void initContent() {  

		Log.i("coder", "-----initContent----");  

		setGravity(Gravity.CENTER_VERTICAL);  
		setBackgroundColor(Color.BLACK);

		Context context = getContext();  
		buttonLeft = new Button(context);  
		buttonLeft.setTextColor(Color.WHITE);																// 字体颜色  
		buttonLeft.setText("返回");  
		buttonLeft.setVisibility(View.VISIBLE);  
		buttonLeft.setBackgroundColor(Color.TRANSPARENT);
		addView(buttonLeft);  

		textViewTitle = new TextView(context);  
		textViewTitle.setTextColor(Color.WHITE);  
		textViewTitle.setGravity(Gravity.CENTER);  
		buttonLeft.setVisibility(View.VISIBLE);  
		addView(textViewTitle);  

		buttonRight = new Button(context);  
		buttonRight.setVisibility(View.VISIBLE);
		buttonRight.setTextColor(Color.WHITE);
		buttonRight.setVisibility(View.VISIBLE);  
		buttonRight.setBackgroundColor(Color.TRANSPARENT);
		addView(buttonRight);  

		ViewTreeObserver vto = this.getViewTreeObserver();
		vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				// TODO 自动生成的方法存根
				buttonLeft.setX(10);
				textViewTitle.setX(NavigationBar.this.getWidth()/2-textViewTitle.getWidth()/2);
				textViewTitle.setY(NavigationBar.this.getHeight()/2-textViewTitle.getHeight()/2);
				buttonRight.setX(NavigationBar.this.getWidth()-buttonRight.getWidth());
			}
		});
	}  



	public Button getButtonLeft() {  
		return buttonLeft;  
	}  

	public Button getButtonRight() {  
		return buttonRight;  
	}  

	public TextView getTextViewTitle() {  
		return textViewTitle;  
	}  

	public void setButtonLeftText(String text) {  
		buttonLeft.setText(text);
	}  

	public void setButtonRightText(String text) {  
		buttonRight.setText(text);
	}  

}  