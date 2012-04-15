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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FillIn extends Activity {
	TextView questionData;
	EditText userAnswer;
	Button ok;
	String answer;
	String[] data;
	QuizParser parser;
	int i;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fillin);

		String quizID = getIntent().getExtras().getString("Quiz");

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
		userAnswer = (EditText) findViewById(R.id.editText1);
		ok = (Button) findViewById(R.id.button1);

		for (i = 0; i < parser.getNumFIBQuestions(); i++) {
			questionData.setText(parser.getFIBQuestionText(i));

			TextView tv2 = (TextView) findViewById(R.id.textView2);
			tv2.setText("Current Score: " + Integer.toString(myScore.get()));

			ok.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					answer = userAnswer.getText().toString();

					if (answer.equals(parser.getFIBQuestionAnswers(i))) {
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
										+ parser.getFIBQuestionAnswers(i),
								duration);
						toast.setGravity(5, 5, 5);
						toast.show();
					}
				}
			});
		}Intent myIntent = new Intent(FillIn.this, Boolean.class);
		myIntent.putExtra("Quiz", quizID);
		startActivity(myIntent);
	}
}
