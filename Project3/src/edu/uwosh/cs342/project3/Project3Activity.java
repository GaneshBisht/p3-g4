package edu.uwosh.cs342.project3;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Project3Activity extends Activity {

	private static int totalCorrect = 0, questionNum = -1;
	private String question, answer;
	private String quiz[] = { "TF", "FI", "MC", "FI", "TF", "MC" };
	Spinner spinner;

	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button myButton = (Button) findViewById(R.id.button1);
		String[] items = new String[] { "Quiz 1", "Quiz 2", "Quiz 3" };
		spinner = (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, items);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		myButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String text = spinner.getSelectedItem().toString();
				// Builder.create();

				Intent myIntent = new Intent(Project3Activity.this,
						Control.class);
				// myIntent.putExtra("Quiz", quiz);

				// Intent myIntent = new Intent(Project3Activity.this,
				// FillIn.class);
				// question = "In the year _____ the world will end";
				// answer = "2012";
				// myIntent.putExtra("Question", question);

				Score myScore = new Score();
				myScore.reset();

				startActivity(myIntent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.mymenu2, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		Intent myIntent = new Intent(Project3Activity.this, History.class);
		startActivity(myIntent);
		return super.onOptionsItemSelected(item);
	}
}
// @Override
// protected void onResume() {
//
// if (getIntent().hasExtra("userAnswer")) {
// questionNum++;
// if (getIntent().getExtras().getString("userAnswer").equals(answer))
// totalCorrect++;
//
// if (quiz[questionNum].equals("TF")) {
// Intent myIntent = new Intent(Project3Activity.this,
// Boolean.class);
// question = "Is it nice out today?";
// answer = "True";
// myIntent.putExtra("Question", question);
// startActivity(myIntent);
//
// } else if (quiz[questionNum].equals("MC")) {
// String options[] = { "2011", "2012", "2013" };
// Intent myIntent = new Intent(Project3Activity.this,
// MultipleChoice.class);
// question = "What year is it?";
// answer = "2012";
// myIntent.putExtra("Question", question);
// myIntent.putExtra("Options", options);
// startActivity(myIntent);
//
// } else {
// Intent myIntent = new Intent(Project3Activity.this,
// FillIn.class);
// question = "In the year _____ the world will end";
// answer = "2012";
// myIntent.putExtra("Question", question);
// startActivity(myIntent);
// }
// }
//
// super.onResume();
// }

