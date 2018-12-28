package com.mobile.balithi.kamusbalithi.Komoditas;

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

public class CustomListAdapter extends ArrayAdapter<Card> {

    private static final String TAG = "ListAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;




    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        TextView title, deskripsi;
        ImageView image;

    }

    /**
     * Default constructor for the PersonListAdapter
     * @param context
     * @param resource
     * @param object
     */
    public CustomListAdapter(Context context, int resource, ArrayList<Card> object) {
        super(context, resource, object);
        mContext = context;
        mResource = resource;

    }

    @NonNull
//    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        //sets up the image loader library
        setupImageLoader();



        //get the persons information
        String idkomo = getItem(position).getIdkomo();
        String title = getItem(position).getTitle();
        String imgUrl = getItem(position).getImgURL();
        String deskripsi = getItem(position).getDeskripsi();



        try{

            //create the view result for showing the animation
            final View result;

            //ViewHolder object
            ViewHolder holder;

            if(convertView == null){
                LayoutInflater inflater = LayoutInflater.from(mContext);
                convertView = inflater.inflate(mResource, parent, false);
                holder= new ViewHolder();
                holder.title = (TextView) convertView.findViewById(R.id.cardTitle);
                holder.image = (ImageView) convertView.findViewById(R.id.cardImage);
                holder.deskripsi = (TextView) convertView.findViewById(R.id.cardDesc);

                result = convertView;

                convertView.setTag(holder);
            }
            else{
                holder = (ViewHolder) convertView.getTag();
                result = convertView;
            }

            lastPosition = position;

            holder.title.setText(title);
            holder.deskripsi.setText(Html.fromHtml(deskripsi));

            //create the imageloader object
            ImageLoader imageLoader = ImageLoader.getInstance();


            //create display options
//        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
//                .cacheOnDisc(true).resetViewBeforeLoading(true)
//                .showImageForEmptyUri(defaultImage)
//                .showImageOnFail(defaultImage)
//                .showImageOnLoading(defaultImage).build();

            //download and display image from url

            imageLoader.displayImage(imgUrl, holder.image);

        }catch (IllegalArgumentException e) {
            Log.e(TAG, "getView: IllegalArgumentException: " + e.getMessage());
            return convertView;
        }
        return convertView;
    }


    /**
     * Required for setting up the Universal Image loader Library
     */
    private void setupImageLoader(){
        // UNIVERSAL IMAGE LOADER SETUP
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc(true).cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                mContext)
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .discCacheSize(100 * 1024 * 1024).build();

        ImageLoader.getInstance().init(config);
        // END - UNIVERSAL IMAGE LOADER SETUP
    }


}
