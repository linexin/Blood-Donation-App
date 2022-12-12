package com.linex.google.blooddonnerapp.WelcomeScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.linex.google.blooddonnerapp.R;
import com.linex.google.blooddonnerapp.UI.LoginActivity;

public class WelcomeActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
		
		new Handler().postDelayed((Runnable) () -> {
			startActivity(new Intent(this, LoginActivity.class));
			finish();
		},3000);
	}
}