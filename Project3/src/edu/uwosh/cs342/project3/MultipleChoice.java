package edu.uwosh.cs342.project3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MultipleChoice extends Activity {

	Spinner spinner;
	String[] data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.multiplechoice);

		Button myButton = (Button) findViewById(R.id.button01);
		TextView tv = (TextView) findViewById(R.id.textView1);
		TextView tv2 = (TextView)findViewById(R.id.textView2);
		data = getIntent().getExtras().getStringArray("Question");
		tv.setText(data[1]);
		Score myScore = new Score();
		tv2.setText("Current Score: " + Integer.toString(myScore.get()));

		String[] items;

		List<String> list = new ArrayList<String>();

		for (int i = 3; i < data.length; i++) {
			list.add(data[i]);
		}
		items = list.toArray(new String[list.size()]);

		spinner = (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, items);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		myButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent myIntent = new Intent(MultipleChoice.this, Control.class);
				String text = spinner.getSelectedItem().toString();

				if (text.equals(data[2])) {
					Score score = new Score();
					score.increment();
					Context context = getApplicationContext();
					int duration = Toast.LENGTH_SHORT;

					Toast toast = Toast.makeText(context, "Correct", duration);
					toast.setGravity(5, 5, 5);
					toast.show();
				} else {
					Context context = getApplicationContext();
					int duration = Toast.LENGTH_SHORT;

					Toast toast = Toast
							.makeText(context, "Incorrect", duration);
					toast.setGravity(5, 5, 5);
					toast.show();
				}

				int number = getIntent().getExtras().getInt("Number");
				number++;
				myIntent.putExtra("qNumber", (number));
				startActivity(myIntent);
			}
		});

	}
}
