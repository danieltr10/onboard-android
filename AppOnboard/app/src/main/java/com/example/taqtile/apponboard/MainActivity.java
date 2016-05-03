package com.example.taqtile.apponboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private List<User> mUsersList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private UsersAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "Mensagem de Log");

        prepareUserData();

        configurarRecyclerView();

        mAdapter.notifyDataSetChanged();

    }

    private void configurarRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new UsersAdapter(mUsersList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this.getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        Intent intent = new Intent(view.getContext(), DetailActivity.class);
                        Bundle extras = new Bundle();

                        extras.putString("USER_ID", String.valueOf(mUsersList.get(position).getId()));
                        extras.putString("USER_FIRST_NAME", mUsersList.get(position).getFirstName());
                        extras.putString("USER_LAST_NAME", mUsersList.get(position).getLastName());
                        extras.putString("USER_AVATAR", mUsersList.get(position).getAvatar());

                        intent.putExtras(extras);
                        startActivity(intent);
                    }
                })
        );

    }

    private void prepareUserData() {
        User user = new User();
        user.setFirstName("Daniel");
        user.setLastName("Rizzuto");
        user.setId(0);
        user.setAvatar("www.avatar.com/" + user.getFirstName() + user.getLastName() + ".png");
        mUsersList.add(user);

        user = new User();
        user.setFirstName("Daniel");
        user.setLastName("Nakasato");
        user.setId(1);
        user.setAvatar("www.avatar.com/" + user.getFirstName() + user.getLastName() + ".png");
        mUsersList.add(user);

        user = new User();
        user.setFirstName("Roberto");
        user.setLastName("Rizzuto");
        user.setId(2);
        user.setAvatar("www.avatar.com/" + user.getFirstName() + user.getLastName() + ".png");
        mUsersList.add(user);

        user = new User();
        user.setFirstName("Laura");
        user.setLastName("Regina");
        user.setId(3);
        user.setAvatar("www.avatar.com/" + user.getFirstName() + user.getLastName() + ".png");
        mUsersList.add(user);

        user = new User();
        user.setFirstName("Rafael");
        user.setLastName("Trigo");
        user.setId(4);
        user.setAvatar("www.avatar.com/" + user.getFirstName() + user.getLastName() + ".png");
        mUsersList.add(user);

        user = new User();
        user.setFirstName("Daniel");
        user.setLastName("Lobato");
        user.setId(5);
        user.setAvatar("www.avatar.com/" + user.getFirstName() + user.getLastName() + ".png");
        mUsersList.add(user);

        user = new User();
        user.setFirstName("Roberta");
        user.setLastName("Truppa");
        user.setId(6);
        user.setAvatar("www.avatar.com/" + user.getFirstName() + user.getLastName() + ".png");
        mUsersList.add(user);

        user = new User();
        user.setFirstName("Arnaldo");
        user.setLastName("Coelho");
        user.setId(7);
        user.setAvatar("www.avatar.com/" + user.getFirstName() + user.getLastName() + ".png");
        mUsersList.add(user);

        user = new User();
        user.setFirstName("Bruno");
        user.setLastName("Fagundes");
        user.setId(8);
        user.setAvatar("www.avatar.com/" + user.getFirstName() + user.getLastName() + ".png");
        mUsersList.add(user);

        user = new User();
        user.setFirstName("Monteiro");
        user.setLastName("Lobato");
        user.setId(9);
        user.setAvatar("www.avatar.com/" + user.getFirstName() + user.getLastName() + ".png");
        mUsersList.add(user);

        user = new User();
        user.setFirstName("Zé");
        user.setLastName("Carioca");
        user.setId(10);
        user.setAvatar("www.avatar.com/" + user.getFirstName() + user.getLastName() + ".png");
        mUsersList.add(user);

        user = new User();
        user.setFirstName("Amanda");
        user.setLastName("Trimer");
        user.setId(11);
        user.setAvatar("www.avatar.com/" + user.getFirstName() + user.getLastName() + ".png");
        mUsersList.add(user);

        user = new User();
        user.setFirstName("Paloma");
        user.setLastName("Oliveira");
        user.setId(12);
        user.setAvatar("www.avatar.com/" + user.getFirstName() + user.getLastName() + ".png");
        mUsersList.add(user);


    }

}
