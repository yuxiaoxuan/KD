package com.dy.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MainMenuActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("���߹���ϵͳ-��ҳ��");
        setContentView(R.layout.main_menu);
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));
    }
    
    // �̳�BaseAdapter
    public class ImageAdapter extends BaseAdapter {
    	// ������
        private Context mContext;
        // ���췽��
        public ImageAdapter(Context c) {
            mContext = c;
        }
        // �������
        public int getCount() {
            return mThumbIds.length;
        }
        // ��ǰ���
        public Object getItem(int position) {
            return null;
        }
        // ��ǰ���id
        public long getItemId(int position) {
            return 0;
        }
        // ��õ�ǰ��ͼ
        public View getView(int position, View convertView, ViewGroup parent) {
        	// ����ͼƬ��ͼ
            ImageView imageView;
            if (convertView == null) {
            	// ʵ����ͼƬ��ͼ
                imageView = new ImageView(mContext);
                // ����ͼƬ��ͼ����
                imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8, 8, 8, 8);
            } else {
                imageView = (ImageView) convertView;
            }
            // ����ͼƬ��ͼͼƬ��Դ
            imageView.setImageResource(mThumbIds[position]);
            // Ϊ��ǰ��ͼ��Ӽ�����
            switch (position) {
			case 0:
				// ��ӵ�ͼ�����
				imageView.setOnClickListener(shoppingLinstener);
				break;
			case 1:
				// ��̨������
				imageView.setOnClickListener(cartLinstener);
				break;
			case 2:
				// ���ת̨������
				imageView.setOnClickListener(ordersLinstener);
				break;
			default:
				break;
			}
            
            return imageView;
        }
        // ͼƬ��Դ����
        private Integer[] mThumbIds = {
                R.drawable.shopping, R.drawable.cart,
                R.drawable.orders
        };
    }
    
    OnClickListener shoppingLinstener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			// ��������Activity
			intent.setClass(MainMenuActivity.this, GoodsListActivity.class);
			startActivity(intent);
		}
	};
	
    OnClickListener cartLinstener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			// ��������Activity
			intent.setClass(MainMenuActivity.this, GwcListActivity.class);
			startActivity(intent);
		}
	};
	
    OnClickListener ordersLinstener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			// ��������Activity
			intent.setClass(MainMenuActivity.this, DdListActivity.class);
			startActivity(intent);
		}
	};
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(0, 1, 1, "���µ���");
		menu.add(0, 2, 2, "�˳�");
	
		return super.onCreateOptionsMenu(menu);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (item.getItemId() == 1) {//���µ���
			
			Intent intent = new Intent();
			intent.setClass(MainMenuActivity.this,
					LoginActivity.class);
			startActivity(intent);
		} else if (item.getItemId() == 2) {//�˳�
			System.exit(0);  
		} 
		return true;

	}
}