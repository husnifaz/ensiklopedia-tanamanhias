package com.mobile.balithi.kamusbalithi.Komoditas;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mobile.balithi.kamusbalithi.R;

import java.util.ArrayList;

/**
 * Created by User on 4/4/2017.
 */

public class VarListAdapter extends ArrayAdapter<DesVarietas> {

    private static final String TAG = "ListAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;




    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        TextView var1, peneliti1, tahun1;

    }

    /**
     * Default constructor for the PersonListAdapter
     * @param context
     * @param resource
     * @param object
     */
    public VarListAdapter(Context context, int resource, ArrayList<DesVarietas> object) {
        super(context, resource, object);
        mContext = context;
        mResource = resource;

    }

    @NonNull
//    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        //sets up the image loader library



        //get the persons information
        String idvar = getItem(position).getIdvar();
        String varietas = getItem(position).getVarietas();
        String peneliti = getItem(position).getPeneliti();
        String tahun = getItem(position).getTahun();



        try{

            //create the view result for showing the animation
            final View result;

            //ViewHolder object
            ViewHolder holder;

            if(convertView == null){
                LayoutInflater inflater = LayoutInflater.from(mContext);
                convertView = inflater.inflate(mResource, parent, false);
                holder= new ViewHolder();
                holder.var1 = (TextView) convertView.findViewById(R.id.jenis_varietas);
                holder.peneliti1 = (TextView) convertView.findViewById(R.id.peneliti);
                holder.tahun1 = (TextView) convertView.findViewById(R.id.varietas_tahun);

                result = convertView;

                convertView.setTag(holder);
            }
            else{
                holder = (ViewHolder) convertView.getTag();
                result = convertView;
            }

            lastPosition = position;

            holder.var1.setText(varietas);
            holder.peneliti1.setText(peneliti);
            holder.tahun1.setText(tahun);

            //create the imageloader object

        }catch (IllegalArgumentException e) {
            Log.e(TAG, "getView: IllegalArgumentException: " + e.getMessage());
            return convertView;
        }
        return convertView;
    }

}
