package com.dy.activity;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.SimpleAdapter;

public class HomeActivity extends Activity {

	private int[] images = new int[] { R.drawable.gwc_02, R.drawable.gwc_02,
			R.drawable.gwc_02, R.drawable.gwc_02, R.drawable.gwc_02,
			R.drawable.gwc_02, R.drawable.gwc_02, R.drawable.gwc_02 };
	private String[] texts = new String[] { "RMB 59.5", "RMB 69", "RMB 49",
			"RMB 59.5", "RMB 59.5", "RMB 59.5", "RMB 69", "RMB 69" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.goodshow);
		GridView gridview = (GridView) findViewById(R.id.gridview);
		gridview.setAdapter(myAdapter(texts, images));
	}

	/**
	 * 构造菜单Adapter
	 * 
	 * @param menuNameArray
	 *            名称
	 * @param imageResourceArray
	 *            图片
	 * @return SimpleAdapter
	 */
	private SimpleAdapter myAdapter(String[] texts, int[] images) {

		ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < texts.length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("itemImage", images[i]);
			map.put("itemText", texts[i]);
			data.add(map);
		}
		SimpleAdapter simperAdapter = new SimpleAdapter(this, data,
				R.layout.item, new String[] { "itemImage", "itemText" },
				new int[] { R.id.ml_icon, R.id.textview01 });
		return simperAdapter;

	}

}