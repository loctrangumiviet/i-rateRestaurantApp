package com.example.i_raterestaurantapp.data.local.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.i_raterestaurantapp.data.local.dao.RatingDAO;
import com.example.i_raterestaurantapp.data.model.RatingModel;

// version -> will change when update database
//exportSchema = false -> not export file schema
@Database(entities = {RatingModel.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    public  abstract RatingDAO ratingDAO();
    public static AppDatabase BuilderDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context,AppDatabase.class,"RatingDao")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
