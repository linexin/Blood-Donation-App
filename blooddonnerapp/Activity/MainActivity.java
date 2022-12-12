package com.linex.google.blooddonnerapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.linex.google.blooddonnerapp.Adapter.UserAdapter;
import com.linex.google.blooddonnerapp.R;
import com.linex.google.blooddonnerapp.Model.Users;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
	private  RecyclerView myRecyclerView;
	private UserAdapter userAdapter;
	FirebaseDatabase firebaseDatabase;
	DatabaseReference databaseReference;
	FirebaseAuth auth;
	private TextView tvName, tvPhoneNo, tvBloodGroup, tvAddress;
	private List<Users> usersList = new ArrayList<>();
	ProgressBar progressBar;
	

	@SuppressLint("MissingInflatedId")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		auth = FirebaseAuth.getInstance();
		progressBar = (ProgressBar) findViewById(R.id.progressbar);
		
		initView();
		
	}
	
	private void initView() {
		
		firebaseDatabase = FirebaseDatabase.getInstance();
		databaseReference = firebaseDatabase.getReference("Users");
		myRecyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);
		tvName = (TextView) findViewById(R.id.tvName);
		tvPhoneNo = (TextView) findViewById(R.id.tvPhoneNo);
		tvBloodGroup = (TextView) findViewById(R.id.tvBloodGroup);
		tvAddress = (TextView) findViewById(R.id.tvAddress);
		myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		progressBar.setVisibility(View.VISIBLE);
		databaseReference.addValueEventListener(new ValueEventListener() {
			@SuppressLint("NotifyDataSetChanged")
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
				usersList.clear();
				progressBar.setVisibility(View.GONE);
				for (DataSnapshot ds : snapshot.getChildren()){
					Users users = ds.getValue(Users.class);
					usersList.add(users);
					
				}
				
				userAdapter = new UserAdapter(MainActivity.this, usersList);
				myRecyclerView.setAdapter(userAdapter);
				userAdapter.notifyDataSetChanged();
				if(usersList.isEmpty()){
					progressBar.setVisibility(View.VISIBLE);
				}else{
					progressBar.setVisibility(View.GONE);
				}
			}
			@Override
			public void onCancelled(@NonNull DatabaseError error) {
			}
		});
	
	}
	
}