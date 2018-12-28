package com.mobile.balithi.kamusbalithi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Muhammad Husni Farid on 13/10/2017.
 */

public class Coba1 extends AppCompatActivity {

    EditText cobas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coba);

        cobas = (EditText) findViewById(R.id.kitas3);
        String string2 = getIntent().getStringExtra("komoditas");
        cobas.setText(string2);

    }
}
