package com.templateonetwo.testingtemplateonetwo;

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
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.templateonetwo.testingtemplateonetwo.Login.LoginActivity;
import com.templateonetwo.testingtemplateonetwo.Utils.CommonUtils;
import com.templateonetwo.testingtemplateonetwo.Utils.FragmentModelDataPasssing;
import com.templateonetwo.testingtemplateonetwo.Utils.FragmentdataPass;
import com.templateonetwo.testingtemplateonetwo.Utils.SectionsPagerAdapter;
import com.templateonetwo.testingtemplateonetwo.Utils.SectionsStatePagerAdapter;
import com.templateonetwo.testingtemplateonetwo.Utils.SessionManager;
import com.templateonetwo.testingtemplateonetwo.Utils.UniversalImageLoader;


public  class MainActivity extends AppCompatActivity implements Fragment1.OnPhotoSelectedLister,Fragment1.OnVideoSelectedLister,Fragment1.OnTextSelectedLister, Fragment4_B1.OnProjectTitleSetListener,FragmentdataPass {

    private static final String TAG = "MainActivity";
    private static final String TAG2 = "Fragment4_B3";
    private static final int REQUEST_CODE_P = 123;
    private static final int ACTIVITY_NUM = 0;
    private Context mContext = MainActivity.this;
    RelativeLayout mRelativeLayout;
    private TextView mToolbarTitle;
    Fragment mfragment4 = new Fragment4_B3();
    FragmentModelDataPasssing mFragmentModelDataPasssing;

    Uri uri;
    Uri mUri; /*just adding this to make the below code work for interface*/
    Bitmap mBitmap;
    String mString;

    protected DatabaseReference mDatabase;
    protected FirebaseUser firebaseUser;
    public FirebaseAuth firebaseAuth;
    SessionManager mSessionManager;

    private SectionsStatePagerAdapter mSectionsStatePagerAdapter;
    private ViewPager mViewPager; /*created in activity main XML all the way at the bottom*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Started.");

        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mSessionManager=SessionManager.NewInstance(this);

        if(!firebaseUser.isEmailVerified())
        {
            Toast.makeText(mContext, "Email is not verified. Logging out", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
            mSessionManager.setLogin(false);
            mSessionManager.saveProfileData(this,null);
            Intent i = new Intent(this, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
        }


        mToolbarTitle = findViewById(R.id.profilenameTitletoolbar);

        initImageLoader();

        mSectionsStatePagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        /* create view pager object above ^^ as that is what we will be referencing*/

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
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
        final SectionsStatePagerAdapter adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment1(), "Fragment1");  /*Fragment number '0'*/
        adapter.addFragment(new Fragment2(), "Fragment2");  /*Fragment number '1'*/
        adapter.addFragment(new Fragment3(), "Fragment3");  /*Fragment number '2'*/
        adapter.addFragment(new Fragment4_A1(), "Fragment4_A1"); /*Fragment number '3'*/
        adapter.addFragment(new Fragment4_B1(), "Fragment4_B1"); /*Fragment number '4'*/
        adapter.addFragment(new Fragment4_B2(), "Fragment4_B2"); /*Fragment number '5'*/
        adapter.addFragment(new Fragment4_B3(), "Fragment4_B3"); /*Fragment number '6'*/
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                       //   MaleLocation1 maleLocation1 = (MaleLocation1) getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.viewpager + ":" + viewPager.getCurrentItem());
                switch (position)
                {
                    case 6:
                        String tag = "android:switcher:" + R.id.viewpager + ":" + mViewPager.getCurrentItem();
                        //  Fragment4_B3 f = (Fragment4_B3) getSupportFragmentManager().findFragmentByTag(tag);

                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;
            }
        });

       // mViewPager.beginFakeDrag();
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
            ActivityCompat.requestPermissions(MainActivity.this, permissions,
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

    @Override
    public void passFragmentdata(FragmentModelDataPasssing message) {
        SectionsStatePagerAdapter adapter=(SectionsStatePagerAdapter)mViewPager.getAdapter();
        Fragment4_B3 f = (Fragment4_B3)adapter.mFragmentList.get(6);
        f.receiveData(message);
    }



   /* for 'Bitmap setImagePath (){..' i think if you add an mUri global variable (which I have done above)
    and also change this from "Public Bitmap" to "Public Uri" that might work, in order to match getImagePath method components */
  /*change this to the following code stated here when you have time */
  //  @Override
  //  public Bitmap setImagePath() {
  //      return null;
  //  }


}


