package com.mobile.balithi.kamusbalithi.HamaPenyakit;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.balithi.kamusbalithi.Database.DataHelper;
import com.mobile.balithi.kamusbalithi.Database.DatabaseAccess;
import com.mobile.balithi.kamusbalithi.R;

import java.util.ArrayList;


/**
 * Created by Muhammad Husni Farid on 24/08/2017.
 */

public class IntroHapen extends AppCompatActivity {

    WebView intro;
    TextView namaintro, title, intro2;
    ListView listhapen;
    protected Cursor cursor, cursor2;
    DataHelper dbHelper;
    ArrayList<AdapterStringHama> AraylistHapen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_hapen);

        String jenis = getIntent().getStringExtra("jenis");

        intro=(WebView)findViewById(R.id.isi_hama);
        listhapen=(ListView)findViewById(R.id.list_hapen);
        namaintro=(TextView)findViewById(R.id.nama_hapen);
        namaintro.setText(jenis);
        title=(TextView)findViewById(R.id.title_hapen);
        title.setText(jenis);
        dbHelper = new DataHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM intro WHERE jenis = '" + jenis + "'", null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            String awal, akhir, des1;
            awal = "<html><style type=\"text/css\">\n" +
                    "\tbody{\n" +
                    "\t\tfont-size: 14px;\n" +
                    "\t\tcolor: #707070;\n" +
                    "\t}\n" +
                    "\tp{\n" +
                    "\t\ttext-align: justify;\n" +
                    "\t\ttext-indent: 0.2in;\n" +
                    "\t}\n" +
                    "</style><body>";
            akhir = "</body></html>";
            des1 = cursor.getString(2).toString();

            intro.loadData(awal + des1 + akhir, "text/html", "utf-8");
        }

        intro2=(TextView)findViewById(R.id.hapen44);
        intro2.setText("Daftar "+getIntent().getStringExtra("jenis")+" Yang Menyerang Tanaman Hias Krisan Antara Lain :");

        AraylistHapen = new ArrayList<>();
        cursor2 = db.rawQuery("SELECT * FROM view_hapen where jenis='"+jenis+"'", null);
        cursor2.moveToFirst();
        while (!cursor2.isAfterLast()) {
            AraylistHapen.add(new AdapterStringHama(cursor2.getString(0), cursor2.getString(2)));
            cursor2.moveToNext();
        }
        cursor2.close();

        AdapterHama adapter = new AdapterHama(this, R.layout.design_hapen, AraylistHapen);
        listhapen.setAdapter(adapter);
        listhapen.setSelected(true);
        ViewCompat.setNestedScrollingEnabled(listhapen, true);
        listhapen.setFocusable(false);

        listhapen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent(getApplicationContext(), IsiHapen.class);
                    i.putExtra("id_hapen", AraylistHapen.get(position).getIdhapen());
                    startActivity(i);
                }
            });

        }

}
