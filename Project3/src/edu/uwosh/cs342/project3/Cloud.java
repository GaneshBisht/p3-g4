package edu.uwosh.cs342.project3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

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

	public String checkUser(String userName, String passWord) {
		try {

			user = userName;
			pass = passWord;
			httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://173.89.153.5/p3.php?login&user="
							+ userName + "&pass=" + passWord);
			response = httpclient.execute(httppost);
			inputStream = response.getEntity().getContent();


			inputStream.close();

			return response.getEntity().getContent().toString();
		} catch (Exception e) {
			return e.getMessage();
			
			
		}
	}
	
	public DocumentBuilder getQuiz(int quizId) {
		try {

			httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://173.89.153.5/p3.php?q=" + quizId);
			response = httpclient.execute(httppost);
			inputStream = response.getEntity().getContent();


			inputStream.close();
			FileWriter fstream = new FileWriter("quiz.xml");
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(response.getEntity().getContent().toString());
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			db.parse("quiz.xml");
			return db;

		} catch (Exception e) {
			
			
		}
		
		
		return null;
		
		
		
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
