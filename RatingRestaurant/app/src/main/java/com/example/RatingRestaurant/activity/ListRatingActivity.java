package com.example.RatingRestaurant.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.RatingRestaurant.R;
import com.example.RatingRestaurant.database.AppDatabase;
import com.example.RatingRestaurant.model.Rate;
import com.example.RatingRestaurant.adapter.AdapterRate;
import com.example.RatingRestaurant.utils.GlobalVariables;

import java.util.ArrayList;

public class ListRatingActivity extends AppCompatActivity implements AdapterRate.OnClickListener {
    ArrayList<Rate> rates;
    AdapterRate adapter;
    AppDatabase database;
    Context context;
    EditText edtSearch;
    ImageView btnBackScreen,btnSearch;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_list);
        initView();
        setupView();
        onClickButton();
    }

    private void onClickButton() {
        //search event
        btnSearch.setOnClickListener(view ->{
            String keySearch = edtSearch.getText().toString();
            if(keySearch.isEmpty()){
                //get all list rate on database
                rates = (ArrayList<Rate>) database.ratingDAO().findAllRateSync();
            }
            else {
                //get list rate have RestaurantName or RestaurantType contains keySearch
                rates = (ArrayList<Rate>) database.ratingDAO().searchRate("%" + keySearch + "%");
            }
            //update list on UI
            adapter.setList(rates);
        });

        //back event
        btnBackScreen.setOnClickListener(view -> {
            //close current activity
            finish();
        });
    }

    private void setupView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //get all rate list from database
        rates = (ArrayList<Rate>) database.ratingDAO().findAllRateSync();
        //create Adapter
        adapter = new AdapterRate(rates,this);
        //set adapter for recyclerview
        recyclerView.setAdapter(adapter);
        //set layout
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        adapter.notifyDataSetChanged();
        //this is function onclick line 85
        adapter.setOnclickListener(this);
    }


    //onclick each item on recyclerview
    @Override
    public void onclick(Rate rate) {
        //show dialog option
        displayAlertDialog(rate.getId());
    }

    private void initView() {
        context = this;
        rates = new ArrayList<Rate>();
        database = AppDatabase.BuilderDatabase(this);
        edtSearch = findViewById(R.id.tvSearch);
        btnSearch = findViewById(R.id.btnSearch);
        recyclerView = findViewById(R.id.rcvRate);
        btnBackScreen = findViewById(R.id.btnBackScreen);
    }

    public void displayAlertDialog(int rateID) {
        //init layout
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialog_layout, null,false);
        //init view
        final  Button btnUpdate = alertLayout.findViewById(R.id.btnUpdateRate);
        final  Button btnDelete = alertLayout.findViewById(R.id.btnDeleteRate);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        //set view for AlertDialog
        alert.setView(alertLayout);
        AlertDialog dialog = alert.create();
        dialog.show();

        btnDelete.setOnClickListener( view -> {
            //delete rate selected
            database.ratingDAO().deleteRate(rateID);
            Toast.makeText(this, getString(R.string.message_delete_success),Toast.LENGTH_LONG).show();
            //get all list rate
            rates = (ArrayList<Rate>) database.ratingDAO().findAllRateSync();
            //update data of recycler view
            adapter.setList(rates);
            dialog.dismiss();
        });

        btnUpdate.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddRatingActivity.class);
            //put variable id from this screen to BasicDetailInputActivity screen
            intent.putExtra(GlobalVariables.RATE,rateID);
            //close dialog
            dialog.dismiss();
            startActivity(intent);
        });
    }
}