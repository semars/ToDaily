package com.semars.todaily;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

/**
 * Created by semar on 9/1/15.
 */
public class TaskDatePickerFragment extends DialogFragment {

    // Empty constructor required for DialogFragment
    public TaskDatePickerFragment() {

    }

    public static TaskDatePickerFragment newInstance(String title) {
        TaskDatePickerFragment fragment = new TaskDatePickerFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString("title");
        DatePickerDialog.Builder datePickerDialogBuilder = new DatePickerDialog.Builder(getActivity());
        datePickerDialogBuilder.setTitle(title);

        return super.onCreateDialog(savedInstanceState);
    }
}