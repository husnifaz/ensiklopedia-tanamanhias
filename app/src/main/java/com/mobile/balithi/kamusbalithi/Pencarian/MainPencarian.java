package com.mobile.balithi.kamusbalithi.Pencarian;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.balithi.kamusbalithi.Database.DataHelper;
import com.mobile.balithi.kamusbalithi.HamaPenyakit.IsiHapen;
import com.mobile.balithi.kamusbalithi.Komoditas.LihatKomoditas;
import com.mobile.balithi.kamusbalithi.R;
import com.mobile.balithi.kamusbalithi.Tanaman.DataTanaman;
import com.mobile.balithi.kamusbalithi.Tropis.DescTropis;

import java.util.ArrayList;

/**
 * Created by Muhammad Husni Farid on 08/10/2017.
 */

public class MainPencarian extends AppCompatActivity {

    String pat, kat;
    SQLiteDatabase db;
    ListView newListview;
    SearchListAdapter adapter, adapter2;
    public int jumB = 0;
    Toolbar toolbar;
    private Cursor cursor;
    LinearLayout view;
    double wMulai, wSelesai, wTotal;
    ArrayList<SearchAdapter> resultList = new ArrayList<>();
    String c1, c2, c3, c4;
    ArrayList<SearchAdapter> cont1 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_search);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        view=(LinearLayout)findViewById(R.id.lay_main_search);

        DataHelper dbHelper=new DataHelper(this);
        db = dbHelper.getReadableDatabase();

        c1="Komoditas Balithi";
        c2="Varietas Balithi";
        c3="Tanaman Hias Tropis";
        c4="Hama dan Penyakit Krisan";

        newListview=(ListView)findViewById(R.id.listakhir);
        pat=getIntent().getStringExtra("kolom");
        pat=pat.toLowerCase();
        kat=getIntent().getStringExtra("konten");
        toolbar.setTitle(pat);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if (kat.equals(c1)){
            wMulai=System.currentTimeMillis();
            cursor = db.rawQuery("SELECT * FROM komoditas", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {

                cont1.add(new SearchAdapter(cursor.getString(0), cursor.getString(1)));
                cursor.moveToNext();
            }
            adapter = new SearchListAdapter(this, R.layout.design_search, cont1);
            newListview.setAdapter(adapter);
            startSearch(pat);
            wSelesai=System.currentTimeMillis();
            wTotal=(wSelesai-wMulai);
            Toast.makeText(this, "Waktu pencarian "+wTotal+" ms, "+jumB+" ditemukan.", Toast.LENGTH_SHORT).show();

            newListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent sir1 = new Intent(MainPencarian.this, LihatKomoditas.class);
                    sir1.putExtra("id_komoditas",resultList.get(position).getPk());
                    startActivity(sir1);
                }
            });


        }else if (kat.equals(c2)){
            wMulai=System.currentTimeMillis();
            cursor = db.rawQuery("SELECT * FROM view_varietas", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {

                cont1.add(new SearchAdapter(cursor.getString(0), cursor.getString(2)));
                cursor.moveToNext();
            }

            adapter = new SearchListAdapter(this, R.layout.design_search, cont1);
            newListview.setAdapter(adapter);
            startSearch(pat);
            wSelesai=System.currentTimeMillis();
            wTotal=(wSelesai-wMulai);
            Toast.makeText(this, "Waktu pencarian "+wTotal+" ms, "+jumB+" ditemukan.", Toast.LENGTH_SHORT).show();

            newListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String selected = resultList.get(position).getPk();
                    Intent sir2 = new Intent(MainPencarian.this, DataTanaman.class);
                    sir2.putExtra("id_varietas",selected);
                    startActivity(sir2);
                }
            });
        }
        else if (kat.equals(c3)){
            wMulai=System.currentTimeMillis();
            cursor = db.rawQuery("SELECT * FROM tropis", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {

                cont1.add(new SearchAdapter(cursor.getString(0), cursor.getString(1)));
                cursor.moveToNext();
            }
            adapter = new SearchListAdapter(this, R.layout.design_search, cont1);
            newListview.setAdapter(adapter);
            startSearch(pat);
            wSelesai=System.currentTimeMillis();
            wTotal=(wSelesai-wMulai);
            Toast.makeText(this, "Waktu pencarian "+wTotal+" ms, "+jumB+" ditemukan.", Toast.LENGTH_SHORT).show();

            newListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent sir = new Intent(MainPencarian.this, DescTropis.class);
                    sir.putExtra("id_tropis",resultList.get(position).getPk());
                    startActivity(sir);
                }
            });
        }else if (kat.equals(c4)){
            wMulai=System.currentTimeMillis();
            cursor = db.rawQuery("SELECT * FROM view_hapen", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                cont1.add(new SearchAdapter(cursor.getString(0), cursor.getString(2)));
                cursor.moveToNext();
            }
            adapter = new SearchListAdapter(this, R.layout.design_search, cont1);
            newListview.setAdapter(adapter);
            startSearch(pat);
            wSelesai=System.currentTimeMillis();
            wTotal=(wSelesai-wMulai);
            Toast.makeText(this, "Waktu pencarian "+wTotal+" ms, "+jumB+" ditemukan.", Toast.LENGTH_SHORT).show();
            newListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent sir = new Intent(MainPencarian.this, IsiHapen.class);
                    sir.putExtra("id_hapen",resultList.get(position).getPk());
                    startActivity(sir);
                }
            });
        }

    }

    public void startSearch(String pattern) {
        resultList.clear();
        adapter = new SearchListAdapter(this, R.layout.design_search, cont1);
        newListview.setAdapter(adapter);
        try {
            for (SearchAdapter sa : cont1){
                String nama1=sa.getNama().toLowerCase();
                String id1=sa.getPk();

                BM_start(id1, nama1, pattern);
            }
            adapter2 = new SearchListAdapter(this, R.layout.design_search, resultList);
            newListview.setAdapter(adapter2);
        } catch (Exception e) {
            Log.d("Print", "catch");
        }

    }

    static String txtB;
    static String patB;
    static String idB;
    static char[] xB;
    static char[] yB;
    static int nB;
    static int mB;
    static int[] suf = new int[256];
    static int[] bmBc = new int[256];
    static int[] bmGs = new int[256];

    public void BM_start(String getId, String getTxt, String getPat) {
        txtB = getTxt;
        patB = getPat;
        idB = getId;
        xB = patB.toCharArray();
        yB = txtB.toCharArray();
        nB = txtB.length();
        mB = patB.length();
        BM();
    }
    public static void preBmBc() {
        for (int i = 0; i < 256; ++i) {
            bmBc[i] = mB;
        }
        for (int i = 0; i < mB - 1; ++i) {
            bmBc[xB[i]] = mB - i - 1;
        }
    }
    public static void suffixes() {
        int f = 0;
        int g;
        suf[mB - 1] = mB;
        g = mB - 1;
        for (int i = mB - 2; i >= 0; --i) {
            if (i > g && suf[i + mB - 1 - f] < i - g) {
                suf[i] = suf[i + mB - 1 - f];
            } else {
                if (i < g) {
                    g = i;
                }
                f = i;
                while (g >= 0 && xB[g] == xB[g + mB - 1 - f]) {
                    --g;
                }
                suf[i] = f - g;
            }
        }
    }
    public static void preBmGs() {
        suffixes();
        for (int i = 0; i < mB; ++i) {
            bmGs[i] = mB;
        }
        int j = 0;
        for (int i = mB - 1; i >= 0; --i) {
            if (suf[i] == i + 1) {
                for (; j < mB - 1 - i; ++j) {
                    if (bmGs[j] == mB) {
                        bmGs[j] = mB - 1 - i;
                    }
                }
            }
        }
        for (int i = 0; i <= mB - 2; ++i) {
            bmGs[mB - 1 - suf[i]] = mB - 1 - i;
        }
    }
    public void BM() {
        int i, j;
/* Pre-processing */
        preBmGs();
        preBmBc();
/* Searching */
        j = 0;
        while (j <= nB - mB) {
            for (i = mB - 1; i >= 0 && xB[i] == yB[i + j]; --i);
            if (i < 0) {
//System.out.println(" Result = " + j);
                int pos = j;
                j += bmGs[0];//
                jumB++;//menmpung nilai yg uda ditemukan
                SearchAdapter m = new SearchAdapter(idB, txtB);
                m.setNama(txtB);
                m.setPk(idB);
                resultList.add(m);
            } else {
                j += Math.max(bmGs[i], bmBc[yB[i + j]] - mB + 1 +
                        i);
            }
        }
    }
}
