package com.semars.todaily;

/**
 * Created by semar on 9/13/15.
 */
public class Task {

    private String taskName;
    private String taskDate;
    private boolean completed;
    private static int lastTaskId = 0;

    public Task(String taskName, String taskDate, boolean completed) {
        this.taskName = taskName;
        this.taskDate = taskDate;
        this.completed = completed;
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

}
