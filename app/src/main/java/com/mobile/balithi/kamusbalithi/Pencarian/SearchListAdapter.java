package com.mobile.balithi.kamusbalithi.Pencarian;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.balithi.kamusbalithi.Komoditas.Card;
import com.mobile.balithi.kamusbalithi.R;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;

/**
 * Created by Muhammad Husni Farid on 14/07/2017.
 */

public class SearchListAdapter extends ArrayAdapter<SearchAdapter> {

    private static final String TAG = "ListAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;




    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        TextView pk, nama;


    }

    /**
     * Default constructor for the PersonListAdapter
     * @param context
     * @param resource
     * @param object
     */
    public SearchListAdapter(Context context, int resource, ArrayList<SearchAdapter> object) {
        super(context, resource, object);
        mContext = context;
        mResource = resource;

    }

    @NonNull
//    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        //sets up the image loader library


        //get the persons information
        String idkomo = getItem(position).getPk();
        String nama2 = getItem(position).getNama();




        try{

            //create the view result for showing the animation
            final View result;

            //ViewHolder object
            ViewHolder holder;

            if(convertView == null){
                LayoutInflater inflater = LayoutInflater.from(mContext);
                convertView = inflater.inflate(mResource, parent, false);
                holder= new ViewHolder();
                holder.nama = (TextView) convertView.findViewById(R.id.nama_search);
//                holder.pk=(TextView)convertView.findViewById(R.id.id_search);

                result = convertView;

                convertView.setTag(holder);
            }
            else{
                holder = (ViewHolder) convertView.getTag();
                result = convertView;
            }

            lastPosition = position;

            holder.nama.setText(Html.fromHtml(nama2));
//            holder.pk.setText(idkomo);
            //create the imageloader object

        }catch (IllegalArgumentException e) {
            Log.e(TAG, "getView: IllegalArgumentException: " + e.getMessage());
            return convertView;
        }
        return convertView;
    }


    /**
     * Required for setting up the Universal Image loader Library
     */

}
