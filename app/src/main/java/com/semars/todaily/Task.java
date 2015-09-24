package com.semars.todaily;

/**
 * Created by semar on 9/13/15.
 */
public class Task {

    private String taskName;
    private String taskDate;
    private boolean completed;
    private static int lastTaskId = 0;

    public Task() {
        // no-args constructor
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
