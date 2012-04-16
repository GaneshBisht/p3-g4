package edu.uwosh.cs342.project3;

import java.util.ArrayList;

import android.*;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class History extends Activity {
	private ArrayList<String> scores;
	private ArrayList<String> calculator;
	private TextView average, standardDev;
	private RESEncryption myRes;
	private static final String RES_KEY = "378927272909";
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.history);
		myRes = new RESEncryption(RES_KEY);
		scores = new ArrayList<String>();
		calculator = new ArrayList<String>();
		
		average = (TextView)findViewById(R.id.textAverage);
		standardDev = (TextView)findViewById(R.id.textStandDev);
		
		ListView list = (ListView)findViewById(R.id.historyQuizList);
		list.setAdapter(new ArrayAdapter<String>(this, R.layout.list_item, scores));
		list.setTextFilterEnabled(true);
		
		Score myScore = new Score();
		Cloud cloud = new Cloud(this);
		String scoresList = cloud.getScoresList(myScore.getUsername());
		
		String[] listItems = scoresList.split("\n");
		for (int i = 0; i < listItems.length; i++){
			String[] score = listItems[i].split("\\+");
			scores.add(score[1] + "                                   " 
					+ myRes.decrypt(score[2]));
			calculator.add(myRes.decrypt(score[2]));
		}
		
		calculateAverage();
		calculateStandardDev();
	}
	
	private void calculateAverage(){
		double total = 0, result;
		for (int i = 0; i < calculator.size(); i++){
			total += Double.parseDouble(calculator.get(i));
		}
		result = total / calculator.size();
		average.setText(result + "");
	}
	
	private void calculateStandardDev(){
		double[] helpArray = new double[calculator.size()];
		double averageValue = Double.parseDouble(average.getText().toString());
		double total = 0;
		for (int i = 0; i < helpArray.length; i++){
			helpArray[i] = Math.pow((Double.parseDouble(calculator.get(i)) - averageValue), 2);
			total += helpArray[i];
		}
		total = total / (helpArray.length - 1);
		standardDev.setText(Math.sqrt(total) + "");
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		finish();
	}
}