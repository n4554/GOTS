package com.whitedevs.gameoftrumps;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by nixan on 28/01/2017.
 */

public class InternetCheck {


    public boolean NetworkConnection(Context context) {

        Log.i("response", "NetworkConnection()");
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        Log.i("response", "return:"+haveConnectedWifi+"  "+haveConnectedMobile);
        return haveConnectedWifi || haveConnectedMobile;

    }
}
