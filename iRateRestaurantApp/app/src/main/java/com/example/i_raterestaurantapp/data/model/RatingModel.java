package com.example.i_raterestaurantapp.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Rating")
public class RatingModel implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String restaurantName;
    private String restaurantType;
    private String dateVisit;
    private String averagePrice;
    private String serviceRating;
    private String cleanlinessRating;
    private String foodQualityRating;
    private String notes;
    private String reporterName;

    public RatingModel(String restaurantName, String restaurantType, String dateVisit, String averagePrice, String serviceRating, String cleanlinessRating, String foodQualityRating, String notes, String reporterName) {
        this.id = 0;
        this.restaurantName = restaurantName;
        this.restaurantType = restaurantType;
        this.dateVisit = dateVisit;
        this.averagePrice = averagePrice;
        this.serviceRating = serviceRating;
        this.cleanlinessRating = cleanlinessRating;
        this.foodQualityRating = foodQualityRating;
        this.notes = notes;
        this.reporterName = reporterName;
    }

    public String getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(String averagePrice) {
        this.averagePrice = averagePrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantType() {
        return restaurantType;
    }

    public void setRestaurantType(String restaurantType) {
        this.restaurantType = restaurantType;
    }

    public String getDateVisit() {
        return dateVisit;
    }

    public void setDateVisit(String dateVisit) {
        this.dateVisit = dateVisit;
    }

    public String getServiceRating() {
        return serviceRating;
    }

    public void setServiceRating(String serviceRating) {
        this.serviceRating = serviceRating;
    }

    public String getCleanlinessRating() {
        return cleanlinessRating;
    }

    public void setCleanlinessRating(String cleanlinessRating) {
        this.cleanlinessRating = cleanlinessRating;
    }

    public String getFoodQualityRating() {
        return foodQualityRating;
    }

    public void setFoodQualityRating(String foodQualityRating) {
        this.foodQualityRating = foodQualityRating;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getReporterName() {
        return reporterName;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }
}
