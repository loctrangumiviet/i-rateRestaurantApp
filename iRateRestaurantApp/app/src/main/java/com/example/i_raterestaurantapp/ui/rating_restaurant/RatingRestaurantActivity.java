package com.example.i_raterestaurantapp.ui.rating_restaurant;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.i_raterestaurantapp.R;
import com.example.i_raterestaurantapp.data.local.database.AppDatabase;
import com.example.i_raterestaurantapp.data.model.RatingModel;
import com.example.i_raterestaurantapp.ui.basic_detail_input.BasicDetailInputActivity;
import com.example.i_raterestaurantapp.utils.Contants;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class RatingRestaurantActivity extends AppCompatActivity implements AdapterRating.OnClickListener {
    TextInputEditText tvSearch;
    Button btnSearch;
    RecyclerView rcvRate;
    ArrayList<RatingModel> listRate;
    AdapterRating adapterRating;
    AppDatabase mDB;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_restaurant);
        init();
        setupView();
        mOnClick();
    }

    private void mOnClick() {
        btnSearch.setOnClickListener(view ->{
            String keySearch = tvSearch.getText().toString();
            if(keySearch.isEmpty()){
                //get all list rate on database
                listRate = (ArrayList<RatingModel>) mDB.ratingDAO().findAllRateSync();
            }
            else {
                //get list rate have RestaurantName or RestaurantType contains keySearch
                listRate = (ArrayList<RatingModel>) mDB.ratingDAO().searchRate("%" + keySearch + "%");
            }
            //update list on UI
            adapterRating.setList(listRate);
        });
    }

    private void setupView() {
        //get all rate list from database
        listRate = (ArrayList<RatingModel>) mDB.ratingDAO().findAllRateSync();
        //create Adapter
        adapterRating = new AdapterRating(listRate,this);
        //set adapter for recyclerview
        rcvRate.setAdapter(adapterRating);
        //set layout
        rcvRate.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        adapterRating.notifyDataSetChanged();
        //this is function onclick line 85
        adapterRating.setOnclickListener(this);
    }

    private void init() {
        context = this;
        listRate = new ArrayList<RatingModel>();
        mDB = AppDatabase.BuilderDatabase(this);
        tvSearch = findViewById(R.id.tvSearch);
        btnSearch = findViewById(R.id.btnSearch);
        rcvRate = findViewById(R.id.rcvRate);
    }

    //onclick each item on recyclerview
    @Override
    public void onclick(RatingModel rate) {
        //show dialog option
        displayAlertDialog(rate.getId());
    }

    public void displayAlertDialog(int rateID) {
        //init layout
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialog_layout, null,false);
        //init view
        final  Button btnUpdate = alertLayout.findViewById(R.id.btnUpdateItem);
        final  Button btnDelete = alertLayout.findViewById(R.id.btnDeleteItem);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        //set view for AlertDialog
        alert.setView(alertLayout);
        AlertDialog dialog = alert.create();
        dialog.show();

        btnDelete.setOnClickListener( view -> {
            //delete rate selected
            mDB.ratingDAO().deleteRate(rateID);
            Toast.makeText(this, getString(R.string.message_delete_success),Toast.LENGTH_LONG).show();
            //get all list rate
            listRate = (ArrayList<RatingModel>) mDB.ratingDAO().findAllRateSync();
            //update data of recycler view
            adapterRating.setList(listRate);
            dialog.dismiss();
        });

        btnUpdate.setOnClickListener(view -> {
            Intent intent = new Intent(this, BasicDetailInputActivity.class);
            //put variable id from this screen to BasicDetailInputActivity screen
            intent.putExtra(Contants.RATE,rateID);
            //close dialog
            dialog.dismiss();
            startActivity(intent);
            finish();
        });
    }
}