package edu.uwosh.cs342.project3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FillIn extends Activity {
	TextView questionData;
	EditText userAnswer;
	Button ok;
	String answer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fillin);

		questionData = (TextView) findViewById(R.id.textView1);
		userAnswer = (EditText) findViewById(R.id.editText1);
		ok = (Button) findViewById(R.id.button1);
		CharSequence quest = getIntent().getExtras().getString("Question");
		questionData.setText(quest.toString());

		ok.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent myIntent = new Intent(FillIn.this,
						Project3Activity.class);
				answer = userAnswer.getText().toString();
				myIntent.putExtra("userAnswer", answer);
				startActivity(myIntent);
			}
		});
	}

}
