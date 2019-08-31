package com.example.lista10;

public class Item {
    int id;
    String name;
    String description;
    String detail;
    String quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + "\n" + "Detail: " + this.detail + "\n" + "Description: " + this.description + "\n" + "Quantity:" + this.getQuantity();
    }
}
