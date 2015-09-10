package com.semars.todaily;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

public class AddActivity extends FragmentActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private EditText etTaskDueDate;
    private ImageButton btnTaskDueDate;
    private int year;
    private int month;
    private int day;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etTaskDueDate = (EditText) findViewById(R.id.etTaskDueDate);
        btnTaskDueDate = (ImageButton) findViewById(R.id.btnTaskDueDate);

        Calendar calendar = Calendar.getInstance();
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
        String date = dayOfMonth + "/" + (monthOfYear+1) + "/" + year;
        etTaskDueDate.setText(date);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnTaskDueDate: {

            }
            case R.id.etTaskDueDate: {

            }
        }
    }

}
