package edu.uwosh.cs342.project3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jdom.Document;
import org.jdom.JDOMException;

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
	QuizParser parser;
	int i;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.multiplechoice);
		
		String quizID = getIntent().getExtras().getString("Quiz");

		Score myScore = new Score();

		Cloud myCloud = new Cloud(this);

		try {
			parser = new QuizParser(new Document());
			// new QuizParser(myCloud.getQuiz(quizID));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}

		Button myButton = (Button) findViewById(R.id.button01);
		TextView tv = (TextView) findViewById(R.id.textView1);
		TextView tv2 = (TextView) findViewById(R.id.textView2);

		for (i = 0; i < parser.getNumMCQuestions(); i++) {
			tv.setText(parser.getMCQuestionPoint(i));

			tv2.setText("Current Score: " + Integer.toString(myScore.get()));

			String[] items;

			List<String> list = parser.getMCQuestionChoices(i);

			items = list.toArray(new String[list.size()]);

			spinner = (Spinner) findViewById(R.id.spinner1);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item, items);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinner.setAdapter(adapter);

			myButton.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					String text = spinner.getSelectedItem().toString();

					if (text.equals(parser.getMCQuestionAnswer(i))) {
						Score score = new Score();
						score.increment(parser.getMCQuestionPoint(i));
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
										+ parser.getMCQuestionAnswer(i),
								duration);
						toast.setGravity(5, 5, 5);
						toast.show();
					}
				}
			});
		}
		Intent myIntent = new Intent(MultipleChoice.this, FillIn.class);
		myIntent.putExtra("Quiz", quizID);
		startActivity(myIntent);
	}
}
