package com.jorba.other;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;

public class ExpandableListViewDatingFriends extends ExpandableListView
		implements OnScrollListener, OnGroupClickListener {
	public ExpandableListViewDatingFriends(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		registerListener();
	}

	public ExpandableListViewDatingFriends(Context context, AttributeSet attrs) {
		super(context, attrs);
		registerListener();
	}

	public ExpandableListViewDatingFriends(Context context) {
		super(context);
		registerListener();
	}

	/**
	 * Adapter 鎺ュ彛 . 鍒楄〃蹇呴』瀹炵幇姝ゆ帴鍙? .
	 */
	public interface HeaderAdapter {
		public static final int PINNED_HEADER_GONE = 0;
		public static final int PINNED_HEADER_VISIBLE = 1;
		public static final int PINNED_HEADER_PUSHED_UP = 2;

		/**
		 * 銮峰彇 Header 镄勭姸镐?
		 * 
		 * @param groupPosition
		 * @param childPosition
		 * @return 
		 *         PINNED_HEADER_GONE,PINNED_HEADER_VISIBLE,PINNED_HEADER_PUSHED_UP
		 *         鍏朵腑涔嬩竴
		 */
		int getHeaderState(int groupPosition, int childPosition);

		/**
		 * 閰岖疆 Header, 璁? Header 鐭ラ亾鏄剧ず镄勫唴瀹?
		 * 
		 * @param header
		 * @param groupPosition
		 * @param childPosition
		 * @param alpha
		 */
		void configureHeader(View header, int groupPosition, int childPosition,
				int alpha);

		/**
		 * 璁剧疆缁勬寜涓嬬殑钟舵??
		 * 
		 * @param groupPosition
		 * @param status
		 */
		void setGroupClickStatus(int groupPosition, int status);

		/**
		 * 銮峰彇缁勬寜涓嬬殑钟舵??
		 * 
		 * @param groupPosition
		 * @return
		 */
		int getGroupClickStatus(int groupPosition);

	}

	private static final int MAX_ALPHA = 255;

	private HeaderAdapter mAdapter;

	/**
	 * 鐢ㄤ簬鍦ㄥ垪琛ㄥご鏄剧ず镄? View,mHeaderViewVisible 涓? true 镓嶅彲瑙?
	 */
	private View mHeaderView;

	/**
	 * 鍒楄〃澶存槸鍚﹀彲瑙?
	 */
	private boolean mHeaderViewVisible;

	private int mHeaderViewWidth;

	private int mHeaderViewHeight;

	public void setHeaderView(View view) {
		mHeaderView = view;
		AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		view.setLayoutParams(lp);

		if (mHeaderView != null) {
			setFadingEdgeLength(0);
		}

		requestLayout();
	}

	private void registerListener() {
		setOnScrollListener(this);
		setOnGroupClickListener(this);
	}

	/**
	 * 镣瑰向 HeaderView 瑙﹀彂镄勪簨浠?
	 */
	private void headerViewClick() {
		long packedPosition = getExpandableListPosition(this
				.getFirstVisiblePosition());

		int groupPosition = ExpandableListView
				.getPackedPositionGroup(packedPosition);

		if (mAdapter.getGroupClickStatus(groupPosition) == 1) {
			this.collapseGroup(groupPosition);
			mAdapter.setGroupClickStatus(groupPosition, 0);
		} else {
			this.expandGroup(groupPosition);
			mAdapter.setGroupClickStatus(groupPosition, 1);
		}

		this.setSelectedGroup(groupPosition);
	}

	private float mDownX;
	private float mDownY;

	/**
	 * 濡傛灉 HeaderView 鏄彲瑙佺殑 , 姝ゅ嚱鏁扮敤浜庡垽鏂槸鍚︾偣鍑讳简 HeaderView, 骞跺锅氱浉搴旗殑澶勭悊 , 锲犱负
	 * HeaderView 鏄敾涓婂幓镄? , 镓?浠ヨ缃簨浠剁洃鍚槸镞犳晥镄? , 鍙湁镊鎺у埗 .
	 */
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (mHeaderViewVisible) {
			switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				mDownX = ev.getX();
				mDownY = ev.getY();
				if (mDownX <= mHeaderViewWidth && mDownY <= mHeaderViewHeight) {
					return true;
				}
				break;
			case MotionEvent.ACTION_UP:
				float x = ev.getX();
				float y = ev.getY();
				float offsetX = Math.abs(x - mDownX);
				float offsetY = Math.abs(y - mDownY);
				// 濡傛灉 HeaderView 鏄彲瑙佺殑 , 镣瑰向鍦? HeaderView 鍐? , 闾ｄ箞瑙﹀彂
				// headerClick()
				if (x <= mHeaderViewWidth && y <= mHeaderViewHeight
						&& offsetX <= mHeaderViewWidth
						&& offsetY <= mHeaderViewHeight) {
					if (mHeaderView != null) {
						headerViewClick();
					}

					return true;
				}
				break;
			default:
				break;
			}
		}

		return super.onTouchEvent(ev);

	}

	@Override
	public void setAdapter(ExpandableListAdapter adapter) {
		super.setAdapter(adapter);
		mAdapter = (HeaderAdapter) adapter;
	}

	/**
	 * 
	 * 镣瑰向浜? Group 瑙﹀彂镄勪簨浠? , 瑕佹抵鎹抵鎹綋鍓岖偣鍑? Group 镄勭姸镐佹潵
	 */
	@Override
	public boolean onGroupClick(ExpandableListView parent, View v,
			int groupPosition, long id) {
		if (mAdapter.getGroupClickStatus(groupPosition) == 0) {
			mAdapter.setGroupClickStatus(groupPosition, 1);
			parent.expandGroup(groupPosition);
			// Header镊姩缃《
			// parent.setSelectedGroup(groupPosition);

		} else if (mAdapter.getGroupClickStatus(groupPosition) == 1) {
			mAdapter.setGroupClickStatus(groupPosition, 0);
			parent.collapseGroup(groupPosition);
		}

		// 杩斿洖 true 镓嶅彲浠ュ脊锲炵涓?琛? , 涓岖煡阆扑负浠?涔?
		return true;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		if (mHeaderView != null) {
			measureChild(mHeaderView, widthMeasureSpec, heightMeasureSpec);
			mHeaderViewWidth = mHeaderView.getMeasuredWidth();
			mHeaderViewHeight = mHeaderView.getMeasuredHeight();
		}
	}

	private int mOldState = -1;

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		final long flatPostion = getExpandableListPosition(getFirstVisiblePosition());
		final int groupPos = ExpandableListView
				.getPackedPositionGroup(flatPostion);
		final int childPos = ExpandableListView
				.getPackedPositionChild(flatPostion);
		int state = mAdapter.getHeaderState(groupPos, childPos);
		if (mHeaderView != null && mAdapter != null && state != mOldState) {
			mOldState = state;
			mHeaderView.layout(0, 0, mHeaderViewWidth, mHeaderViewHeight);
		}

		configureHeaderView(groupPos, childPos);
	}

	public void configureHeaderView(int groupPosition, int childPosition) {
		if (mHeaderView == null || mAdapter == null
				|| ((ExpandableListAdapter) mAdapter).getGroupCount() == 0) {
			return;
		}

		int state = mAdapter.getHeaderState(groupPosition, childPosition);

		switch (state) {
		case HeaderAdapter.PINNED_HEADER_GONE: {
			mHeaderViewVisible = false;
			break;
		}

		case HeaderAdapter.PINNED_HEADER_VISIBLE: {
			mAdapter.configureHeader(mHeaderView, groupPosition, childPosition,
					MAX_ALPHA);

			if (mHeaderView.getTop() != 0) {
				mHeaderView.layout(0, 0, mHeaderViewWidth, mHeaderViewHeight);
			}

			mHeaderViewVisible = true;

			break;
		}

		case HeaderAdapter.PINNED_HEADER_PUSHED_UP: {
			View firstView = getChildAt(0);
			int bottom = firstView.getBottom();

			// intitemHeight = firstView.getHeight();
			int headerHeight = mHeaderView.getHeight();

			int y;

			int alpha;

			if (bottom < headerHeight) {
				y = (bottom - headerHeight);
				alpha = MAX_ALPHA * (headerHeight + y) / headerHeight;
			} else {
				y = 0;
				alpha = MAX_ALPHA;
			}

			mAdapter.configureHeader(mHeaderView, groupPosition, childPosition,
					alpha);

			if (mHeaderView.getTop() != y) {
				mHeaderView.layout(0, y, mHeaderViewWidth, mHeaderViewHeight
						+ y);
			}

			mHeaderViewVisible = true;
			break;
		}
		}
	}

	@Override
	/**
	 * 鍒楄〃鐣岄溃镟存柊镞惰皟鐢ㄨ鏂规硶(濡傛粴锷ㄦ椂)
	 */
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
		if (mHeaderViewVisible) {
			// 鍒嗙粍镙忔槸鐩存帴缁桦埗鍒扮晫闱腑锛岃?屼笉鏄姞鍏ュ埌ViewGroup涓?
			drawChild(canvas, mHeaderView, getDrawingTime());
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		final long flatPos = getExpandableListPosition(firstVisibleItem);
		int groupPosition = ExpandableListView.getPackedPositionGroup(flatPos);
		int childPosition = ExpandableListView.getPackedPositionChild(flatPos);

		configureHeaderView(groupPosition, childPosition);
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
	}
}
