package com.example.i_raterestaurantapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.i_raterestaurantapp.R;

public class MainActivity extends AppCompatActivity {

    Button btnShowActivityAdd,btnShowActivityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        btnShowActivityAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddRatingActivity.class);
                startActivity(intent);
            }
        });

        btnShowActivityList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListRatingActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        btnShowActivityAdd = findViewById(R.id.btnShowActivityAdd);
        btnShowActivityList = findViewById(R.id.btnShowActivityList);
    }


}