package com.mobile.balithi.kamusbalithi.Tropis;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mobile.balithi.kamusbalithi.R;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by User on 4/4/2017.
 */

public class ListAdapter extends ArrayAdapter<Design> {

    private static final String TAG = "ListAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;



    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        TextView jenis, umum, asal;
        CircleImageView gambar;

    }

    /**
     * Default constructor for the PersonListAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public ListAdapter(Context context, int resource, ArrayList<Design> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;


    }

    @NonNull
//    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        //sets up the image loader library


        setupImageLoader();

        //get the persons information

        String idtropis=getItem(position).getIdtropis();
        String jenis = getItem(position).getJenis();
        String asal = getItem(position).getAsal();
        String umum = getItem(position).getUmum();
        String img = getItem(position).getImg();



        try{

            //create the view result for showing the animation
            final View result;

            //ViewHolder object
            ViewHolder holder;

            if(convertView == null){
                LayoutInflater inflater = LayoutInflater.from(mContext);
                convertView = inflater.inflate(mResource, parent, false);
                holder= new ViewHolder();
                holder.jenis = (TextView) convertView.findViewById(R.id.jenis_tropis);
                holder.asal = (TextView)convertView.findViewById(R.id.asal_tropis);
                holder.umum = (TextView) convertView.findViewById(R.id.umum_tropis);
                holder.gambar = (CircleImageView)convertView.findViewById(R.id.daftar_icon);

                result = convertView;

                convertView.setTag(holder);
            }
            else{
                holder = (ViewHolder) convertView.getTag();
                result = convertView;
            }

            lastPosition = position;

            holder.jenis.setText(jenis);
            holder.umum.setText(umum);
            holder.asal.setText(asal);

            ImageLoader imageLoader = ImageLoader.getInstance();

            imageLoader.displayImage(img, holder.gambar);

            //create the imageloader object


        }catch (IllegalArgumentException e) {
            Log.e(TAG, "getView: IllegalArgumentException: " + e.getMessage());
            return convertView;
        }
        return convertView;
    }

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
