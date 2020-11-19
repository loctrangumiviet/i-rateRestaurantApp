package com.example.i_raterestaurantapp.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.i_raterestaurantapp.model.Rate;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface RateDAO {

    @Insert(onConflict = REPLACE)
    void insertRate(Rate rating);

    @Update(onConflict = REPLACE)
    void updateRate(Rate rating);

    @Query("SELECT * FROM Rate Where id = :id")
    Rate getRating(int id);

    @Query("DELETE FROM Rate Where id = :id")
    void deleteRate(int id);

    @Query("SELECT * FROM Rate WHERE (name LIKE :keyWord OR type LIKE :keyWord)")
    List<Rate> searchRate(String keyWord);

    @Query("SELECT * FROM Rate")
    List<Rate> findAllRateSync();
}
