package com.example.RatingRestaurant.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.RatingRestaurant.model.Rate;

// version -> will change when update database
//exportSchema = false -> not export file schema
@Database(entities = {Rate.class}, version = 2,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    public  abstract RateDAO ratingDAO();
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
