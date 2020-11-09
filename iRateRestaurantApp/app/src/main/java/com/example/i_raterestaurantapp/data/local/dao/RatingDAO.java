package com.example.i_raterestaurantapp.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.i_raterestaurantapp.data.model.RatingModel;

import java.util.ArrayList;
import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface RatingDAO {

    @Insert(onConflict = REPLACE)
    void insertRate(RatingModel rating);

    @Update(onConflict = REPLACE)
    void updateRate(RatingModel rating);

    @Query("SELECT * FROM Rating Where id = :id")
    RatingModel getRating(int id);

    @Query("DELETE FROM Rating Where id = :id")
    void deleteRate(int id);

    @Query("SELECT * FROM Rating WHERE (restaurantName LIKE :keyWord OR restaurantType LIKE :keyWord)")
    List<RatingModel> searchRate(String keyWord);

    @Query("SELECT * FROM Rating")
    List<RatingModel> findAllRateSync();
}
