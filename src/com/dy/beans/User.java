package com.dy.beans;

import android.app.Application;

public class User extends Application {

	private int id;
    private String userName;
    public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
    
}
