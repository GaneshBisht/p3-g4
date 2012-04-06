package edu.uwosh.cs342.project3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FillIn extends Activity {
	TextView questionData;
	EditText userAnswer;
	Button ok;
	String answer;
	String[] data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fillin);

		questionData = (TextView) findViewById(R.id.textView1);
		userAnswer = (EditText) findViewById(R.id.editText1);
		ok = (Button) findViewById(R.id.button1);
		data = getIntent().getExtras().getStringArray("Question");
		questionData.setText(data[1]);
		
		TextView tv2 = (TextView)findViewById(R.id.textView2);
		Score myScore = new Score();
		tv2.setText("Current Score: " + Integer.toString(myScore.get()));

		ok.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent myIntent = new Intent(FillIn.this, Control.class);
				answer = userAnswer.getText().toString();

				if (answer.equals(data[2])) {
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
