package edu.uwosh.cs342.project3;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Project3Activity extends Activity {
	private Button login;
	private String userName, userPassword;
	private EditText password, name;
	Spinner spinner;

	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final Dialog alert = new Dialog(this);
		alert.setContentView(R.layout.auth);
		alert.setCancelable(true);
		alert.setTitle("Login");
		alert.show();

		login = (Button) (alert.findViewById(R.id.login));

		login.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				alert.cancel();
			}
		});

		Button myButton = (Button) findViewById(R.id.button1);
		String[] items = new String[] { "Multiple Choice", "Boolean", "Fill In" };
		spinner = (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, items);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		myButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
			}
		});
	}
}