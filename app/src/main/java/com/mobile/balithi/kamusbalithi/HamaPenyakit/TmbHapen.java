package com.mobile.balithi.kamusbalithi.HamaPenyakit;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.mobile.balithi.kamusbalithi.R;

/**
 * Created by Muhammad Husni Farid on 26/08/2017.
 */

public class TmbHapen extends Fragment {

    public TmbHapen(){}
    LinearLayout view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = (LinearLayout) inflater.inflate(R.layout.layout_happen, container, false);

        getActivity().setTitle("Hama & Penyakit Krisan");

        Button hama3 = (Button)view.findViewById(R.id.tmb_hama);
        Button penyakit3 = (Button)view.findViewById(R.id.tmb_penyakit);

        hama3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Button b = (Button)v;
                String buttonText = b.getText().toString();
                Intent i = new Intent(getActivity(), IntroHapen.class);
                i.putExtra("jenis", buttonText);
                startActivity(i);
            }
        });
        penyakit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Button b = (Button) v;
                String buttonText2 = b.getText().toString();
                Intent i = new Intent(getActivity(), IntroHapen.class);
                i.putExtra("jenis", buttonText2);
                startActivity(i);

            }
        });

        return view;
    }
}
