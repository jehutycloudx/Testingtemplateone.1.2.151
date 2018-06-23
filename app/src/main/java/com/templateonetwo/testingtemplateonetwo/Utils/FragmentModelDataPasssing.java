package com.templateonetwo.testingtemplateonetwo.Utils;

public class FragmentModelDataPasssing {
    String title,description,category,date,time,location,switchValue;

    public FragmentModelDataPasssing()
    {

    }

    public FragmentModelDataPasssing(String title, String description, String category, String date, String time, String location, String switchValue) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.date = date;
        this.time = time;
        this.location = location;
        this.switchValue = switchValue;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSwitchValue() {
        return switchValue;
    }

    public void setSwitchValue(String switchValue) {
        this.switchValue = switchValue;
    }
}
