package com.dy.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dy.adapter.DdSimpleAdapter;
import com.dy.beans.Declare;
import com.dy.util.HttpUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnCreateContextMenuListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class DdListActivity extends Activity {

	DdSimpleAdapter adapter;
	ListView lv;
	EditText et;
	String question;
	List<Map<String, Object>> list;
	List<Integer> listItemID = new ArrayList<Integer>();
	String goodsName; 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ddlist);
		Declare declare = (Declare) getApplicationContext();
		String username = declare.getUserName();
		if (username == null) {
			setTitle("��ǰλ��---�ҵĶ���");
		} else {
			setTitle("���ã�" + username + "   ��ǰλ��---�ҵĶ���");
		}

		setViews();
	}

	private void setViews() {
		lv = (ListView) findViewById(R.id.dd_list_view);
		list= getDatas();
		adapter = new DdSimpleAdapter(this,list,
				R.layout.message_list, new String[] { "icon", "name", "price",
						"count" }, new int[] { R.id.ml_icon, R.id.listName,
						R.id.listPrice, R.id.listCount });
		lv.setAdapter(adapter);

		// ��ӳ������
		lv.setOnCreateContextMenuListener(myListener2);

	}

	private OnCreateContextMenuListener myListener2 = new OnCreateContextMenuListener() {

		public void onCreateContextMenu(ContextMenu menu, View v,
				ContextMenuInfo menuInfo) {
			menu.add(0, 0, 0, "�鿴��ϸ��Ϣ");
			//menu.add(0, 1, 1, "����");
		}
	};

	// �����˵���Ӧ����
	@Override
	public boolean onContextItemSelected(MenuItem item) {

		if (item.getItemId() == 0) {//�鿴��ϸ��Ϣ
			ContextMenuInfo info = item.getMenuInfo();
			AdapterContextMenuInfo contextMenuInfo = (AdapterContextMenuInfo) info;
			// ��ȡѡ����λ��
			int position = contextMenuInfo.position;
			// ��ȡ��������
			HashMap<String, Object> itemd = (HashMap<String, Object>) list.get(position);
			question = (String) itemd.get("ddid");
			//dialog();
			
			//��ת
			Intent intent = new Intent();
			intent.setClass(DdListActivity.this,
					DdMoreActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("ddid", question);
			intent.putExtras(bundle);
			startActivity(intent);
			
		} 
		return super.onContextItemSelected(item);
	}

	private List<Map<String, Object>> getDatas() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			Declare declare = (Declare) getApplicationContext();
			int myid = declare.getId();
			System.out.println("myid          =" + myid);
			String url = HttpUtil.BASE_URL + "DdListServlet?myid="+myid;
			// ��ѯ���ؽ��
			String result = HttpUtil.queryStringForPost(url);
			System.out.println("=========================  " + result);
			String[] results = result.split("@");
			for (int i = 0; i < results.length; i++) {
				String[] photos = results[i].split(",");

					Map<String, Object> map = new HashMap<String, Object>();
					map.put("ddid", photos[0]);
					map.put("ddtime", photos[1]);
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
		menu.add(0, 1, 1, "�˳�/���µ���");
	
		return super.onCreateOptionsMenu(menu);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (item.getItemId() == 1) {//ȷ���ύ
			
			Intent intent = new Intent();
			intent.setClass(DdListActivity.this,
					LoginActivity.class);
			startActivity(intent);
		} 
		return true;

	}
}