package com.example.taqtile.apponboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private TextView mTextViewId, mTextViewFirstName, mTextViewLastName, mTextViewAvatar;
    private Button mButtonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mTextViewId = (TextView) findViewById(R.id.text_id);
        mTextViewFirstName = (TextView) findViewById(R.id.text_first_name);
        mTextViewLastName = (TextView) findViewById(R.id.text_last_name);
        mTextViewAvatar = (TextView) findViewById(R.id.text_avatar);

        mButtonBack = (Button) findViewById(R.id.btnBack);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        mTextViewId.setText(extras.getString("USER_ID"));
        mTextViewFirstName.setText(extras.getString("USER_FIRST_NAME"));
        mTextViewLastName.setText(extras.getString("USER_LAST_NAME"));
        mTextViewAvatar.setText(extras.getString("USER_AVATAR"));


        mButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
}
