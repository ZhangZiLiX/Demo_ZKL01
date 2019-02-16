package com.example.administrator.demo_zkl01.startfragmentpage.circlepage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.demo_zkl01.R;

/**
 * Author : 张自力
 * Created on time.
 *
 * Fragment圈子
 *
 */

public class FragmentCircle extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_circle,null);

        return view;
    }
}
