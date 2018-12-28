package com.mobile.balithi.kamusbalithi.Komoditas;

import android.app.Fragment;
import android.content.Intent;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.mobile.balithi.kamusbalithi.Coba1;
import com.mobile.balithi.kamusbalithi.Database.DatabaseAccess;
import com.mobile.balithi.kamusbalithi.R;

import java.util.ArrayList;

public class MainKomo extends Fragment {

    public MainKomo(){}
    private ListView mListView;
    EditText txtsearch1;
    Button tmbcari;
    CustomListAdapter adapter;
    String pat;
    ArrayList resultList = new ArrayList();
    public int jumB = 0;
    double wMulai, wSelesai, wTotal;
    ArrayList<Card> newr;
    LinearLayout view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = (LinearLayout) inflater.inflate(R.layout.list_komoditas, container, false);

        getActivity().setTitle("Komoditas Tanaman Hias");


        mListView = (ListView) view.findViewById(R.id.listView);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity());
        databaseAccess.open();
        final ArrayList<Card> Komoditas = databaseAccess.getKomoditas();
        newr=new ArrayList<>(Komoditas);
        databaseAccess.close();

        adapter = new CustomListAdapter(getActivity(), R.layout.cardview_layout, Komoditas);
        mListView.setAdapter(adapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(getActivity(), LihatKomoditas.class);
                i.putExtra("id_komoditas", Komoditas.get(position).getIdkomo());
                startActivity(i);
            }
        });

        return view;
    }

}
