package com.mobile.balithi.kamusbalithi.Komoditas;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.mobile.balithi.kamusbalithi.ImagePagerAdapter;
import com.mobile.balithi.kamusbalithi.Database.DataHelper;
import com.mobile.balithi.kamusbalithi.R;
import com.mobile.balithi.kamusbalithi.Tanaman.DataTanaman;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Muhammad Husni Farid on 16/08/2017.
 */

public class LihatKomoditas extends AppCompatActivity {

    private AssetManager assetManager;
    TextView judul, deskripsi;
    ImageView gambar;
    DataHelper dbHelper;
    protected Cursor cursor;
    protected Cursor cursor2;
    private Context mContext;
    ListView daftarTanaman;
    String imageFolder, getKomo;
    private String get;
    SearchView search;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lihat_komoditas);

        get=getIntent().getStringExtra("id_komoditas");

        assetManager=getAssets();
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        judul = (TextView) findViewById(R.id.nama_tanaman);
        deskripsi=(TextView)findViewById(R.id.id_komoditas);
        gambar = (ImageView)findViewById(R.id.cols_img);
        dbHelper = new DataHelper(this);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM komoditas WHERE id_komoditas = '" +get+ "'", null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            judul.setText(cursor.getString(1).toString());
            deskripsi.setText(Html.fromHtml(cursor.getString(2).toString()));
            toolbar.setTitle(cursor.getString(1).toString());

            getKomo = cursor.getString(1).toString();
            InputStream is = null;
            try {
                is = assetManager.open("komoditas/" + cursor.getString(3).toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            gambar.setImageBitmap(bitmap);


        }


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        imageFolder="slider/"+getKomo;
        mContext = getApplicationContext();

        SQLiteDatabase dba = dbHelper.getReadableDatabase();
        final ArrayList<DesVarietas> list5 = new ArrayList<>();
        cursor2 = dba.rawQuery("SELECT * FROM view_varietas where id_komoditas='" +get+"'",null);
        cursor2.moveToFirst();
        while (!cursor2.isAfterLast()) {
            list5.add(new DesVarietas(cursor2.getString(0), cursor2.getString(2), cursor2.getString(5), cursor2.getString(4)));
            cursor2.moveToNext();
        }
        cursor2.close();

        TextView teks1 = (TextView)findViewById(R.id.text3);
        teks1.setText("Varietas Dari Komoditas "+getKomo+" Adalah : ");

        daftarTanaman = (ListView) findViewById(R.id.listvarietas);
        ViewCompat.setNestedScrollingEnabled(daftarTanaman, true);
        VarListAdapter adapter1 = new VarListAdapter(this, R.layout.design_list_tanaman, list5);
        daftarTanaman.setAdapter(adapter1);
        daftarTanaman.setSelected(true);
        daftarTanaman.setFocusable(false);

        daftarTanaman.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), DataTanaman.class);
                i.putExtra("id_varietas", list5.get(position).getIdvar());
                startActivity(i);
            }
        });


        mContext = getApplicationContext();
        ViewPager vp = (ViewPager) findViewById(R.id.vp);
        ImagePagerAdapter adapter = new ImagePagerAdapter(mContext, imageFolder);
        vp.setAdapter(adapter);
        vp.setCurrentItem(0);
        adapter.setTimer(vp,3);

    }


}
