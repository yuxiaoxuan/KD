package com.dy.activity;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dy.adapter.GirdSimpleAdapter;
import com.dy.adapter.ListSimpleAdapter;
import com.dy.beans.User;
import com.dy.util.HttpUtil;
import com.dy.util.ImageService;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

public class GoodsGirdActivity extends Activity {
	private GirdSimpleAdapter adapter;
	private GridView lv;
	private EditText et;
	private String question;
	private Button firstbtn;
	private List<Map<String, Object>> list;
	private List<Integer> listItemID = new ArrayList<Integer>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goodshow);
		User declare = (User) getApplicationContext();
		String username = declare.getUserName();
		// if (username == null) {
		// setTitle("��ǰλ��---��Ʒ�б�");
		// } else {
		// setTitle("���ã�" + username + "   ��ǰλ��---��Ʒ�б�");
		// }

		setViews();
		firstbtn.setVisibility(View.INVISIBLE) ;
	}

	private void setViews() {
		lv = (GridView) findViewById(R.id.lv_goods);
		firstbtn=(Button) findViewById(R.id.bt_back_first);
		
		list = getDatas();
		adapter = new GirdSimpleAdapter(this, list, R.layout.goods_item,
				new String[] { "icon", "name", "price", "count", "text" },
				new int[] { R.id.ml_icon, R.id.listName, R.id.listPrice,
						R.id.listCount, R.id.listtext });
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
//
//				Log.i("===========================>>>>",
//						list.get(arg2).get("name").toString());
//				Log.i("===========================>>>>",
//						list.get(arg2).get("price").toString());
//				Log.i("===========================>>>>",
//						list.get(arg2).get("count").toString());
//				Log.i("===========================>>>>",
//						list.get(arg2).get("text").toString());

				Intent i = new Intent(GoodsGirdActivity.this,
						GoodsDetailActivity.class);

				Bitmap bit = (Bitmap) list.get(arg2).get("icon");
				Bundle bundle = new Bundle();
				bundle.putParcelable("icon", bit);
				bundle.putString("name", list.get(arg2).get("name").toString());
				bundle.putString("price", list.get(arg2).get("price")
						.toString());
				bundle.putString("count", list.get(arg2).get("count")
						.toString());
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
			User declare = (User) getApplicationContext();
			int myid = declare.getId();
			System.out.println("myid          =" + myid);
			String url = HttpUtil.BASE_URL + "GoodsListServlet";

			// ��ѯ���ؽ��
			String result = HttpUtil.queryStringForPost(url);
			System.out.println("=========================  " + result);
			String[] results = result.split("@");
			for (int i = 0; i < results.length; i++) {
				String[] photos = results[i].split(",");
				String path = photos[1];

				byte[] data = ImageService.getImage(path);// ��ȡͼƬ����

				if (data != null) {
					Map<String, Object> map = new HashMap<String, Object>();
					// ����λͼ����
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

			Toast.makeText(getApplicationContext(), "�����б�ɹ�", 1).show();
		}
		return list;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(0, 1, 1, "ȷ���ύ/�鿴���ﳵ");
		menu.add(0, 2, 2, "�˳�/���µ���");

		return super.onCreateOptionsMenu(menu);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (item.getItemId() == 1) {// ȷ���ύ

			listItemID.clear();
			for (int i = 0; i < adapter.mChecked.size(); i++) {
				if (adapter.mChecked.get(i)) {
					listItemID.add(i);
				}
			}

			if (listItemID.size() == 0) {
				AlertDialog.Builder builder1 = new AlertDialog.Builder(
						GoodsGirdActivity.this);
				builder1.setMessage("û��ѡ���κμ�¼");
				builder1.show();
			} else {
				for (int i = 0; i < listItemID.size(); i++) {
					String goodsName = list.get(listItemID.get(i)).get("name")
							+ "";
					System.out.println("goodsNamegoodsNamegoodsName   "
							+ goodsName);
					try {
						User declare = (User) getApplicationContext();
						int myid = declare.getId();
						String url = HttpUtil.BASE_URL
								+ "AddGoodsServlet?myid="
								+ myid
								+ "&goodsName="
								+ URLEncoder.encode(
										URLEncoder.encode(goodsName, "UTF-8"),
										"UTF-8");
						// ��ѯ���ؽ��
						String result = HttpUtil.queryStringForPost(url);
						System.out.println("resultresultresult  " + result);
						if (!result.equals("0")) {// �ɹ� //��������ת
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				Intent intent = new Intent();
				intent.setClass(GoodsGirdActivity.this, GwcActivity.class);
				startActivity(intent);
			}

		} else if (item.getItemId() == 2) {
			Intent intent = new Intent();
			intent.setClass(GoodsGirdActivity.this, LoginActivity.class);
			startActivity(intent);
		}
		return true;

	}
}