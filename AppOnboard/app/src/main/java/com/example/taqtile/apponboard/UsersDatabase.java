package com.example.taqtile.apponboard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taqtile on 5/5/16.
 */
public class UsersDatabase extends SQLiteOpenHelper {

    private static final String TAG = "UsersDatabase";
    private static final String DB_NAME = "database";
    private static final String TABLE_NAME = "users";

    public UsersDatabase(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL("CREATE TABLE " + TABLE_NAME + " (id integer primary key, first_name TEXT NOT NULL, last_name TEXT NOT NULL, avatar TEXT NOT NULL)");
        }
        catch (SQLException e) {
            Log.d(TAG, e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public Boolean addUser(User user) {

        try {
            User tempUser = new User();
            if (getUser(user.getId())!=null) {
                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("id", user.getId());
                contentValues.put("first_name", user.getFirst_name());
                contentValues.put("last_name", user.getLast_name());
                contentValues.put("avatar", user.getAvatar());
                db.insert(TABLE_NAME, null, contentValues);

                return true;
            }
            return false;
        }
        catch (SQLiteException e) {
            Log.d(TAG, e.getMessage());

            return false;
        }
    }


    public User getUser(int id){

        User user = new User();
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where id = " + id + " ", null);
            res.moveToFirst();

            user.setId(Integer.parseInt(res.getString(res.getColumnIndex("id"))));
            user.setFirst_name(res.getString(res.getColumnIndex("first_name")));
            user.setLast_name(res.getString(res.getColumnIndex("last_name")));
            user.setAvatar(res.getString(res.getColumnIndex("avatar")));

        }
        catch (CursorIndexOutOfBoundsException e) {
            Log.d(TAG, e.getMessage());
        }

        return user;
    }

    public ArrayList<User> getAllUsers()
    {
        ArrayList<User> usersArray = new ArrayList<User>();

        try {

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
            res.moveToFirst();

            while (res.isAfterLast() == false) {

                User user = new User();
                user.setId(Integer.parseInt(res.getString(res.getColumnIndex("id"))));
                user.setFirst_name(res.getString(res.getColumnIndex("first_name")));
                user.setLast_name(res.getString(res.getColumnIndex("last_name")));
                user.setAvatar(res.getString(res.getColumnIndex("avatar")));
                usersArray.add(user);
                res.moveToNext();
            }
        }
        catch (SQLiteException e) {
            Log.d(TAG, e.getMessage());
        }
        return usersArray;
    }

    public boolean updateUser(int id, String first_name, String last_name, String avatar) {

        try {

            deleteUser(id);

            User user = new User();
            user.setId(id);
            user.setFirst_name(first_name);
            user.setLast_name(last_name);
            user.setAvatar(avatar);

            addUser(user);

            return true;

        }
        catch (SQLiteException e) {

            Log.d(TAG, e.getMessage());

            return false;
        }

    }

    public boolean deleteUser(int id) {

        try {

            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_NAME, "id=" + id, null);
            return true;
        }

        catch (SQLiteException e) {
            Log.d(TAG, e.getMessage());
            return false;
        }


    }
}
