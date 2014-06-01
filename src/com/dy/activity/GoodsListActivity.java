package com.dy.activity;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dy.beans.Declare;
import com.dy.util.CopyOfImageSimpleAdapter;
import com.dy.util.HttpUtil;
import com.dy.util.ImageService;
import com.dy.util.ImageSimpleAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

public class GoodsListActivity extends Activity {
	private CopyOfImageSimpleAdapter adapter;
	private GridView lv;
	private EditText et;
	private String question;
	private List<Map<String, Object>> list;
	private	List<Integer> listItemID = new ArrayList<Integer>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goodshow);
		Declare declare = (Declare) getApplicationContext();
		String username = declare.getUserName();
//		if (username == null) {
//			setTitle("当前位置---商品列表");
//		} else {
//			setTitle("您好：" + username + "   当前位置---商品列表");
//		}

		setViews();
	}

	private void setViews() {
		lv = (GridView) findViewById(R.id.lv_goods);
		list = getDatas();
		adapter = new CopyOfImageSimpleAdapter(this, list, R.layout.goods_item,
				new String[] { "icon", "name", "price", "count" ,"text"}, new int[] {
						R.id.ml_icon, R.id.listName, R.id.listPrice,
						R.id.listCount ,R.id.listtext});
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				
			Log.i("===========================>>>>"	,list.get(arg2).get("name").toString());
			Log.i("===========================>>>>"	,list.get(arg2).get("price").toString());
			Log.i("===========================>>>>"	,list.get(arg2).get("count").toString());
			Log.i("===========================>>>>"	,list.get(arg2).get("text").toString());
			
			Intent i=new Intent(GoodsListActivity.this,DetailActivity.class);
			
			Bitmap bit=(Bitmap) list.get(arg2).get("icon");
			Bundle bundle=new Bundle();
//			bundle.putParcelable("icon", bit);
			bundle.putString("name", list.get(arg2).get("name").toString());
			bundle.putString("price", list.get(arg2).get("price").toString());
			bundle.putString("count", list.get(arg2).get("count").toString());
			bundle.putString("text", list.get(arg2).get("text").toString());
				
				i.putExtras(bundle);
				startActivity(i);
//				finish();
				
				
			}
		});
		
		
	}
	
	
	
	
	
	
	
	

	private List<Map<String, Object>> getDatas() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			Declare declare = (Declare) getApplicationContext();
			int myid = declare.getId();
			System.out.println("myid          =" + myid);
			String url = HttpUtil.BASE_URL + "GoodsListServlet";

			// 查询返回结果
			String result = HttpUtil.queryStringForPost(url);
			System.out.println("=========================  " + result);
			String[] results = result.split("@");
			for (int i = 0; i < results.length; i++) {
				String[] photos = results[i].split(",");
				String path = photos[1];

				byte[] data = ImageService.getImage(path);// 获取图片数据

				if (data != null) {
					Map<String, Object> map = new HashMap<String, Object>();
					// 构建位图对象
					Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0,
							data.length);
					map.put("icon", bitmap);
					map.put("name", photos[0]);
					map.put("price", photos[2]);
					map.put("count", photos[3]);
					map.put("text", photos[4]);

					list.add(map);
				}
			}
		} catch (Exception e) {

			Toast.makeText(getApplicationContext(), "更新列表成功", 1).show();
		}
		return list;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(0, 1, 1, "确认提交/查看购物车");
		menu.add(0, 2, 2, "退出/重新登入");

		return super.onCreateOptionsMenu(menu);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (item.getItemId() == 1) {// 确认提交

			listItemID.clear();
			for (int i = 0; i < adapter.mChecked.size(); i++) {
				if (adapter.mChecked.get(i)) {
					listItemID.add(i);
				}
			}

			if (listItemID.size() == 0) {
				AlertDialog.Builder builder1 = new AlertDialog.Builder(
						GoodsListActivity.this);
				builder1.setMessage("没有选中任何记录");
				builder1.show();
			} else {
				for (int i = 0; i < listItemID.size(); i++) {
					String goodsName = list.get(listItemID.get(i)).get("name")
							+ "";
					System.out.println("goodsNamegoodsNamegoodsName   "
							+ goodsName);
					try {
						Declare declare = (Declare) getApplicationContext();
						int myid = declare.getId();
						String url = HttpUtil.BASE_URL
								+ "AddGoodsServlet?myid="
								+ myid
								+ "&goodsName="
								+ URLEncoder.encode(
										URLEncoder.encode(goodsName, "UTF-8"),
										"UTF-8");
						// 查询返回结果
						String result = HttpUtil.queryStringForPost(url);
						System.out.println("resultresultresult  " + result);
						if (!result.equals("0")) {// 成功 //在这里跳转
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				Intent intent = new Intent();
				intent.setClass(GoodsListActivity.this, GwcListActivity.class);
				startActivity(intent);
			}

		} else if (item.getItemId() == 2) {
			Intent intent = new Intent();
			intent.setClass(GoodsListActivity.this, LoginActivity.class);
			startActivity(intent);
		}
		return true;

	}
}