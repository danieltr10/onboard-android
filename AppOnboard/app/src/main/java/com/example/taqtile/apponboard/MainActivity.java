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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "Mensagem de Log");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UsersService service = retrofit.create(UsersService.class);

        Call<UsersWrapper> usersCall = service.getUsers();

        usersCall.enqueue(new Callback<UsersWrapper>() {
            @Override
            public void onResponse(Call<UsersWrapper> call, Response<UsersWrapper> response) {

                if(response.isSuccessful()) {
                    UsersWrapper users = response.body();

                    User user = new User();
                    user.setFirst_name(users.getData().get(0).getFirst_name());
                    user.setLast_name(users.getData().get(0).getLast_name());
                    user.setId(users.getData().get(0).getId());
                    user.setAvatar(users.getData().get(0).getAvatar());
                    mUsersList.add(user);

                    mUsersList = users.getData();
                    mAdapter.setmUsersList(mUsersList);

                    mAdapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onFailure(Call<UsersWrapper> call, Throwable t) {

            }
        });


        configurarRecyclerView();




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

    private void prepareUserData() {




        User user = new User();
        user.setFirst_name("Daniel");
        user.setLast_name("Rizzuto");
        user.setId(0);
        user.setAvatar("www.avatar.com/" + user.getFirst_name() + user.getLast_name() + ".png");
        mUsersList.add(user);

        user = new User();
        user.setFirst_name("Daniel");
        user.setLast_name("Nakasato");
        user.setId(1);
        user.setAvatar("www.avatar.com/" + user.getFirst_name() + user.getLast_name() + ".png");
        mUsersList.add(user);

        user = new User();
        user.setFirst_name("Roberto");
        user.setLast_name("Rizzuto");
        user.setId(2);
        user.setAvatar("www.avatar.com/" + user.getFirst_name() + user.getLast_name() + ".png");
        mUsersList.add(user);

        user = new User();
        user.setFirst_name("Laura");
        user.setLast_name("Regina");
        user.setId(3);
        user.setAvatar("www.avatar.com/" + user.getFirst_name() + user.getLast_name() + ".png");
        mUsersList.add(user);

        user = new User();
        user.setFirst_name("Rafael");
        user.setLast_name("Trigo");
        user.setId(4);
        user.setAvatar("www.avatar.com/" + user.getFirst_name() + user.getLast_name() + ".png");
        mUsersList.add(user);

        user = new User();
        user.setFirst_name("Daniel");
        user.setLast_name("Lobato");
        user.setId(5);
        user.setAvatar("www.avatar.com/" + user.getFirst_name() + user.getLast_name() + ".png");
        mUsersList.add(user);

        user = new User();
        user.setFirst_name("Roberta");
        user.setLast_name("Truppa");
        user.setId(6);
        user.setAvatar("www.avatar.com/" + user.getFirst_name() + user.getLast_name() + ".png");
        mUsersList.add(user);

        user = new User();
        user.setFirst_name("Arnaldo");
        user.setLast_name("Coelho");
        user.setId(7);
        user.setAvatar("www.avatar.com/" + user.getFirst_name() + user.getLast_name() + ".png");
        mUsersList.add(user);

        user = new User();
        user.setFirst_name("Bruno");
        user.setLast_name("Fagundes");
        user.setId(8);
        user.setAvatar("www.avatar.com/" + user.getFirst_name() + user.getLast_name() + ".png");
        mUsersList.add(user);

        user = new User();
        user.setFirst_name("Monteiro");
        user.setLast_name("Lobato");
        user.setId(9);
        user.setAvatar("www.avatar.com/" + user.getFirst_name() + user.getLast_name() + ".png");
        mUsersList.add(user);

        user = new User();
        user.setFirst_name("Zé");
        user.setLast_name("Carioca");
        user.setId(10);
        user.setAvatar("www.avatar.com/" + user.getFirst_name() + user.getLast_name() + ".png");
        mUsersList.add(user);

        user = new User();
        user.setFirst_name("Amanda");
        user.setLast_name("Trimer");
        user.setId(11);
        user.setAvatar("www.avatar.com/" + user.getFirst_name() + user.getLast_name() + ".png");
        mUsersList.add(user);

        user = new User();
        user.setFirst_name("Paloma");
        user.setLast_name("Oliveira");
        user.setId(12);
        user.setAvatar("www.avatar.com/" + user.getFirst_name() + user.getLast_name() + ".png");
        mUsersList.add(user);

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
