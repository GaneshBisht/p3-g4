package edu.uwosh.cs342.project3;

import java.io.InputStream;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Cloud extends SQLiteOpenHelper {
	String user = "", pass = "", timeStamp;
	byte[] data;
	HttpPost httppost;
	StringBuffer buffer;
	HttpResponse response;
	HttpClient httpclient;
	InputStream inputStream;
	SharedPreferences app_preferences;
	List<NameValuePair> nameValuePairs;
	public static final String DB_NAME = "contactDB.db";
	public static final int bufferSize = 1024;

	public Cloud(Context context) {
		super(context, DB_NAME, null, 1);
	}

	public int checkUser(String userName, String passWord) {
		try {

			user = userName;
			pass = passWord;
			httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://cs346.cs.uwosh.edu/students/klappt98/auth.php?user="
							+ userName + "&password=");
			response = httpclient.execute(httppost);
			inputStream = response.getEntity().getContent();

			data = new byte[256];

			buffer = new StringBuffer();
			int len = 0;
			while (-1 != (len = inputStream.read(data))) {
				buffer.append(new String(data, 0, len));
			}

			inputStream.close();
		} catch (Exception e) {

		}

		// return (buffer.charAt(0) - 48);
		return 1;
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
}
