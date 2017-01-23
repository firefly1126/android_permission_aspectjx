/*
 * FragmentActivity      2016-03-01
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
 * 
 */

package com.firefly1126.permissionaspect.demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;


/**
 * class description here
 *
 * @author simon
 * @version 1.0.0
 * @since 2016-03-01
 */
public class FragmentActivity extends android.support.v4.app.FragmentActivity {

    ViewPager mViewPager;
    Fragment imageFragment;
    Fragment textFragment;
    Fragment image2Fragment;
    Fragment text2Fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_activity_layout);

        mViewPager = (ViewPager)findViewById(R.id.fragment_pager);

        imageFragment = Fragment.instantiate(this, ImageFragment.class.getName());
        textFragment = Fragment.instantiate(this, TextFragment.class.getName());
        image2Fragment = Fragment.instantiate(this, ImageFragment.class.getName());
        text2Fragment = Fragment.instantiate(this, TextFragment.class.getName());

        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setCurrentItem(0);
        mViewPager.setAdapter(new FPagerAdapter(getSupportFragmentManager()));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    class FPagerAdapter extends FragmentPagerAdapter {

        public FPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return imageFragment;
                case 1:
                    return textFragment;
                case 2:
                    return image2Fragment;
                case 3:
                    return text2Fragment;
                default:
                    return imageFragment;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}