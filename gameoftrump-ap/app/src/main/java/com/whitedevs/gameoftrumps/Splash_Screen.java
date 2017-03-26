package com.whitedevs.gameoftrumps;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Mehrnaz on 1/27/2017.
 */

public class Splash_Screen extends Activity {
    TextView txtLoading;
    Typeface font;
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 10000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        txtLoading = (TextView) findViewById(R.id.txtLoading);
        font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        txtLoading.setTypeface(font);
        caller();

        try {
            Log.d("Response: ", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>try in");
            SharedPre sp = new SharedPre(getApplicationContext());
           int stars = Integer.parseInt(sp.Get("stars").toString());
            Log.d("Response: ", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>GET"+stars);
            if (stars==400400400) {stars=0;}
        }catch(NumberFormatException nfe)
        {
        }
    }
    private  void  caller()
    {
        InternetCheck IC = new InternetCheck();
        if (true==IC.NetworkConnection(getApplicationContext())) {
            Log.i("response", "Splash():If");
            new Handler().postDelayed(new Runnable() {


                @Override
                public void run() {

                    Intent intent = new Intent(Splash_Screen.this, NavigationDrawer.class);
                    startActivity(intent);


                    finish();
                }
            }, SPLASH_TIME_OUT);
        }
        else{
            Log.i("response", "Splash():else");
            Toast.makeText(Splash_Screen.this, "Enable the Internet connection and try again.",
                    Toast.LENGTH_LONG).show();
            new Handler().postDelayed(new Runnable() {


                @Override
                public void run() {

                    Intent intent = new Intent(Splash_Screen.this, Splash_Screen.class);
                    startActivity(intent);

                    finish();
                }
            }, SPLASH_TIME_OUT);
        }
    }

}
