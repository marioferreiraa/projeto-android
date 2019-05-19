package com.example.mybookcase.data.model;

import android.graphics.drawable.Drawable;
import android.media.Image;

public class Item {

    /***
     * ====================== Lembrar de Limpar essa classe =========================
     */

    private Integer id;
    private String name;
    private String description;
    private String type;
    private byte[] imageByte;
    private int imageId;
    private Drawable testImage;
    private Drawable image;
    private String isAcervo;

    public Item(String name, String description, Drawable testImage/*,String type*/){
        this.setName(name);
        this.setDescription(description);
        this.setTestImage(testImage);
    }

    public Item(Integer id, String name, String description, String type, String isAcervo, byte[] imageByte){
        this.setId(id);
        this.setName(name);
        this.setDescription(description);
        this.setType(type);
        this.setIsAcervo(isAcervo);
        this.setImageByte(imageByte);
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) { this.id = id; }

    public String getIsAcervo() {
        return isAcervo;
    }

    public void setIsAcervo(String isAcervo) {
        this.isAcervo = isAcervo;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public byte[] getImageByte() {
        return imageByte;
    }

    public void setImageByte(byte[] imageByte) {
        this.imageByte = imageByte;
    }
}