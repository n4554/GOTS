package com.whitedevs.gameoftrumps;

/**
 * Created by Mehrnaz on 1/27/2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.reward.RewardedVideoAd;

public class userFrag extends Fragment {
    private RewardedVideoAd mAd;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Use an activity context to get the rewarded video instance.

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.usr_frg, container, false);
    }

}