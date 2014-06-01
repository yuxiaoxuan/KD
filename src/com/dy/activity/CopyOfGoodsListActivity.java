package com.dy.activity;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dy.beans.Declare;
import com.dy.util.HttpUtil;
import com.dy.util.ImageService;
import com.dy.util.ImageSimpleAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

public class CopyOfGoodsListActivity extends Activity {

	private ImageSimpleAdapter adapter;
	private ListView lv;
	private EditText et;
	private String question;
	private List<Map<String, Object>> list;
	private List<Integer> listItemID = new ArrayList<Integer>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goodslist);
		Declare declare = (Declare) getApplicationContext();
		String username = declare.getUserName();
//		if (username == null) {
//			setTitle("��ǰλ��---��Ʒ�б�");
//		} else {
//			setTitle("���ã�" + username + "   ��ǰλ��---��Ʒ�б�");
//		}

		setViews();
	}

	
	
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		
	}




	private void setViews() {
		lv = (ListView) findViewById(R.id.h_list_view);
		list = getDatas();
		adapter = new ImageSimpleAdapter(this, list, R.layout.message_list,
				new String[] { "icon", "name", "price", "count" }, new int[] {
						R.id.ml_icon, R.id.listName, R.id.listPrice,
						R.id.listCount });
		lv.setAdapter(adapter);
	}

	private List<Map<String, Object>> getDatas() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			Declare declare = (Declare) getApplicationContext();
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

					list.add(map);
				}
			}
		} catch (Exception e) {

			Toast.makeText(getApplicationContext(), "", 1).show();
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
						CopyOfGoodsListActivity.this);
				builder1.setMessage("û��ѡ���κμ�¼");
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
				intent.setClass(CopyOfGoodsListActivity.this, GwcListActivity.class);
				startActivity(intent);
			}

		} else if (item.getItemId() == 2) {
			Intent intent = new Intent();
			intent.setClass(CopyOfGoodsListActivity.this, LoginActivity.class);
			startActivity(intent);
		}
		return true;

	}
}