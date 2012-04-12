package edu.uwosh.cs342.project3;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {
	private Button login;
	private String userName, userPassword;
	private EditText password, name;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		final Dialog alert = new Dialog(this);
		alert.setContentView(R.layout.auth);
		alert.setCancelable(true);
		alert.setTitle("Login");
		alert.show();

		login = (Button) (alert.findViewById(R.id.login));

		login.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				name = (EditText) alert.findViewById(R.id.username);
				password = (EditText) alert.findViewById(R.id.password);
				userName = name.getText().toString();
				userPassword = password.getText().toString();
				doCheckCloud();
				alert.cancel();
				
				Intent myIntent = new Intent(Login.this, Project3Activity.class);
				startActivity(myIntent);
			}
		});
	}

	private void doCheckCloud() {
		Cloud myCloud = new Cloud(this);
		String authenticate = myCloud.checkUser(userName, userPassword);
		// authenticate will return relevant text instead of just numbers, so we must compare with .equals
		if (authenticate.equals("Login validated")) {
			
			Context context = getApplicationContext(); 
			CharSequence text = "Login Validated";
			int duration = Toast.LENGTH_SHORT;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();

		}
		else if (authenticate.equals("Permission denied")) {
			
			Context context = getApplicationContext();
			CharSequence text = "Permission to server denied";
			int duration = Toast.LENGTH_SHORT;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}
		else  {
			
			Context context = getApplicationContext();
			CharSequence text = "Wrong username or password";
			int duration = Toast.LENGTH_SHORT;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();

			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
		}
	}
}
