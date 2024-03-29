package edu.uwosh.cs342.project3;

import android.app.Activity;
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
import android.widget.Spinner;
import android.widget.Toast;

public class Project3Activity extends Activity {

	Spinner spinner;
	Score myScore;
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		myScore = new Score();
		Bundle extras = this.getIntent().getExtras();
		String username = extras.getString("username");

		Button myButton = (Button) findViewById(R.id.button1);

		Cloud myCloud = new Cloud(this);
		String quizList = myCloud.getQuizList(username);

		final String[] items = quizList.split("\n");
		

		spinner = (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, items);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		if (getIntent().hasExtra("Quiz")) {

			Context context = getApplicationContext();
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(context, "You scored " + myScore.get()
					+ " points", duration);
			toast.show();

			myCloud.sendScore(myScore.getUsername(), getIntent().getExtras()
					.getString("Quiz"), Integer.toString(myScore.get()));
		}

		myButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String quiz = spinner.getSelectedItem().toString();
				if (!quiz.isEmpty()) {
					Intent myIntent = new Intent(Project3Activity.this,
							MultipleChoice.class);
					myIntent.putExtra("Quiz", quiz);
	
					myScore.reset();
	
					startActivity(myIntent);
				}
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