package com.semars.todaily;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String FILE = "taskItems.json";
    public static final Gson GSON = new Gson();
    private ArrayList<String> items;
    private RecyclerView rvTasks;
    private LinearLayoutManager linearLayoutManager;
    private TasksAdapter tasksAdapter;
    private List<Task> tasks;
    private FloatingActionButton fabAddItem;

    static final int ADD_TASK_REQUEST = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);


        rvTasks = (RecyclerView) findViewById(R.id.rvTasks);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.scrollToPosition(0);
        rvTasks.setLayoutManager(linearLayoutManager);
        tasks = readItems(FILE);
        tasksAdapter = new TasksAdapter(tasks);
        rvTasks.setAdapter(tasksAdapter);
        rvTasks.setHasFixedSize(true);

        fabAddItem = (FloatingActionButton) findViewById(R.id.fabAddItem);
        fabAddItem.setOnClickListener(this);
        
    }

    public void onAddItem(Task task) {
        tasks.add(task);
        tasksAdapter.notifyDataSetChanged();
        writeItems(FILE, tasks);
    }

    private List<Task> readItems(String inFile) {
        List<Task> tasks;

        // Restore preferences
        SharedPreferences preferences = getSharedPreferences(inFile, MODE_PRIVATE);
        String tasksJson = preferences.getString("task_list_JSON", "");

        if (tasksJson.isEmpty()) {
            tasks = new ArrayList<>();
        }
        else {
            Type collectionType = new TypeToken<List<Task>>(){}.getType();
            tasks = GSON.fromJson(tasksJson, collectionType);
        }
        return tasks;
    }

    private void writeItems(String outFile, List<Task> tasks) {
        SharedPreferences preferences = getSharedPreferences(outFile, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("task_list_JSON", GSON.toJson(tasks));
        editor.apply();
    }

    public void launchAddView() {
        Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
        startActivityForResult(intent, ADD_TASK_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == ADD_TASK_REQUEST) {
            Task task = GSON.fromJson(data.getExtras().getString("task"), Task.class);
            onAddItem(task);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()) {
            case R.id.action_settings: {
                return true;
            }
            default: return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fabAddItem: {
                launchAddView();
                break;
            }
        }
    }

}
