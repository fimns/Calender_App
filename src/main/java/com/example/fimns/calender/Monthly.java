package com.example.fimns.calender;

import android.app.Activity;
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
import android.widget.Toast;

import java.util.Date;

public class Monthly extends Fragment {
    Button Before, Next;
    private static Calendars Cal_Month = new Calendars();
    public void Re_Calender(ViewGroup rootView) {

        final TextView []Month_Calender = new TextView[43];
        Month_Calender[1] = (TextView) rootView.findViewById(R.id.T1);
        Month_Calender[2] = (TextView) rootView.findViewById(R.id.T2);
        Month_Calender[3] = (TextView) rootView.findViewById(R.id.T3);
        Month_Calender[4] = (TextView) rootView.findViewById(R.id.T4);
        Month_Calender[5] = (TextView) rootView.findViewById(R.id.T5);
        Month_Calender[6] = (TextView) rootView.findViewById(R.id.T6);
        Month_Calender[7] = (TextView) rootView.findViewById(R.id.T7);
        Month_Calender[8] = (TextView) rootView.findViewById(R.id.T8);
        Month_Calender[9] = (TextView) rootView.findViewById(R.id.T9);
        Month_Calender[10] = (TextView) rootView.findViewById(R.id.T10);
        Month_Calender[11] = (TextView) rootView.findViewById(R.id.T11);
        Month_Calender[12]= (TextView) rootView.findViewById(R.id.T12);
        Month_Calender[13] = (TextView) rootView.findViewById(R.id.T13);
        Month_Calender[14] = (TextView) rootView.findViewById(R.id.T14);
        Month_Calender[15] = (TextView) rootView.findViewById(R.id.T15);
        Month_Calender[16] = (TextView) rootView.findViewById(R.id.T16);
        Month_Calender[17] = (TextView) rootView.findViewById(R.id.T17);
        Month_Calender[18] = (TextView) rootView.findViewById(R.id.T18);
        Month_Calender[19] = (TextView) rootView.findViewById(R.id.T19);
        Month_Calender[20] = (TextView) rootView.findViewById(R.id.T20);
        Month_Calender[21] = (TextView) rootView.findViewById(R.id.T21);
        Month_Calender[22] = (TextView) rootView.findViewById(R.id.T22);
        Month_Calender[23] = (TextView) rootView.findViewById(R.id.T23);
        Month_Calender[24] = (TextView) rootView.findViewById(R.id.T24);
        Month_Calender[25] = (TextView) rootView.findViewById(R.id.T25);
        Month_Calender[26] = (TextView) rootView.findViewById(R.id.T26);
        Month_Calender[27] = (TextView) rootView.findViewById(R.id.T27);
        Month_Calender[28] = (TextView) rootView.findViewById(R.id.T28);
        Month_Calender[29] = (TextView) rootView.findViewById(R.id.T29);
        Month_Calender[30] = (TextView) rootView.findViewById(R.id.T30);
        Month_Calender[31] = (TextView) rootView.findViewById(R.id.T31);
        Month_Calender[32] = (TextView) rootView.findViewById(R.id.T32);
        Month_Calender[33] = (TextView) rootView.findViewById(R.id.T33);
        Month_Calender[34] = (TextView) rootView.findViewById(R.id.T34);
        Month_Calender[35] = (TextView) rootView.findViewById(R.id.T35);
        Month_Calender[36] = (TextView) rootView.findViewById(R.id.T36);
        Month_Calender[37] = (TextView) rootView.findViewById(R.id.T37);
        Month_Calender[38] = (TextView) rootView.findViewById(R.id.T38);
        Month_Calender[39] = (TextView) rootView.findViewById(R.id.T39);
        Month_Calender[40] = (TextView) rootView.findViewById(R.id.T40);
        Month_Calender[41] = (TextView) rootView.findViewById(R.id.T41);
        Month_Calender[42] = (TextView) rootView.findViewById(R.id.T42);
        Cal_Month.setCalender(Cal_Month.Year, Cal_Month.Month,Cal_Month.getWeekday(Cal_Month.Year,Cal_Month.Month,1));

        int css = 0, today;
        for(int i=1; i<=42; i++) Month_Calender[i].setText(String.valueOf(Cal_Month.Calender[i]));

        for(int i=1; i<=42; i++) {
            if(Cal_Month.Calender_State[i] == 0) {
                Month_Calender[i].setTextColor(Color.parseColor("#FFCACACA"));
                Month_Calender[i].setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.rect));
            }
            else {
                css = css + 1;
                today = Cal_Month.getDay(Cal_Month.Year, Cal_Month.Month, css);
                if(Cal_Month.isTask(today)) {
                    Month_Calender[i].setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.circle));
                }
                else {
                    Month_Calender[i].setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.rect));
                    if ((i - 1) % 7 == 0) Month_Calender[i].setTextColor(Color.parseColor("#F44F3D"));
                    else if (i % 7 == 0)  Month_Calender[i].setTextColor(Color.parseColor("#2D49FF"));
                    else Month_Calender[i].setTextColor(Color.parseColor("#000000"));
                }
            }
        }
        TextView Text_Month = (TextView) rootView.findViewById(R.id.Text_Month);
        Text_Month.setText(String.valueOf(Cal_Month.Year) + "년 " + String.valueOf(Cal_Month.Month) + "월");

    }
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle saveInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.monthly, container, false);

        Button Before = (Button) rootView.findViewById(R.id.Button_Before);
        Button Next = (Button) rootView.findViewById(R.id.Button_Next);

        Re_Calender(rootView);


        Before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cal_Month.Before_Month();
                Re_Calender(rootView);
            }
        });
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cal_Month.Next_Month();
                Re_Calender(rootView);
            }
        });

        /////////////////////////
        return rootView;
    }
}