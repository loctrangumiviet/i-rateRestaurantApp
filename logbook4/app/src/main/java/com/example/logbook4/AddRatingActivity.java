package com.example.logbook4;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddRatingActivity extends AppCompatActivity {

    Button btnAddRate, btnUpdateRate;
    ImageView btnBackScreen;
    LinearLayout addLayoutButton, updateLayoutButton;
    EditText edtName, edtType, edtDate, edtAverage, edtNotes, edtReporter;
    AutoCompleteTextView tvService, tvCleanliness, tvFoodQuality;
    int ID = -1;
    String[] satisfactionList = new String[]{"Excellent", "Good","OKAY","improve"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_detail_input);
        setupView();
        onClick();
    }

    private Rate getDataFromInput(){
        Rate rate = new Rate(
                edtName.getText().toString(),
                edtType.getText().toString(),
                edtDate.getText().toString(),
                edtAverage.getText().toString(),
                tvService.getText().toString(),
                tvFoodQuality.getText().toString(),
                tvCleanliness.getText().toString(),
                edtNotes.getText().toString(),
                edtReporter.getText().toString()
        );
        return rate;
    }

    private void clearInput(){
        edtName.setText(getString(R.string.empty_string));
        edtType.setText(getString(R.string.empty_string));
        edtDate.setText("");
        edtAverage.setText(getString(R.string.empty_string));
        tvService.setText(getString(R.string.empty_string));
        tvFoodQuality.setText(getString(R.string.empty_string));
        tvCleanliness.setText(getString(R.string.empty_string));
        edtNotes.setText(getString(R.string.empty_string));
        edtReporter.setText(getString(R.string.empty_string));
    }

    private void onClick() {
        //add rating
        btnAddRate.setOnClickListener(view -> {
            Rate rateInput = getDataFromInput();
            //field empty -> show message
            if (rateInput.checkEmpty()) {
                Toast.makeText(this, getString(R.string.warrning), Toast.LENGTH_LONG).show();
            }
            //
            else {
                Toast.makeText(this, getString(R.string.message_complete), Toast.LENGTH_LONG).show();
                clearInput();
            }
        });

        btnBackScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //event show data time picker
        edtDate.setOnClickListener(view -> {
            pickDateAndTime(edtDate);
        });
    }

    public void setupView() {
        edtName = findViewById(R.id.edtName);
        edtType = findViewById(R.id.edtType);
        edtDate = findViewById(R.id.pkDateTime);
        edtAverage = findViewById(R.id.edtAverage);
        edtNotes = findViewById(R.id.edtNotes);
        edtReporter = findViewById(R.id.edtReporter);
        tvService = findViewById(R.id.cbbService);
        tvCleanliness = findViewById(R.id.cbbCleanliness);
        tvFoodQuality = findViewById(R.id.cbbFoodQuality);
        btnAddRate = findViewById(R.id.btnAdd);
        btnUpdateRate = findViewById(R.id.btnUpdate);
        addLayoutButton = findViewById(R.id.addLayout);
        updateLayoutButton = findViewById(R.id.updateLayout);
        btnBackScreen = findViewById(R.id.btnBackScreen);

        // constructor data for combobox
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, satisfactionList);
        tvService.setAdapter(adapter);
        tvCleanliness.setAdapter(adapter);
        tvFoodQuality.setAdapter(adapter);
    }

    private void pickDateAndTime(final TextView dateTime) {
        final Calendar calendar = Calendar.getInstance();

        //view -> DatePicker
        //set onDate
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.YEAR, year);

                //view -> TimePicker
                //set onTime
                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault());
                        dateTime.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                };

                //show dialog time picker
                new TimePickerDialog(AddRatingActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        false).show();
            }
        };

        //show dialog date picker
        new DatePickerDialog(AddRatingActivity.this, AlertDialog.THEME_HOLO_LIGHT, dateSetListener, calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.YEAR)).show();
    }
}