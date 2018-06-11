package com.templateonetwo.testingtemplateonetwo;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment4_B3 extends android.support.v4.app.Fragment implements Fragment1.OnVideoSelectedLister, Fragment1.OnPhotoSelectedLister, AdapterView.OnItemSelectedListener {



    private static final String Tag4b3 = "Fragment4_B3";
    private Button btnbacktoNavFrag4b2;
    private Button btnPost;

    public TextView mTimefield3;
    public TextView mDatefield3;
    public TextView mLocationfield3;
    public TextView mCategoryfield3;
    public TextView mProjecttitle3;
    public TextView mDescription3;


      public Fragment4_B3() {}

    String[] CategoryNames={"General Fix", "Appliances","Brick, Concrete, & Stone", "Cleaning", "Drywall",
            "Electric", "Flooring", "Garage", "Junk Removal", "Kitchen",
            "Heating & Cooling", "Lawn & Yard work", "Painting",
            "Plumbing & Bathroom", "Remodeling", "Roofing & Gutters", "Siding",
            "Windows", "Other" };

    /*below is auto-generate code from right clicking and inserting 'OnCreateView', this method
    is specific to Fragments vs. 'OnCreate' which is just for activities.
    You also have to create View objects and return at the bottom of onCreateView
     Deleted the 'super return...' code line */



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
         final View view = inflater.inflate(R.layout.fragment4_b3_layout_video_path, container, false);

        mProjecttitle3 = (TextView) view.findViewById(R.id.titleProjectName3);
        mDescription3 = (TextView) view.findViewById(R.id.textViewDescription3);

        mTimefield3 = (TextView) view.findViewById(R.id.TimePostText);
        mDatefield3 = (TextView) view.findViewById(R.id.DatePostText);
        mLocationfield3 = (TextView) view.findViewById(R.id.LocationPostText);
        mCategoryfield3 = (TextView) view.findViewById(R.id.CategoryPostText);

        btnbacktoNavFrag4b2 = (Button) view.findViewById(R.id.btnbackto4b2);
        btnPost = (Button) view.findViewById(R.id.btnPost);



        Log.d(Tag4b3, "onCreateView: started.");



    /*For fragments, contextually, you are "in" an activity already, so you don't traditionally
      navigate to other 'activities', you have to 'getActivity'as seen below*/

    //* button 4b placeholder *//
        btnbacktoNavFrag4b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Going to Fragment 4B2", Toast.LENGTH_SHORT).show();
                ((MainActivity) getActivity()).setViewPager(5);
            }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Going to Fragment 4B3", Toast.LENGTH_SHORT).show();
                ((MainActivity) getActivity()).setViewPager(7);
            }

        });

            return view;

            }

/*Needed for this class to implement spinner class methods in order to work*/
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

/*configuration for camcorder setup 2, this code is if camera activity will happen on same screen, so am temporarily disabling
      as we need the code from Fragement 1 which is where camera activity is located */
  /*  @Override
    public void onActivityResult (int requestCode, int resultCode, Intent getVideopath){
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Uri videoUri = getVideopath.getData(); /*this '.getData' function is just going to fetch where the data was stored*/
//            result_video.setVideoURI(videoUri);
//            Intent videoIntent = getVideopath;


        /*configuration for camcorder setup */
        /*configuration for camcorder setup 2, this code is if camera activity will happen on same screen, so am temporarily disabling
      as we need the code from Fragement 1 which is where camera activity is located */
//        mPlayButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {

//            }
//        });
//    }




    @Override
    public void getImagePath(Uri imagePath) {
    }

    @Override
    public void getImageBitmap(Bitmap bitmap) {
    }

    @Override
    public Uri setImagePath() {
        return null;
    }

    @Override
    public Bitmap setImageBitmap() {
        return null;
    }

    @Override
    public Uri getVideopath() {
        return null;
    }

    @Override
    public void setVideopath(Uri data) {

    }
}



/* extra junk code to test/play with
        Bitmap bmThumbnail = createVideoThumbnail(String.valueOf(mOnVideoSelectedLister), MediaStore.Images.Thumbnails.MINI_KIND);
        /*   viewImage.setImageBitmap(bmThumbnail); */

