package edu.aachuaumich.a436_proj2;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;


/*****************************
 * Created by Prof. John P. Baugh on 3/18/2018
 * Availability: List Fragments and Fragments Tutorial by Prof. John P. Baugh
 * Edited by Anthony Chua on 3/22/2018
 *****************************/

public class ProfessionsActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.specific_profession);

        Intent intent = getIntent();
        int whichProf = intent.getIntExtra("whichProf", -1);
        Fragment specificProf = null;

        if(whichProf == 0)
        {
            specificProf = new WarriorFragment();
        }
        else if(whichProf == 1)
        {
            specificProf = new MageFragment();
        }
        else if (whichProf == 2)
        {
            specificProf = new HealerFragment();
        }
        else if(whichProf == 3)
        {
            specificProf = new HunterFragment();
        }
        else if(whichProf == 4)
        {
            specificProf = new PaladinFragment();
        }

        if(specificProf != null)
        {
            FragmentManager fragmentManager =
                    getFragmentManager();
            FragmentTransaction fragmentTransaction =
                    fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.profession_frame,
                    specificProf);
            fragmentTransaction.commit();
        }
    }
}
