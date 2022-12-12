package com.linex.google.blooddonnerapp.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.linex.google.blooddonnerapp.Activity.DashboardActivity;
import com.linex.google.blooddonnerapp.Activity.MainActivity;
import com.linex.google.blooddonnerapp.R;

public class LoginActivity extends AppCompatActivity {
	
	private EditText etEmail, etPassword;
	private Button btnLogin, btnSignUp;
	private ProgressDialog pd;
	private FirebaseAuth auth;
	
	
	@SuppressLint("MissingInflatedId")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
		
		etEmail = (EditText) findViewById(R.id.editTextTextEmailAddress);
		etPassword = (EditText) findViewById(R.id.editTextTextPassword);
		btnLogin = (Button) findViewById(R.id.btnRegister);
		btnSignUp = (Button) findViewById(R.id.btnSignUp);
		
		auth = FirebaseAuth.getInstance();
		
		pd = new ProgressDialog(this);
		pd.setCancelable(false);
		pd.setMessage("Loading...");
		pd.dismiss();
		
		btnSignUp.setOnClickListener(v ->{
			startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
		});
		
		btnLogin.setOnClickListener(v ->{
			
			String email, password;
			email = etEmail.getText().toString();
			password = etPassword.getText().toString();
			
			if(TextUtils.isEmpty(email)){
				etEmail.setError("Required Email");
				etEmail.requestFocus();
			}else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
				etEmail.setError("Enter Valid Email Address");
				etEmail.requestFocus();
			}else if(TextUtils.isEmpty(password)){
				etPassword.setError("Required Password");
				etPassword.requestFocus();
			}else if(!(password.length() >= 6)){
				etPassword.setError("Enter minimum 6 digits Password");
				etPassword.requestFocus();
			}else{
				pd.show();
				loginNewUsers(email, password);
			}
		});
	}
	
	private void loginNewUsers(String email, String password) {
	
		auth.signInWithEmailAndPassword(email, password)
				.addOnCompleteListener(this, task -> {
					if(task.isSuccessful()){
						pd.dismiss();
						Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
						startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
						finish();
					}else {
						pd.dismiss();
						Toast.makeText(LoginActivity.this, "Failed! Please login after some time.", Toast.LENGTH_SHORT).show();
					}
				});
	
	}
	@Override
	public void onStart() {
		super.onStart();
		FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
		if(currentUser != null){
			startActivity(new Intent(this, DashboardActivity.class));
			finish();
		}
	}
	
}