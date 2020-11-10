package com.example.i_raterestaurantapp.ui.basic_detail_input;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.i_raterestaurantapp.R;
import com.example.i_raterestaurantapp.data.local.database.AppDatabase;
import com.example.i_raterestaurantapp.data.model.RatingModel;
import com.example.i_raterestaurantapp.ui.rating_restaurant.RatingRestaurantActivity;
import com.example.i_raterestaurantapp.utils.Contants;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BasicDetailInputActivity extends AppCompatActivity {
    TextInputEditText edtRestaurantName, edtRestaurantType, edtPickDateAndTime, edtAveragePrice, edtNotes, edtReporterName;
    AutoCompleteTextView tvServiceRating, tvCleanlinessRating, tvFoodQualityRating;
    Button btnAdd, btnShow, btnUpdate;
    LinearLayout addLayout,updateLayout;
    AppDatabase mDB;
    int rateID = -1;
    String[] RatingList = new String[]{"Need to improve", "OKAY", "Good", "Excellent"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_detail_input);
        init();
        setupIntent();
        setUpUI();
        setupEventClick();
    }

    private void setupIntent() {
        Intent intent = getIntent();
        rateID =  intent.getIntExtra(Contants.RATE,-1);
        if(rateID != -1) {
            RatingModel getRate = mDB.ratingDAO().getRating(rateID);
            rateID = getRate.getId();
            edtRestaurantName.setText(getRate.getRestaurantName());
            edtRestaurantType.setText(getRate.getRestaurantType());
            edtPickDateAndTime.setText(getRate.getDateVisit());
            edtAveragePrice.setText(getRate.getAveragePrice());
            tvServiceRating.setText(getRate.getServiceRating());
            tvFoodQualityRating.setText(getRate.getFoodQualityRating());
            tvCleanlinessRating.setText(getRate.getCleanlinessRating());
            edtNotes.setText(getRate.getNotes());
            edtReporterName.setText(getRate.getReporterName());
            addLayout.setVisibility(View.GONE);
            updateLayout.setVisibility(View.VISIBLE);
        }
    }

    private void clearField(){
        edtRestaurantName.setText(getString(R.string.empty_string));
        edtRestaurantType.setText(getString(R.string.empty_string));
        edtPickDateAndTime.setText(getString(R.string.pick_date_and_time));
        edtAveragePrice.setText(getString(R.string.empty_string));
        tvServiceRating.setText(getString(R.string.empty_string));
        tvFoodQualityRating.setText(getString(R.string.empty_string));
        tvCleanlinessRating.setText(getString(R.string.empty_string));
        edtNotes.setText(getString(R.string.empty_string));
        edtReporterName.setText(getString(R.string.empty_string));
    }

    private void setupEventClick() {
        //add rating
        btnAdd.setOnClickListener(view -> {
            String resName = edtRestaurantName.getText().toString();
            String resType = edtRestaurantType.getText().toString();
            String resTime = edtPickDateAndTime.getText().toString();
            String averagePrice = edtAveragePrice.getText().toString();
            String serviceRating = tvServiceRating.getText().toString();
            String foodQualityRating = tvFoodQualityRating.getText().toString();
            String cleanlinessRating = tvCleanlinessRating.getText().toString();
            String notes = edtNotes.getText().toString();
            String reporterName = edtReporterName.getText().toString();
            //field empty -> show message
            if (resName.isEmpty() || resType.isEmpty() || resTime.isEmpty() || averagePrice.isEmpty()
                    || serviceRating.isEmpty() || foodQualityRating.isEmpty() || cleanlinessRating.isEmpty()
                    || reporterName.isEmpty() || resTime.equals(getString(R.string.pick_date_and_time))) {
                Toast.makeText(this, getString(R.string.warrning), Toast.LENGTH_LONG).show();
            }
            //
            else {
                //create object
                RatingModel rate = new RatingModel(resName, resType, resTime, averagePrice, serviceRating, cleanlinessRating, foodQualityRating, notes, reporterName);
                //add object to database
                mDB.ratingDAO().insertRate(rate);
                //show message complete
                Toast.makeText(this, getString(R.string.message_complete), Toast.LENGTH_LONG).show();
                clearField();
            }
        });

        //show list
        btnShow.setOnClickListener(view -> {
            Intent intent = new Intent(this, RatingRestaurantActivity.class);
            // change to  screen rating list
            startActivity(intent);
        });

        //update rate
        btnUpdate.setOnClickListener(view ->{
            String resName = edtRestaurantName.getText().toString();
            String resType = edtRestaurantType.getText().toString();
            String resTime = edtPickDateAndTime.getText().toString();
            String averagePrice = edtAveragePrice.getText().toString();
            String serviceRating = tvServiceRating.getText().toString();
            String foodQualityRating = tvFoodQualityRating.getText().toString();
            String cleanlinessRating = tvCleanlinessRating.getText().toString();
            String notes = edtNotes.getText().toString();
            String reporterName = edtReporterName.getText().toString();
            //field empty -> show message
            if (resName.isEmpty() || resType.isEmpty() || resTime.isEmpty() || averagePrice.isEmpty()
                    || serviceRating.isEmpty() || foodQualityRating.isEmpty() || cleanlinessRating.isEmpty()
                    || reporterName.isEmpty() || resTime.equals(getString(R.string.pick_date_and_time))) {
                Toast.makeText(this, getString(R.string.warrning), Toast.LENGTH_LONG).show();
            }
            else {
                //get rate data form id
                RatingModel rateUpdate = mDB.ratingDAO().getRating(rateID);
                //update data from input
                rateUpdate.setRestaurantName(resName);
                rateUpdate.setRestaurantType(resType);
                rateUpdate.setDateVisit(resTime);
                rateUpdate.setAveragePrice(averagePrice);
                rateUpdate.setServiceRating(serviceRating);
                rateUpdate.setFoodQualityRating(foodQualityRating);
                rateUpdate.setCleanlinessRating(cleanlinessRating);
                rateUpdate.setNotes(notes);
                rateUpdate.setReporterName(reporterName);

                //update rate on database
                mDB.ratingDAO().updateRate(rateUpdate);
                Toast.makeText(this, getString(R.string.update_success), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, RatingRestaurantActivity.class);
                //set visible UI
                addLayout.setVisibility(View.VISIBLE);
                updateLayout.setVisibility(View.GONE);
                //change activity
                startActivity(intent);
            }
        });
    }

    private void setUpUI() {
        //event show data time picker
        edtPickDateAndTime.setOnClickListener(view -> {
            pickDateAndTime(edtPickDateAndTime);
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, RatingList);
        tvServiceRating.setAdapter(adapter);
        tvCleanlinessRating.setAdapter(adapter);
        tvFoodQualityRating.setAdapter(adapter);
    }

    public void init() {
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
        addLayout = findViewById(R.id.addLayout);
        updateLayout = findViewById(R.id.updateLayout);

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