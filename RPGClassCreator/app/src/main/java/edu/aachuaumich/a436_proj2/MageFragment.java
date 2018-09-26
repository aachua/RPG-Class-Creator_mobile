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
 * Fragment for mage
 *****************************/

public class MageFragment extends Fragment
{
    RatingBar mageStrength;
    RatingBar mageIntellect;
    RatingBar mageWisdom;
    RatingBar mageDexterity;
    TextView magePoints;
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
        magePoints.setText(String.valueOf(totalRating));
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
        mageStrength.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener()
        {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b)
            {
                int tempStrength = strengthRating; //placeholder for old value
                strengthRating = Math.round(mageStrength.getRating());
                if(isAllowed() == true)
                {
                    newTotal();
                }
                else
                {
                    strengthRating = tempStrength;
                    mageStrength.setRating(strengthRating);
                }
            }
        });

        mageIntellect.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener()
        {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b)
            {
                int tempIntellect = intellectRating; //placeholder for old value
                intellectRating = Math.round(mageIntellect.getRating());
                if(isAllowed() == true)
                {
                    newTotal();
                }
                else
                {
                    intellectRating = tempIntellect;
                    mageIntellect.setRating(intellectRating);
                }
            }
        });

        mageWisdom.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener()
        {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b)
            {
                int tempWisdom = wisdomRating; //placeholder for old value
                wisdomRating = Math.round(mageWisdom.getRating());
                if(isAllowed() == true)
                {
                    newTotal();
                }
                else
                {
                    wisdomRating = tempWisdom;
                    mageWisdom.setRating(wisdomRating);
                }
            }
        });

        mageDexterity.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener()
        {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b)
            {
                int tempDexterity = dexterityRating; //placeholder for old value
                dexterityRating = Math.round(mageDexterity.getRating());
                if(isAllowed() == true)
                {
                    newTotal();
                }
                else
                {
                    dexterityRating = tempDexterity;
                    mageDexterity.setRating(dexterityRating);
                }
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.mage_fragment_layout, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        //map variables to widgets
        mageStrength = getView().findViewById(R.id.mageStrength);
        mageIntellect = getView().findViewById(R.id.mageIntellect);
        mageWisdom = getView().findViewById(R.id.mageWisdom);
        mageDexterity = getView().findViewById(R.id.mageDexterity);
        magePoints = getView().findViewById(R.id.magePoints);
        sharedStats = getActivity().getSharedPreferences("mageStats", Context.MODE_PRIVATE);
        editor = sharedStats.edit();

        ratingBarListener();
    }

    @Override
    public void onPause()
    {
        //save values from widgets
        editor.putInt("strength", Math.round(mageStrength.getRating()));
        editor.putInt("intellect", Math.round(mageIntellect.getRating()));
        editor.putInt("wisdom", Math.round(mageWisdom.getRating()));
        editor.putInt("dexterity", Math.round(mageDexterity.getRating()));
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

        mageStrength.setRating(tempStrength);
        mageIntellect.setRating(tempIntellect);
        mageWisdom.setRating(tempWisdom);
        mageDexterity.setRating(tempDexterity);

        super.onResume();
    }
}