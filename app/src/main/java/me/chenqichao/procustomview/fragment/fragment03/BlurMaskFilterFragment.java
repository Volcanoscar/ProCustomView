package me.chenqichao.procustomview.fragment.fragment03;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.chenqichao.procustomview.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlurMaskFilterFragment extends Fragment {


    public BlurMaskFilterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blur_mask_filter, container, false);
    }


}
