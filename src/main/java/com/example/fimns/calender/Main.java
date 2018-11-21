package com.example.fimns.calender;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class Main extends AppCompatActivity {

    Calendars CalendarM;
    Weekly Week;
    Monthly Month;
    Daily Day;
    TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        getSupportActionBar().hide();
        Week = new Weekly();
        Month = new Monthly();
        Day = new Daily();
        CalendarM = new Calendars(getApplicationContext());
        Intent intent = new Intent(getApplicationContext(), full.class);
        startActivity(intent);

        tabs = (TabLayout) findViewById(R.id.tabLayout);
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                                    @Override
                                    public void onTabSelected(TabLayout.Tab tab) {
                                        int State = tab.getPosition();
                                        onFragmentChanged(State+1);
                                    }
                                    @Override
                                    public void onTabUnselected(TabLayout.Tab tab) {
                                    }
                                    @Override
                                    public void onTabReselected(TabLayout.Tab tab) {}});

    }

    public void onFragmentChanged(int index) {
        if ( index == 1 ) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, Month).commit();
        }
        else if (index == 2 ) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, Week).commit();
        }
        else if ( index == 3 ) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, Day).commit();
        }
    }
    View.OnClickListener Listener_Month = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onFragmentChanged(1);
        }
    };
    View.OnClickListener Listener_Week = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onFragmentChanged(2);

        }
    };
    View.OnClickListener Listener_Day = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onFragmentChanged(3);
        }
    };

    private int max(int a, int b) {
        if ( a > b) return a;
        else return b;
    }
}