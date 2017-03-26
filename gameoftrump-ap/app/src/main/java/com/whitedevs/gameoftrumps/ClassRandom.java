
package com.whitedevs.gameoftrumps;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

/**
 * the fisher function create list of unquie array int
 * Created by hassan on 7/16/2015.
 * https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
 */
public class ClassRandom {


    boolean check=true;

    public int[] Getrandomlist(int lenghtN)
    {
     //   scratch=lenghtN;
        int[] solutionArray = new int[lenghtN];
        for (int i=0 ; i<lenghtN ; i++)
        {
            solutionArray[i]=5+i;
        }


        return  solutionArray;

    }


    private int GetRandomInt (int min , int max)
    {
        Random r = new Random();
        int ret=r.nextInt((max - min) + 1) + min;
        return ret;
    }
    public int[] Fisher(int n)
    {
        ArrayList<Integer> listSource = new ArrayList <Integer> ();
        int[] result = new int[n];

        for (int i=0 ; i<n ; i++){
            listSource.add(i);
            result[i]=i;
        }

       for (int i=1 ; i<n; i++){
           result[i]=GetRandomInt(1,listSource.size());
           if (result[i]==listSource.size())
           {
               result[i]=result[i]-1;
           }
           result[i]=listSource.get(result[i]);
           listSource.remove(Integer.valueOf(result[i]));
       }
        for (int i=0 ; i<n ;i++)
        {
            Log.d("number", "i: " + i + " result[i]: " + result[i]);
        }
        return result;
    }



    public int[][] Fisher3(int n)
    {
        ArrayList<Integer> listSource = new ArrayList <Integer> ();
        int[][] result = new int[n][2];
        int min=61440;
        int Max=61834;
        int max=Max-((n*2)+15);


        int icon=GetRandomInt(min,max);
        //icon=61617;
        if (((n*2)+icon) > 61834)
        {
            icon=icon-(n*2);
        }

        for (int i=0 ; i<n ; i++){
            listSource.add(i);
            result[i][0]=i;
        }
        int in=icon;
        int lastIn=in ;
        for (int i=0 ; i<n; i++){
            result[i][0]=GetRandomInt(1,listSource.size());
            if (result[i][0]==listSource.size())
            {
                result[i][0]=result[i][0]-1;
            }
            result[i][0]=listSource.get(result[i][0]);
            listSource.remove(Integer.valueOf(result[i][0]));



            //new start

            in=icon+(i*2);
            if (61618<in && in<61632)
            { in =61632+(61632-in);}

            switch (in)
            {
                //fXXf
                case 61455:
                case 61471:
                case 61487:
                case 61503:
                case 61519:
                case 61535:
                case 61551:
                case 61567:
                case 61583:
                case 61599:
                case 61615:
                case 61631:
                case 61647:
                case 61663:
                case 61679:
                case 61695:
                case 61711:
                case 61727:
                case 61743:
                case 61759:
                case 61775:
                case 61791:
                case 61807:
                case 61823:
                case 61718:
                    //  Log.d("While before","in:"+in);
                    in=in+3;
                    //    Log.d("While after","in:"+in);
                    break;
                case 0:
                    in=61834;
                    break;
            }
            if (i%2==0 || i==0)
            {result[i][1]=in;}
            else
            {result[i][1]=lastIn;}
            lastIn=in;
            //   Log.d("resul","result: "+result[i][1] + "i:" + i);
           // i=i+1;
          //  result[i][1]=in;
            // Log.d("resul", "result: " + result[i][1] + "i:" + i);

            //new end */
        }
        for (int i=0 ; i<n ;i++)
        {
            Log.d("numberF3", "i: " + i + " result[i][0]: " + result[i][0] +" result[i][1]: "  + result[i][1]);
        }
        return result;
    }

    public int[][] Fisher4(int n)
    {
        //  fa-trophy [&#xf091;] 61585 jam
        ArrayList<Integer> listSource = new ArrayList <Integer> ();

        int[][] result = new int[n][2];
        int max=89;
        int[] icons ={
                 61720,61550,61671,61715,61757,61819,61817,62115,61545,61881,
                 62030,61852,62157,61683,61949,61958,61925,62078,61922,61832,
                 61601,61959,61571,61603,62056,61870,61684,61774,61685,61977,
                 62082,62170,62105,62014,61691,61826,61722,61827,61558,61444,
                 61853,61612,61440,61547,61667,61721,61923,62086,61748,61549,
                 62057,62086,61721,61923,61723,61667,61547,62102,61440,61612,
                 61828,61853,62166,61444,61461,61548,61820,61558,61827,61722,
                 61654,61830,61980,61441,62012,61948,61872,61864,61910,62081,
                 61749,61636,61978,62124,61847,61829,62130,62050,61585,61593,
                 61689};


        int[] fisher=Fisher(max);

        for (int i=0 ; i<n ; i++){
            listSource.add(i);
            result[i][0]=i;
        }
   //    int in=icon;
        int in;
        int lastIn=0 ;
        for (int i=0 ; i<n; i++){
            result[i][0]=GetRandomInt(1,listSource.size());
            if (result[i][0]==listSource.size())
            {
                result[i][0]=result[i][0]-1;
            }
            result[i][0]=listSource.get(result[i][0]);
            listSource.remove(Integer.valueOf(result[i][0]));
            Log.d("Fisher4", "i: " + i );
            Log.d("Fisher4", "fisher[i]: " + fisher[i] );
            Log.d("Fisher4", "icons[fisher[i]]: " + icons[fisher[i]] );
            in=icons[fisher[i]];
            if (i%2==0 || i==0)
            {result[i][1]=in;}
            else
            {result[i][1]=lastIn;}
            lastIn=in;
            //   Log.d("resul","result: "+result[i][1] + "i:" + i);
            // i=i+1;
            //  result[i][1]=in;
            // Log.d("resul", "result: " + result[i][1] + "i:" + i);

            //new end */
        }
        for (int i=0 ; i<n ;i++)
        {
            Log.d("Fisher4", "i: " + i + " result[i][0]: " + result[i][0] +" result[i][1]: "  + result[i][1]);
        }
        return result;
    }
}
