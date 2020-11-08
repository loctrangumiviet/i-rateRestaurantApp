package com.example.i_raterestaurantapp.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.i_raterestaurantapp.data.model.RatingModel;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface RatingDAO {

    @Insert(onConflict = REPLACE)
    void insertBook(RatingModel rating);

    @Update(onConflict = REPLACE)
    void updateBook(RatingModel rating);

    @Query("DELETE FROM Rating")
    void deleteAll();

    @Query("SELECT * FROM Rating")
    public List<RatingModel> findAllBookSync();
}
