package com.linex.google.blooddonnerapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.linex.google.blooddonnerapp.R;
import com.linex.google.blooddonnerapp.Model.Users;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.userViewHolder>{
	
	Context context;
	List<Users> listData;
	
	public UserAdapter(Context context, List<Users> listData) {
		this.context = context;
		this.listData = listData;
	}
	
	@NonNull
	@Override
	public UserAdapter.userViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_details, parent, false);
		UserAdapter.userViewHolder userViewHolder = new UserAdapter.userViewHolder(view);
		return userViewHolder;
	}
	
	@Override
	public void onBindViewHolder(@NonNull UserAdapter.userViewHolder holder, int position) {
		Users users = (Users) listData.get(position);
		
	holder.tvName.setText(users.name);
	holder.tvPhoneNo.setText(users.phoneNo);
	holder.tvBloodGroup.setText(users.bloodGroup);
	holder.tvAddress.setText(users.address);
	
	}
	
	@Override
	public int getItemCount() {
		return listData.size();
	}
	
	public class userViewHolder extends RecyclerView.ViewHolder {
		TextView tvName, tvPhoneNo, tvBloodGroup, tvAddress;
		public userViewHolder(@NonNull View itemView) {
			super(itemView);
			tvName = itemView.findViewById(R.id.tvName);
			tvPhoneNo = itemView.findViewById(R.id.tvPhoneNo);
			tvBloodGroup= itemView.findViewById(R.id.tvBloodGroup);
			tvAddress = itemView.findViewById(R.id.tvAddress);
		}
	}
}
