package com.example.ahmed.eagletech;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ahmed on 8/15/2016.
 */
public class MainFragment extends Fragment {
    public MainFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rowView = inflater.inflate(R.layout.main_fragment, container, false);

        ViewPager viewPager = (ViewPager) rowView.findViewById(R.id.pager);
        // Assign created adapter to viewPager
        viewPager.setAdapter(new TabsExamplePagerAdapter(getChildFragmentManager()));

        TabLayout tabLayout = (TabLayout) rowView.findViewById(R.id.tab_layout);
        // This method setup all required method for TabLayout with Viewpager
        tabLayout.setupWithViewPager(viewPager);

        return rowView;
    }
    public static class TabsExamplePagerAdapter extends FragmentPagerAdapter {
        // As we are implementing two tabs
        private static final int NUM_ITEMS = 2;

        public TabsExamplePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        // For each tab different fragment is returned
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new TabOneFragment();
                case 1:
                    return new TabTwoFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;

        }

        @Override
        public CharSequence getPageTitle(int position) {
            // For simplicity of this tutorial this string is hardcoded
            // Otherwise it should be access using string resource

            if(position==0){
                return "My Tasks";
            }else{
                return "Manage Tasks";
            }
        }
    }

}
