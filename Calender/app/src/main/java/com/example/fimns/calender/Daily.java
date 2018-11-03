package com.example.fimns.calender;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
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
    public static Task task = new Task();
    Calendars_Day Cal_Day = new Calendars_Day();
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


        Creation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = (TextView)rootView.findViewById(R.id.input);
                int today = Cal_Day.getDay(Cal_Day.Year,Cal_Day.Month,Cal_Day.Day) - 737000;
                if(text.getText().length() >= 21) {
                    Toast.makeText(getActivity(),"일정은 20자 이하로 입력해 주세요.", Toast.LENGTH_SHORT).show();

                }
                else if (text.getText().length() == 0 ) {
                    Toast.makeText(getActivity(),"일정을 1자 이상 입력해 주세요.", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (task.num[today] <= 4) {
                        Main.db.execSQL("insert into task(i, num, ta) values ( "+ String.valueOf(today)+" , "+ String.valueOf(task.num[today])+" ,'"+
                                String.valueOf(text.getText())+"') ;");
                   //     Toast.makeText(getActivity(),String.valueOf(today),Toast.LENGTH_SHORT).show();
                        task.tasks[today][task.num[today]] = String.valueOf(text.getText());
                        task.num[today] = task.num[today] + 1;
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
        int i;
        ListView list = (ListView) rootView.findViewById(R.id.listview);
        int today = Cal_Day.getDay(Cal_Day.Year,Cal_Day.Month,Cal_Day.Day) - 737000;
        String []temp = new String[task.num[today]];
        for(i=0; i<task.num[today]; i++) {
            temp[i] = task.tasks[today][i];
        }
        ArrayAdapter adapt = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1 ,temp);
        list.setAdapter(adapt);
        final int[] temp1 = {0};
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                int t_id = Cal_Day.getDay(Cal_Day.Year,Cal_Day.Month,Cal_Day.Day);
                int today = t_id - 737000;
                task.Del(Cal_Day.getDay(Cal_Day.Year,Cal_Day.Month,Cal_Day.Day),position);
                Toast.makeText(getActivity(),"삭제 완료",Toast.LENGTH_SHORT).show();
                temp1[0] = 1;
      //          Toast.makeText(getActivity(),String.valueOf(t_id-737000),Toast.LENGTH_SHORT).show();
                Main.db.execSQL("delete from task where i=" + String.valueOf(today) + ";");
                for(int j=0; j<task.num[today]; j++) {
                    Main.db.execSQL("insert into task(i, num, ta) values ( "+ String.valueOf(today)+" , "+ String.valueOf(j)+" ,'"+
                            String.valueOf(task.tasks[today][j])+"') ;");
                }
                Re_View(rootView);
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
        int i;
        ListView list = (ListView) rootView.findViewById(R.id.listview);
        int today = Cal_Day.getDay(Cal_Day.Year,Cal_Day.Month,Cal_Day.Day) - 737000;
        String []temp = new String[task.num[today]];
        for(i=0; i<task.num[today]; i++) {
            temp[i] = task.tasks[today][i];
        }
        ArrayAdapter adapt = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1 ,temp);
        list.setAdapter(adapt);
    }
}