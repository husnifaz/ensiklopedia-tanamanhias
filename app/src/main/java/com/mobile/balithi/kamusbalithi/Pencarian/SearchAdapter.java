package com.mobile.balithi.kamusbalithi.Pencarian;

/**
 * Created by Muhammad Husni Farid on 13/10/2017.
 */

public class SearchAdapter {
    private String pk;
    private String nama;


    public SearchAdapter(String pk, String nama){

        this.pk= pk;
        this.nama=nama;

    }
    public String getPk(){
        return pk;
    }
    public void setPk(String pk){
        this.pk=pk;
    }

    public String getNama(){
        return nama;
    }

    public void setNama(String nama){
        this.nama=nama;
    }

}
