package com.mobile.balithi.kamusbalithi.HamaPenyakit;

/**
 * Created by User on 4/4/2017.
 */

public class AdapterStringHama {
    private String title;
    private String idhapen;


    public AdapterStringHama(String idhapen, String title) {
        this.title = title;
        this.idhapen = idhapen;
    }

    public String getTitle() {
        return title;
    }

    public String getIdhapen(){
        return idhapen;
    }

}
