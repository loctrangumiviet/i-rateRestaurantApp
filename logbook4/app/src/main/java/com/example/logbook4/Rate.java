package com.example.logbook4;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Rate")
public class Rate implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String type;
    private String date;
    private String average;
    private String service;
    private String cleanliness;
    private String foodQuality;
    private String notes;
    private String reporter;

    public Rate(){

    }

    @Ignore
    public Rate(String name, String restaurantType, String dateVisit, String averagePrice, String serviceRating, String cleanlinessRating, String foodQualityRating, String notes, String reporterName) {
        this.id = 0;
        this.name = name;
        this.type = restaurantType;
        this.date = dateVisit;
        this.average = averagePrice;
        this.service = serviceRating;
        this.cleanliness = cleanlinessRating;
        this.foodQuality = foodQualityRating;
        this.notes = notes;
        this.reporter = reporterName;
    }

    public Boolean checkEmpty(){
        if(name.isEmpty() || type.isEmpty() || date.isEmpty() || average.isEmpty() || service.isEmpty()
            ||cleanliness.isEmpty() || foodQuality.isEmpty() || reporter.isEmpty()){
            return true;
        }
        return false;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getCleanliness() {
        return cleanliness;
    }

    public void setCleanliness(String cleanliness) {
        this.cleanliness = cleanliness;
    }

    public String getFoodQuality() {
        return foodQuality;
    }

    public void setFoodQuality(String foodQuality) {
        this.foodQuality = foodQuality;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }
}
