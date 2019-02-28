package edu.miracostacollege.cs134.ck;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import java.util.List;

import edu.miracostacollege.cs134.ck.model.Task;

public class TaskListAdapter extends ArrayAdapter<Task> {

    private Context mContext;
    private int mResourceID;
    private List<Task> mAllTasks;

    public TaskListAdapter(Context context, int resource, List<Task> objects) {
        super(context, resource, objects);
        //initalise instance variables
        mContext = context;
        mResourceID = resource;
        mAllTasks = objects;
    }

    //override the getView method


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //for each task in the list inflate its view
        Task focusedTask = mAllTasks.get(position);

        //create a layout inflater
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(mResourceID, null);

        CheckBox isDoneCheckBox = view.findViewById(R.id.isDoneCheckBox);
        isDoneCheckBox.setChecked(focusedTask.isDone());
        isDoneCheckBox.setText(focusedTask.getDescription());

        return view;


    }
}
