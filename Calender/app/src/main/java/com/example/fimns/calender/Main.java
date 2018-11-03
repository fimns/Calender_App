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
    Weekly Week;
    Monthly Month;
    Daily Day;
    TabLayout tabs;
    public static SQLiteDatabase db;
   // public DatabaseHelper dbHelper;
    int chk;
  //  Button ToMon, ToWeek, ToDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ///////--Database--//////////
        db = openOrCreateDatabase("DB",MODE_PRIVATE,null);
     //   db.execSQL("drop table task");
        try {
            db.execSQL("create table task (i integer, num integer, ta text);");
        } catch(Exception ex) {
        }
        Cursor c1 = db.rawQuery("select * from task;",null);
        int count = c1.getCount(), j;
        int _id, _num;
        String _task;
        for(int i =0; i<count; i++) {
            c1.moveToNext();
            _id = c1.getInt(0);
            _num = c1.getInt(1);
            Daily.task.num[_id] = max(Daily.task.num[_id],_num+1);
            _task = c1.getString(2);
            Daily.task.tasks[_id][_num] = _task;
        }
        c1.close();

        /////////////
        setContentView(R.layout.main);
        getSupportActionBar().hide();
        Week = new Weekly();
        Month = new Monthly();
        Day = new Daily();
        chk = 0;

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

        //  ToMon = (Button) findViewById(R.id.ToMonth);
      //  ToMon.setOnClickListener(Listener_Month);
       // ToWeek = (Button) findViewById(R.id.ToWeek);
      //  ToWeek.setOnClickListener(Listener_Week);
       // ToDay = (Button) findViewById(R.id.ToDay);
     //   ToDay.setOnClickListener(Listener_Day);
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
