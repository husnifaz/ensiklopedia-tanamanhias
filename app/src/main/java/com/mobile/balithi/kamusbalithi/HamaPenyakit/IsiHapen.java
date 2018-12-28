package com.mobile.balithi.kamusbalithi.HamaPenyakit;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.webkit.WebView;
import android.widget.TextView;

import com.mobile.balithi.kamusbalithi.Database.DataHelper;
import com.mobile.balithi.kamusbalithi.ImagePagerAdapter;
import com.mobile.balithi.kamusbalithi.R;

/**
 * Created by Muhammad Husni Farid on 16/08/2017.
 */

public class IsiHapen extends AppCompatActivity {

    private AssetManager assetManager;
    TextView nama, kategori, judul;
    WebView deshama, pengendalian1;
    DataHelper dbHelper;
    protected Cursor cursor;
    private Context mContext;
    String imageFolder, bufferkat, getHap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.isi_hapen);
        assetManager=getAssets();

        getHap=getIntent().getStringExtra("id_hapen");

        judul=(TextView)findViewById(R.id.title_isi_hapen);
        nama = (TextView) findViewById(R.id.nama_penyakit);
        kategori=(TextView)findViewById(R.id.nama_kategori);
        deshama=(WebView) findViewById(R.id.deskripsi_hama);
        pengendalian1 = (WebView) findViewById(R.id.pengendalian_hama);
        dbHelper = new DataHelper(this);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM view_hapen where id_hapen='"+getHap+"'", null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            nama.setText(Html.fromHtml(cursor.getString(2)));
            judul.setText(Html.fromHtml(cursor.getString(2)));

            bufferkat=cursor.getString(1);

            if (bufferkat.equals("Hama")){
                kategori.setText("\nOrdo :\t"+cursor.getString(3)+"\n\n"+"Family :\t"+cursor.getString(4)+"\n");
            }else {
                kategori.setText(cursor.getString(5));
            }

            String gambar = cursor.getString(8);
            imageFolder="Hapen/"+gambar;

            String awal, akhir, text1, text2;
            awal="<html><style type=\"text/css\">\n" +
                    "\tbody{\n" +
                    "font-size:14px; " +
                    "color:#707070;\n" +
                    "\t}\n" +
                    "</style><body>";
            akhir="</body></html>";
            text1=cursor.getString(6).toString();
            text2=cursor.getString(7).toString();

            deshama.loadData(awal+text1+akhir,"text/html","utf-8");
            pengendalian1.loadData(awal+text2+akhir,"text/html","utf-8");
        }


        mContext = getApplicationContext();
        ViewPager vp = (ViewPager) findViewById(R.id.gambar_hapen);
        ImagePagerAdapter adapter = new ImagePagerAdapter(mContext, imageFolder);
        vp.setAdapter(adapter);
        vp.setCurrentItem(0);
        adapter.setTimer(vp,3);

    }


}
