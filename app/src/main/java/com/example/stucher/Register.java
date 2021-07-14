package com.example.stucher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

public class Register extends AppCompatActivity {
    //TabLayout : used to implement horizontal tabs.
    //ViewPager widget : it allows the user to swipe left or right to see an entirely new screen.
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        viewPager=(ViewPager)findViewById(R.id.viewPager);

        //setText() : sets the name of the tab
        //newTab() : new tabs are created
        //addTab() : tabs of layout are attached over TabLayout
        //setTabGravity : set the gravity to use when laying out the tabs.
        tabLayout.addTab(tabLayout.newTab().setText("PRINCIPAL"));
        tabLayout.addTab(tabLayout.newTab().setText("STAFF"));
        tabLayout.addTab(tabLayout.newTab().setText("STUDENT"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //addOnPageChangeListener() : makes slides the different fragments of tabs
        //addOnTabSelectedListener : select the current tab on tab selection.
        final CombineSections adapter = new CombineSections (this,getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}
