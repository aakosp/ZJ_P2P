package com.aako.zjp2p.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by aako on 15-12-23.
 */
public class FragmentSpreading extends Fragment{
    TextView curView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("FragmentSpreading", "FragmentSpreading onCreateView begin");
        if (null != curView) {
            ((ViewGroup) curView.getParent()).removeView(curView);
            return curView;
        }
        curView = new TextView(this.getActivity());
        curView.setText("FragmentSpreading...");
        Log.d("FragmentSpreading", "FragmentSpreading onCreateView begin");
        return curView;
    }
}
