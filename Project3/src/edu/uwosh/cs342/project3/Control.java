package edu.uwosh.cs342.project3;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class Control extends Activity {
	ArrayList<?> quiz;
	Iterator<?> iterator;
	String[] question;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		ArrayList<String[]> quiz = new ArrayList<String[]>();

		String[] q1 = { "FI", "In the year ____ the world will end", "2012" };
		String[] q2 = { "MC", "What year is it?", "2012", "2013", "2014", "2012" };
		String[] q3 = { "TF", "It is nice out today", "true" };

		quiz.add(q1);
		quiz.add(q2);
		quiz.add(q3);

		// quiz = Builder.getQuiz();

		

		if (getIntent().hasExtra("qNumber")
				&& getIntent().getExtras().getInt("qNumber") < quiz.size()) {
			int number = getIntent().getExtras().getInt("qNumber");
			if (quiz.get(number)[0].equals("MC")) {
				Intent myIntent = new Intent(Control.this, MultipleChoice.class);
				myIntent.putExtra("Question", quiz.get(number));
				myIntent.putExtra("Number", number);
				startActivity(myIntent);
			} else if (quiz.get(number)[0].equals("TF")) {
				Intent myIntent = new Intent(Control.this, Boolean.class);
				myIntent.putExtra("Question", quiz.get(number));
				myIntent.putExtra("Number", number);
				startActivity(myIntent);
			} else {
				Intent myIntent = new Intent(Control.this, FillIn.class);
				myIntent.putExtra("Question", quiz.get(number));
				myIntent.putExtra("Number", number);
				startActivity(myIntent);
			}
		} else if (getIntent().hasExtra("qNumber") == false) {
			if (quiz.get(0)[0].equals("MC")) {
				Intent myIntent = new Intent(Control.this, MultipleChoice.class);
				myIntent.putExtra("Question", quiz.get(0));
				myIntent.putExtra("Number", 0);
				startActivity(myIntent);
			} else if (quiz.get(0)[0].equals("TF")) {
				Intent theIntent = new Intent(Control.this, Boolean.class);
				theIntent.putExtra("Question", quiz.get(0));
				theIntent.putExtra("Number", 0);
				startActivity(theIntent);
			} else {
				Intent theIntent = new Intent(Control.this, FillIn.class);
				theIntent.putExtra("Question", quiz.get(0));
				theIntent.putExtra("Number", 0);
				startActivity(theIntent);
			}
		} else {
//			Score myScore = new Score();
//			String text = Integer.toString(myScore.get());
//			
//			Context context = getApplicationContext();
//			int duration = Toast.LENGTH_LONG;
//
//			Toast toast = Toast.makeText(context, text, duration);
//			toast.show();
			
			Intent myIntent = new Intent(Control.this, Project3Activity.class);
			startActivity(myIntent);
		}
		// iterator = quiz.iterator();

	}

	// @Override
	// protected void onResume() {
	// super.onResume();
	//
	// Intent myIntent;
	// String[] question;
	//
	//
	// while (iterator.hasNext()) {
	// question = (String[]) iterator.next();
	//
	// Context context = getApplicationContext();;
	// int duration = Toast.LENGTH_LONG;
	//
	// Toast toast = Toast.makeText(context, question[0], duration);
	// toast.show();
	//
	// if (question[0].equals("MC")) {
	// myIntent = new Intent(Control.this, MultipleChoice.class);
	// myIntent.putExtra("Question", question);
	// startActivity(myIntent);
	// } else if (question[0].equals("TF")) {
	// myIntent = new Intent(Control.this, Boolean.class);
	// myIntent.putExtra("Question", question);
	// startActivity(myIntent);
	// } else {
	// myIntent = new Intent(Control.this, FillIn.class);
	// myIntent.putExtra("Question", question);
	// startActivity(myIntent);
	// }
	// }
	//
	// myIntent = new Intent(Control.this, Project3Activity.class);
	// startActivity(myIntent);
	// }
}
