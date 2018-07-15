package com.templateonetwo.testingtemplateonetwo.Utils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.templateonetwo.testingtemplateonetwo.CustomerListingFragment.BidsFragment;
import com.templateonetwo.testingtemplateonetwo.CustomerListingFragment.CustomerCurrentListingsFragment;

import java.util.ArrayList;
import java.util.List;

/* you created a new class, then you 'extends' the 'FragementStatePagerAdapter' from the developer
 android site, once inputted, you will see it's red, click alt+enter and you can see what
  other prerequisite items you need, select all in the pop-up and click ok to see the new Override
  method placed. It will still be red, and once you hover over it again it will say it wants a default
  constructor, hit alt+enter again and click ok for the first option to select default constructor and
  click ok, an voila, you have generated all this automatically without typeing once.*/


/*after above is done we need to create two objects, one is an object for the list of the fragments,
and another is a list for the title of the fragments. */

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = "SectionPagerAdapter";
    private final List<Fragment> mFragmentList = new ArrayList<>();
    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] { "Current Listings", "Bids" };
    private Context context;

    public SectionsPagerAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.context=context;

    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=new CustomerCurrentListingsFragment();
       switch (position)
       {
           case 0:
               fragment=new CustomerCurrentListingsFragment();
               break;
           case 1:
               fragment=new BidsFragment();
               break;
       }
       return fragment;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    public void addFragment (Fragment fragment) {
        mFragmentList.add(fragment);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }

}
