package com.dy.activity;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends TabActivity implements OnClickListener{
	ImageView mBut1, mBut2, mBut3, mBut4, mBut5;
	TextView mCateText1, mCateText2, mCateText3, mCateText4, mCateText5;
	Intent mItent1, mItent2, mItent3, mItent4,mItent5;
	public static String TAB_TAG_HOME = "home";
	public static String TAB_TAG_CHANNEL = "new";
	public static String TAB_TAG_ACCOUNT = "class";
	public static String TAB_TAG_SEARCH = "gwc";
	public static String TAB_TAB_MORE = "more";
	public static TabHost mTabHost;
	//int mCurTabId = R.id.channel1;
	static final int COLOR1 = Color.parseColor("#ff585858");
	static final int COLOR2 = Color.parseColor("#ffffff");
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
		prepareIntent();
		setupIntent();
		prepareView();
	}

	private void prepareView() {
		// TODO Auto-generated method stub
	
		mBut1 = (ImageView) findViewById(R.id.imageView1);
		mBut2 = (ImageView) findViewById(R.id.imageView2);
		mBut3 = (ImageView) findViewById(R.id.imageView3);
		mBut4 = (ImageView) findViewById(R.id.imageView4);
		mBut5 = (ImageView) findViewById(R.id.imageView5);
		findViewById(R.id.channel1).setOnClickListener(this);
		findViewById(R.id.channel2).setOnClickListener(this);
		findViewById(R.id.channel3).setOnClickListener(this);
		findViewById(R.id.channel4).setOnClickListener(this);
		findViewById(R.id.channel5).setOnClickListener(this);
		mCateText1 = (TextView) findViewById(R.id.textView1);
		mCateText2 = (TextView) findViewById(R.id.textView2);
		mCateText3 = (TextView) findViewById(R.id.textView3);
		mCateText4 = (TextView) findViewById(R.id.textView4);
		mCateText5 = (TextView) findViewById(R.id.textView5);
		
	}

	private void setupIntent() {
		// TODO Auto-generated method stub
		mTabHost = getTabHost();
		mTabHost.addTab(buildTabSpec(TAB_TAG_HOME, R.string.home_s,
				R.drawable.home_h, mItent1));
		mTabHost.addTab(buildTabSpec(TAB_TAG_CHANNEL,R.string.class_s, 
				R.drawable.class_h, mItent2));
		mTabHost.addTab(buildTabSpec(TAB_TAG_SEARCH, R.string.gwc_s,
				R.drawable.gwc_h, mItent3));
		mTabHost.addTab(buildTabSpec(TAB_TAG_ACCOUNT,R.string.new_s, 
				R.drawable.new_h, mItent4));
		mTabHost.addTab(buildTabSpec(TAB_TAB_MORE, R.string.more_s,
			R.drawable.more_h, mItent5));
	}

	private void prepareIntent() {
		// TODO Auto-generated method stub
		mItent1 = new Intent(this, GoodsListActivity.class);
		mItent2 = new Intent(this, CopyOfGoodsListActivity.class);
    	mItent3 = new Intent(this, GwcListActivity.class);
		mItent4 = new Intent(this, DdListActivity.class);
		mItent5 = new Intent(this, DetailActivity.class);
	}

	
	
	
	
	
	private TabHost.TabSpec buildTabSpec(String tag, int resLabel, int resIcon,
			final Intent content) {
		return mTabHost
				.newTabSpec(tag)
				.setIndicator(getString(resLabel),
						getResources().getDrawable(resIcon))
				.setContent(content);
	}

	public static void setCurrentTabByTag(String tab) {
		mTabHost.setCurrentTabByTag(tab);
	}
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		//if (mCurTabId == v.getId()) {
			//return;
	//	}
		mBut1.setImageResource(R.drawable.home_p);
		mBut2.setImageResource(R.drawable.class_p);
		mBut3.setImageResource(R.drawable.gwc_p);
		mBut4.setImageResource(R.drawable.new_p);
		mBut5.setImageResource(R.drawable.more_p);
		mCateText1.setTextColor(COLOR1);
		mCateText2.setTextColor(COLOR1);
		mCateText3.setTextColor(COLOR1);
		mCateText4.setTextColor(COLOR1);
		mCateText5.setTextColor(COLOR1);
	  
		
		int checkedId = v.getId();
		
		switch (checkedId) {
		case R.id.channel1:
			mTabHost.setCurrentTabByTag(TAB_TAG_HOME);
			mBut1.setImageResource(R.drawable.home_h);
			mCateText1.setTextColor(COLOR2);
			break;
		case R.id.channel2:
			mTabHost.setCurrentTabByTag(TAB_TAG_CHANNEL);
			mBut2.setImageResource(R.drawable.class_h );
			mCateText2.setTextColor(COLOR2);
			break;
		case R.id.channel3:
			mTabHost.setCurrentTabByTag(TAB_TAG_SEARCH);
			mBut3.setImageResource(R.drawable.gwc_h);
			mCateText3.setTextColor(COLOR2);
			break;
		case R.id.channel4:
			mTabHost.setCurrentTabByTag(TAB_TAG_ACCOUNT);
			mBut4.setImageResource(R.drawable.new_h);
			mCateText4.setTextColor(COLOR2);
			break;
		case R.id.channel5:
			mTabHost.setCurrentTabByTag(TAB_TAB_MORE);
			mBut5.setImageResource(R.drawable.more_h);
			mCateText5.setTextColor(COLOR2);
			break;
		default:
			break;
		}

		
	}

}
