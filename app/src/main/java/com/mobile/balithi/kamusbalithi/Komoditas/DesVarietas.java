package com.mobile.balithi.kamusbalithi.Komoditas;

/**
 * Created by User on 4/4/2017.
 */

public class DesVarietas {
    private String idvar;
    private String varietas;
    private String peneliti;
    private String tahun;


    public DesVarietas(String idvar, String varietas, String peneliti, String tahun) {

        this.idvar = idvar;
        this.varietas = varietas;
        this.peneliti = peneliti;
        this.tahun = tahun;
    }
    public String getIdvar() {
        return idvar;
    }

    public String getVarietas() {
        return varietas;
    }

    public String getPeneliti() {
        return peneliti;
    }

    public String getTahun() {
        return tahun;
    }
}
