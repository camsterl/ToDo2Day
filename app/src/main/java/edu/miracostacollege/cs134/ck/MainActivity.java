package edu.miracostacollege.cs134.ck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.miracostacollege.cs134.ck.model.DBHelper;

public class MainActivity extends AppCompatActivity {
    private DBHelper mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instantiate Database
        mDB = new DBHelper(this);



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDB.close();
    }
}
