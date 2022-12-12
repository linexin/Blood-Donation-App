package com.linex.google.blooddonnerapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.linex.google.blooddonnerapp.R;
import com.linex.google.blooddonnerapp.UI.LoginActivity;

public class DashboardActivity extends AppCompatActivity {
	private ProgressBar pb;
	private FirebaseAuth auth;
	
	@SuppressLint("MissingInflatedId")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);
		pb = (ProgressBar) findViewById(R.id.pb);
		auth = FirebaseAuth.getInstance();
	}
	
	public void onClickAllList(View view) {
		startActivity(new Intent(this, MainActivity.class));
		
	}
	
	public void onClickLogout(View view) {
		pb.setVisibility(View.VISIBLE);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				auth.signOut();
				pb.setVisibility(View.GONE);
				startActivity(new Intent(DashboardActivity.this, LoginActivity.class));
				finish();
			}
		},2000);
		
	}
	

}