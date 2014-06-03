package com.dy.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GoodsDetailActivity extends Activity {
	private Intent intent;
	private Bundle data;
	private TextView name, price, count, text;
	private ImageView iv;
	private Bitmap bit;
   private  Button back,buy;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.detail_goods);

		prepareView();
		getData();
		
		back.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
//				Intent intent = new Intent(GoodsDetailActivity.this,GoodsGirdActivity.class);
//				startActivity(intent);
				finish();
			}
		});

		buy.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(GoodsDetailActivity.this,GoodsListActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

	private void prepareView() {
		// TODO Auto-generated method stub
		back=(Button) findViewById(R.id.bt_back_first);
		buy=(Button) findViewById(R.id.detail_buy_button);
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
