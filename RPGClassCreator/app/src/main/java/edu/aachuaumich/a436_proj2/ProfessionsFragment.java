package edu.aachuaumich.a436_proj2;

import android.app.ListFragment;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

/*****************************
 * Created by Prof. John P. Baugh on 3/18/2018
 * Availability: List Fragments and Fragments Tutorial by Prof. John P. Baugh
 * Edited by Anthony Chua on 3/22/2018
 * Handles the creation of the view, activity,
 *****************************/

public class ProfessionsFragment extends ListFragment implements AdapterView.OnItemClickListener
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.list_frag, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.Professions, android.R.layout.simple_list_item_1);

        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        View placeholder = getActivity().findViewById(R.id.profession_frame);

        if (placeholder == null)
        {
            Intent intent = new Intent(getActivity(), ProfessionsActivity.class);
            intent.putExtra("whichProf", i); //send position int
            startActivity(intent);
        }
        else
        {
            Fragment specificProf = null;
            int whichProf = i;

            if (whichProf == 0)
            {
                specificProf = new WarriorFragment();
            } else if (whichProf == 1) {
                specificProf = new MageFragment();
            } else if (whichProf == 2) {
                specificProf = new HealerFragment();
            } else if (whichProf == 3) {
                specificProf = new HunterFragment();
            } else if (whichProf == 4) {
                specificProf = new PaladinFragment();
            }
            if (specificProf != null) {
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
}