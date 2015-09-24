package com.semars.todaily;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.gson.Gson;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

public class AddTaskActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private FloatingActionButton fabDone;
    private EditText etTaskName;
    private EditText etTaskDueDate;
    private ImageButton btnTaskDueDate;
    private String taskName;
    private String taskDate;
    private Calendar currentCalendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        fabDone = (FloatingActionButton) findViewById(R.id.fabDone);
        fabDone.setOnClickListener(this);
        etTaskName = (EditText) findViewById(R.id.etTaskName);
        etTaskName.setOnClickListener(this);
        etTaskDueDate = (EditText) findViewById(R.id.etTaskDueDate);
        etTaskDueDate.setOnClickListener(this);
        btnTaskDueDate = (ImageButton) findViewById(R.id.btnTaskDueDate);
        btnTaskDueDate.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        taskDate = (monthOfYear+1) + "/" + dayOfMonth + "/" + year;
        etTaskDueDate.setText(taskDate);
    }

    public void createDatePickerDialog(Calendar currentCalendar) {
        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                AddTaskActivity.this,
                currentCalendar.get(Calendar.YEAR),
                currentCalendar.get(Calendar.MONTH),
                currentCalendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.vibrate(false);
        datePickerDialog.dismissOnPause(true);
        datePickerDialog.show(getFragmentManager(), "Datepickerdialog");
    }

    public void onSubmit(View v) {
        taskName = etTaskName.getText().toString();
        Task task = new Task();
        task.setTaskName(taskName);
        task.setTaskDate(taskDate);
        task.setCompleted(false);
        String taskJson = new Gson().toJson(task);
        Intent data = new Intent();
        data.putExtra("task", taskJson);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.etTaskDueDate:
            case R.id.btnTaskDueDate: {
                currentCalendar = Calendar.getInstance();
                createDatePickerDialog(currentCalendar);
                break;
            }
            case R.id.fabDone: {
                onSubmit(v);
                break;
            }
        }
    }

}
