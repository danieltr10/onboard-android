package com.example.taqtile.apponboard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taqtile on 5/5/16.
 */
public class UsersDatabase extends SQLiteOpenHelper {

    private static final String TAG = "UsersDatabase";
    private static final String DB_NAME = "Users.db";
    private static final String TABLE_NAME = "users";

    public UsersDatabase(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL("CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY, first_name TEXT not null, last_name TEXT not null, avatar TEXT not null");
        }
        catch (SQLException e) {
            Log.d(TAG, e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);

    }

    public void addUser(User user) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("id", user.getId());
        values.put("first_name", user.getFirst_name());
        values.put("last_name", user.getLast_name());
        values.put("avatar", user.getAvatar());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<User> getAllUsers() {

        SQLiteDatabase db = this.getWritableDatabase();
        List<User> usersList = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.getCount() > 0) {

            if (cursor.moveToFirst()) {
                do {
                    User user = new User();
                    user.setId(Integer.parseInt(cursor.getColumnName(cursor.getColumnIndex("id"))));
                    user.setFirst_name(cursor.getColumnName(cursor.getColumnIndex("first_name")));
                    user.setLast_name(cursor.getColumnName(cursor.getColumnIndex("last_name")));
                    user.setAvatar(cursor.getColumnName(cursor.getColumnIndex("avatar")));
                    usersList.add(user);
                } while (cursor.moveToNext());

                }

        }

        return usersList;

    }

    public User getUser(int id) {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE id=" + String.valueOf(id) + "", null);

        User user = new User();
        user.setId(Integer.parseInt(cursor.getColumnName(cursor.getColumnIndex("id"))));
        user.setFirst_name(cursor.getColumnName(cursor.getColumnIndex("first_name")));
        user.setLast_name(cursor.getColumnName(cursor.getColumnIndex("last_name")));
        user.setAvatar(cursor.getColumnName(cursor.getColumnIndex("avatar")));

        return user;
    }

    public void updateUser(int id, String first_name, String last_name, String avatar) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("first_name", first_name);
        contentValues.put("last_name", last_name);
        contentValues.put("avatar", avatar);

        db.update(TABLE_NAME, contentValues, "id = ? ", new String[] { Integer.toString(id) } );

    }

    public Integer deleteUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_NAME, "int id = ?", new String[]{ Integer.toString(id)});

    }
}
