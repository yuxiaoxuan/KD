package com.dy.util;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageService {

	public static byte[] getImage(String path) throws Exception {

		URL url = new URL(path);

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		// ���ó�ʱʱ��

		conn.setConnectTimeout(5000);

		conn.setRequestMethod("GET");

		if (conn.getResponseCode() == 200) {

			InputStream inStream = conn.getInputStream();

			byte[] data = StreamTool.read(inStream);

			return data;

		}

		return null;

	}

}
