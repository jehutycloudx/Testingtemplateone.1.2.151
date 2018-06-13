package com.templateonetwo.testingtemplateonetwo;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.templateonetwo.testingtemplateonetwo.R;

public class UniversalImageLoader {

    private static final int defaultimage = R.drawable.ic_android_black_14dp;
    private Context mContext;

    public UniversalImageLoader(Context context) {
        mContext = context;
    }

    public ImageLoaderConfiguration getConfig() {

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(defaultimage)
                .showImageForEmptyUri(defaultimage)
                .showImageOnFail(defaultimage)
                .cacheOnDisk(true).cacheInMemory(true)
                .cacheOnDisk(true).resetViewBeforeLoading(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

            ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(mContext)
                    .defaultDisplayImageOptions(defaultOptions)
                    .memoryCache(new WeakMemoryCache())
                    .diskCacheSize(100 * 1024 * 1024).build();

            return configuration;

        }

        /* This method can be used to set images that are static. It can't be used if the images
        are being changed in the Fragment/Activity - OR if they are being set in a list or
        a grid

        *@param imgURL
        *@param image
        *@param mProgressBar
        * param append
        */



            //*below method is to only set a SINGLE IMAGE, will not work with multiple *//
            /*using 'append' as one of the parameters for the method below so that you can interchage various files types
            as an image, i.e. from website http://, from Bitmap, etc. */

        public static void setImage(String imgURL, ImageView image, final ProgressBar mProgressBar, String append){
            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.displayImage(append + imgURL, image, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {
                    if (mProgressBar!= null){
                        mProgressBar.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    if (mProgressBar != null) {
                        mProgressBar.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    if (mProgressBar != null) {
                        mProgressBar.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {
                    if (mProgressBar != null) {
                        mProgressBar.setVisibility(View.GONE);
                    }
                }
            });

        }





}


