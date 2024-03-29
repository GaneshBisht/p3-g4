package edu.uwosh.cs342.project3;

import java.io.IOException;

import org.jdom.Document;
import org.jdom.JDOMException;

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
	Button ok;
	String theirAnswer, quizID;
	RadioButton tru;
	int i;
	QuizParser parser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bool);

		quizID = getIntent().getExtras().getString("Quiz");

		Score myScore = new Score();

		Cloud myCloud = new Cloud(this);

		try {
			parser = new QuizParser(myCloud.getQuiz(quizID));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}

		questionData = (TextView) findViewById(R.id.textView1);
		ok = (Button) findViewById(R.id.button1);
		TextView tv2 = (TextView) findViewById(R.id.textView2);

		tv2.setText("Current Score: " + Integer.toString(myScore.get()));

		if (getIntent().hasExtra("Number")) {
			i = Integer.parseInt(getIntent().getExtras().get("Number")
					.toString());
		} else
			i = 0;

		if (i < parser.getNumTFQuestions()) {
			questionData.setText(parser.getTFQuestionText(i));

			tru = (RadioButton) findViewById(R.id.radio0);

			ok.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					if ((tru.isChecked() == true && parser.getTFQuestionAnswer(
							i).equals("true"))
							|| (tru.isChecked() == false && parser
									.getTFQuestionAnswer(i).equals("false"))) {
						Score score = new Score();
						score.increment(85);

						Context context = getApplicationContext();
						int duration = Toast.LENGTH_SHORT;

						Toast toast = Toast.makeText(context, "Correct",
								duration);
						toast.setGravity(5, 5, 5);
						toast.show();
					} else {
						Context context = getApplicationContext();
						int duration = Toast.LENGTH_SHORT;

						Toast toast = Toast.makeText(
								context,
								"The correct answer is: "
										+ parser.getTFQuestionAnswer(i),
								duration);
						toast.setGravity(5, 5, 5);
						toast.show();
					}
					Intent myIntent = null;
					i++;
					if (i < parser.getNumMCQuestions()) {
						myIntent = new Intent(Boolean.this, Boolean.class);
						myIntent.putExtra("Quiz", quizID);
						myIntent.putExtra("Number", i);
						startActivity(myIntent);
					} else {
						myIntent = new Intent(Boolean.this,
								Project3Activity.class);
						myIntent.putExtra("Quiz", quizID);
						startActivity(myIntent);
					}
				}
			});
		}
	}
}
