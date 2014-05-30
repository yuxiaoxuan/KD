package com.dy.activity;

import java.net.URLEncoder;

import com.dy.app.Declare;
import com.dy.util.HttpUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class DingdanActivity extends Activity {
	/** Called when the activity is first created. */
	private Button button1;
	private Button button2;
	private Spinner spinner1;
	private ArrayAdapter<String> adapter1;
	private EditText text1;
	private EditText text2;
	private String value1;
	private static final String[] m1 = { "����ֱ��֧��", "���ÿ�֧��", "�绰֧��"}; // ��ʽ

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dingdan);
		button1 = (Button) this.findViewById(R.id.Button21);
		button1.setOnClickListener(myListener1);
		
		button2 = (Button) this.findViewById(R.id.Button22);
		button2.setOnClickListener(myListener2);

		text1 = (EditText) findViewById(R.id.Text21);
		text2 = (EditText) findViewById(R.id.Text22);

		// -------------------------spinner1
		spinner1 = (Spinner) findViewById(R.id.Spinner21);
		// ����ѡ������ArrayAdapter��������
		adapter1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, m1);

		// ���������б�ķ��
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// ��adapter ��ӵ�spinner��
		spinner1.setAdapter(adapter1);

		// ����¼�Spinner�¼�����
		spinner1.setOnItemSelectedListener(new SpinnerSelectedListener1());

		// ����Ĭ��ֵ
		spinner1.setVisibility(View.VISIBLE);
		Declare declare = (Declare) getApplicationContext();
		String username = declare.getUserName();
		if (username == null) {
			setTitle("��ǰλ��---����¼��");
		} else {
			setTitle("���ã�" + username + "   ��ǰλ��---����¼��");
		}
	}

	// ʹ��������ʽ����
	class SpinnerSelectedListener1 implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			value1 = m1[arg2];   

		}

		public void onNothingSelected(AdapterView<?> arg0) {
		}
	}


	private OnClickListener myListener1 = new Button.OnClickListener() {

		public void onClick(View v) {
			String phone = text1.getText().toString();
			String address = text2.getText().toString();
			String type = value1;
			Declare declare = (Declare) getApplicationContext();
			int myid = declare.getId();
			try {
				String url = HttpUtil.BASE_URL
						+ "JsServlet?myid="+ myid+ "&phone="
								+ URLEncoder.encode(
										URLEncoder.encode(phone, "UTF-8"),
										"UTF-8")+ "&address="
												+ URLEncoder.encode(
														URLEncoder.encode(address, "UTF-8"),
														"UTF-8")+ "&type="
														+ URLEncoder.encode(
																URLEncoder.encode(type, "UTF-8"),
																"UTF-8");
				// ��ѯ���ؽ��
				String result = HttpUtil.queryStringForPost(url);
				System.out.println("resultresultresult  "+result);
				if (!result.equals("0")) {//�ɹ� //��������ת
					Intent intent = new Intent();
					intent.setClass(DingdanActivity.this,
							DdListActivity.class);
					startActivity(intent);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	};
	
	// ���
			private OnClickListener myListener2 = new Button.OnClickListener() {
				public void onClick(View v) {
					text1.setText("");
					text2.setText("");
				}

			};

}