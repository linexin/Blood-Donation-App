package com.linex.google.blooddonnerapp.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.linex.google.blooddonnerapp.R;
import com.linex.google.blooddonnerapp.Model.Users;

public class SignUpActivity extends AppCompatActivity {
	
	private EditText etEmail, etPassword, etName, etPhoneNo,  etAddress, etBloodGroup;
	private Button btnRegister, btnBackToLogin;
	private ProgressDialog pd;
	FirebaseAuth auth;
	DatabaseReference myRef;
	FirebaseDatabase firebaseDatabase;
	
	
	@SuppressLint("MissingInflatedId")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		//WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
		
		etEmail = (EditText) findViewById(R.id.editTextEmailAddress);
		etPassword = (EditText) findViewById(R.id.editTextPassword);
		etName = (EditText) findViewById(R.id.editTextName);
		etPhoneNo = (EditText) findViewById(R.id.editTextPhoneNo);
		etBloodGroup = (EditText) findViewById(R.id.editTextBloodGroup);
		etAddress = (EditText) findViewById(R.id.editTextAddress);
		btnRegister = (Button) findViewById(R.id.btnRegister);
		btnBackToLogin = (Button) findViewById(R.id.btnBackToLogin);
		
		
		auth = FirebaseAuth.getInstance();
		firebaseDatabase = FirebaseDatabase.getInstance();
		myRef = firebaseDatabase.getReference("Users");
		
		
		pd = new ProgressDialog(this);
		pd.setCancelable(false);
		pd.setMessage("Loading...");
		pd.dismiss();
		
		btnBackToLogin.setOnClickListener(view -> {
			startActivity(new Intent(this, LoginActivity.class));
		});
		
		btnRegister.setOnClickListener(v ->{
			
			String  email, password, name, phoneNo, address,bloodGroup;
			email = etEmail.getText().toString();
			password = etPassword.getText().toString();
			name = etName.getText().toString();
			phoneNo = etPhoneNo.getText().toString();
			bloodGroup = etBloodGroup.getText().toString();
			address = etAddress.getText().toString();
			
			
			if (TextUtils.isEmpty(name)) {
				etName.setError("Required Name");
				etName.requestFocus();
			}else if (TextUtils.isEmpty(phoneNo)) {
				etPhoneNo.setError("Required Phone Number");
				etPhoneNo.requestFocus();
			}else if (TextUtils.isEmpty(bloodGroup)) {
				etBloodGroup.setError("Required Blood Group");
				etBloodGroup.requestFocus();
			}else if (TextUtils.isEmpty(address)) {
				etAddress.setError("Required Address");
				etAddress.requestFocus();
			}else if (TextUtils.isEmpty(email)) {
					etEmail.setError("Required Email");
					etEmail.requestFocus();
				} else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
					etEmail.setError("Enter Valid Email Address");
					etEmail.requestFocus();
				} else if (TextUtils.isEmpty(password)) {
					etPassword.setError("Required Password");
					etPassword.requestFocus();
				} else if (!(password.length() >= 6)) {
					etPassword.setError("Enter minimum 6 digits Password");
					etPassword.requestFocus();
				} else {
					pd.show();
					newUsersRegisterAccount(email, password, name, phoneNo, bloodGroup,  address );
				}
		});
		
		
	}
	
	private void newUsersRegisterAccount(String email, String password, String name, String phoneNo, String bloodGroup, String address) {
		
		auth.createUserWithEmailAndPassword(email, password)
				.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
					@Override
					public void onComplete(@NonNull Task<AuthResult> task) {
						if (task.isSuccessful()) {
							pd.dismiss();
							Toast.makeText(SignUpActivity.this, "Register Successful", Toast.LENGTH_SHORT).show();
							
							Users users = new Users();
							users.setName(name);
							users.setPhoneNo(phoneNo);
							users.setBloodGroup(bloodGroup);
							users.setAddress(address);
							
							
							myRef.child(auth.getUid()).setValue(users);
							startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
							finish();
							} else {
								pd.dismiss();
								Toast.makeText(SignUpActivity.this, "Failed! Please login after some time.", Toast.LENGTH_SHORT).show();
							}
						}
				});
		
	}
	
	
	
}