package com.templateonetwo.testingtemplateonetwo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;


public class Fragment1 extends android.support.v4.app.Fragment {
    private static final String Tag = "Fragment1";

    public static final int VIDEO_FILE_REQUEST_CODE = 1111;
    private static final int CAMERA_FILE_REQUEST_CODE = 2222;
    private static final int PICK_FILE_REQUEST_CODE = 3333;

    /*Need interface to interact with Activityresults from camera photo/video and media upload */
    /*Lecture, 'Selecting an Image for Uploading' - Android Classifieds... */
   
    public interface OnPhotoSelectedLister {
       void getImagePath(Uri imagePath);
       void getImageBitmap(Bitmap bitmap);
       
        Bitmap setImagePath();
        Bitmap setImageBitmap();

       }

    public interface OnVideoSelectedLister {
        Uri setVideopath();  //?????? maybe video is data here?
        void getVideopath(Uri data);

         }

    OnPhotoSelectedLister mOnPhotoSelectedLister;  //*maybe there is a video version of this*//
    OnVideoSelectedLister mOnVideoSelectedLister;

    public static VideoView mVideoview; /*just added this to make code work below */


    private Button btnNavFrag1;
    private Button btnNavFrag2;
    private Button btnNavFrag3;
    private Button btnNavFrag4;
    private Button btnNavFrag4b;
    private Button btnNavSecondActivity;
    private Button btnCameraVideo;
    private Button btnCameraImage;
    private Button btnMediaUpload;


    public Bitmap bitmapimage;
    public static final String TAG4b2 = "com.templateonetwo.testingtemplateonetwo.fragment4_b2";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            mOnPhotoSelectedLister = (OnPhotoSelectedLister) getActivity();
            mOnVideoSelectedLister = (OnVideoSelectedLister) getActivity();
            Log.d(Tag, "Checking2" + mOnPhotoSelectedLister + mOnVideoSelectedLister);

        } catch (ClassCastException e) {
            Log.e(Tag, "OnAttach: ClassCastException: " + e.getMessage());
    }

    }

    /*below is auto-generate code from right clicking and inserting 'OnCreateView', this method
        is specific to Fragments vs. 'OnCreate' which is just for activities.
        You also have to create View objects and return at the bottom of onCreateView
         Deleted the 'super return...' code line */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1_layout, container, false);
        final View view2 = inflater.inflate(R.layout.fragment2_layout, container, false);
        View view3 = inflater.inflate(R.layout.fragment3_layout, container, false);
        View view4a1 = inflater.inflate(R.layout.fragment4_a1_layout_image_path, container, false);
        View view4b1 = inflater.inflate(R.layout.fragment4_b1_layout_video_path, container, false);
        btnNavFrag1 = (Button) view.findViewById(R.id.btnNavFrag1); /*must use 'view' for fragments as opposed to not specifying like for normal activity */
        btnNavFrag2 = (Button) view.findViewById(R.id.btnNavFrag2);
        btnNavFrag3 = (Button) view.findViewById(R.id.btnNavFrag3);
        btnNavFrag4 = (Button) view.findViewById(R.id.btnNavFrag4);
        btnNavFrag4b = (Button) view.findViewById(R.id.btnNavFrag4b1);
        btnNavSecondActivity = (Button) view.findViewById(R.id.btnNavSecondActivity);
        btnCameraVideo = (Button) view.findViewById(R.id.btnCameraVideo);
        btnCameraImage = (Button) view.findViewById(R.id.btnCameraImage);
        btnMediaUpload = (Button) view.findViewById(R.id.btnMediaUpload);


        Log.d(Tag, "onCreateView: started.");

            /*For fragments, contextually, you are "in" an activity already, so you don't traditionally
              navigate to other 'activities', you have to 'getActivity'as seen below*/

        btnNavFrag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Going to Fragment 1", Toast.LENGTH_SHORT).show();

                ((MainActivity) getActivity()).setViewPager(0);
                     /*^^This code will allow you to access any method from the MainActivity, assuming
                      it is set to public. Also you set 'ViewPager' to 'int', so you must
                      follow android logic and put '0' as that references the starting page*/
            }
        });

        btnNavFrag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Going to Fragment 2", Toast.LENGTH_SHORT).show();
                ((MainActivity) getActivity()).setViewPager(1);
            }
        });

        btnNavFrag3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Going to Fragment 3", Toast.LENGTH_SHORT).show();
                ((MainActivity) getActivity()).setViewPager(2);
            }
        });

        btnNavFrag4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Going to Fragment 4a1", Toast.LENGTH_SHORT).show();
                ((MainActivity) getActivity()).setViewPager(3);
            }
        });

        btnNavFrag4b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view4) {
                Toast.makeText(getActivity(), "Going to Fragment 4b", Toast.LENGTH_SHORT).show();
                ((MainActivity) getActivity()).setViewPager(4);
            }
        });


        btnNavSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Going to Second Activity", Toast.LENGTH_SHORT).show();
                /* Use 'Intent' here since this is a activity you wish to navigate from*/
                Intent intent = new Intent(getActivity(), SecondActivity.class);
                startActivity(intent);
            }
        });

        btnCameraVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Using Camera Video", Toast.LENGTH_SHORT).show();
                /* Use 'Intent' here since this is a activity you wish to navigate from*/
                Intent intentCamVideo = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(intentCamVideo, VIDEO_FILE_REQUEST_CODE);
                /*Attempting to try this out*/
               // setTargetFragment(new Fragment4_B1(), VIDEO_FILE_REQUEST_CODE);

            }

        });

        btnCameraImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Using Camera Image", Toast.LENGTH_SHORT).show();
                /* Use 'Intent' here since this is a activity you wish to navigate from*/
                Intent intentCamImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intentCamImage, CAMERA_FILE_REQUEST_CODE);
                /*testing*/

                //setTargetFragment(new Fragment4_B1(), 5); /*based on video, the integer here doesnt not have to equal the CAMERA_FILE_REQUEST_CODE, it can be a random integer as you are sending this data to next frag*/

                //     setTargetFragment(null, 5);

            }
        });

        btnMediaUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Accessing Gallery", Toast.LENGTH_SHORT).show();
                /* Use 'Intent' here since this is a activity you wish to navigate from*/
                Intent intentMediaUpload = new Intent(Intent.ACTION_GET_CONTENT);
                intentMediaUpload.setType("image/*");   /* this may need to be modified to add video as well */
                startActivityForResult(intentMediaUpload, PICK_FILE_REQUEST_CODE);

            }
        });

        return view;

    }

    Fragment mFragment4B1 = new Fragment4_B1();

////////////*OnActivityResult*///////////////////////

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /* Results when selecting a new image from memory*/

        if (requestCode == PICK_FILE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri selectedImageUri = data.getData();
            Log.d(Tag, "onActivityResult image uri:, Uploading Media from Gallery " + selectedImageUri);

            //Send the uri to PostFragment or Posting page/area

            if (mOnPhotoSelectedLister != null && mOnVideoSelectedLister !=null)
            mOnPhotoSelectedLister.getImagePath(selectedImageUri);
            MainActivity mainActivity=(MainActivity)getActivity();
            mainActivity.gotoFragment(4);
           // fragmentx.setTargetFragment(this, requestCode);
            //      Bitmap mBitmapView = (Bitmap) <unknown>.findViewById(R.id.bmThumbnail);
            /*or try setting here*/  //setTargetFragment(fragmentx,PICK_FILE_REQUEST_CODE);

         }

        /* Results when taking a new photo/image from Camera*/

        else if (requestCode == CAMERA_FILE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Log.d(Tag, "onActivity Result: done taking new photo");
            Bitmap bitmapimage;
            bitmapimage = (Bitmap) data.getExtras().get("data");
            mOnPhotoSelectedLister.getImageBitmap(bitmapimage);
            mOnVideoSelectedLister.getVideopath(null);
            MainActivity mainActivity=(MainActivity)getActivity();
            mainActivity.gotoFragment(4);

            //Send the bitmapimage to PostFragment or Posting page/area
           if (mOnPhotoSelectedLister != null)
            mOnPhotoSelectedLister.getImageBitmap(bitmapimage);
          //  fragmentx.setTargetFragment(this, requestCode);
            /*or try setting here*/ // setTargetFragment(fragmentx,CAMERA_FILE_REQUEST_CODE);

            //  ImageView mImageView = (ImageView)
            //bitmapimage = (Bitmap) view4b1.findViewById(R.id.bmThumbnail);

            /* Or..try sending Bitmap to proper referenced object as mentinoed in stackoverflow*/
        }

        /* Results when taking a new video from Camera, filled in presumptions based on Android documentation*/
        /*Most likely will have to change as how video is handled here*/
        /*followed this: https://developer.android.com/training/camera/videobasics*/
        //start 'Camera Storage Permission Lecture'
        else if (requestCode == VIDEO_FILE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri videoUri = data.getData();
//            mVideoview.setVideoURI(videoUri);
            mOnVideoSelectedLister.getVideopath(videoUri);
            MainActivity mainActivity=(MainActivity)getActivity();
            mainActivity.gotoFragment(4);

            Log.d(Tag, "onActivity Result: done taking new video");
            //Send the videoUri to PostFragment or Posting page/area
            if (mOnVideoSelectedLister != null)
            mOnVideoSelectedLister.getVideopath(videoUri);
            /*or try setting here*/ //  setTargetFragment(fragmentx,CAMERA_FILE_REQUEST_CODE);


            // VideoView videoView = (VideoView) mVideoview.findViewById(R.id.videoView2);


        }
    }


    /*Below is referencing Selecting an Image for Uploading - Androids Classifieds App*/
    @Override
    public void onAttach(Context context) {
        try {
            mOnPhotoSelectedLister = (OnPhotoSelectedLister) getTargetFragment();
            mOnVideoSelectedLister = (OnVideoSelectedLister) getTargetFragment();
            Log.d(Tag, "Checking" + mOnPhotoSelectedLister + mOnVideoSelectedLister);

        } catch (ClassCastException e) {
            Log.e(Tag, "OnAttach: ClassCastException: " + e.getMessage());
        }
        {

        }

        super.onAttach(context);

        /* just testing stuff */

        // https://stackoverflow.com/questions/10436120/failure-saving-state-target-not-in-fragment-manager-settargetfragment?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa

    }

}

