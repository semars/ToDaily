package com.semars.todaily;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by semar on 9/17/15.
 */

// Custom ViewHolder gives access to views
public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTaskName;
        public TextView tvTaskDue;
        public CheckBox cbTaskCompleted;

        // Constructor accepts entire item row and does view lookups to find each subview
        public ViewHolder(View itemView) {
            super(itemView);

            tvTaskName = (TextView) itemView.findViewById(R.id.tvTaskName);
            tvTaskDue = (TextView) itemView.findViewById(R.id.tvTaskDate);
            cbTaskCompleted = (CheckBox) itemView.findViewById(R.id.cbTaskComplete);
        }
    }

    private List<Task> tasks;

    public TasksAdapter(List<Task> tasks) {
        this.tasks = tasks;
    }

    // Inflates a layout from XML and returns the holder
    @Override
    public TasksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View taskView = layoutInflater.inflate(R.layout.item_task, parent, false);
        ViewHolder viewHolder = new ViewHolder(taskView);
        return viewHolder;
    }

    // Populates data into the item through the view holder
    @Override
    public void onBindViewHolder(TasksAdapter.ViewHolder viewHolder, int position) {
        Task task = tasks.get(position);
        TextView taskName = viewHolder.tvTaskName;
        TextView taskDue = viewHolder.tvTaskDue;
        CheckBox taskCompleted = viewHolder.cbTaskCompleted;

        taskName.setText(task.getTaskName());
        taskDue.setText(task.getTaskDate());
        if (task.isCompleted()) {
            taskCompleted.setChecked(true);
        }
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
}
