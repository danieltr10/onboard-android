package com.example.taqtile.apponboard;


/**
 * Created by taqtile on 5/3/16.
 */

import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.*;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {

    private List<User> usersList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView userId, userName;

        public MyViewHolder(View v) {
            super(v);
            userId = (TextView) v.findViewById(R.id.userId);
            userName = (TextView) v.findViewById(R.id.userName);
        }

    }

    public UsersAdapter(List<User> usersList) {
        this.usersList = usersList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        User user = usersList.get(position);
        holder.userId.setText("ID: " + String.valueOf(user.getId()));
        holder.userName.setText(user.getFirstName() + " " + user.getLastName());
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

}


