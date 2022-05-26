package com.example.lostandfound2.model;

public class AllItems {

    private int item_id;
    private String item_name;
    private String item_Phone;
    private String item_Description;
    private String item_Date;
    private String item_Location;
    private String item_LostFound;

    public AllItems(String item_name, String item_Description,String item_Location,String item_Phone,  String item_Date,  String item_LostFound) {

        this.item_name = item_name;
        this.item_Phone = item_Phone;
        this.item_Description = item_Description;
        this.item_Date = item_Date;
        this.item_Location = item_Location;
        this.item_LostFound = item_LostFound;
    }

    public AllItems(int item_id, String item_name, String item_Description,String item_Location,String item_Phone,  String item_Date,  String item_LostFound) {

        this.item_id =item_id;
        this.item_name = item_name;
        this.item_Phone = item_Phone;
        this.item_Description = item_Description;
        this.item_Date = item_Date;
        this.item_Location = item_Location;
        this.item_LostFound = item_LostFound;
    }



    public int getItem_id(){return item_id;}

    public String getItem_name() {
        return item_name;
    }

    public String getItem_Phone() {
        return item_Phone;
    }

    public String getItem_Description() {
        return item_Description;
    }

    public String getItem_Date() {
        return item_Date;
    }

    public String getItem_Location() {
        return item_Location;
    }

    public String getItem_LostFound() {
        return item_LostFound;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public void setItem_Phone(String item_Phone) {
        this.item_Phone = item_Phone;
    }

    public void setItem_Description(String item_Description) {this.item_Description = item_Description;
    }

    public void setItem_Date(String item_Date) {
        this.item_Date = item_Date;
    }

    public void setItem_Location(String item_Location) {
        this.item_Location = item_Location;
    }

    public void setItem_LostFound(String item_LostFound) {
        this.item_LostFound = item_LostFound;
    }

}
