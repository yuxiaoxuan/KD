package com.dy.activity;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;

import com.dy.adapter.DdMoreSimpleAdapter;
import com.dy.beans.User;
import com.dy.util.HttpUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DdMoreActivity extends Activity {

	private TextView et32, et34, et36, et38;

	DdMoreSimpleAdapter adapter;
	ListView lv;
	EditText et;
	String question;
	List<Map<String, Object>> list;
	List<Integer> listItemID = new ArrayList<Integer>();
	String goodsName;
	String ddid;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dingdanmore);

		et32 = (TextView) this.findViewById(R.id.TextView32);
		et34 = (TextView) this.findViewById(R.id.TextView34);
		et36 = (TextView) this.findViewById(R.id.TextView36); // �ͻ���ַ
		et38 = (TextView) this.findViewById(R.id.TextView38);
		// ��ֵ
		// ��������
		Bundle extras = this.getIntent().getExtras();
		ddid = extras.getString("ddid");

		User declare = (User) getApplicationContext();
		String username = declare.getUserName();
		if (username == null) {
			setTitle("��ǰλ��---��������");
		} else {
			setTitle("���ã�" + username + "   ��ǰλ��---��������");
		}
		setTvValue();
		setViews();
	}

	// ��
	private void setTvValue() {
		try {
			 
			User declare = (User) getApplicationContext();
			int myid = declare.getId();
			System.out.println("myid          =" + myid);
			String url = HttpUtil.BASE_URL + "DdMoreServlet?myid=" + myid
					+ "&ddid=" + ddid;
			// ��ѯ���ؽ��
			String result = HttpUtil.queryStringForPost(url);
			System.out.println("=========================  " + result);
			Log.i("DdMoreActivity", result);
			String[] photos = result.split(",");
			et32.setText(photos[0]);
			et34.setText(photos[1]);
			et38.setText(photos[3]);
			Log.i("DdMoreActivity", photos[2]);
			et36.setText(URLDecoder.decode(photos[2], "GB2312")); // �ͻ���ַ
			Log.i("DdMoreActivity", URLDecoder.decode(photos[2], "GB2312"));
		} catch(Exception e) {

			Toast.makeText(getApplicationContext(), "", 1).show();
		}
	}

	private void setViews() {
		lv = (ListView) findViewById(R.id.ddmore_list_view);
		list = getDatas();
		adapter = new DdMoreSimpleAdapter(this, list,
				R.layout.ddmoremessage_list, new String[] { "icon", "name",
						"price", "count" }, new int[] { R.id.ml_icon,
						R.id.listName, R.id.listPrice, R.id.listCount });
		lv.setAdapter(adapter);

	}

	private List<Map<String, Object>> getDatas() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			User declare = (User) getApplicationContext();
			int myid = declare.getId();
			System.out.println("myid          =" + myid);
			String url = HttpUtil.BASE_URL + "DdMoreListServlet?myid=" + myid
					+ "&ddid=" + ddid;
			// ��ѯ���ؽ��
			String result = HttpUtil.queryStringForPost(url);
			System.out.println("=========================  " + result);
			String[] results = result.split("@");
			for (int i = 0; i < results.length; i++) {
				String[] photos = results[i].split(",");

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("gname", photos[0]);
				map.put("gcount", photos[1]);
				list.add(map);
			}
		} catch (Exception e) {

			Toast.makeText(getApplicationContext(), "", 1).show();
		}
		return list;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(0, 1, 1, "��������");
		menu.add(0, 2, 2, "��չ��ﳵ");
		menu.add(0, 3, 3, "���ɶ���");
		menu.add(0, 4, 4, "�˳�/���µ���");

		return super.onCreateOptionsMenu(menu);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (item.getItemId() == 1) {// ȷ���ύ

			Intent intent = new Intent();
			intent.setClass(DdMoreActivity.this, GoodsGirdActivity.class);
			startActivity(intent);
			listItemID.clear();
			if (listItemID.size() == 0) {
				AlertDialog.Builder builder1 = new AlertDialog.Builder(
						DdMoreActivity.this);
				builder1.setMessage("û��ѡ���κμ�¼");
				builder1.show();
			} else {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < listItemID.size(); i++) {
					String goodsName = list.get(listItemID.get(i)).get("name")
							+ "";
					System.out.println("goodsNamegoodsNamegoodsName   "
							+ goodsName);
					if (i != 0) {
						sb.append(",");

					}
					sb.append(goodsName);
				}

				try {
					User declare = (User) getApplicationContext();
					int myid = declare.getId();
					String url = HttpUtil.BASE_URL
							+ "AddGoodsServlet?myid="
							+ myid
							+ "&goodsName="
							+ URLEncoder.encode(
									URLEncoder.encode(sb.toString(), "UTF-8"),
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

		} else if (item.getItemId() == 2) {
			User declare = (User) getApplicationContext();
			int myid = declare.getId();
			String url = HttpUtil.BASE_URL + "DeleteAllGwcServlet?myid=" + myid;

			// ��ѯ���ؽ��
			String result = HttpUtil.queryStringForPost(url);
			System.out.println("resultresultresult  " + result);
			if (!result.equals("0")) {// �ɹ� //��������ת

			}

		} else if (item.getItemId() == 3) {// ���ɶ���
			// String str="";
			// for (int i = 0; i < adapter.mChecked.size(); i++) {
			// if(i!=0){
			// str+=",";
			// str+=adapter.mChecked.get(i);
			// }
			//
			// }
			Intent intent = new Intent();
			intent.setClass(DdMoreActivity.this, DingdanActivity.class);
			// Bundle bundle = new Bundle();
			// bundle.putString("str", str);
			// intent.putExtras(bundle);
			startActivity(intent);

			// System.out.println("str============ "+str);
			// Declare declare = (Declare) getApplicationContext();
			// int myid = declare.getId();
			// String url = HttpUtil.BASE_URL
			// + "JsServlet?myid="+ myid+"&str="+str;
			//
			// // ��ѯ���ؽ��
			// String result = HttpUtil.queryStringForPost(url);
			// System.out.println("resultresultresult  "+result);
			// if (!result.equals("0")) {//�ɹ� //��������ת
			//
			// }

		} else if (item.getItemId() == 4) {

		}
		return true;

	}
}