package com.example.yoshievinsmoke.ayomangan.tab;

/**
 * Created by yoshievinsmoke on 5/1/18.
 */

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;

import com.example.yoshievinsmoke.ayomangan.R;
import com.example.yoshievinsmoke.ayomangan.fragment.HomeFragment;
import com.example.yoshievinsmoke.ayomangan.fragment.ProfilFragment;


/**
 * Created by Ujang Wahyu on 18/08/2016.
 */

public class MyAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private String[] titles = {"A", "B", "C", "D"};
    int[] icon = new int[]{R.drawable.ic_home_black_24dp, R.drawable.ic_account_box_black_24dp, R.drawable.ic_local_grocery_store_black_24dp};
    private int heightIcon;

    public MyAdapter(FragmentManager fm, Context c) {
        super(fm);
        mContext = c;
        double scale = c.getResources().getDisplayMetrics().density;
        heightIcon = (int) (24 * scale + 0.5f);
    }

    @Override
    public Fragment getItem(int position) {

        return null;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

//    @Override
//    public Fragment getItem(int position) {
//        Fragment frag = null;

//
//        @Override
//        public int getCount () {
//            return titles.length;
//        }
//
//        public CharSequence getPageTitle ( int position){
//            Drawable d = mContext.getResources().getDrawable(icon[position]);
//            d.setBounds(0, 0, heightIcon, heightIcon);
//            ImageSpan is = new ImageSpan(d);
//
//            SpannableString sp = new SpannableString(" ");
//            sp.setSpan(is, 0, sp.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//            return sp;
//        }
//    }


    @Override
    public CharSequence getPageTitle(int position) {
            Drawable d = mContext.getResources().getDrawable(icon[position]);
            d.setBounds(0, 0, heightIcon, heightIcon);
            ImageSpan is = new ImageSpan(d);

            SpannableString sp = new SpannableString(" ");
            sp.setSpan(is, 0, sp.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            return sp;
    }
}