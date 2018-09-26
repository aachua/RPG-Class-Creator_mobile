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
 * Fragment for healer
 *****************************/

public class HealerFragment extends Fragment
{
    RatingBar healerStrength;
    RatingBar healerIntellect;
    RatingBar healerWisdom;
    RatingBar healerDexterity;
    TextView healerPoints;
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
        healerPoints.setText(String.valueOf(totalRating));
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
        healerStrength.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener()
        {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b)
            {
                int tempStrength = strengthRating; //placeholder for old value
                strengthRating = Math.round(healerStrength.getRating());
                if(isAllowed() == true)
                {
                    newTotal();
                }
                else
                {
                    strengthRating = tempStrength;
                    healerStrength.setRating(strengthRating);
                }
            }
        });

        healerIntellect.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener()
        {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b)
            {
                int tempIntellect = intellectRating; //placeholder for old value
                intellectRating = Math.round(healerIntellect.getRating());
                if(isAllowed() == true)
                {
                    newTotal();
                }
                else
                {
                    intellectRating = tempIntellect;
                    healerIntellect.setRating(intellectRating);
                }
            }
        });

        healerWisdom.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener()
        {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b)
            {
                int tempWisdom = wisdomRating; //placeholder for old value
                wisdomRating = Math.round(healerWisdom.getRating());
                if(isAllowed() == true)
                {
                    newTotal();
                }
                else
                {
                    wisdomRating = tempWisdom;
                    healerWisdom.setRating(wisdomRating);
                }
            }
        });

        healerDexterity.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener()
        {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b)
            {
                int tempDexterity = dexterityRating; //placeholder for old value
                dexterityRating = Math.round(healerDexterity.getRating());
                if(isAllowed() == true)
                {
                    newTotal();
                }
                else
                {
                    dexterityRating = tempDexterity;
                    healerDexterity.setRating(dexterityRating);
                }
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.healer_fragment_layout, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        //map variables to widgets
        healerStrength = getView().findViewById(R.id.healerStrength);
        healerIntellect = getView().findViewById(R.id.healerIntellect);
        healerWisdom = getView().findViewById(R.id.healerWisdom);
        healerDexterity = getView().findViewById(R.id.healerDexterity);
        healerPoints = getView().findViewById(R.id.healerPoints);
        sharedStats = getActivity().getSharedPreferences("healerStats", Context.MODE_PRIVATE);
        editor = sharedStats.edit();

        ratingBarListener();
    }

    @Override
    public void onPause()
    {
        //save values from widgets
        editor.putInt("strength", Math.round(healerStrength.getRating()));
        editor.putInt("intellect", Math.round(healerIntellect.getRating()));
        editor.putInt("wisdom", Math.round(healerWisdom.getRating()));
        editor.putInt("dexterity", Math.round(healerDexterity.getRating()));
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

        healerStrength.setRating(tempStrength);
        healerIntellect.setRating(tempIntellect);
        healerWisdom.setRating(tempWisdom);
        healerDexterity.setRating(tempDexterity);

        super.onResume();
    }
}