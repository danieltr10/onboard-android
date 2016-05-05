package com.example.taqtile.apponboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements UsersAdapter.UserAdapterListener{

    private static final String TAG = "MainActivity";
    private static final String BASE_URL = "http://reqres.in/";


    private List<User> mUsersList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private UsersAdapter mAdapter;
    private UsersDatabase mUsersDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "Mensagem de Log");

        mUsersDatabase = new UsersDatabase(this);

        User user = new User();
        user.setFirst_name("Daniel");
        user.setLast_name("Rizzuto");
        user.setId(3);
        user.setAvatar("Avatar.png");

        mUsersDatabase.addUser(user);

        User user4 = new User();
        user4 = mUsersDatabase.getUser(3);
        getUsersOnPage(1);

        configurarRecyclerView();

    }

    private void getUsersOnPage(int pageNumber) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UsersService service = retrofit.create(UsersService.class);

        Call<UsersWrapper> usersCall = service.getUsers(pageNumber);

        usersCall.enqueue(new Callback<UsersWrapper>() {
            @Override
            public void onResponse(Call<UsersWrapper> call, Response<UsersWrapper> response) {

                if(response.isSuccessful()) {

                    UsersWrapper users = response.body();
                    mUsersList = users.getData();

                    mAdapter.setmUsersList(mUsersList);

                    mAdapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onFailure(Call<UsersWrapper> call, Throwable t) {

            }
        });

    }

    private void configurarRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new UsersAdapter(mUsersList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
        mRecyclerView.setAdapter(mAdapter);


    }


    @Override
    public void onItemClicked(int position) {


        Intent intent = new Intent(this, DetailActivity.class);
        Bundle extras = new Bundle();

        extras.putString("USER_ID", String.valueOf(mUsersList.get(position).getId()));
        extras.putString("USER_FIRST_NAME", mUsersList.get(position).getFirst_name());
        extras.putString("USER_LAST_NAME", mUsersList.get(position).getLast_name());
        extras.putString("USER_AVATAR", mUsersList.get(position).getAvatar());

        intent.putExtras(extras);
        startActivity(intent);

    }
}
