package com.whitedevs.gameoftrumps;

import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.animation.AnimatorInflater;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Random;

import java.util.Timer;
import java.util.TimerTask;

import static com.whitedevs.gameoftrumps.R.id.adView;

public class Game extends Activity {

	Typeface font;
	TextView txtStarBg;
	TextView txtIconStar;
	//H.T added start
	private static int ROW_COUNT = -1;
	private static int COL_COUNT = -1;
	private Context context;
	private AdView mAdView;

	private int [] [] cards;
	private  int winCard;
	private int size;
	private Card firstCard;
	private Card seconedCard;
	private ButtonListener buttonListener;
	public int[][] rtrn;
	private static Object lock = new Object();
	private int turns;
	private TableLayout mainTable;
	private UpdateCardsHandler handler;
	SharedPre sp;
	int levelNo;
	int icon;
	int sizeIcon;
	private int starsin;
	private int endRow;
    private int uniqeIcon;

    //H>T added End

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_layout);

		Typeface font2=Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
		TextView txtg= (TextView) findViewById(R.id.game);
		txtg.setTypeface(font2);
		TextView txtsq1= (TextView) findViewById(R.id.sqbg);
		TextView txtstar= (TextView) findViewById(R.id.star);
		txtstar.setTypeface(font2);
		sizeIcon=40;
		TextView plus = (TextView) findViewById(R.id.pl);
		plus.setTypeface(font);
//		txtIconStar = (TextView) findViewById(R.id.txtstar);
//		txtIconStar.setTypeface(font2);
//		txtStarBg = (TextView) findViewById(R.id.txtstarbg);
//		txtStarBg.setTypeface(font2);
		//mehrnaz
		final FrameLayout fl= (FrameLayout) findViewById(R.id.popupFrameLayout2);
		final FrameLayout fm= (FrameLayout) findViewById(R.id.frameMain);
		//fm.setPadding(20,20,20,20);

		fl.setVisibility(View.GONE);
		TextView txtcar= (TextView) findViewById(R.id.txtcar);
		txtcar.setTypeface(font2);
		TextView txtviewarrow= (TextView) findViewById(R.id.arrow);
		txtviewarrow.setTypeface(font2);
		//
		//HT added
		handler = new UpdateCardsHandler();
		buttonListener = new ButtonListener();
		font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
		levelNo = getIntent().getIntExtra("levelNo", 0);
		Log.i("loadCards()","levelNo=" + levelNo);

		mainTable = (TableLayout)findViewById(R.id.TableLayout03);
		context  = mainTable.getContext();
		sp = new SharedPre(getApplicationContext());
		try {
			starsin=Integer.parseInt(sp.Get("winstars").toString());
			Log.e("sp.Get()", starsin+"");
		} catch(NumberFormatException nfe) {
			//	System.out.println("Could not parse " + nfe);
			starsin=0;
		}

		String userName;
		try {
			userName=(sp.Get("username"));
			if (userName.equals("400400400400")){userName="Name!";}
			Log.e("userName", userName+"");
		} catch(NumberFormatException nfe) {
			//	System.out.println("Could not parse " + nfe);
			userName="Name";
		}
		TextView tv1 = (TextView)findViewById(R.id.starsNo);
		tv1.setText(Integer.toString(starsin));
		tv1 = (TextView)findViewById(R.id.tvUserName);
		tv1.setText(userName);

		newGame(levelNo);



		if (levelNo>20) {
			//mehrnaz 777
			String s = Character.toString((char) uniqeIcon);
			txtcar.setText(s);
			fl.setVisibility(View.VISIBLE);

			final Animation myAnim = AnimationUtils.loadAnimation(Game.this, R.anim.anim2);
			fl.startAnimation(myAnim);
			fm.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					fl.setVisibility(v.GONE);

				}
			});
			fl.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					fl.setVisibility(v.GONE);

				}
			});
		}

		//
	}
	// /*
	private void newGame(int levelNo) {


//		String icons ;
		Log.i("cards_leveNo", levelNo+"");
		int r[]=getRowAndCol(levelNo);
		COL_COUNT = r[0] ;
		ROW_COUNT =  r[1] ;

		int CCount = (ROW_COUNT*COL_COUNT) ;
		Log.i("cards_CCount", CCount+"");
	//	final int[] checkPaper = new int[CCount];
		ClassRandom rndm = new ClassRandom();

		switch (levelNo) {
			case 2:
				Log.i("goneToGame()", "case 01");
				icon=R.string.icon_glass;
				break;
			case 4:
				Log.i("goneToGame()", "case 02");
				icon=R.string.icon_glass;
				break;
			case 6:
				Log.i("goneToGame()", "case 03");
				icon=R.string.icon_gratipay;
				break;
			case 8:
				Log.i("goneToGame()", "case 04");
				icon=R.string.icon_glass;
				break;
			case 10:
				Log.i("goneToGame()", "case 05");
				icon=R.string.icon_globe;
				break;
			case 12:
				Log.i("goneToGame()", "case 06");
				icon=R.string.icon_gratipay;
			case 14:
				icon=R.string.icon_plus;
				break;
			case 16:
				icon=R.string.icon_plus;
				break;
			case 18:
				icon=R.string.icon_plus;
				break;
			case 20:
				icon=R.string.icon_plus;
				break;
			case 22:
				icon=R.string.icon_plus;
				break;
			case 24:
				icon=R.string.icon_plus;
				break;
			case 26:
				icon=R.string.icon_plus;
				break;
			case 28:
				icon=R.string.icon_plus;
				break;
			case 30:
				icon=R.string.icon_plus;
				break;
			case 32:
				icon=R.string.icon_plus;
				break;
			case 34:
				icon=R.string.icon_plus;
				break;
			case 36:
				icon=R.string.icon_plus;
				break;
			case 38:
				icon=R.string.icon_plus;
				break;
			case 40:
				icon=R.string.icon_plus;
				break;
			case 42:
				icon=R.string.icon_plus;
				break;
			case 44:
				icon=R.string.icon_plus;
				break;
			case 46:
				icon=R.string.icon_plus;
				break;
			case 48:
				icon=R.string.icon_plus;
				break;
			case 50:
				Log.i("goneToGame()", "case 00");
				icon=R.string.icon_trophy;

				break;

		}
		Log.i("cards_fisher","levelNo: " +levelNo);
		rtrn = rndm.Fisher4(levelNo);
        Random rand = new Random();
		if (levelNo>20) {
			uniqeIcon = rand.nextInt((levelNo / 2) + 1);
			uniqeIcon = rtrn[uniqeIcon][1];
		}
        Log.i("cards_fisher_uniqeIcon","uniqeIcon: " +uniqeIcon);
		cards = new int [COL_COUNT] [ROW_COUNT];
		mAdView = (AdView) findViewById(adView);

		AdRequest adRequest = new AdRequest.Builder().build();
		mAdView.loadAd(adRequest);



		TableRow tr = ((TableRow)findViewById(R.id.TableRow03));
		tr.removeAllViews();

		mainTable = new TableLayout(context);
		tr.addView(mainTable);

		for (int y = 0; y < ROW_COUNT; y++) {
			mainTable.addView(createRow(y));
		}
		firstCard=null;
		loadCards();
		//tedad bar sai : turns
		turns=0;
	}
	private int[] getRowAndCol(int i)
	{
		int row=0;
		int col =0;
		switch (i) {
			case 2:
				col=2;
				row=1;
				endRow=0;
				break;
			case 4:
				col=2;
				row=2;
				endRow=0;
				break;
            case 6:
                col=3;
                row=2;
                endRow=0;
                break;
            case 8:
                col=4;
                row=2;
                endRow=0;
                break;
            case 10:
				col=2;
				row=5;
				endRow=0;
				break;
			case 12:
				col=3;
				row=4;
				endRow=0;
				break;
			case 14:
				col=2;
				row=7;
				endRow=0;
				break;
			case 16:
				col=2;
				row=8;
				endRow=0;
				break;
			case 18:
				col=3;
				row=6;
				endRow=0;
				break;
			case 20:
				col=4;
				row=5;
				endRow=0;
				break;
			case 22:
				col=5;
				row=5;
				endRow=2;
				break;
			case 24:
				col=5;
				row=5;
				endRow=4;
				break;
			case 26:
				col=5;
				row=6;
				endRow=2;
				break;
			case 28:
				col=5;
				row=6;
				endRow=4;
				break;
			case 30:
				col=5;
				row=6;
				endRow=0;
				break;
			case 32:
				col=4;
				row=8;
				endRow=0;
				break;
			case 34:
				col=5;
				row=7;
				endRow=4;
				break;
			case 36:
				col=5;
				row=8;
				endRow=1;
				break;
			case 38:
				col=5;
				row=8;
				endRow=3;
				break;
			case 40:
				col=5;
				row=8;
				endRow=0;
				break;
			case 42:
				col=5;
				row=9;
				endRow=2;
				break;
			case 44:
				col=5;
				row=9;
				endRow=4;
				break;
			case 46:
				col=5;
				row=10;
				endRow=1;
				break;
			case 48:
				col=5;
				row=9;
				endRow=3;
				break;
			case 50:
				col=5;
				row=10;
				endRow=0;
				break;
		}
		Log.i("cards_calulate","A(row)=" + row);
		Log.i("cards_calulate","B(Col)=" + col);
		Log.i("cards_calulate","endrow=" + endRow);
		int[] r={col,row,endRow};
		return r;
	}

	private void loadCards(){
		try{
			winCard=0;
			size = levelNo;
			Log.i("loadCards()","size=" + size);
			ArrayList<Integer> list = new ArrayList<Integer>();
			for(int i=0;i<size;i++){
				list.add(new Integer(i));
			}
			Random r = new Random();
			for(int i=size-1;i>=0;i--){
				int t=0;
				if(i>0){
					t = r.nextInt(i);
				}
				t=list.remove(t).intValue();
				cards[i%COL_COUNT][i/COL_COUNT]=rtrn[t][1];
				Log.i("loadCards()", "card["+(i%COL_COUNT)+
						"]["+(i/COL_COUNT)+"]=" + cards[i%COL_COUNT][i/COL_COUNT]);
			}
		}
		catch (Exception e) {
			Log.e("loadCards()", e+"");
		}

	}

	private TableRow createRow(int y){
		//cards_calulate
		Log.e("cards_createRow","row: "+y);
		TableRow row = new TableRow(context);
		row.setHorizontalGravity(Gravity.CENTER);
		if(y<ROW_COUNT) {
			if (endRow!=0 && y+1==ROW_COUNT) {
				for (int x = 0; x < endRow; x++) {
					Log.e("cards_createRow", "endrow col:" + x);
					row.addView(createButton(x, y));
				}
			}else {
				for (int x = 0; x < COL_COUNT; x++) {
					Log.e("cards_createRow", "row col" + x);
					row.addView(createButton(x, y));
				}
			}
		}
		return row;
	}

	private View createButton(int x, int y){
		final Button button = new Button(context);

		TextView textView=new TextView(context);
		//button.setBackgroundDrawable(null);
		Typeface fontawsome = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
		button.setTypeface(fontawsome);
		TextView plus = (TextView) findViewById(R.id.pl);
		plus.setTypeface(fontawsome);
		button.setTextColor(Color.WHITE);

		//button.setText("\uf04d");
		Log.i("cards-icon",""+icon);
		button.setText(icon);
	    button.setBackgroundResource(R.drawable.square);


		//textView.setText("Hi");
		//textView.setTextColor(Color.RED);

		button.setTextSize(TypedValue.COMPLEX_UNIT_PX, sizeIcon);
		button.setId(100*x+y);
		button.setOnClickListener(buttonListener);




		/* H.T E
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final Animation myAnim = AnimationUtils.loadAnimation(Game.this, R.anim.anim2);
				button.startAnimation(myAnim);
			}
		});
		*/
		return button;
	}

	class ButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {

			synchronized (lock) {
				if(firstCard!=null && seconedCard != null){
					return;
				}
				int id = v.getId();
				int x = id/100;
				int y = id%100;
				Log.i("onClick()", "card["+(x)+
						"]["+(y)+"]=" + cards[x][y]);
				turnCard((Button)v,x,y);
			}

		}

		private void turnCard(Button button,int x, int y) {
			//	button.setBackgroundDrawable(images.get(cards[x][y]));
			Log.i("turndCards()", "card["+(x)+
					"]["+(y)+"]=" + cards[x][y]);
			Typeface fontawsome = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
			button.setTypeface(fontawsome);
			button.setTextColor(Color.parseColor("#f6bb07"));
			String s = Character.toString((char) cards[x][y]);
			button.setText(s);
			button.setTextSize(TypedValue.COMPLEX_UNIT_PX, sizeIcon);
//			final Animation myAnim = AnimationUtils.loadAnimation(Game.this, R.anim.changecardsanim);
//			button.startAnimation(myAnim);

			if(firstCard==null){
				firstCard = new Card(button,x,y);
			}
			else{

				if(firstCard.x == x && firstCard.y == y){
					return; //the user pressed the same card
				}

				seconedCard = new Card(button,x,y);

				turns++;
				//((TextView)findViewById(R.id.tv1)).setText("Tries: "+turns);


				TimerTask tt = new TimerTask() {

					@Override
					public void run() {
						try{
							synchronized (lock) {
								handler.sendEmptyMessage(0);
							}
						}
						catch (Exception e) {
							Log.e("E1", e.getMessage());
						}
					}
				};

				Timer t = new Timer(false);
				t.schedule(tt, 1300);
			}
		}

	}

	class UpdateCardsHandler extends Handler{

		@Override
		public void handleMessage(Message msg) {
			synchronized (lock) {
				checkCards();
			}
		}
		public void checkCards(){
			if(cards[seconedCard.x][seconedCard.y] == cards[firstCard.x][firstCard.y]){
				firstCard.button.setVisibility(View.INVISIBLE);
				seconedCard.button.setVisibility(View.INVISIBLE);
				winCard +=1;
				Log.e("checkCards()", winCard+"");
				Log.e("checkCards_comper", cards[seconedCard.x][seconedCard.y] +"");
				int starsin;
				if (winCard==levelNo/2 ||  uniqeIcon==cards[seconedCard.x][seconedCard.y])
				{

					try {
						starsin=Integer.parseInt(sp.Get("winstars").toString());
						Log.e("sp.Get(stars)", starsin+"");
						if (uniqeIcon==cards[seconedCard.x][seconedCard.y] && levelNo>20)
						{uniqeIcon=1;
							Log.e("uniqeIcon", uniqeIcon+"");
						}else {
							uniqeIcon=0;}
						starsin=starsin+getStar();
					} catch(NumberFormatException nfe) {
						//	System.out.println("Could not parse " + nfe);
						starsin=getStar();
					}
					TextView tv1 = (TextView)findViewById(R.id.starsNo);
					tv1.setText(Integer.toString(starsin));
					sp.Set("winstars",Integer.toString(starsin));

					Intent intent = new Intent(Game.this, Lev.class);
					intent.putExtra("winstars", (Integer)getStar());
					startActivity(intent);


				}

			}
			else {
				//seconedCard.button.setBackgroundDrawable(null);
				//firstCard.button.setBackgroundDrawable(null);

				seconedCard.button.setTextColor(Color.WHITE);
				seconedCard.button.setText(icon);
				seconedCard.button.setTextSize(TypedValue.COMPLEX_UNIT_PX, sizeIcon);


				firstCard.button.setTextColor(Color.WHITE);
				firstCard.button.setText(icon);
				firstCard.button.setTextSize(TypedValue.COMPLEX_UNIT_PX, sizeIcon);
			}

			firstCard=null;
			seconedCard=null;
		}

		public int getStar ()
		{
			int sizeDiv2=size/2;
			int r=0;
			if (turns<size)
			{r= 3;}
			if (turns>=size && turns< (size*((sizeDiv2)-(sizeDiv2-2))))
			{
				if (uniqeIcon==1){
					r=3;}else {r= 2;}

			}
			if (turns>= (size*(sizeDiv2)) &&turns<= (size*((sizeDiv2)-sizeDiv2-3) ))
			{if (uniqeIcon==1){
				r=2;}else {r= 1;}}

			return r;
		}

	}


	@Override
	public void onBackPressed() {
		try {
			starsin=Integer.parseInt(sp.Get("winstars").toString());

			Log.e("sp.Get(winstars)", starsin+"");

		} catch(NumberFormatException nfe) {
			//	System.out.println("Could not parse " + nfe);
			starsin=0;
		}
		TextView tv1 = (TextView)findViewById(R.id.starsNo);
		tv1.setText(Integer.toString(starsin));

		Intent intent = new Intent(Game.this, Lev.class);
		startActivity(intent);


		/*
		AlertDialog.Builder dialog = new AlertDialog.Builder(Game.this);
		dialog.setCancelable(false);
		dialog.setTitle("Eixt Game");
		dialog.setMessage("Do you want exit game?" );
		dialog.setPositiveButton("Yest", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				Intent intent = new Intent(Game.this, Lev.class);
				startActivity(intent);
			}
		})
				.setNegativeButton("Cancel ", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//Action for "Cancel".
					}
				});

		final AlertDialog alert = dialog.create();
		alert.show(); */
	}

	public void onGGP(View view) {
		Intent intent = new Intent(Game.this, userProfile.class);
		startActivity(intent);
	}

	//*/
}