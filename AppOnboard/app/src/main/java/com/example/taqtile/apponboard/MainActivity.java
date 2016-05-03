package com.example.taqtile.apponboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private List<User> usersList = new ArrayList<>();
    private RecyclerView recyclerView;
    private UsersAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "Mensagem de Log");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new UsersAdapter(usersList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
        recyclerView.setAdapter(mAdapter);

        prepareUserData();

    }

    private void prepareUserData() {
        User user = new User();
        user.setFirstName("Daniel");
        user.setLastName("Rizzuto");
        user.setId(0);
        usersList.add(user);

        user = new User();
        user.setFirstName("Daniel");
        user.setLastName("Nakasato");
        user.setId(1);
        usersList.add(user);

        user = new User();
        user.setFirstName("Roberto");
        user.setLastName("Rizzuto");
        user.setId(2);
        usersList.add(user);

        user = new User();
        user.setFirstName("Laura");
        user.setLastName("Regina");
        user.setId(3);
        usersList.add(user);

        user = new User();
        user.setFirstName("Rafael");
        user.setLastName("Trigo");
        user.setId(4);
        usersList.add(user);

        user = new User();
        user.setFirstName("Daniel");
        user.setLastName("Lobato");
        user.setId(5);
        usersList.add(user);

        user = new User();
        user.setFirstName("Roberta");
        user.setLastName("Truppa");
        user.setId(6);
        usersList.add(user);

        user = new User();
        user.setFirstName("Arnaldo");
        user.setLastName("Coelho");
        user.setId(7);
        usersList.add(user);

        user = new User();
        user.setFirstName("Bruno");
        user.setLastName("Fagundes");
        user.setId(8);
        usersList.add(user);

        user = new User();
        user.setFirstName("Monteiro");
        user.setLastName("Lobato");
        user.setId(9);
        usersList.add(user);

        user = new User();
        user.setFirstName("Zé");
        user.setLastName("Carioca");
        user.setId(10);
        usersList.add(user);

        user = new User();
        user.setFirstName("Amanda");
        user.setLastName("Trimer");
        user.setId(11);
        usersList.add(user);

        user = new User();
        user.setFirstName("Paloma");
        user.setLastName("Oliveira");
        user.setId(12);
        usersList.add(user);

        mAdapter.notifyDataSetChanged();


    }

}
