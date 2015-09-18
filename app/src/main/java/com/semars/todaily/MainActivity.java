package com.semars.todaily;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.internal.app.ToolbarActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.gson.Gson;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView lvItems;
    private RecyclerView rvTasks;
    private LinearLayoutManager linearLayoutManager;
    private TasksAdapter tasksAdapter;
    private List<Task> tasks;
    private FloatingActionButton fabAddItem;

    static final int ADD_TASK_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        fabAddItem = (FloatingActionButton) findViewById(R.id.fabAddItem);
        fabAddItem.setOnClickListener(this);

        rvTasks = (RecyclerView) findViewById(R.id.rvTasks);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.scrollToPosition(0);
        rvTasks.setLayoutManager(linearLayoutManager);
        tasks = new ArrayList<Task>();
        tasksAdapter = new TasksAdapter(tasks);
        rvTasks.setAdapter(tasksAdapter);
        rvTasks.setHasFixedSize(true);

        // OLD: Listview
        //lvItems = (ListView) findViewById(R.id.lvItems);
        //items = new ArrayList<String>();
        //readItems();
        //itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        //lvItems.setAdapter(itemsAdapter);

        //setupListViewListener();
        // END
    }

    public void onAddItem(Task task) {
        tasks.add(task);
        tasksAdapter.notifyDataSetChanged();
        writeItems();
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                items.remove(position);
                itemsAdapter.notifyDataSetChanged();
                writeItems();
                return true;
            }
        });
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            items = new ArrayList<String>(FileUtils.readLines(todoFile));
        } catch (IOException e) {
            items = new ArrayList<String>();
        }
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(todoFile, items);
        } catch (IOException e) {
            e.printStackTrace();
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

    public void launchAddView() {
        Intent intent = new Intent(MainActivity.this, AddActivity.class);
        startActivityForResult(intent, ADD_TASK_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == ADD_TASK_REQUEST) {
            Task task = new Gson().fromJson(data.getExtras().getString("task"), Task.class);
            onAddItem(task);
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
