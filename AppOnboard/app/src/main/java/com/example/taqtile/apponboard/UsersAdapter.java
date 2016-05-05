package com.example.taqtile.apponboard;


/**
 * Created by taqtile on 5/3/16.
 */

import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.*;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {

    private List<User> mUsersList;
    private static final String TAG = "UsersAdapter";
    private UserAdapterListener mListener ;

    public void setmUsersList(List<User> mUsersList) {
        this.mUsersList = mUsersList;
    }

    public interface UserAdapterListener {
        public void onItemClicked(int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mUserId, mUserName;
        public RelativeLayout mCellContainer;

        public MyViewHolder(View v) {
            super(v);

            mUserId = (TextView) v.findViewById(R.id.user_id);
            mUserName = (TextView) v.findViewById(R.id.user_name);
            mCellContainer = (RelativeLayout) v.findViewById(R.id.cell_container);
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
        holder.mUserName.setText(user.getFirst_name() + " " + user.getLast_name());

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


