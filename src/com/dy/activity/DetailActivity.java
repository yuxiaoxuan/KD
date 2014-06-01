package com.dy.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends Activity {
	private Intent intent;
	private Bundle data;
	private TextView name, price, count, text;
	private ImageView iv;
	private Bitmap bit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.detail_goods);

		prepareView();
		getData();


	}

	private void prepareView() {
		// TODO Auto-generated method stub
		name = (TextView) findViewById(R.id.goods_name_detail);
		price = (TextView) findViewById(R.id.goods_price_detail);
		count = (TextView) findViewById(R.id.goods_count_detail);
		text = (TextView) findViewById(R.id.goods_text_detail);
       iv=(ImageView) findViewById(R.id.lstp);
	}

	private void getData() {
		// TODO Auto-generated method stub
		intent = getIntent();
		data = intent.getExtras();
		name.setText(data.getString("name"));
		price.setText(data.getString("price"));
		count.setText(data.getString("count"));
		text.setText(data.getString("text"));
	   iv.setImageBitmap((Bitmap) data.getParcelable("icon"));
			}

}
