package com.mobile.balithi.kamusbalithi.Komoditas;

/**
 * Created by User on 4/4/2017.
 */

public class Card {
    private String idkomo;
    private String imgURL;
    private String title;
    private String deskripsi;


    public Card(String idkomo, String title, String imgURL, String deskripsi) {

        this.idkomo=idkomo;
        this.title = title;
        this.imgURL = imgURL;
        this.deskripsi = deskripsi;
    }
    public String getIdkomo(){
        return idkomo;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
