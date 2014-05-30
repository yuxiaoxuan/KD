package com.dy.activity;

import java.net.URLEncoder;

import com.dy.app.Declare;
import com.dy.util.HttpUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
	// ������¼��ȡ����ť
	private Button cancelBtn,loginBtn,exitBtn;
	private TextView registerBtn;
	// �����û��������������
	private EditText userEditText,pwdEditText;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ���ñ���
		//setTitle("���߹���ϵͳ-����");
		// ���õ�ǰActivity���沼��
		setContentView(R.layout.login_system);
		// ͨ��findViewById����ʵ�������
		
		loginBtn = (Button)findViewById(R.id.signin_button);
		
		registerBtn = (TextView) findViewById(R.id.register_link);
		// ͨ��findViewById����ʵ�������
		userEditText = (EditText)findViewById(R.id.username_edit);
		// ͨ��findViewById����ʵ�������
		pwdEditText = (EditText)findViewById(R.id.password_edit);
		
		
		
		registerBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
				startActivity(intent);
			}
		});
		
		loginBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				try {
					String url = HttpUtil.BASE_URL
							+ "LoginServlet?userName="
							+ URLEncoder.encode(
									URLEncoder.encode(userEditText.getText().toString(), "UTF-8"), "UTF-8")+"&password="
									+ URLEncoder.encode(
									URLEncoder.encode(pwdEditText.getText().toString(), "UTF-8"), "UTF-8");
					// ��ѯ���ؽ��
					String result = HttpUtil.queryStringForPost(url);
					System.out.println("=========================  "+result);
					if(!result.equals("0")){
						System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@");
						Declare declare = (Declare) getApplicationContext();
						declare.setId(Integer.parseInt(result));
						declare.setUserName(userEditText.getText().toString());
						Toast.makeText(getApplicationContext(), "����ɹ�", 1).show();
						Intent intent = new Intent();
						intent.setClass(LoginActivity.this,
								MainMenuActivity.class);
						startActivity(intent);
						
					}else{
						Toast.makeText(getApplicationContext(), "����ʧ��", 1).show();
					}
				} catch (Exception e) {
					// TODO: handle exception
					Log.i("LoginActivity",e.toString());
				}
				
			}
		});
	}
}