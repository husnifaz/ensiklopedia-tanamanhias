package com.mobile.balithi.kamusbalithi.Tropis;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.balithi.kamusbalithi.ImagePagerAdapter;
import com.mobile.balithi.kamusbalithi.Database.DataHelper;
import com.mobile.balithi.kamusbalithi.R;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Muhammad Husni Farid on 16/08/2017.
 */

public class DescTropis extends AppCompatActivity {

    private AssetManager assetManager;
    TextView nama, nama2, famili, umum, asal;
    WebView deskrip, lingkungan, manfaat ;
    ImageView gambar2;
    DataHelper dbHelper;
    protected Cursor cursor;
    private Context mContext;
    String imageFolder, gambar, getTro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lihat_tropis);

        getTro =getIntent().getStringExtra("id_tropis");
        assetManager=getAssets();

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        nama = (TextView) findViewById(R.id.nama_tanaman);
        nama2 = (TextView) findViewById(R.id.nama_tanaman2);
        famili=(TextView)findViewById(R.id.isi_famili);
        umum=(TextView)findViewById(R.id.isi_umum);
        asal=(TextView)findViewById(R.id.isi_asal);
        deskrip=(WebView) findViewById(R.id.tropis_deskripsi);
        lingkungan=(WebView) findViewById(R.id.lingkungan_tumbuh);
        manfaat=(WebView) findViewById(R.id.tropis_manfaat);
        gambar2 = (ImageView)findViewById(R.id.cols_img2);
        dbHelper = new DataHelper(this);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM tropis WHERE id_tropis = '" +getTro+ "'", null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            nama.setText(Html.fromHtml(cursor.getString(1).toString()));
            nama2.setText(Html.fromHtml(cursor.getString(2).toString()));
            famili.setText(cursor.getString(3).toString());
            umum.setText(cursor.getString(4).toString());
            asal.setText(cursor.getString(5).toString());

            gambar = cursor.getString(1).toString();


            toolbar.setTitle(cursor.getString(1));
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });


            String awal, akhir, des1, ling1, man1;
            awal="<html><body><p style=\"text-align: justify; font-size:14px; color:#707070;\">";
            akhir="</p></body></html>";
            des1=cursor.getString(6).toString();
            ling1=cursor.getString(7).toString();
            man1=cursor.getString(8).toString();

            deskrip.loadData(awal+des1+akhir,"text/html","utf-8");
            lingkungan.loadData(awal+ling1+akhir,"text/html","utf-8");
            manfaat.loadData(awal+man1+akhir,"text/html","utf-8");


            InputStream is = null;
            try {
                is = assetManager.open("Tropis/"+cursor.getString(1).toString()+"/"+cursor.getString(1).toString()+".jpg");
            } catch (IOException e) {
                e.printStackTrace();
            }
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            gambar2.setImageBitmap(bitmap);



        }

        imageFolder="Tropis/"+gambar;
        mContext = getApplicationContext();
//
//
        mContext = getApplicationContext();

        ViewPager vp = (ViewPager) findViewById(R.id.vp);
        ImagePagerAdapter adapter = new ImagePagerAdapter(mContext, imageFolder);
        vp.setAdapter(adapter);
        vp.setCurrentItem(0);
        adapter.setTimer(vp,3);

    }
}
