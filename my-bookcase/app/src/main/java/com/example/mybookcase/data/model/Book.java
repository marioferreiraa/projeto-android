package com.example.mybookcase.data.model;

import android.media.Image;

import com.example.mybookcase.R;

public class Book {

    private String name;
    private String description;
    //private Image image;

    public Book(String name, String description){
        this.setName(name);
        this.setDescription(description);
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

    /*public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }*/
}
