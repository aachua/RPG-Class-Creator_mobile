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
 * Fragment for warrior
 *****************************/

public class WarriorFragment extends Fragment
{
    RatingBar warriorStrength;
    RatingBar warriorIntellect;
    RatingBar warriorWisdom;
    RatingBar warriorDexterity;
    TextView warriorPoints;
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
        warriorPoints.setText(String.valueOf(totalRating));
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
        warriorStrength.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener()
        {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b)
            {
                int tempStrength = strengthRating; //placeholder for old value
                strengthRating = Math.round(warriorStrength.getRating());
                if(isAllowed() == true)
                {
                    newTotal();
                }
                else
                {
                    strengthRating = tempStrength;
                    warriorStrength.setRating(strengthRating);
                }
            }
        });

        warriorIntellect.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener()
        {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b)
            {
                int tempIntellect = intellectRating; //placeholder for old value
                intellectRating = Math.round(warriorIntellect.getRating());
                if(isAllowed() == true)
                {
                    newTotal();
                }
                else
                {
                    intellectRating = tempIntellect;
                    warriorIntellect.setRating(intellectRating);
                }
            }
        });

        warriorWisdom.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener()
        {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b)
            {
                int tempWisdom = wisdomRating; //placeholder for old value
                wisdomRating = Math.round(warriorWisdom.getRating());
                if(isAllowed() == true)
                {
                    newTotal();
                }
                else
                {
                    wisdomRating = tempWisdom;
                    warriorWisdom.setRating(wisdomRating);
                }
            }
        });

        warriorDexterity.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener()
        {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b)
            {
                int tempDexterity = dexterityRating; //placeholder for old value
                dexterityRating = Math.round(warriorDexterity.getRating());
                if(isAllowed() == true)
                {
                    newTotal();
                }
                else
                {
                    dexterityRating = tempDexterity;
                    warriorDexterity.setRating(dexterityRating);
                }
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.warrior_fragment_layout, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        //map variables to widgets
        warriorStrength = getView().findViewById(R.id.warriorStrength);
        warriorIntellect = getView().findViewById(R.id.warriorIntellect);
        warriorWisdom = getView().findViewById(R.id.warriorWisdom);
        warriorDexterity = getView().findViewById(R.id.warriorDexterity);
        warriorPoints = getView().findViewById(R.id.warriorPoints);
        sharedStats = getActivity().getSharedPreferences("warriorStats", Context.MODE_PRIVATE);
        editor = sharedStats.edit();

        ratingBarListener();
    }

    @Override
    public void onPause()
    {
        //save values from widgets
        editor.putInt("strength", Math.round(warriorStrength.getRating()));
        editor.putInt("intellect", Math.round(warriorIntellect.getRating()));
        editor.putInt("wisdom", Math.round(warriorWisdom.getRating()));
        editor.putInt("dexterity", Math.round(warriorDexterity.getRating()));
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

        warriorStrength.setRating(tempStrength);
        warriorIntellect.setRating(tempIntellect);
        warriorWisdom.setRating(tempWisdom);
        warriorDexterity.setRating(tempDexterity);

        super.onResume();
    }
}