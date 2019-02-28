package edu.miracostacollege.cs134.ck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import edu.miracostacollege.cs134.ck.model.DBHelper;
import edu.miracostacollege.cs134.ck.model.Task;

public class MainActivity extends AppCompatActivity {

    private DBHelper mDB;
    private List<Task> mAllTasks;
    private EditText taskEditText;
    private ListView mTaskListView;
    private TaskListAdapter mTaskListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instantiate Database
        mDB = new DBHelper(this);
        //Fill the list with data from database
        mAllTasks = mDB.getAllTasks();

        mTaskListView = findViewById(R.id.taskListView);
        taskEditText = findViewById(R.id.taskEditText);

        //associate the taskListAdapter to the list view
        mTaskListAdapter = new TaskListAdapter(this,R.layout.task_item, mAllTasks);
        mTaskListView.setAdapter(mTaskListAdapter);


    }

    public void addtask(View v)
    {
        String description = taskEditText.getText().toString();
        Task newTask = new Task(description);
        //put new task in database
        mDB.addTask(newTask);
        //Notify the listAdapter that it has changed
        mTaskListAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDB.close();
    }


}
