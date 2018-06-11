package com.templateonetwo.testingtemplateonetwo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public  class MainActivity extends AppCompatActivity implements Fragment1.OnPhotoSelectedLister,Fragment1.OnVideoSelectedLister {

    private static final String TAG = "MainActivity";
    private static final int REQUEST_CODE_P = 123;
    Uri uri;
    Bitmap mBitmap;

    private SectionsStatePagerAdapter mSectionsStatePagerAdapter;
    private ViewPager mViewPager; /*created in activity main XML all the way at the bottom*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Started.");

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
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        /*simple code to fix the highlighting of the appropriate bottom nav icon when tapped, menu item index is set to '0' for first icon*/
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);


        /*below you will need to navigate to different areas when the nav buttons are pressed and you
          will need to use switch statements, breaks, and the code below */
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.ic_new_project:
                        Intent intent0 = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent0);
                        break;

                    case R.id.ic_current_listings:
                        Intent intent1 = new Intent(MainActivity.this, Activity_Current_Listings.class);
                        startActivity(intent1);
                        break;


                    case R.id.ic_find_contractor:
                        Intent intent2 = new Intent(MainActivity.this, Activity_Find_Contractors.class);
                        startActivity(intent2);
                        break;


                    case R.id.ic_chat:
                        Intent intent3 = new Intent(MainActivity.this, Activity_Chat.class);
                        startActivity(intent3);

                        break;


                    case R.id.ic_settings:
                        Intent intent4 = new Intent(MainActivity.this, Activity_Settings.class);
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
        adapter.addFragment(new Fragment1(), "Fragment1");
        adapter.addFragment(new Fragment2(), "Fragment2");
        adapter.addFragment(new Fragment3(), "Fragment3");
        adapter.addFragment(new Fragment4_A1(), "Fragment4_A1");
        adapter.addFragment(new Fragment4_B1(), "Fragment4_B1");
        adapter.addFragment(new Fragment4_B2(), "Fragment4_B2");
        adapter.addFragment(new Fragment4_B3(), "Fragment4_B3");
        viewPager.setAdapter(adapter);
    }

    public void gotoFragment(int i)
    {
       mViewPager.setCurrentItem(i);
    }

    public void setViewPager(int fragmentNumber) {
        mViewPager.setCurrentItem(fragmentNumber);
    }


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
            ActivityCompat.requestPermissions(MainActivity.this, permissions,
                    REQUEST_CODE_P);
        } /*above is just taking the result and we will put it in the below,
        'onRequestPermissionResult...'*/
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
     verifyPermissions();

    }


    /////*Getting image and video paths below *///////

    @Override
    public void getImagePath(Uri imagePath) {
        uri=imagePath;
    }

    @Override
    public void getImageBitmap(Bitmap bitmap) {
       mBitmap=bitmap;
    }

    @Override
    public Uri setImagePath() {
        return uri;
    }

    @Override
    public Bitmap setImageBitmap() {
        return mBitmap;
    }

    @Override
    public Uri getVideopath() {
        return uri;
    }

    @Override
    public void setVideopath(Uri data) {
        uri=data;

    }
}


