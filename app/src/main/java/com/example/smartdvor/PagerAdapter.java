package com.example.smartdvor;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> fragmentList = new ArrayList<>();

    public PagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    void addFragmet(Fragment fragment) {
        fragmentList.add(fragment);
    }

//    @NonNull
//    @Override
//    public Fragment getItem(int position) {
//
//        switch (position) {
//            case 0:
//                LoginFragment loginFragment = new LoginFragment();
//                return loginFragment;
//            case 1:
//                RegisterFragment registerFragment = new RegisterFragment();
//                return registerFragment;
//            default:
//                return null;
//        }
//
//    }
//
//    @Override
//    public int getCount() {
//        return 0;
//    }
}
