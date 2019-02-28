package edu.miracostacollege.cs134.ck.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

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

    //method to add a task to the database
    //corresponds to insert

    public void addTask(Task task)
    {
        //do not grab id (-1), let database assign it
        String description = task.getDescription();
        boolean isDone = task.isDone();

        //get a reference to the database (readable or writeable?)
        //ContentValues is key/value mapping
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FIELD_DESCRIPTION, description);
        values.put(FIELD_IS_DONE, isDone ? 1 : 0);


        //adds a new record to the database
        //set Long
        Long id = db.insert(DATABASE_TABLE, null, values);
        //After adding new task set its ID to the one generated from the database
        task.setId(id);

        //close the database
        db.close();



    }

    //method to get all of the tasks existing in the database
    public List<Task> getAllTasks()
    {
        // Construct and empty list
        List<Task> allTasks = new ArrayList<>();

        //fill it from the database

        SQLiteDatabase db = getReadableDatabase();
        //make a query to extract everything
        //Query results in SQLite are called Cursor objects
        Cursor cursor = db.query(DATABASE_TABLE, new String[]{FIELD_PRIMARY_KEY, FIELD_DESCRIPTION, FIELD_IS_DONE}, null, null, null, null, null);

        // loop through the cursor results one at a time
        //create task objects and add them to the List
        //First determine if there are results

        if(cursor.moveToFirst())
        {
            do {
                Task task = new Task(cursor.getLong(0), cursor.getString(1), cursor.getInt(2)==1);
                    allTasks.add(task);
                }while(cursor.moveToNext());
            }//remember to close the cursor
        cursor.close();
        db.close();

        //return the filled list
        return allTasks;

    }

    //make a method to delete
    public void clearAllTasks()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(DATABASE_TABLE,null, null);

        db.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
