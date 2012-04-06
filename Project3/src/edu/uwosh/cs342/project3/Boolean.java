package edu.uwosh.cs342.project3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Boolean extends Activity {

	TextView questionData, tv;
	String[] data;
	Button ok;
	String theirAnswer;
	RadioButton tru;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bool);

		questionData = (TextView) findViewById(R.id.textView1);
		ok = (Button) findViewById(R.id.button1);
		
		TextView tv2 = (TextView)findViewById(R.id.textView2);
		Score myScore = new Score();
		tv2.setText("Current Score: " + Integer.toString(myScore.get()));

		data = getIntent().getExtras().getStringArray("Question");
		questionData.setText(data[1]);

		tru = (RadioButton) findViewById(R.id.radio0);

		ok.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent myIntent = new Intent(Boolean.this,
						Control.class);
				if ((tru.isChecked() == true && data[2].equals("true"))
						|| (tru.isChecked() == false && data[2].equals("false"))) {
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
