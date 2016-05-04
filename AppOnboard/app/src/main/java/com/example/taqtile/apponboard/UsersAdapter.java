package com.example.taqtile.apponboard;


/**
 * Created by taqtile on 5/3/16.
 */

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.*;
import android.widget.*;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {

    private List<User> mUsersList;
    private static final String TAG = "UsersAdapter";

    public interface UserAdapterListener {
        public void onItemClicked(int position);
    }

    private UserAdapterListener mListener ;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mUserId, mUserName;
        public RelativeLayout mCellContainer;

        public MyViewHolder(View v) {
            super(v);

            mUserId = (TextView) v.findViewById(R.id.mUserId);
            mUserName = (TextView) v.findViewById(R.id.mUserName);
            mCellContainer = (RelativeLayout) v.findViewById(R.id.mCellContainer);
        }

    }

    public UsersAdapter(List<User> usersList, UserAdapterListener listener) {
        this.mUsersList = usersList;
        this.mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_row, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        User user = mUsersList.get(position);
        holder.mUserId.setText("ID: " + String.valueOf(user.getId()));
        holder.mUserName.setText(user.getFirstName() + " " + user.getLastName());

        holder.mCellContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClicked(position);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mUsersList.size();
    }

}


