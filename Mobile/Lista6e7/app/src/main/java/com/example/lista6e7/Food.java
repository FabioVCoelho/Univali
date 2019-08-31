package com.example.lista6e7;

public class Food {
    String name;
    String price;
    String description;
    String calories;
    String quantity;

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + "\n" + "Price: " + this.price + "\n" + "Calories: " + this.calories + "\n" + "Description: " + this.description;
    }
}
