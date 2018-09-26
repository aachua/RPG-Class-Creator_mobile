package edu.aachuaumich.a436_proj2;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

/*****************************
 * Created by Prof. John P. Baugh on 3/18/2018
 * Availability: List Fragments and Fragments Tutorial by Prof. John P. Baugh
 * Edited by Anthony Chua on 3/22/2018
 * Fragment for paladin
 *****************************/

public class PaladinFragment extends Fragment
{
    RatingBar paladinStrength;
    RatingBar paladinIntellect;
    RatingBar paladinWisdom;
    RatingBar paladinDexterity;
    TextView paladinPoints;
    SharedPreferences sharedStats;
    SharedPreferences.Editor editor;

    int strengthRating;
    int intellectRating;
    int wisdomRating;
    int dexterityRating;
    int totalRating;

    //calculates new total points left to distribute
    public void newTotal()
    {
        totalRating = (10 - (strengthRating + intellectRating + wisdomRating + dexterityRating));
        paladinPoints.setText(String.valueOf(totalRating));
    }

    //checks to see if new rating is allowed
    public boolean isAllowed()
    {
        if(10 >= (strengthRating + intellectRating + wisdomRating + dexterityRating))
            return true;
        else
            return false;
    }

    //listens for changes in rating bar
    public void ratingBarListener()
    {
        paladinStrength.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener()
        {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b)
            {
                int tempStrength = strengthRating; //placeholder for old value
                strengthRating = Math.round(paladinStrength.getRating());
                if(isAllowed() == true)
                {
                    newTotal();
                }
                else
                {
                    strengthRating = tempStrength;
                    paladinStrength.setRating(strengthRating);
                }
            }
        });

        paladinIntellect.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener()
        {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b)
            {
                int tempIntellect = intellectRating; //placeholder for old value
                intellectRating = Math.round(paladinIntellect.getRating());
                if(isAllowed() == true)
                {
                    newTotal();
                }
                else
                {
                    intellectRating = tempIntellect;
                    paladinIntellect.setRating(intellectRating);
                }
            }
        });

        paladinWisdom.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener()
        {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b)
            {
                int tempWisdom = wisdomRating; //placeholder for old value
                wisdomRating = Math.round(paladinWisdom.getRating());
                if(isAllowed() == true)
                {
                    newTotal();
                }
                else
                {
                    wisdomRating = tempWisdom;
                    paladinWisdom.setRating(wisdomRating);
                }
            }
        });

        paladinDexterity.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener()
        {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b)
            {
                int tempDexterity = dexterityRating; //placeholder for old value
                dexterityRating = Math.round(paladinDexterity.getRating());
                if(isAllowed() == true)
                {
                    newTotal();
                }
                else
                {
                    dexterityRating = tempDexterity;
                    paladinDexterity.setRating(dexterityRating);
                }
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.paladin_fragment_layout, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        //map variables to widgets
        paladinStrength = getView().findViewById(R.id.paladinStrength);
        paladinIntellect = getView().findViewById(R.id.paladinIntellect);
        paladinWisdom = getView().findViewById(R.id.paladinWisdom);
        paladinDexterity = getView().findViewById(R.id.paladinDexterity);
        paladinPoints = getView().findViewById(R.id.paladinPoints);
        sharedStats = getActivity().getSharedPreferences("paladinStats", Context.MODE_PRIVATE);
        editor = sharedStats.edit();

        ratingBarListener();
    }

    @Override
    public void onPause()
    {
        //save values from widgets
        editor.putInt("strength", Math.round(paladinStrength.getRating()));
        editor.putInt("intellect", Math.round(paladinIntellect.getRating()));
        editor.putInt("wisdom", Math.round(paladinWisdom.getRating()));
        editor.putInt("dexterity", Math.round(paladinDexterity.getRating()));
        editor.apply();

        super.onPause();
    }

    @Override
    public void onResume()
    {
        //load stats
        int tempStrength = sharedStats.getInt("strength",0);
        int tempIntellect = sharedStats.getInt("intellect",0);
        int tempWisdom = sharedStats.getInt("wisdom",0);
        int tempDexterity = sharedStats.getInt("dexterity",0);

        paladinStrength.setRating(tempStrength);
        paladinIntellect.setRating(tempIntellect);
        paladinWisdom.setRating(tempWisdom);
        paladinDexterity.setRating(tempDexterity);

        super.onResume();
    }
}