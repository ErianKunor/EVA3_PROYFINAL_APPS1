package com.example.bisonapp30;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerController extends FragmentPagerAdapter {
    int numTabs;

    public PagerController(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numTabs = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Calendario();
            case 1:
                return new Tareas();
            case 2:
                return new Libre();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numTabs;
    }
}
