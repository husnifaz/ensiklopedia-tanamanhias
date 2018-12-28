package com.mobile.balithi.kamusbalithi.Pencarian;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.mobile.balithi.kamusbalithi.HamaPenyakit.IntroHapen;
import com.mobile.balithi.kamusbalithi.R;

/**
 * Created by Muhammad Husni Farid on 08/10/2017.
 */

public class PencarianAwal extends Fragment {

    public PencarianAwal(){}
    LinearLayout view;
    EditText kolomcari;
    Button btncari1;
    Spinner spin;
    String buffer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = (LinearLayout) inflater.inflate(R.layout.layout_search, container, false);
        getActivity().setTitle("Pencarian");


        kolomcari=(EditText)view.findViewById(R.id.txt_cari);
        btncari1=(Button)view.findViewById(R.id.btn_cari);
        spin=(Spinner)view.findViewById(R.id.spin_konten);

        btncari1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buffer=spin.getSelectedItem().toString();
                String bufkolom = kolomcari.getText().toString();
                Intent i = new Intent(getActivity(), MainPencarian.class);
                i.putExtra("kolom",bufkolom);
                i.putExtra("konten",buffer);
                startActivity(i);
            }
        });

        return view;
    }
}
