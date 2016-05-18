package com.example.tuanlv.myweather.controllers;

/**
 * Created by vieta on 17/5/2016.
 */
public class MenuDrawer {
    private String description;
    private int linkImage;

    public MenuDrawer(String description, int linkImage) {
        this.description = description;
        this.linkImage = linkImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(int linkImage) {
        this.linkImage = linkImage;
    }
}
