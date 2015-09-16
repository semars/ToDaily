package com.semars.todaily;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private FloatingActionButton fabDone;
    private EditText etTaskDueDate;
    private ImageButton btnTaskDueDate;
    private int year;
    private int month;
    private int day;
    private Calendar currentCalendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        fabDone = (FloatingActionButton) findViewById(R.id.fabDone);
        fabDone.setOnClickListener(this);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            //TaskDatePickerFragment taskDatePickerDialogFragment = TaskDatePickerFragment.newInstance();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = (monthOfYear+1) + "/" + dayOfMonth + "/" + year;
        etTaskDueDate.setText(date);
    }

    public void createDatePickerDialog(Calendar currentCalendar) {
        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                AddActivity.this,
                currentCalendar.get(Calendar.YEAR),
                currentCalendar.get(Calendar.MONTH),
                currentCalendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show(getFragmentManager(), "Datepickerdialog");
    }

    public void completeListItem() {

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
                completeListItem();
                this.finish();
                break;
            }
        }
    }

}
