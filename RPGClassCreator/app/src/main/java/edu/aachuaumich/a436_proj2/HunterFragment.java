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
 * Fragment for hunter
 *****************************/

public class HunterFragment extends Fragment
{
    RatingBar hunterStrength;
    RatingBar hunterIntellect;
    RatingBar hunterWisdom;
    RatingBar hunterDexterity;
    TextView hunterPoints;
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
        hunterPoints.setText(String.valueOf(totalRating));
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
        hunterStrength.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener()
        {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b)
            {
                int tempStrength = strengthRating; //placeholder for old value
                strengthRating = Math.round(hunterStrength.getRating());
                if(isAllowed() == true)
                {
                    newTotal();
                }
                else
                {
                    strengthRating = tempStrength;
                    hunterStrength.setRating(strengthRating);
                }
            }
        });

        hunterIntellect.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener()
        {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b)
            {
                int tempIntellect = intellectRating; //placeholder for old value
                intellectRating = Math.round(hunterIntellect.getRating());
                if(isAllowed() == true)
                {
                    newTotal();
                }
                else
                {
                    intellectRating = tempIntellect;
                    hunterIntellect.setRating(intellectRating);
                }
            }
        });

        hunterWisdom.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener()
        {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b)
            {
                int tempWisdom = wisdomRating; //placeholder for old value
                wisdomRating = Math.round(hunterWisdom.getRating());
                if(isAllowed() == true)
                {
                    newTotal();
                }
                else
                {
                    wisdomRating = tempWisdom;
                    hunterWisdom.setRating(wisdomRating);
                }
            }
        });

        hunterDexterity.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener()
        {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b)
            {
                int tempDexterity = dexterityRating; //placeholder for old value
                dexterityRating = Math.round(hunterDexterity.getRating());
                if(isAllowed() == true)
                {
                    newTotal();
                }
                else
                {
                    dexterityRating = tempDexterity;
                    hunterDexterity.setRating(dexterityRating);
                }
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.hunter_fragment_layout, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        //map variables to widgets
        hunterStrength = getView().findViewById(R.id.hunterStrength);
        hunterIntellect = getView().findViewById(R.id.hunterIntellect);
        hunterWisdom = getView().findViewById(R.id.hunterWisdom);
        hunterDexterity = getView().findViewById(R.id.hunterDexterity);
        hunterPoints = getView().findViewById(R.id.hunterPoints);
        sharedStats = getActivity().getSharedPreferences("hunterStats", Context.MODE_PRIVATE);
        editor = sharedStats.edit();

        ratingBarListener();
    }

    @Override
    public void onPause()
    {
        //save values from widgets
        editor.putInt("strength", Math.round(hunterStrength.getRating()));
        editor.putInt("intellect", Math.round(hunterIntellect.getRating()));
        editor.putInt("wisdom", Math.round(hunterWisdom.getRating()));
        editor.putInt("dexterity", Math.round(hunterDexterity.getRating()));
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

        hunterStrength.setRating(tempStrength);
        hunterIntellect.setRating(tempIntellect);
        hunterWisdom.setRating(tempWisdom);
        hunterDexterity.setRating(tempDexterity);

        super.onResume();
    }
}