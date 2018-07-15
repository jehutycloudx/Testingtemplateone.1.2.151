package com.templateonetwo.testingtemplateonetwo;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.templateonetwo.testingtemplateonetwo.Models.ConsumerPostModel;
import com.templateonetwo.testingtemplateonetwo.Models.UserModel;
import com.templateonetwo.testingtemplateonetwo.Utils.CommonUtils;
import com.templateonetwo.testingtemplateonetwo.Utils.FragmentDataReceive;
import com.templateonetwo.testingtemplateonetwo.Utils.FragmentModelDataPasssing;
import com.templateonetwo.testingtemplateonetwo.Utils.SessionManager;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

import static android.text.TextUtils.isEmpty;

public class Fragment4_B3 extends android.support.v4.app.Fragment implements Fragment1.OnVideoSelectedLister, Fragment1.OnPhotoSelectedLister, AdapterView.OnItemSelectedListener, Fragment4_B1.OnProjectTitleSetListener,FragmentDataReceive {


    protected FirebaseStorage storage;
    //change the url according to your firebase app
    protected StorageReference storageRef;
    // ...creating refreence to firebase database
    protected DatabaseReference mDatabase;
    private static final String Tag4b3 = "Fragment4_B3";
    private Button btnbacktoNavFrag4b2;
    private Button btnPost;
    ConsumerPostModel mConsumerPostModel;
    UserModel mUserModel;
    SessionManager mSessionManager;

    public TextView mTimefield3;
    public TextView mDatefield3;
    public TextView mLocationfield3;
    public TextView mCategoryfield3;
    public TextView mProjecttitle3;
    public TextView mDescription3;
    public TextView mSwitch3;

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



/*below is auto-generate code from right clicking and inserting 'OnCreateView', this method
    is specific to Fragments vs. 'OnCreate' which is just for activities.
    You also have to create View objects and return at the bottom of onCreateView
     Deleted the 'super return...' code line */

    //4:28 Classifieds: Compressing images in Android


    private void uploadNewphoto(Bitmap bmp){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
      //  mDatabase.child("posts").child(mUserModel.getUserKey()).child(mDatabase.push().getKey()).setValue(mConsumerPostModel);
        final StorageReference childRef = storageRef.child(mUserModel.getUserKey()).child("photo").child(mDatabase.push().getKey());
        UploadTask uploadTask = childRef.putBytes(byteArray);
        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }


              return childRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    mConsumerPostModel.photoUrl=downloadUri.toString();
                    mConsumerPostModel.videoUrl="";
                    mDatabase.child("posts").child(mUserModel.getUserKey()).child(mDatabase.push().getKey()).setValue(mConsumerPostModel);
                    Toast.makeText(getActivity(), "Post Uploaded Successfully", Toast.LENGTH_SHORT).show();
                } else {

                }
            }
        });



    }

    void uploadNewVideo(Uri videoUri)
    {

        //  mDatabase.child("posts").child(mUserModel.getUserKey()).child(mDatabase.push().getKey()).setValue(mConsumerPostModel);
        final StorageReference childRef = storageRef.child(mUserModel.getUserKey()).child("video").child(mDatabase.push().getKey());
        UploadTask uploadTask = childRef.putFile(videoUri);
        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }


                return childRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    mConsumerPostModel.videoUrl=downloadUri.toString();
                    mConsumerPostModel.photoUrl="";
                    mDatabase.child("posts").child(mUserModel.getUserKey()).child(mDatabase.push().getKey()).setValue(mConsumerPostModel);
                    Toast.makeText(getActivity(), "Post Uploaded Successfully", Toast.LENGTH_SHORT).show();
                } else {

                }
            }
        });



    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment4_b3_layout_video_path, container, false);
        mSessionManager=SessionManager.NewInstance(getActivity());
        mUserModel=mSessionManager.getProfileData(getActivity());
        mProjecttitle3 = (TextView) view.findViewById(R.id.titleProjectName3);
        mDescription3 = (TextView) view.findViewById(R.id.title_field3);

        mTimefield3 = (TextView) view.findViewById(R.id.TimePostText);
        mDatefield3 = (TextView) view.findViewById(R.id.DatePostText);
        mLocationfield3 = (TextView) view.findViewById(R.id.LocationPostText);
        mCategoryfield3 = (TextView) view.findViewById(R.id.CategoryPostText);
        mSwitch3 = (TextView) view.findViewById(R.id.SwitchValuefrag3);

        btnbacktoNavFrag4b2 = (Button) view.findViewById(R.id.btnbackto4b2);
        btnPost = (Button) view.findViewById(R.id.btnPost);


        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();


        //Databse Intialization...
        mDatabase = FirebaseDatabase.getInstance().getReference();



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
                Log.d(Tag4b3, "onClick: Attempting to post...");
                if(!isEmpty(mProjecttitle3.getText().toString())
                   && !isEmpty(mDescription3.getText().toString())
                   && !isEmpty(mCategoryfield3.getText().toString())
                   && !isEmpty(mLocationfield3.getText().toString())
                   && !isEmpty(mDatefield3.getText().toString())
                   && !isEmpty(mTimefield3.getText().toString())) {

                    //we have a Uri and no bitmap
                    Fragment1.OnPhotoSelectedLister onPhotoSelectedLister = (Fragment1.OnPhotoSelectedLister) getActivity();
                    Fragment1.OnVideoSelectedLister onVideoSelectedLister = (Fragment1.OnVideoSelectedLister) getActivity();

                    if (onVideoSelectedLister.setVideopath() == null)
                    {


                    uploadNewphoto(onPhotoSelectedLister.setImageBitmap());



                }
                //we have no Uri and a bitmap
                else  {
                    uploadNewVideo(onVideoSelectedLister.setVideopath());
                    /*setVideopath and setImagePath may need to be fixed
                    as they may not refer to the right data being stored for
                    transfer*/
                }
            }

            else{
                Toast.makeText(getActivity(), "You must fill out all the fields", Toast.LENGTH_SHORT).show();



                }
            }

        });







     /* Image and Video placement, for review, should be same as fragment 1 and is placed
           as same time as fragment 1*////////////////////
////////////////////////////////////////////////////////////////////////

        result_video = (VideoView) view.findViewById(R.id.videoView3);
        secondimage = (ImageView) view.findViewById(R.id.imageView3);

        Fragment1.OnPhotoSelectedLister onPhotoSelectedLister = (Fragment1.OnPhotoSelectedLister) getActivity();
         Fragment1.OnVideoSelectedLister onVideoSelectedLister = (Fragment1.OnVideoSelectedLister) getActivity();

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

    @Override
    public void onResume() {
        super.onResume();
      //  FragmentModelDataPasssing fragmentModelDataPasssing= CommonUtils.getFragmentData(getActivity());

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void receiveData(FragmentModelDataPasssing fragmentModelDataPasssing) {
        if(fragmentModelDataPasssing!=null) {
            mProjecttitle3.setText(fragmentModelDataPasssing.getTitle());
            mDescription3.setText(fragmentModelDataPasssing.getDescription());
            mCategoryfield3.setText(fragmentModelDataPasssing.getCategory());
            mDatefield3.setText(fragmentModelDataPasssing.getDate());
            mLocationfield3.setText(fragmentModelDataPasssing.getLocation());
            mTimefield3.setText(fragmentModelDataPasssing.getTime());

            mConsumerPostModel=new ConsumerPostModel();
            mConsumerPostModel.category=fragmentModelDataPasssing.getCategory();
            mConsumerPostModel.date=fragmentModelDataPasssing.getDate();
            mConsumerPostModel.description=fragmentModelDataPasssing.getDescription();
            mConsumerPostModel.location=fragmentModelDataPasssing.getLocation();
            mConsumerPostModel.time=fragmentModelDataPasssing.getTime();
            mConsumerPostModel.projectTitle=fragmentModelDataPasssing.getTitle();
            mConsumerPostModel.specific=fragmentModelDataPasssing.getSwitchValue();


         // mSwitch3.setText(fragmentModelDataPasssing.getSwitchValue());
        }
    }
}



/* extra junk code to test/play with
        Bitmap bmThumbnail = createVideoThumbnail(String.valueOf(mOnVideoSelectedLister), MediaStore.Images.Thumbnails.MINI_KIND);
        /*   viewImage.setImageBitmap(bmThumbnail); */

