package com.mobile.balithi.kamusbalithi.HamaPenyakit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Html;
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

public class AdapterHama extends ArrayAdapter<AdapterStringHama> {

    private static final String TAG = "ListAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;


    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        TextView title;

    }

    /**
     * Default constructor for the PersonListAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public AdapterHama(Context context, int resource, ArrayList<AdapterStringHama> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;

    }

    @NonNull
//    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        //sets up the image loader librar


        //get the persons information

        String title = getItem(position).getTitle();
        String idhapen = getItem(position).getIdhapen();


        try{

            //create the view result for showing the animation
            final View result;

            //ViewHolder object
            ViewHolder holder;

            if(convertView == null){
                LayoutInflater inflater = LayoutInflater.from(mContext);
                convertView = inflater.inflate(mResource, parent, false);
                holder= new ViewHolder();
                holder.title = (TextView) convertView.findViewById(R.id.jenis_tropis);

                result = convertView;

                convertView.setTag(holder);
            }
            else{
                holder = (ViewHolder) convertView.getTag();
                result = convertView;
            }

            lastPosition = position;

            holder.title.setText(Html.fromHtml(title));

            //create the imageloader object

        }catch (IllegalArgumentException e) {
            Log.e(TAG, "getView: IllegalArgumentException: " + e.getMessage());
            return convertView;
        }
        return convertView;
    }

}
