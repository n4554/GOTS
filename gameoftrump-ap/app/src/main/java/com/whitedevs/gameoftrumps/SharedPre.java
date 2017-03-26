package com.whitedevs.gameoftrumps;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Created by hassan on 6/19/2015.
 */
public class SharedPre {

    private Context context;

    public SharedPre(Context contextIn)
    {
        context=contextIn;
    }
	//این تابع ست می کنه  با نام نیم این  مقدار ولیو رو 
    public boolean Set(String nameIn , String value ){
        ////return true if action is done
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(nameIn, value);
        return editor.commit();

    }
	//  _WD_0  برای خواندن مقدار ست شده به این روش که نیم این را می گیرد  و ولیو را بر می گردانند . اگر مقداری ست نشده باشه مقدار 
	// بر می گرداند
    public String Get (String nameIn)
    {
        //return value of nameIn
        Log.d("Response: ", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sp.Get()");
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getString(nameIn, "400400400400");
    }
	// یاز به داشتن نام اش هست و اگر حذف کند مقدار
   public boolean Remove (String nameIn)
   {
       //return true if action is done
       SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
       SharedPreferences.Editor editor = settings.edit();
       editor.remove(nameIn);
       return editor.commit();

   }
   
    // اضافه کردن دو مقدار متغییر مرتبط به هم 
	// فرض کنید نام و ولیو هر دو متغییر باشه از این روش می شه استفاده کرد
	// یساختار شبه درخت داره بخونیش متوحه می ش
    public boolean AddNode (String nameIn , String value , boolean check)
    {
        //check is boolen for check is update (true) and new (false)
        //father
        int idFather=0;
        String father;
        boolean re;
        if (check==false) {
            father = Get("WD_father_78_");
            idFather = Integer.parseInt(father);
            re = Set(String.valueOf(idFather+1),value );
            if (re==false) {return  false ;}
            re = Set(String.valueOf(idFather+1),nameIn);
            if (re==false) {return  false ;}

        }
        if (check==true)
        {
            father = Get("WD_father_78_");
            idFather= Integer.parseInt(father);
            re = Set(String.valueOf(idFather-1),value );
            if (re==false) {return  false ;}
            re = Set(String.valueOf(idFather+1),nameIn);
            if (re==false) {return  false ;}

        }
        Set("WD_father_78_", String.valueOf(idFather));
        return  true;
    }
	// حذف از یک گره با داشتن نام
    public boolean DeleteNode (String nameIn)
    {
        String father = Get(nameIn);
        boolean re=Remove(nameIn);
        if (re==false) {return  false ;}
        re=Remove(father);
        if (re==false) {return  false ;}

        return  true;

    }
	// خواندن یک گره با داشتن نام
    public String[] GetNode(String nameIn)
    {
        String[] re = new String[1];
        re[0] = Get(nameIn);
        re[1] = Get(re[0]);


        return  re;
    }
	// خواندن همه گره ها
    public  String[] GetNodes ()
    {

        String father = Get("WD_father_78_");
        int idFather = Integer.parseInt(father);
        String[] re = new String[idFather];
        for (int i=idFather ; i>=0 ; i--)
        {
            String get=Get(String.valueOf(idFather));
            if (get=="400400400")
            {
                i--;
            }
            else {
                re[i]=get;
            }
        }

        return  re;


    }
}
