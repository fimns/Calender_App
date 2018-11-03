package com.example.fimns.calender;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Calendars_Week {


    public int []Calender = new int [43];
    public int []Calender_State = new int [43];
    public final int Current_M;
    public final int Current_Y;
    public final int Current_D;

    public static int Year;
    public static int Month;
    public static int Day;
    public static int Week;
    public static Date Today = new Date();
    public static String YMD;

    final private int []md = {0,31,28,31,30,31,30,31,31,30,31,30,31};

    SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");

    public Calendars_Week() {
        Date day = new Date();
        this.YMD = date.format(day);
        this.Current_Y = Year = Integer.parseInt(YMD.substring(0,4));
        this.Current_M = Month = Integer.parseInt(YMD.substring(4,6));
        this.Current_D = Day = Integer.parseInt(YMD.substring(6,8));
        this.Week = This_Week();
    }

    public int getDay(int year, int month, int day) {
        int i;
        int d = -2;
        int yn = 0;

        for(i=0; i<year; i++) {
            if(i % 400 == 0 || (i % 4 == 0 && i % 100 !=0 )) {
                d = d + 366;
            }
            else {
                d = d + 365;
            }
        }
        if(year % 400 == 0 || (year % 4 == 0 && year % 100 !=0 )) yn = 1;
        else yn = 0;
        for(i=1; i<month; i++) {
            if ( i == 2 ) d = d + yn + md[i];
            d = d + md[i];
        }
        d = d + day;
        return d;

    }
    public int getWeekday(int year, int month, int day) {
        return getDay(year,month,day)%7 + 1;
    }
    public void setCalender(int year, int month, int first) {
        int i;
        int c = md[(month+10)%12+1];
        for(i=first-1; i>=1; i--) {
            Calender[i] = c--;
            Calender_State[i] = 0;
        }
        for(i=first; i<=md[month]+first-1; i++) {
            Calender[i] = i+1-first;
            Calender_State[i] = 1;
        }
        for(i=md[month]+first; i<=42; i++) {
            Calender[i] = i-md[month]-first+1;
            Calender_State[i] = 0;
        }
    }
    public int This_Week() {
        int first = getWeekday(Year,Month,1), i;
        setCalender(Year,Month,first);
        for(i = 1; i<= 42; i++) {
            if( Calender[i] == Day && i >= first) break;
        }
        return (i-1) / 7 + 1;
    }
    public void Next_Week() {
        Week = Week + 1;
        if ( Week >= 5) {
            Next_Month();
            Week = 1;
        }
        setCalender(Year,Month,getWeekday(Year,Month,1));
    }
    public void Before_Week() {
        Week = Week -1;
        if ( Week <= 0 ) {
            Before_Month();
            Day = md[Month];
            Week = This_Week();
        }
    }
    public void Before_Day() {
        Day = Day - 1;
        if ( Day <= 0 ) {
            Before_Month();
            Day =  md[Month];
        }
    }
    public void Next_Month() {
        this.Month = this.Month + 1;
        if ( this.Month >= 13 ) {
            this.Month = 1;
            this.Year = this.Year + 1;
        }
    }
    public void Before_Month() {
        this.Month = this.Month - 1;
        if ( this.Month <= 0) {
            this.Month = 12;
            this.Year = this.Year - 1;
        }
    }
}
