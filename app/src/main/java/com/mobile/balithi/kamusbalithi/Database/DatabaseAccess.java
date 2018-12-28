package com.mobile.balithi.kamusbalithi.Database;

/**
 * Created by Muhammad Husni Farid on 17/07/2017.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mobile.balithi.kamusbalithi.Komoditas.Card;
import com.mobile.balithi.kamusbalithi.HamaPenyakit.AdapterStringHama;
import com.mobile.balithi.kamusbalithi.Tropis.Design;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DataHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of quotes
     */


    public ArrayList<Card> getKomoditas() {
        ArrayList<Card> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM komoditas order by nama_komoditas asc", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(new Card(cursor.getString(0),cursor.getString(1),"assets://komoditas/"+cursor.getString(3),cursor.getString(2)));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public ArrayList<Design> getTropis(){
        ArrayList<Design> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM tropis", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(new Design(cursor.getString(0), cursor.getString(1), cursor.getString(5), cursor.getString(4),"assets://Tropis/"+cursor.getString(1).toString()+"/"+cursor.getString(1).toString()+".jpg"));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public ArrayList<AdapterStringHama> getHama(){
        ArrayList<AdapterStringHama> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM view_hapen where jenis='Hama'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(new AdapterStringHama(cursor.getString(0), cursor.getString(2)));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public ArrayList<AdapterStringHama> getPenyakit(){
        ArrayList<AdapterStringHama> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM view_hapen where jenis='Penyakit'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(new AdapterStringHama(cursor.getString(0), cursor.getString(2)));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

}

