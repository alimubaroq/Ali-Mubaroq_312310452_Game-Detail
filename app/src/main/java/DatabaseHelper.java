package com.example.gamedetall;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    private static final String DB_NAME = "GamesDB";
    private static final int DB_VERSION = 1;

    private static final String TABLE_FAVORITES = "favorites";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_IMAGE = "image";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("CREATE TABLE " + TABLE_FAVORITES + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TITLE + " TEXT, " +
                    COLUMN_DESCRIPTION + " TEXT, " +
                    COLUMN_IMAGE + " INTEGER)");
        } catch (SQLException e) {
            Log.e(TAG, "Error creating database table: " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITES);
            onCreate(db);
        } catch (SQLException e) {
            Log.e(TAG, "Error upgrading database: " + e.getMessage());
        }
    }

    public boolean isFavorite(String title) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_FAVORITES, new String[]{COLUMN_TITLE},
                    COLUMN_TITLE + " = ?", new String[]{title},
                    null, null, null);
            return cursor != null && cursor.getCount() > 0;
        } catch (SQLException e) {
            Log.e(TAG, "Error checking favorite: " + e.getMessage());
            return false;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public void addFavorite(String title, String description, int image) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_TITLE, title);
            values.put(COLUMN_DESCRIPTION, description);
            values.put(COLUMN_IMAGE, image);
            db.insert(TABLE_FAVORITES, null, values);
        } catch (SQLException e) {
            Log.e(TAG, "Error adding favorite: " + e.getMessage());
        }
    }

    public void removeFavorite(String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.delete(TABLE_FAVORITES, COLUMN_TITLE + " = ?", new String[]{title});
        } catch (SQLException e) {
            Log.e(TAG, "Error removing favorite: " + e.getMessage());
        }
    }

    public Cursor getAllFavorites() {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            return db.query(TABLE_FAVORITES, null, null, null, null, null, null);
        } catch (SQLException e) {
            Log.e(TAG, "Error retrieving all favorites: " + e.getMessage());
            return null;
        }
    }
}