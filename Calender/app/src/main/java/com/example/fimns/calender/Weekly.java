package com.example.fimns.calender;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Weekly extends Fragment {
    Calendars_Week Cal_Weekly = new Calendars_Week();
    final String []Weekend = {"일","월","화","수","목","금","토"};

    public void Re_Calender(ViewGroup rootView) {
        int Weeks;
        final TextView[]Week_Calender = new TextView[36];
        final TextView[]W = new TextView[8];

        W[1] = (TextView) rootView.findViewById(R.id.W1);
        W[2] = (TextView) rootView.findViewById(R.id.W2);
        W[3] = (TextView) rootView.findViewById(R.id.W3);
        W[4] = (TextView) rootView.findViewById(R.id.W4);
        W[5] = (TextView) rootView.findViewById(R.id.W5);
        W[6] = (TextView) rootView.findViewById(R.id.W6);
        W[7] = (TextView) rootView.findViewById(R.id.W7);
        Week_Calender[1] = (TextView) rootView.findViewById(R.id.T1);
        Week_Calender[2] = (TextView) rootView.findViewById(R.id.T2);
        Week_Calender[3] = (TextView) rootView.findViewById(R.id.T3);
        Week_Calender[4] = (TextView) rootView.findViewById(R.id.T4);
        Week_Calender[5] = (TextView) rootView.findViewById(R.id.T5);
        Week_Calender[6] = (TextView) rootView.findViewById(R.id.T6);
        Week_Calender[7] = (TextView) rootView.findViewById(R.id.T7);
        Week_Calender[8] = (TextView) rootView.findViewById(R.id.T8);
        Week_Calender[9] = (TextView) rootView.findViewById(R.id.T9);
        Week_Calender[10] = (TextView) rootView.findViewById(R.id.T10);
        Week_Calender[11] = (TextView) rootView.findViewById(R.id.T11);
        Week_Calender[12]= (TextView) rootView.findViewById(R.id.T12);
        Week_Calender[13] = (TextView) rootView.findViewById(R.id.T13);
        Week_Calender[14] = (TextView) rootView.findViewById(R.id.T14);
        Week_Calender[15] = (TextView) rootView.findViewById(R.id.T15);
        Week_Calender[16] = (TextView) rootView.findViewById(R.id.T16);
        Week_Calender[17] = (TextView) rootView.findViewById(R.id.T17);
        Week_Calender[18] = (TextView) rootView.findViewById(R.id.T18);
        Week_Calender[19] = (TextView) rootView.findViewById(R.id.T19);
        Week_Calender[20] = (TextView) rootView.findViewById(R.id.T20);
        Week_Calender[21] = (TextView) rootView.findViewById(R.id.T21);
        Week_Calender[22] = (TextView) rootView.findViewById(R.id.T22);
        Week_Calender[23] = (TextView) rootView.findViewById(R.id.T23);
        Week_Calender[24] = (TextView) rootView.findViewById(R.id.T24);
        Week_Calender[25] = (TextView) rootView.findViewById(R.id.T25);
        Week_Calender[26] = (TextView) rootView.findViewById(R.id.T26);
        Week_Calender[27] = (TextView) rootView.findViewById(R.id.T27);
        Week_Calender[28] = (TextView) rootView.findViewById(R.id.T28);
        Week_Calender[29] = (TextView) rootView.findViewById(R.id.T29);
        Week_Calender[30] = (TextView) rootView.findViewById(R.id.T30);
        Week_Calender[31] = (TextView) rootView.findViewById(R.id.T31);
        Week_Calender[32] = (TextView) rootView.findViewById(R.id.T32);
        Week_Calender[33] = (TextView) rootView.findViewById(R.id.T33);
        Week_Calender[34] = (TextView) rootView.findViewById(R.id.T34);
        Week_Calender[35] = (TextView) rootView.findViewById(R.id.T35);

       int weeks = Cal_Weekly.Week;
       int c= 0, i, j;
       for(i = (weeks-1)*7+1; i<=(weeks-1)*7+7; i++) {
            W[(i-1)%7+1].setText(Weekend[(i-1)%7] + "\n(" + String.valueOf(Cal_Weekly.Calender[i]) + ")");
            if(Cal_Weekly.Calender_State[i] == 0 ) W[(i-1)%7+1].setTextColor(Color.parseColor("#FFCACACA"));
            else  {
                for(j=0; j<5; j++) Week_Calender[((i-1)%7+1) + (j*7)].setText("");
                for(j=0; j<Daily.task.getNum(Cal_Weekly.Year,Cal_Weekly.Month,Cal_Weekly.Calender[i]); j++) {
                    Week_Calender[((i-1)%7+1) + (j*7)].setText(
                            Daily.task.tasks[Daily.task.getDay(Cal_Weekly.Year,Cal_Weekly.Month,Cal_Weekly.Calender[i])][j]);
                }
                if((i-1)%7 == 0 ) W[(i-1)%7+1].setTextColor(Color.parseColor("#F44F3D"));
                else if((i-1)%7 == 6 ) W[(i-1)%7+1].setTextColor(Color.parseColor("#2D49FF"));
                else W[(i-1)%7+1].setTextColor(Color.parseColor("#000000"));
           }
        }
        for(i = (weeks-1)*7+1; i<=(weeks-1)*7+7; i++) {
            Daily.task.isTask(Cal_Weekly.Year, Cal_Weekly.Month, Cal_Weekly.Calender[i]);
        }
        TextView Text_Week = (TextView) rootView.findViewById(R.id.Text_Week);
        Text_Week.setText(String.valueOf(Cal_Weekly.Year) + "." + String.valueOf(Cal_Weekly.Month) +"." + String.valueOf(Cal_Weekly.Week) + "주");

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle saveInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.weekly, container, false);

        Button Next = (Button) rootView.findViewById(R.id.Button_Next);
        final TextView Text_Week = (TextView) rootView.findViewById(R.id.Text_Week);
        Button Before = (Button) rootView.findViewById(R.id.Button_Before);
        Re_Calender(rootView);
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cal_Weekly.Next_Week();
                Re_Calender(rootView);
            }
        });
        Before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cal_Weekly.Before_Week();
                Re_Calender(rootView);
            }
        });

        return rootView;
    }

}
