package com.mobile.balithi.kamusbalithi.Database;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DataHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "tanamanhias.db";
    private static final int DATABASE_VERSION = 1;

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}