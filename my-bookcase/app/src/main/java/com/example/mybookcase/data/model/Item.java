package com.example.mybookcase.data.model;

import android.media.Image;

public class Item {
    private String name;
    private String description;
    private String type;
    //private Image image;

    public Item(String name, String description,String type){
        this.setName(name);
        this.setDescription(description);
        this.setType(type);
        //this.setImage(image );
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

   /* public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }*/
}

