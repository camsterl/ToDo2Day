package edu.miracostacollege.cs134.ck.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static java.sql.Types.INTEGER;


public class DBHelper extends SQLiteOpenHelper {

    public static final String TAG = DBHelper.class.getSimpleName();

    //step 1 define all database info in CONSTANTS
    public static final String DATABASE_NAME = "ToDo2Day";
    public static final String DATABASE_TABLE = "Tasks";
    public static final String FIELD_PRIMARY_KEY = "_id";
    public static final String FIELD_DESCRIPTION = "description";
    public static final String FIELD_IS_DONE = "is_done";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    //Constructor forDPHelper

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create all of our database tables
        //1) Determine whether to read or write the data base (CREATE = write)
        //opens  writeable connection to the ToDo2Day database
        db = getWritableDatabase();

        //Execute the create table statement

        String sql = "CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE + "(" + FIELD_PRIMARY_KEY + " INTEGER PRIMARY KEY," + FIELD_DESCRIPTION + " TEXT," + FIELD_IS_DONE + " INTEGER" +")";

        Log.i(TAG, sql);
        db.execSQL(sql);


        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
