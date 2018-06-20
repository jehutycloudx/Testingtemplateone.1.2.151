package com.templateonetwo.testingtemplateonetwo.ServiceProviderPackage;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.templateonetwo.testingtemplateonetwo.Activity_Chat;
import com.templateonetwo.testingtemplateonetwo.Activity_Current_Listings;
import com.templateonetwo.testingtemplateonetwo.Activity_Find_Contractors;
import com.templateonetwo.testingtemplateonetwo.Activity_Settings;
import com.templateonetwo.testingtemplateonetwo.BottomNavigationViewHelper;
import com.templateonetwo.testingtemplateonetwo.Fragment1;
import com.templateonetwo.testingtemplateonetwo.Fragment2;
import com.templateonetwo.testingtemplateonetwo.Fragment3;
import com.templateonetwo.testingtemplateonetwo.Fragment4_A1;
import com.templateonetwo.testingtemplateonetwo.Fragment4_B1;
import com.templateonetwo.testingtemplateonetwo.Fragment4_B2;
import com.templateonetwo.testingtemplateonetwo.Fragment4_B3;
import com.templateonetwo.testingtemplateonetwo.R;
import com.templateonetwo.testingtemplateonetwo.Utils.SectionsStatePagerAdapter;
import com.templateonetwo.testingtemplateonetwo.Utils.UniversalImageLoader;


public  class MainActivity2_ServiceProvider extends AppCompatActivity implements Fragment1.OnPhotoSelectedLister,Fragment1.OnVideoSelectedLister,Fragment1.OnTextSelectedLister, Fragment4_B1.OnProjectTitleSetListener {

    /*This class serves as the Main Activity for the Service Provider User Path*/


    private static final String TAG = "MainActivity_Provider";
    private static final int REQUEST_CODE_P = 124;
    private static final int ACTIVITY_NUM = 2;
    private Context mContext = MainActivity2_ServiceProvider.this;
    private TextView mToolbarTitle;


    Uri uri;
    Uri mUri; /*just adding this to make the below code work for interface*/
    Bitmap mBitmap;
    String mString;

    private SectionsStatePagerAdapter mSectionsStatePagerAdapter;
    private ViewPager mViewPager; /*created in activity main XML all the way at the bottom*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p_activity_main_provider);
        Log.d(TAG, "onCreate: Started.");
        mToolbarTitle = findViewById(R.id.profilenameTitletoolbar);

        initImageLoader();

        mSectionsStatePagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        /* create view pager object above ^^ as that is what we will be referencing*/

        mViewPager = (ViewPager) findViewById(R.id.container);
        verifyPermissions();
        /* setupViewPager(mViewPager);   --->this code is removed and replaced with 'verifyPermissions();'
        above as we want to have the permissions be requested before anything else is setup in app.
        the vairable mViewPager is still left as is.*/

        /* reference the container as this is what we will be swapping the fragments out of*/
        //setup the pager below

        /*code below is to specifically disable shiftmode on bottom nav bar*/
        BottomNavigationView bottomNavigationView2 = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar2);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView2);

        /*simple code to fix the highlighting of the appropriate bottom nav icon when tapped, menu item index is set to '0' for first icon*/
        Menu menu2 = bottomNavigationView2.getMenu();
        MenuItem menuItem2 = menu2.getItem(0);
        menuItem2.setChecked(true);


        /*below you will need to navigate to different areas when the nav buttons are pressed and you
          will need to use switch statements, breaks, and the code below */
        bottomNavigationView2.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

        //          case R.id.ic_new_project:
        //              Intent intent0 = new Intent(MainActivity2_ServiceProvider.this, MainActivity2_ServiceProvider.class);
        //              startActivity(intent0);
        //              break;

                    case R.id.ic_current_listings:
                        Intent intent1 = new Intent(MainActivity2_ServiceProvider.this, Activity_Provider_Current_Listings.class);
                        startActivity(intent1);
                        break;


                    case R.id.ic_chat_provider:
                        Intent intent2 = new Intent(MainActivity2_ServiceProvider.this, Activity_Provider_Chat.class);
                        startActivity(intent2);

                        break;

                    case R.id.ic_profile_provider:
                        Intent intent3 = new Intent(MainActivity2_ServiceProvider.this, Activity_Provider_Profile.class);
                        startActivity(intent3);
                        break;


                    case R.id.ic_settings_provider:
                        Intent intent4 = new Intent(MainActivity2_ServiceProvider.this, Activity_Provider_Settings.class);
                        startActivity(intent4);

                        break;
                }
                return false;
            }
        });

    }
        /* each of these correspond to a number i.e. '0','1', '2', when referencing fragments */
    private void setupViewPager(ViewPager viewPager) {
        SectionsStatePagerAdapter adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment1(), "Fragment1");  /*Fragment number '0'*/
        adapter.addFragment(new Fragment2(), "Fragment2");  /*Fragment number '1'*/
        adapter.addFragment(new Fragment3(), "Fragment3");  /*Fragment number '2'*/
        adapter.addFragment(new Fragment4_A1(), "Fragment4_A1"); /*Fragment number '3'*/
        adapter.addFragment(new Fragment4_B1(), "Fragment4_B1"); /*Fragment number '4'*/
        adapter.addFragment(new Fragment4_B2(), "Fragment4_B2"); /*Fragment number '5'*/
        adapter.addFragment(new Fragment4_B3(), "Fragment4_B3"); /*Fragment number '6'*/
        viewPager.setAdapter(adapter);
    }

    public void gotoFragment(int i)
    {
       mViewPager.setCurrentItem(i);
    }

    public void setViewPager(int fragmentNumber) {
        mViewPager.setCurrentItem(fragmentNumber);
    }



 //////////////*Setup Univeral Image loader init method*/////////////////
    private void initImageLoader(){
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(mContext);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
        /*^^this retrieve the configuration information from 'UniversalImageLoader' class*/

    }
/////////////////////////////////////////////////////////////////////////




    /*setup permissions*/
    private void verifyPermissions() {
        Log.d(TAG, "verify permissions: asking user for permissions");
        String[] permissions = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_FINE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                permissions[0]) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this.getApplicationContext(),
                permissions[1]) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this.getApplicationContext(),
                permissions[2]) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this.getApplicationContext(),
                permissions[3]) == PackageManager.PERMISSION_GRANTED
                ) {
            setupViewPager(mViewPager);   /*I presume mViewPager can go here...just assuming, may need to fix*/
        } else {
            ActivityCompat.requestPermissions(MainActivity2_ServiceProvider.this, permissions,
                    REQUEST_CODE_P);
        } /*above is just taking the result and we will put it in the below,
        'onRequestPermissionResult...'*/
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
     verifyPermissions();

    }

/////*Do Fragment Transaction, 32:17 Fragment to Fragment communication video *//////
////*In order to pass data through to final fragment*//////
    private void doFragmentTransaction (Fragment fragment, boolean addToBackStack, String message){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if(!message.equals("")){
            Bundle bundle = new Bundle();
         // bundle.putString(getString(R.string.project_title_optional), message);
            fragment.setArguments(bundle);
        }

    }




////////*Getting image and video paths below *///////



    @Override
    public void getVideopath(Uri data) {
        uri=data;
    }

    @Override
    public void getImageBitmap(Bitmap bitmap) {
        mBitmap=bitmap;
    }


    @Override
    public void getImagePath(Uri imagePath) {
        uri=imagePath;
    }

    @Override
    public Uri setVideopath() {
        return uri;
    }

    @Override
    public Bitmap setImageBitmap() {
        return mBitmap;
    }

    @Override
    public Uri setImagePath() {
        return null;  /*Need to fix this for Media Upload button to work*/
    }

    @Override
    public void getTextpath(String stringPath1) {
        mString = stringPath1;
    }

    @Override
    public String setTextpath() {
        return mString;
    }



    @Override
    public void setProjectTitle(String projectTitle) {

    //  Fragment4_B3 passtofrag4b3 = (Fragment4_B3) getSupportFragmentManager().findFragmentById(R.id.????????????); ?????
    //  findFragmentByTag is not a possibility either yet, Need assistance with creating pass through method.
    //  passtofrag4b3.updateinfoPT(projectTitle);

    }



   /* for 'Bitmap setImagePath (){..' i think if you add an mUri global variable (which I have done above)
    and also change this from "Public Bitmap" to "Public Uri" that might work, in order to match getImagePath method components */
  /*change this to the following code stated here when you have time */
  //  @Override
  //  public Bitmap setImagePath() {
  //      return null;
  //  }


}


