package com.example.taqtile.apponboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private TextView textViewId, textViewFirstName, textViewLastName, textViewAvatar;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textViewId = (TextView) findViewById(R.id.textViewId);
        textViewFirstName = (TextView) findViewById(R.id.textViewFirstName);
        textViewLastName = (TextView) findViewById(R.id.textViewLastName);
        textViewAvatar = (TextView) findViewById(R.id.textViewAvatar);

        btnBack = (Button) findViewById(R.id.btnBack);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        textViewId.setText(extras.getString("USER_ID"));
        textViewFirstName.setText(extras.getString("USER_FIRST_NAME"));
        textViewLastName.setText(extras.getString("USER_LAST_NAME"));
        textViewAvatar.setText(extras.getString("USER_AVATAR"));


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
}
