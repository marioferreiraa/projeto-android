package com.example.mybookcase.data.model;

import android.graphics.drawable.Drawable;
import android.media.Image;

public class Item {

    private Integer id;
    private String name;
    private String description;
    private String type;
    private String pathImage;
    private int imageId;
    private Drawable testImage;
    private String isAcervo;

    public Item(String name, String description, Drawable testImage/*,String type*/){
        this.setName(name);
        this.setDescription(description);
        this.setTestImage(testImage);
    }

    public Item(Integer id, String name, String description, String type, String pathImage, String isAcervo){
        this.setName(name);
        this.setDescription(description);
        this.setType(type);
        this.setPathImage(pathImage);
        this.setId(id);
        this.setIsAcervo(isAcervo);
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

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsAcervo() {
        return isAcervo;
    }

    public void setIsAcervo(String isAcervo) {
        this.isAcervo = isAcervo;
    }
}

