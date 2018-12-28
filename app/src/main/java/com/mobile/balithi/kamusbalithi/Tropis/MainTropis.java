package com.mobile.balithi.kamusbalithi.Tropis;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.mobile.balithi.kamusbalithi.Database.DatabaseAccess;
import com.mobile.balithi.kamusbalithi.R;

import java.util.ArrayList;

public class MainTropis extends Fragment {

    public MainTropis(){}
    LinearLayout view;
    private ListView nListView;
    SearchView searchView2;
    ListAdapter adapter2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = (LinearLayout) inflater.inflate(R.layout.list_tropis, container, false);

        getActivity().setTitle("Tanaman Hias Tropis");


        this.nListView = (ListView) view.findViewById(R.id.list2);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity());
        databaseAccess.open();
        final ArrayList<Design> Tropis = databaseAccess.getTropis();
        databaseAccess.close();

        adapter2 = new ListAdapter(getActivity(), R.layout.design_tropis, Tropis);
        nListView.setAdapter(adapter2);

        nListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), DescTropis.class);
                i.putExtra("id_tropis", Tropis.get(position).getIdtropis());
                startActivity(i);
            }
        });

        return view;
    }

}
