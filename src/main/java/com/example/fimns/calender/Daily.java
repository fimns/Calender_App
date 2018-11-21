package com.example.fimns.calender;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Daily extends Fragment {
    Button Creation, Next, Before;
    private Calendars Cal_Day = new Calendars();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle saveInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.daily, container, false);
        final TextView Text_Day = (TextView) rootView.findViewById(R.id.Text_Day);


        Text_Day.setText(String.valueOf(Cal_Day.Year) + "." + String.valueOf(Cal_Day.Month) + "." + String.valueOf(Cal_Day.Day + "."));
        Creation = (Button) rootView.findViewById(R.id.Create);
        Next = (Button) rootView.findViewById(R.id.Button_Next);
        Before = (Button) rootView.findViewById(R.id.Button_Before);


        rootView.setOnTouchListener(new View.OnTouchListener() {
            float sx, sy, lx, ly;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {

                }

                if (event.getAction() == MotionEvent.ACTION_DOWN) {

                }

                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    sx = event.getX(); sy = event.getY();
                }

                return false;
            }
        });

        Creation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = (TextView)rootView.findViewById(R.id.input);
                int today = Cal_Day.getDay(Cal_Day.Year,Cal_Day.Month,Cal_Day.Day);
                if(text.getText().length() >= 21) {
                    Toast.makeText(getActivity(),"일정은 20자 이하로 입력해 주세요.", Toast.LENGTH_SHORT).show();
                }
                else if (text.getText().length() == 0 ) {
                    Toast.makeText(getActivity(),"일정을 1자 이상 입력해 주세요.", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (Cal_Day.getTodayTaskNums(today) <= 4) {
                        //Toast.makeText(getActivity(),String.valueOf(today),Toast.LENGTH_SHORT).show();
                        Cal_Day.addTask(today, String.valueOf(text.getText()));
                    }
                    else Toast.makeText(getActivity(), "일정은 5개까지만 가능합니다", Toast.LENGTH_SHORT).show();
                    text.setText("");
                }
                Re_View(rootView);
            }
        });

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cal_Day.Next_Day();
                Text_Day.setText(String.valueOf(Cal_Day.Year) + "." + String.valueOf(Cal_Day.Month) + "." + String.valueOf(Cal_Day.Day + "."));
                Re_View(rootView);
            }
        });

        Before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cal_Day.Before_Day();
                Text_Day.setText(String.valueOf(Cal_Day.Year) + "." + String.valueOf(Cal_Day.Month) + "." + String.valueOf(Cal_Day.Day + "."));
                Re_View(rootView);

            }
        });



        ListView list = (ListView) rootView.findViewById(R.id.listview);
        int today = Cal_Day.getDay(Cal_Day.Year,Cal_Day.Month,Cal_Day.Day);
        String []temp = new String[Cal_Day.getTodayTaskNums(today)];
        for(int i=0; i<temp.length; i++) temp[i] = Cal_Day.getTodayTask(today)[i];
        ArrayAdapter adapt = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1 , temp);
        list.setAdapter(adapt);


        final int[] temp1 = {0};

       list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                int today = Cal_Day.getDay(Cal_Day.Year,Cal_Day.Month,Cal_Day.Day);
                Cal_Day.delTask(Cal_Day.getDay(Cal_Day.Year,Cal_Day.Month,Cal_Day.Day),position);
                Toast.makeText(getActivity(),"삭제 완료",Toast.LENGTH_SHORT).show();
                Re_View(rootView);
                temp1[0] = 1;
                return false;
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(temp1[0] == 0 ) Toast.makeText(getActivity(),"길게 누르면 일정을 삭제합니다",Toast.LENGTH_SHORT).show();
                else temp1[0] = 0;
            }
        });
        return rootView;
    }

    public void Re_View(ViewGroup rootView) {
        ListView list = (ListView) rootView.findViewById(R.id.listview);
        int today = Cal_Day.getDay(Cal_Day.Year,Cal_Day.Month,Cal_Day.Day);
        String []temp = new String[Cal_Day.getTodayTaskNums(today)];
        for(int i=0; i<temp.length; i++) temp[i] = Cal_Day.getTodayTask(today)[i];
        ArrayAdapter adapt = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1 , temp);
        list.setAdapter(adapt);
    }
}