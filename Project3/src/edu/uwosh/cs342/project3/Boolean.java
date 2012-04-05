package edu.uwosh.cs342.project3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class Boolean extends Activity {

	TextView questionData;
	Button ok;
	String theirAnswer;
	RadioButton tru;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bool);

		questionData = (TextView) findViewById(R.id.textView1);
		ok = (Button) findViewById(R.id.button1);
		CharSequence quest = getIntent().getExtras().getString("Question");
		questionData.setText(quest.toString());
		
		tru = (RadioButton)findViewById(R.id.radio0);

		ok.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent myIntent = new Intent(Boolean.this,
						Project3Activity.class);
				if (tru.isChecked() == true) {
					myIntent.putExtra("userAnswer", "true");
					startActivity(myIntent);
				} else {
					myIntent.putExtra("userAnswer", "false");
					startActivity(myIntent);
				}
			}
		});
	}
}
