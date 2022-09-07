package com.example.simplebeercatalogapp;

public class PostPoJo {
    private String alcohol;
    private String name;
    private String brand;

    public PostPoJo(String alcohol, String name, String brand) {
        this.alcohol = alcohol;
        this.name = name;
        this.brand = brand;
    }

    public void setAlcohol(String alcohol) {
        this.alcohol = alcohol;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getAlcohol() {
        return alcohol;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }
}
