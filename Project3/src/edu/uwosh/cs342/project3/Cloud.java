package edu.uwosh.cs342.project3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.io.*;
import org.jdom.*;
import org.jdom.input.SAXBuilder;

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
	private static final String RES_KEY = "378927272909";
	private RESEncryption myRes;

	public Cloud(Context context) {
		super(context, DB_NAME, null, 1);
		myRes = new RESEncryption(RES_KEY);
	}

	public String checkUser(String userName, String passWord) {
		try {

			user = userName;
			pass = passWord;
			httpclient = new DefaultHttpClient();
			httppost = new HttpPost("http://173.89.153.5/p3.php?login");

			nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("user", user.trim()));
			nameValuePairs.add(new BasicNameValuePair("pass", pass.trim()));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

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
			return e.toString();
		}
		try {
			return buffer.toString();
		} catch (NullPointerException e) {
			return "Permission denied";
		}
	}

	public Document getQuiz(String quizName) {
		try {

			httpclient = new DefaultHttpClient();
			httppost = new HttpPost("http://173.89.153.5/p3.php?q=" + quizName);
			response = httpclient.execute(httppost);
			inputStream = response.getEntity().getContent();
			
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(inputStream);
			
			inputStream.close();

			return doc;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public String getQuizList(String username) {
		try {

			httpclient = new DefaultHttpClient();
			httppost = new HttpPost("http://173.89.153.5/p3.php?qlist&u=" + username);

			nameValuePairs = new ArrayList<NameValuePair>(2);
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
			return e.toString();
		}
		try {
			return buffer.toString();
		} catch (NullPointerException e) {
			return "Data not returned from server";
		}
	}

	public String getScoresList(String user) {
		try {

			httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://173.89.153.5/p3.php?getscores&user=" + user);
			nameValuePairs = new ArrayList<NameValuePair>(2);
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
			return e.toString();
		}
		try {
			return buffer.toString();
		} catch (NullPointerException e) {
			return "Data not returned from server";
		}
	}

	public String sendScore(String user, String quiz, String score) {
		try {
			score = myRes.encrypt(score);

			httpclient = new DefaultHttpClient();
			httppost = new HttpPost(
					"http://173.89.153.5/p3.php?sendscore&user=" + user
							+ "&quizname=" + quiz + "&score=" + score);
			nameValuePairs = new ArrayList<NameValuePair>(2);
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
			return e.toString();
		}
		try {
			return buffer.toString();
		} catch (NullPointerException e) {
			return "Data not returned from server";
		}
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
