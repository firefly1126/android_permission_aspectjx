/*
 * ImageFragment      2016-03-01
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
 * 
 */

package com.firefly1126.permissionaspect.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * class description here
 *
 * @author simon
 * @version 1.0.0
 * @since 2016-03-01
 */
public class ImageFragment extends Fragment {

    private ImageView mImageView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageLoader.getInstance().displayImage("http://cichang.hujiang.com/images/friendquan_share.png", mImageView);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.image_fragment_layout, null);

        mImageView = (ImageView)view.findViewById(R.id.icon_test);

        return view;
    }
}