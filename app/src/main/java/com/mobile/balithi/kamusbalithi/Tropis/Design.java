package com.mobile.balithi.kamusbalithi.Tropis;

/**
 * Created by Muhammad Husni Farid on 21/08/2017.
 */

public class Design {

    private String idtropis;
    private String jenis;
    private String asal;
    private String umum;
    private String img;


    public Design(String idtropis, String jenis, String asal, String umum, String img) {

        this.idtropis = idtropis;
        this.jenis = jenis;
        this.asal = asal;
        this.umum = umum;
        this.img = img;
    }
    public String getIdtropis(){
        return idtropis;
    }

    public String getJenis() {
        return jenis;
    }

    public String getAsal() {
        return asal;
    }

    public String getUmum() {
        return umum;
    }

    public String getImg() {
        return img;
    }

}
