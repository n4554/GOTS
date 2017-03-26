package com.whitedevs.gameoftrumps;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.os.SystemClock;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

// This is mainpage
public class NavigationDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    Typeface font;

    //az icon play baraye mosalasha estfade shode
//    TextView txtIconPlay1;
//    TextView txtIconPlay2;
//    TextView txtIconPlay4;
    TextView txtIconSetting;
    TextView txtIconUser;
    TextView txtIconPlay;
    TextView playGameBg;
    //TextView txtIconMenu;
//    TextView txtIconMenuBg;
    TextView txtIconSquare;

    TextView txtSq2;
    TextView txtSq3;
    TextView txtcloud2;
    TextView txtcloud3;
    TextView txtcloud4;
    TextView txtcloud7;
     TextView plus;
    TextView txtSq2Bg;
    TextView txtSq3Bg;
    private AdView mAdView;
    private static final int REQUEST_INVITE = 0;
    private static final long RIPPLE_DURATION = 250;
    private Context context;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
 //       view=
//Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
//        View contentHamburger=findViewById(R.id.content_hamburger);
        //HT adsMob(banner) section start
        mAdView = (AdView) findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder().build();
        context=getApplicationContext();
        mAdView.loadAd(adRequest);
        TextView txtg= (TextView) findViewById(R.id.game);
        SharedPre sp = new SharedPre(getApplicationContext());
        int starsin;
        try {
            starsin=Integer.parseInt(sp.Get("winstars").toString());
            Log.e("sp.Get(winstars)", starsin+"");
        } catch(NumberFormatException nfe) {
            //	System.out.println("Could not parse " + nfe);
            starsin=0;
        }

        String userName="Name";
        try {
            userName=(sp.Get("username"));
            if (userName=="400400400400"){userName="Name!";}
            Log.e("userName", userName+"");
        } catch(NumberFormatException nfe) {
            //	System.out.println("Could not parse " + nfe);
            userName="Name";
        }
        TextView tv1 = (TextView)findViewById(R.id.starsNo);
        tv1.setText(Integer.toString(starsin));
        tv1 = (TextView)findViewById(R.id.tvUserName);
        tv1.setText(userName);
        //HT adsMob section end
//
//        if (toolbar != null) {
//            setSupportActionBar(toolbar);
//            getSupportActionBar().setTitle(null);
//        }
//        FrameLayout root= (FrameLayout) findViewById(R.id.root);
//        View guillotineMenu = LayoutInflater.from(this).inflate(R.layout.guillotine, null);
//        root.addView(guillotineMenu);
//
//        new GuillotineAnimation.GuillotineBuilder(guillotineMenu, guillotineMenu.findViewById(R.id.guillotine_hamburger), contentHamburger)
//                .setStartDelay(RIPPLE_DURATION)
//                .setActionBarViewForAnimation(toolbar)
//                .setClosedOnStart(true)
//                .build();
        //UI mainpage
        //iconplay haman mosalasha hastan
        font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        txtg.setTypeface(font);
        TextView txtsq1= (TextView) findViewById(R.id.sqbg);
        TextView txtstar= (TextView) findViewById(R.id.star);
        txtstar.setTypeface(font);

//        txtsq1.setTypeface(font);
//        TextView txtsq2= (TextView) findViewById(R.id.sqbg2);
//        txtsq2.setTypeface(font);

//        txtIconPlay1 = (TextView) findViewById(R.id.txticonplay1);
//        txtIconPlay1.setTypeface(font);
//        txtIconPlay2 = (TextView) findViewById(R.id.txticonplay2);
//        txtIconPlay2.setTypeface(font);
//        txtIconPlay4 = (TextView) findViewById(R.id.iconplay4);
//        txtIconPlay4.setTypeface(font);
        txtIconSetting = (TextView) findViewById(R.id.txtIconSetting);
        txtIconSetting.setTypeface(font);
        txtIconSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(NavigationDrawer.this,Setting.class);
                startActivity(i);
            }
        });
        txtIconUser = (TextView) findViewById(R.id.txtIconUser);
        txtIconUser.setTypeface(font);
        //user profile
        txtIconUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NavigationDrawer.this,userProfile.class);
                startActivity(intent);
            }
        });
        //
        txtIconPlay = (TextView) findViewById(R.id.txtIconPlay);
        txtIconPlay.setTypeface(font);

//        playGameBg = (TextView) findViewById(R.id.txtIconPlaybg);
//        playGameBg.setTypeface(font);
//        txtIconMenu = (TextView) findViewById(R.id.txtIconMenu);
//        txtIconMenu.setTypeface(font);
        txtcloud2= (TextView) findViewById(R.id.txtcloud2);
        txtcloud2.setTypeface(font);

        txtcloud3= (TextView) findViewById(R.id.txtcloud3);
        txtcloud3.setTypeface(font);
        txtcloud4= (TextView) findViewById(R.id.txtcloud4);
        txtcloud4.setTypeface(font);
       TextView txtcloud5= (TextView) findViewById(R.id.txtcloud5);
        txtcloud5.setTypeface(font);
        TextView txtcloud6= (TextView) findViewById(R.id.txtcloud6);
        txtcloud6.setTypeface(font);
        txtcloud7= (TextView) findViewById(R.id.txtcloud7);
        txtcloud7.setTypeface(font);
        TextView txtcloud8= (TextView) findViewById(R.id.txtcloud8);
        txtcloud8.setTypeface(font);
        TextView txtcloud9= (TextView) findViewById(R.id.txtcloud9);
        txtcloud9.setTypeface(font);
        TextView txtcloud10= (TextView) findViewById(R.id.txtcloud10);
        txtcloud10.setTypeface(font);
//        txtIconMenuBg = (TextView) findViewById(R.id.txtIconMenubg);
//        txtIconMenuBg.setTypeface(font);
//        txtIconSquare = (TextView) findViewById(R.id.txtsquare);
//        txtIconSquare.setTypeface(font);
        plus = (TextView) findViewById(R.id.pl);
        plus.setTypeface(font);
//        txtIconStar = (TextView) findViewById(R.id.txtstar);
//        txtIconStar.setTypeface(font);
//        txtStarBg = (TextView) findViewById(R.id.txtstarbg);
//        txtStarBg.setTypeface(font);
//        txtUserPlus = (TextView) findViewById(R.id.txtUserPlus);
//        txtUserPlus.setTypeface(font);
//        txtPlusBg = (TextView) findViewById(R.id.txtplusbg);
//        txtPlusBg.setTypeface(font);
//        txtSq2 = (TextView) findViewById(R.id.txtsquare2);
//        txtSq2.setTypeface(font);
//        txtSq3 = (TextView) findViewById(R.id.txtsquare3);
//        txtSq3.setTypeface(font);
//        userPlusbg = (TextView) findViewById(R.id.txtUserPlusbg);
//        userPlusbg.setTypeface(font);
        //sq=square baraye background icon estfade shode
//        txtSq2Bg = (TextView) findViewById(R.id.txtsquare2bg);
//        txtSq2Bg.setTypeface(font);
//        txtSq3Bg = (TextView) findViewById(R.id.txtsquare3bg);
//        txtSq3Bg.setTypeface(font);
        //Go to Level Activity
//
//        txtIconStar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                starFragment star_frag = new starFragment();
////                loadFragment(star_frag);
//
//                Intent intent = new Intent(NavigationDrawer.this,com.myapp.mehrnaz.appapp.Star.class);
//                startActivity(intent);
//            }
//        });
//        txtStarBg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(NavigationDrawer.this,com.myapp.mehrnaz.appapp.Star.class);
//                startActivity(intent);
////                starFragment star_frag = new starFragment();
////                loadFragment(star_frag);
//            }
//        });
        txtIconSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigationDrawer.this, Lev.class);
                startActivity(intent);

            }
        });
        txtIconPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(NavigationDrawer.this, Lev.class);
                startActivity(intent);
            }
        });
        //Go to Level Activity
        txtIconUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                userFrag usr_frag = new userFrag();
//                loadFragment(usr_frag);
                Intent intent = new Intent(NavigationDrawer.this, userProfile.class);
                startActivity(intent);
            }
        });


        new Thread( new Runnable(){
            @Override
            public void run(){
                Looper.prepare();

                //loadBackground();
            }
        }).start();

        //icon menu felan hazf
//        txtIconMenu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawer.openDrawer(GravityCompat.START);
//
//            }
//        });
        //


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    private void loadBackground()
    {
        Log.e("test", "in loadbakground");
        //Rotate texviedw iconplay
        RotateAnimation rotate = (RotateAnimation) AnimationUtils.loadAnimation(context, R.anim.rotateanimation);
//        txtIconPlay1.setAnimation(rotate);
        RotateAnimation rotate2 = (RotateAnimation) AnimationUtils.loadAnimation(context, R.anim.animate2);
        Animation scale= AnimationUtils.loadAnimation(context, R.anim.scale);
       // scale.setRepeatCount(Animation.ZORDER_NORMAL);
        txtIconPlay.startAnimation(scale);

        final Animation move = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_PARENT, 0f,
                TranslateAnimation.RELATIVE_TO_PARENT, 0.7f,
                TranslateAnimation.ABSOLUTE, 0f,
                TranslateAnimation.ABSOLUTE, 0f);
        move.setDuration(8000);
        move.setRepeatCount(0);
        //move.setRepeatMode(Animation.REVERSE);
        move.setInterpolator(new LinearInterpolator());
        TextView txt1= (TextView) findViewById(R.id.txtgame);
        txt1.setTypeface(font);
        txt1.setAnimation(move);
        txtcloud2.setTypeface(font);
        txtcloud2.setAnimation(move);
        txtcloud3.setAnimation(move);
        txtcloud4.setAnimation(move);
        txtcloud7.setAnimation(move);



        Animation scaleDown = new ScaleAnimation(2, 1, 2, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleDown.setDuration(1000);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

  /*
        txtcloud2.setVisibility(View.GONE);
        txtcloud3.setVisibility(View.GONE);
        txtcloud4.setVisibility(View.GONE);
        txtcloud7.setVisibility(View.GONE); */
    }

    @Override
    public void onBackPressed() {
        /*
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            closed();
        }
        */
        closed();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //felan hazf
       // getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {




        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(fragment.getTag());
        transaction.commit();
    }

    public  void closed()
    {
        //HT724
        finish();
        android.os.Process.killProcess(android.os.Process.myPid());
        super.onDestroy();
    }

    public void onGGP(View view) {
        Intent intent = new Intent(NavigationDrawer.this, userProfile.class);
        startActivity(intent);
    }


}
