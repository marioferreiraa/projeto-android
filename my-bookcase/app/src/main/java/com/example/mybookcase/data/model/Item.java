package com.example.mybookcase.data.model;

import android.graphics.drawable.Drawable;
import android.media.Image;

public class Item {

    private String name;
    private String description;
    private String type;
    private int imageId;
    private Drawable testImage;
    //private Image image;

    public Item(String name, String description, Drawable testImage/*,String type*/){
        this.setName(name);
        this.setDescription(description);
        this.setTestImage(testImage);
        //this.setType(type);
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

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public Drawable getTestImage() {
        return testImage;
    }

    public void setTestImage(Drawable testImage) {
        this.testImage = testImage;
    }

    /* public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }*/
}

