package com.example.myapplication;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentStatePagerAdapter{

    public int getItemPosition(Object object) {
        // refresh all fragments when data set changed
        return POSITION_NONE;
    }


    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new tab1();
            case 1: return new tab2();
            case 2: return new tab3();
            default:
                break;
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Состояние";
            case 1:
                return "На карте";
            case 2:
                return "Отчет";
        }
        return null;
    }





    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }
}