package com.example.stucher;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import android.content.Context;

//FragmentPagerAdapter : to define the adapter that will properly determine how many pages exist
// and which fragment to display for each page of the adapter by creating a FragmentPagerAdapter:

    public class CombineSections extends FragmentPagerAdapter {
        private Context myContext;
        int totalTabs;
        public CombineSections(Context context, FragmentManager fm, int totalTabs) {
            super(fm);
            myContext = context;
            this.totalTabs = totalTabs;
        }
        // this is for fragment tabs
        @Override
        //getItem() : return the fragment for the specified position
        //Internally, FragmentStatePagerAdapter calls getItem() to instantiate fragments,
        // and uses the FragmentManager passed into the constructor to manage these fragments.
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    PrincipalFragment principalFragment = new PrincipalFragment();
                    return principalFragment;
                case 1:
                    StaffFragment staffFragment = new StaffFragment();
                    return staffFragment;
                case 2:
                    StudentFragment studentFragment = new StudentFragment();
                    return studentFragment;
                default:
                    return null;
            }
        }
        // this counts total number of tabs
        @Override
        public int getCount() {
            return totalTabs;
        }
    }


