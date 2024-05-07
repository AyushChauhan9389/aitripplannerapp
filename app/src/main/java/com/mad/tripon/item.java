package com.mad.tripon;

public class item {
    String placeid;

    String name;

    String Formatted_address;

    public item(String placeid, String name, String formatted_address) {
        this.placeid = placeid;
        this.name = name;
        Formatted_address = formatted_address;
    }

    public String getPlaceid() {
        return placeid;
    }

    public void setPlaceid(String placeid) {
        this.placeid = placeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormatted_address() {
        return Formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        Formatted_address = formatted_address;
    }
}
