package com.cs360.woodeweighttracker;

public class WeightModel {
    String weightNumber;
    String weightDate;


    public WeightModel(String weightNumber, String weightDate) {
        this.weightNumber = weightNumber;
        this.weightDate = weightDate;
    }


    public String getWeightNumber() {
        return weightNumber;
    }

    public String getWeightDate() {
        return weightDate;
    }
}
