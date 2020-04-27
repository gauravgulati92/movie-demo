package com.example.moviedemo.dtos.request;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

public class MovieCreateRequestDto {

    @NotBlank
    private String title;
   
    private String category;
    @DecimalMin(value = "0.5",message = "rating should be greater than 0.5")
    @DecimalMax(value = "5.0",message = "rating should be less than 5.0")
    private float rating;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

}