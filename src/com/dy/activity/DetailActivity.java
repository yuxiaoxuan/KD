package com.dy.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DetailActivity extends Activity{
private Intent intent;
private Bundle data;
private TextView name,price,count,text; 


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.detail_goods);
		
		prepareView();
//		getData();
		
		 intent=getIntent();
		 data=intent.getExtras();
		 String nss=data.getString("name");
			
			name.setText(nss);
			Log.i("========name=======>",nss);
		
		
		
	}

	private void prepareView() {
		// TODO Auto-generated method stub
		name=(TextView) findViewById(R.id.goods_name_detail);
		price=(TextView) findViewById(R.id.goods_price_detail);
		count=(TextView) findViewById(R.id.goods_count_detail);
		text=(TextView) findViewById(R.id.goods_text_detail);
		
	}

	private void getData() {
		// TODO Auto-generated method stub
		
//		
		
	}
	
	

}
