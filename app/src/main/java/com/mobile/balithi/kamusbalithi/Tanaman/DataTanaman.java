package com.mobile.balithi.kamusbalithi.Tanaman;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.balithi.kamusbalithi.Database.DataHelper;
import com.mobile.balithi.kamusbalithi.ImagePagerAdapter;
import com.mobile.balithi.kamusbalithi.R;

import org.apache.commons.lang3.text.WordUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Muhammad Husni Farid on 14/07/2017.
 */

public class DataTanaman extends AppCompatActivity {

    TextView judul, komoditas, tahun, pemulia, title, status;
    WebView desk;
    DataHelper dbHelper;
    protected Cursor cursor;
    private Context mContext;
    String imageFolder, getVari, buff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lihat_data_tanaman);

        buff = getIntent().getStringExtra("id_varietas");

        judul = (TextView) findViewById(R.id.nama_tanaman);
        desk = (WebView) findViewById(R.id.text_desc);
        komoditas=(TextView)findViewById(R.id.text_komoditas);
        tahun=(TextView)findViewById(R.id.text_tahun);
        pemulia=(TextView)findViewById(R.id.text_pemulia);
        status=(TextView) findViewById(R.id.text_status);
        TextView sk=(TextView)findViewById(R.id.text_sk);
        title=(TextView)findViewById(R.id.title_data_tanaman);
        dbHelper = new DataHelper(this);


        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM view_varietas WHERE id_varietas = '" +buff+ "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            judul.setText(cursor.getString(2).toString());
            komoditas.setText(cursor.getString(3).toString());
            tahun.setText(cursor.getString(4).toString());
            pemulia.setText(cursor.getString(5).toString());
            sk.setText(cursor.getString(6).toString());
            status.setText(cursor.getString(9).toString());

            title.setText(cursor.getString(2));
            getVari = cursor.getString(2);

            String awal, akhir, des1, stat1;
            awal="<html><body><p style=\"text-align: justify; font-size:14px; color:#707070;\">";
            akhir="</p></body></html>";
            des1=cursor.getString(7).toString();

            desk.loadData(awal+des1+akhir,"text/html","utf-8");

        }

        imageFolder="varietas/"+getVari;
        mContext = getApplicationContext();
//
//
        mContext = getApplicationContext();
        ViewPager vp = (ViewPager) findViewById(R.id.vp1);
        ImagePagerAdapter adapter = new ImagePagerAdapter(mContext, imageFolder);
        vp.setAdapter(adapter);
        vp.setCurrentItem(0);
        adapter.setTimer(vp,3);

    }

}
