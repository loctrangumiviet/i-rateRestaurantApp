package com.example.i_raterestaurantapp.ui.basic_detail_input;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.i_raterestaurantapp.R;
import com.example.i_raterestaurantapp.data.local.database.AppDatabase;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BasicDetailInputActivity extends AppCompatActivity {
    TextInputEditText edtRestaurantName, edtRestaurantType,edtPickDateAndTime,edtAveragePrice,edtNotes,edtReporterName;
    AutoCompleteTextView tvServiceRating,tvCleanlinessRating,tvFoodQualityRating;
    Button btnAdd,btnShow,btnUpdate;
    AppDatabase mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_detail_input);
        init();
        setUpUI();
    }

    private void setUpUI() {
        //event show data time picker
        edtPickDateAndTime.setOnClickListener(view -> {
            pickDateAndTime(edtPickDateAndTime);
        });
    }

    public void init(){
        mDB = AppDatabase.BuilderDatabase(this);
        edtRestaurantName = findViewById(R.id.edtRestaurantName);
        edtRestaurantType = findViewById(R.id.edtRestaurantType);
        edtPickDateAndTime = findViewById(R.id.tvPickDateAndTime);
        edtAveragePrice = findViewById(R.id.edtAveragePrice);
        edtNotes = findViewById(R.id.edtNotes);
        edtReporterName = findViewById(R.id.edtReporterName);
        tvServiceRating = findViewById(R.id.spServiceRating);
        tvCleanlinessRating = findViewById(R.id.spCleanlinessRating);
        tvFoodQualityRating = findViewById(R.id.spFoodQualityRating);
        btnAdd = findViewById(R.id.btnAdd);
        btnShow = findViewById(R.id.btnShow);
        btnUpdate = findViewById(R.id.btnUpdate);

    }

    private void pickDateAndTime(final TextView dateTime) {
        final Calendar calendar = Calendar.getInstance();

        //view -> DatePicker
        //set onDate
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.YEAR, year);

            //view -> TimePicker
            //set onTime
            TimePickerDialog.OnTimeSetListener timeSetListener = (view1, hourOfDay, minute) -> {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault());
                dateTime.setText(simpleDateFormat.format(calendar.getTime()));
            };

            //show dialog time picker
            new TimePickerDialog(BasicDetailInputActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    false).show();
        };

        //show dialog date picker
        new DatePickerDialog(BasicDetailInputActivity.this, dateSetListener, calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.YEAR)).show();
    }
}