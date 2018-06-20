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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class Fragment4_B3 extends android.support.v4.app.Fragment implements Fragment1.OnVideoSelectedLister, Fragment1.OnPhotoSelectedLister, AdapterView.OnItemSelectedListener, Fragment4_B1.OnProjectTitleSetListener {



    private static final String Tag4b3 = "Fragment4_B3";
    private Button btnbacktoNavFrag4b2;
    private Button btnPost;

    public TextView mTimefield3;
    public TextView mDatefield3;
    public TextView mLocationfield3;
    public TextView mCategoryfield3;
    public TextView mProjecttitle3;
    public TextView mDescription3;

    public VideoView result_video;
    public ImageView secondimage;
    private Uri m3ImageUri;
    private Bitmap m3Bitmap;

    private String mIncomingMessage_ProjectTitle = "";


      public Fragment4_B3() {}

    String[] CategoryNames={"General Fix", "Appliances","Brick, Concrete, & Stone", "Cleaning", "Drywall",
            "Electric", "Flooring", "Garage", "Junk Removal", "Kitchen",
            "Heating & Cooling", "Lawn & Yard work", "Painting",
            "Plumbing & Bathroom", "Remodeling", "Roofing & Gutters", "Siding",
            "Windows", "Other" };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mIncomingMessage_ProjectTitle = bundle.getString(getString(R.string.project_title_optional));


        }


    }


/*below is auto-generate code from right clicking and inserting 'OnCreateView', this method
    is specific to Fragments vs. 'OnCreate' which is just for activities.
    You also have to create View objects and return at the bottom of onCreateView
     Deleted the 'super return...' code line */



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment4_b3_layout_video_path, container, false);

        mProjecttitle3 = (TextView) view.findViewById(R.id.titleProjectName3);
        mProjecttitle3.setVisibility(View.GONE);
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

     /* Image and Video placement, for review, should be same as fragment 1 and is placed
           as same time as fragment 1*////////////////////
////////////////////////////////////////////////////////////////////////

        result_video = (VideoView) view.findViewById(R.id.videoView3);
        secondimage = (ImageView) view.findViewById(R.id.imageView3);

        Fragment1.OnPhotoSelectedLister onPhotoSelectedLister = (Fragment1.OnPhotoSelectedLister) getActivity();
        final Fragment1.OnVideoSelectedLister onVideoSelectedLister = (Fragment1.OnVideoSelectedLister) getActivity();

        if (onVideoSelectedLister.setVideopath() == null)
            //  bitmapthumbnail.setImageBitmap(onPhotoSelectedLister.setImageBitmap());
            secondimage.setImageBitmap(onPhotoSelectedLister.setImageBitmap());


        else  {
            Uri Viduri = onVideoSelectedLister.setVideopath();
                result_video.setVideoURI(Viduri); /**did change yet from setVideoURI to GetvideoURI*/
         }

             Log.d(Tag4b3, "onCreateView: started.");


        result_video.setOnClickListener(new View.OnClickListener() {
            /*you can replace result_video with mPlaybutton*/

            @Override
            public void onClick(View v) {

                result_video.start();
              //  secondimage.setVisibility(View.INVISIBLE);

            }
        });




//        setIncomingMessage_ProjectTitle();
        /* */////////////////////////////


        return view;
    }
/*May need to use 'onEditerActionListener' or some other listener to set text on 4b3 from 4b1 when typed...usually you use
  onClickListener but...it would make more since if you had seperate fragments instead of a
    viewpager...that way, when you click 'Next', the data could be passed through via onClick, but
    regardless, we will try another method to cope*/


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


    /* This code is to set the incoming message from Frag4b1 to be placed in Frag4b3,
       need to setup a method*/

 //   private void setIncomingMessage_ProjectTitle(){
 //       if(!mIncomingMessage_ProjectTitle.equals("")){
 //           mProjecttitle3.setText(mIncomingMessage_ProjectTitle);
 //       }
 //   }

    public void updateinfoPT(String name){
        mProjecttitle3.setText(name);
        mProjecttitle3.setVisibility(View.VISIBLE);
    }


    @Override
    public void getImagePath(Uri imagePath) {
        Log.d(Tag4b3, "setting image to imageview bitmapthumbnail");

        secondimage.setImageURI(imagePath);
        m3Bitmap = null;
        m3ImageUri = imagePath;
        // setTargetFragment(this, 104);

    }

    @Override
    public void getImageBitmap(Bitmap bitmap) {
        Log.d(Tag4b3, "setting image from gallery to bitmapthumbnail");

        secondimage.setImageBitmap(bitmap);
        m3ImageUri = null;
        m3Bitmap = bitmap;

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
    public Uri setVideopath() {
        return null;
    }

    @Override
    public void getVideopath(Uri data) {

    }

    @Override
    public void setProjectTitle(String projectTitle) {

    }
}



/* extra junk code to test/play with
        Bitmap bmThumbnail = createVideoThumbnail(String.valueOf(mOnVideoSelectedLister), MediaStore.Images.Thumbnails.MINI_KIND);
        /*   viewImage.setImageBitmap(bmThumbnail); */

