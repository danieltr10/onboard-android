package com.example.taqtile.apponboard;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private TextView mTextViewId, mTextViewFirstName, mTextViewLastName;
    private ImageView mImagemAvatar;
    private Button mButtonBack;

    String avatarURL;
    Bitmap avatarImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mTextViewId = (TextView) findViewById(R.id.text_id);
        mTextViewFirstName = (TextView) findViewById(R.id.text_first_name);
        mTextViewLastName = (TextView) findViewById(R.id.text_last_name);

        mImagemAvatar = (ImageView) findViewById(R.id.image_avatar);

        mButtonBack = (Button) findViewById(R.id.btn_back);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        mTextViewId.setText(extras.getString("USER_ID"));
        mTextViewFirstName.setText(extras.getString("USER_FIRST_NAME"));
        mTextViewLastName.setText(extras.getString("USER_LAST_NAME"));

        avatarURL = extras.getString("USER_AVATAR");
        new ImageLoaderClass().execute(avatarURL);

        mButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }

    private class ImageLoaderClass extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        protected Bitmap doInBackground(String... args) {
            try {
                avatarImage = BitmapFactory.decodeStream((InputStream)new URL(args[0]).getContent());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return avatarImage;
        }
        protected void onPostExecute(Bitmap image) {
            if(image != null){
                mImagemAvatar.setImageBitmap(image);
            }
        }
    }

}
