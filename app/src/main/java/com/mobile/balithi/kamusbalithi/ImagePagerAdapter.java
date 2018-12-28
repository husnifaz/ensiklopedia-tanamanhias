package com.mobile.balithi.kamusbalithi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;


// Base class providing the adapter to populate pages inside of a ViewPager.
public class ImagePagerAdapter extends PagerAdapter {
    private Context mContext;
    String imageFolder;
    final Handler handler = new Handler();
    public Timer swipeTimer ;

    /*
        When you implement a PagerAdapter, you must override the following methods at minimum:
            instantiateItem(ViewGroup, int)
            destroyItem(ViewGroup, int, Object)
            getCount()
            isViewFromObject(View, Object)
     */

//    ImagePagerAdapter(Context context, String imageFolder){
//        this.mContext = context;
//
//       this.imageFolder = imageFolder;
//    }

    public ImagePagerAdapter(Context context, String imageFolder) {
        this.mContext = context;
        this.imageFolder = imageFolder;
    }




    // Get images from assets sub folder.
    private String[] getImages(){
        String[] images = new String[]{};
        try{
            images = mContext.getAssets().list(imageFolder);
        }catch(IOException e){
            e.printStackTrace();
        }
        return images;
    }

    /*
        public abstract int getCount ()
            Return the number of views available.
    */
    @Override
    public int getCount(){
        return getImages().length;
    }

    /*
        public abstract boolean isViewFromObject (View view, Object object)
            Determines whether a page View is associated with a specific key object as returned by
            instantiateItem(ViewGroup, int). This method is required for a
            PagerAdapter to function properly.

            Parameters
                view : Page View to check for association with object
                object : Object to check for association with view

            Returns
                true : if view is associated with the key object object
    */
    @Override
    public boolean isViewFromObject(View view, Object object){
        return view == (RelativeLayout)object;
    }

    /*
        public Object instantiateItem (ViewGroup container, int position)
            Create the page for the given position. The adapter is responsible for adding the view
            to the container given here, although it only must ensure this is done by the time it
            returns from finishUpdate(ViewGroup).

            Parameters
                container : The containing View in which the page will be shown.
                position : The page position to be instantiated.

            Returns
                Returns an Object representing the new page. This does not need to be a View, but
                can be some other container of the page.
    */
    @Override
    public Object instantiateItem(ViewGroup container, int position){
        // Create a new LayoutParams object
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        // Initialize a new RelativeLayout instance
        RelativeLayout rl = new RelativeLayout(mContext);
        rl.setLayoutParams(params);
        rl.setBackgroundColor(Color.parseColor("#000000"));
        rl.setPadding(5,5,10,10);

        // Initialize a new ImageView instance
        ImageView iv = new ImageView(mContext);
        iv.setLayoutParams(new LayoutParams(params));
        // Set the ImageView background color
        iv.setBackgroundColor(Color.parseColor("#000000"));
        // Set ImageView padding
        iv.setPadding(15, 2, 15, 2);

        // Generate the current image file path
        final String currentImageFile = imageFolder+"/"+ getImages()[position];

        // Try to set the ImageView image from asset image
        try{
            Bitmap bitmap;
            InputStream is = mContext.getAssets().open(currentImageFile);
            bitmap = BitmapFactory.decodeStream(is);
            iv.setImageBitmap(bitmap);
        }catch(IOException e){
            e.printStackTrace();
        }
        // Specify ImageView image scaling type
        iv.setScaleType(ScaleType.FIT_CENTER);

        // Set a click listener for ImageView
//        iv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(mContext, currentImageFile, Toast.LENGTH_SHORT).show();
//            }
//        });

//        TextView tv = new TextView(mContext);
//        // Set the layout parameters for TextView
//        tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
//        // Put the TextView at the bottom right of RelativeLayout
//        ((LayoutParams)tv.getLayoutParams()).addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//        ((LayoutParams)tv.getLayoutParams()).addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
//        // Set the TextView font size
//        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10);
//        // Set the TextView font and text style
//        tv.setTypeface(Typeface.MONOSPACE, Typeface.ITALIC);
//
//        // Set the TextView padding
//        tv.setPadding(10, 10, 10, 10);
//        // Set TextView background color to transparent white
//        tv.setBackgroundColor(Color.parseColor("#25FFFFFF"));
//        // Set the TextView text
//        tv.setText("Image " + (position + 1) + " of " + getCount());
//        tv.setTextColor(Color.BLACK);
//
//        // Set the TextView margins
//        MarginLayoutParams mlp = (MarginLayoutParams)tv.getLayoutParams();
//        mlp.setMargins(10,10,10,10);
//
//        // Add the TextView to RelativeLayout
//        rl.addView(tv,0);
//        // Add the ImageView to RelativeLayout
        rl.addView(iv,0);

        // Add the RelativeLayout to the container
        container.addView(rl);

        // Finally, return the RelativeLayout as page of ViewPager
        return rl;


    }

    /*
        public void destroyItem (ViewGroup container, int position, Object object)
            Remove a page for the given position. The adapter is responsible for removing the view
            from its container, although it only must ensure this is done by the time
            it returns from finishUpdate(ViewGroup).

            Parameters
                container : The containing View from which the page will be removed.
                position : The page position to be removed.
                object : The same object that was returned by instantiateItem(View, int).
    */
    public void destroyItem(ViewGroup container, int position, Object object){
        // Remove the page from ViewPager object
        container.removeView((RelativeLayout) object);
    }

    public void setTimer(final ViewPager myPager, int time) {
        final int size = 3;


        final Runnable Update = new Runnable() {
            int NUM_PAGES = size;
            int currentPage = 0;

            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                myPager.setCurrentItem(currentPage++, true);
            }
        };

        swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(Update);
            }
        }, 1000, time * 1000);
    }

}