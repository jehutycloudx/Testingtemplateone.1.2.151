package com.templateonetwo.testingtemplateonetwo;

import android.app.DatePickerDialog;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.templateonetwo.testingtemplateonetwo.Models.ConsumerPostModel;
import com.templateonetwo.testingtemplateonetwo.Utils.CommonUtils;
import com.templateonetwo.testingtemplateonetwo.Utils.FragmentModelDataPasssing;


import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Fragment4_B2 extends android.support.v4.app.Fragment implements Fragment1.OnVideoSelectedLister, Fragment1.OnPhotoSelectedLister, AdapterView.OnItemSelectedListener, Fragment1.OnTextSelectedLister,
        View.OnClickListener, LocationListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, Fragment4_B1.OnProjectTitleSetListener{



    private static final String Tag4b2 = "Fragment4_B2";
    private Button btnNavFrag4b2;
    private Button btnNexttoReview; //getAddress;
    private ImageView getAddress;
    EditText mLocation;
    ConsumerPostModel mConsumerPostModel;


    public Switch mSwitch;
    public TextView mTimefield;

    public TextView mDatefield;
    public DatePickerDialog.OnDateSetListener mOnDateSetListener;

    private static final long INTERVAL = 1000 * 10;
    private static final long FASTEST_INTERVAL = 1000 * 5;
    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    Location mCurrentLocation;
   final int MY_PERMISSIONS_ACCESS_FINE_LOCATION=7;


    public Fragment4_B2() {}

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
        View view = inflater.inflate(R.layout.fragment4_b2_layout_video_path, container, false);

        btnNavFrag4b2 = (Button) view.findViewById(R.id.btnNavFrag4b2);
        btnNexttoReview = (Button) view.findViewById(R.id.Next_ToReview);
        mTimefield = (TextView) view.findViewById(R.id.Timefield);
        mDatefield = (TextView) view.findViewById(R.id.Datefield);
        getAddress =(ImageView)view.findViewById(R.id.retrievelocationButton);
        mLocation =(EditText)view.findViewById(R.id.editText5);
        mSwitch = (Switch)view.findViewById(R.id.SwitchValuefrag3);
        getAddress.setOnClickListener(this);
        mDatefield.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar mcalendar = Calendar.getInstance();
                int year = mcalendar.get(Calendar.YEAR);
                int month = mcalendar.get(Calendar.MONTH);
                int day = mcalendar.get(Calendar.DAY_OF_MONTH);
         //       Fragment Fragment4b2 = getActivity().getFragmentManager().findFragmentByTag("Fragment4_B2");
               // Fragment Fragment4b3 =  getSupportFragmentManager().findFragmentByTag("Fragment4_B3");//*setTargetFragment(Fragment4_B2.this.getClass(), 55)*/
                /*^^both might be a way to reference specific fragments (which is based from MainActivity) in the future   */


                DatePickerDialog dateDialog = new DatePickerDialog(

                          getContext(),   /*Eureka!!, this seems to work, but still need to learn more about this*/
                   //     setTargetFragment(Fragment3.this, 3),
                     //   (Fragment4_B2)getActivity().getSupportFragmentManager().getFragment(MainActivity,)
                        android.R.style.Theme_DeviceDefault_InputMethod,
                        mOnDateSetListener,
                        year, month, day);

                dateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));
                dateDialog.show();
            }
/*Theme_Holo_Dialog_MinWidth*/
        });

        mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1; /*Since Jan = 0, Dec = 11, etc. so we have to fix due to Java */
            Log.d(Tag4b2, "onDataSet: mm/dd/yyyy: " + month + "/" + dayOfMonth + "/" + year);
            String datefield4b2 = month + "/" + dayOfMonth + "/" + year;
            mDatefield.setText(datefield4b2);
            }
        };


        Log.d(Tag4b2, "onCreateView: started.");



    /*For fragments, contextually, you are "in" an activity already, so you don't traditionally
      navigate to other 'activities', you have to 'getActivity'as seen below*/

    //* button 4b placeholder *//
        btnNavFrag4b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Going to Fragment 4B1", Toast.LENGTH_SHORT).show();
                ((MainActivity) getActivity()).setViewPager(4);
            }
        });

        btnNexttoReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Going to Fragment 4B3", Toast.LENGTH_SHORT).show();
                FragmentModelDataPasssing fragmentModelDataPasssing= CommonUtils.getFragmentData(getActivity());
                fragmentModelDataPasssing.setDate(mDatefield.getText().toString());
                fragmentModelDataPasssing.setLocation(mLocation.getText().toString());
                fragmentModelDataPasssing.setTime(mTimefield.getText().toString());
            //  fragmentModelDataPasssing.setSwitchValue(mSwitch.getText().toString());
                CommonUtils.saveFragmentData(getActivity(),fragmentModelDataPasssing);

                ((MainActivity) getActivity()).setViewPager(6);
                ((MainActivity) getActivity()).passFragmentdata(fragmentModelDataPasssing);
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

  //  @Override
    //public Uri getImageBitmap() {
      //  return null;     /*6/11, class code was underlined at top in red, added this method to make code work */
    //}

    @Override
    public Uri setVideopath() {
        return null;
    }

    @Override
    public void getVideopath(Uri data) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.retrievelocationButton:
                checkLocationPermissions();/*this code is for location button, change the id to be more descriptive*/
                break;
        }
    }


/////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////All code below is for location methods/callback/////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createLocationRequest();
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();

        mGoogleApiClient.disconnect();

    }

    protected void createLocationRequest() {
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setInterval(INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        startLocationUpdates();
    }

    protected void startLocationUpdates() {
        PendingResult<Status> pendingResult = LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);


        /*This will need to check for permission, may be red but will still work, put in code to check
          location permissions. */

    }



    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
         mCurrentLocation=location;
    }

    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this);
    }

    void checkLocationPermissions() {

        if (ContextCompat.checkSelfPermission(getActivity(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {

                Snackbar.make(getView(), "You need to grant permission to access location to this app to be able to fetch current location", Snackbar.LENGTH_LONG).show();
                requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_ACCESS_FINE_LOCATION);

            } else {
                // No explanation needed, we can request the permission.

                requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_ACCESS_FINE_LOCATION);

                // MY_PERMISSIONS_ACCESS_FINE_LOCATIONis an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
        else
        {
            getCurrentLocation();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Snackbar.make(getView(), "Getting location", Snackbar.LENGTH_LONG).show();
                    if (mGoogleApiClient.isConnected())
                    {
                        startLocationUpdates();
                    }
                    else
                    {
                        mGoogleApiClient.connect();
                    }
                } else {
                    Toast.makeText(getActivity(),"You cannot track the current location without giving access location permission",Toast.LENGTH_LONG).show();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    void getCurrentLocation()
    {
       if(mCurrentLocation!=null)
       {
           Geocoder geocoder;
           List<Address> addresses;
           geocoder = new Geocoder(getActivity(), Locale.getDefault());
           Toast.makeText(getActivity(),"Retrieving Location",Toast.LENGTH_LONG).show();
           try {
               addresses = geocoder.getFromLocation(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5


           String addressa = addresses.get(0).getAddressLine(0); // If any additional mLocation line present than only, check with max available mLocation lines by getMaxAddressLineIndex()
           String city = addresses.get(0).getLocality();
           String state = addresses.get(0).getAdminArea();
           String country = addresses.get(0).getCountryName();
           String postalCode = addresses.get(0).getPostalCode();
           String knownName = addresses.get(0).getFeatureName();
           mLocation.setText(addressa);
           } catch (IOException e) {
               e.printStackTrace();
           }

       }
    }

    @Override
    public void getTextpath(String stringpath1) {

    }

    @Override
    public String setTextpath() {
        return null;
    }

    @Override
    public void setProjectTitle(String projectTitle) {

    }
}



/* extra junk code to test/play with
        Bitmap bmThumbnail = createVideoThumbnail(String.valueOf(mOnVideoSelectedLister), MediaStore.Images.Thumbnails.MINI_KIND);
        /*   viewImage.setImageBitmap(bmThumbnail); */

