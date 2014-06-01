package com.dy.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class PublicTools {
	
	
	public  void ShowToast(Context context,String string) {
		// TODO Auto-generated method stub
				
		Toast toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
   		toast.setGravity(Gravity.CENTER, 0, 0);
   		toast.show();
		
		
		

	}
	
	
	
	

}
