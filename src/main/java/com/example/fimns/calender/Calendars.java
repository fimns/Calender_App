package com.example.fimns.calender;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Calendars {


    public int Year;
    public int Month;
    public int Day;
    public int Week;
    /// 공유 ///
    private static Task task;
    public static Date Today = new Date();
    public static String YMD;
    public static int []Calender = new int [43];
    public static int []Calender_State = new int [43]; // 이번달
    public final int Current_M;
    public final int Current_Y;
    public final int Current_D;
    private final int Out_Day = 737000;


    final private int []md = {0,31,28,31,30,31,30,31,31,30,31,30,31};

    SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");

    public Calendars() {
        Date day = new Date();
        this.YMD = date.format(day);
        this.Current_Y = Year = Integer.parseInt(YMD.substring(0,4));
        this.Current_M = Month = Integer.parseInt(YMD.substring(4,6));
        this.Current_D = Day = Integer.parseInt(YMD.substring(6,8));
        this.Week = This_Week();
        task = new Task();
    }
    public Calendars(Context context) {
        Date day = new Date();
        this.YMD = date.format(day);
        this.Current_Y = Year = Integer.parseInt(YMD.substring(0,4));
        this.Current_M = Month = Integer.parseInt(YMD.substring(4,6));
        this.Current_D = Day = Integer.parseInt(YMD.substring(6,8));
        this.Week = This_Week();
        task = new Task(context);

    }
    public int getWeekToday(int today) {
        return 0;
    }

    public boolean chkLeap(int i) {
        // 윤년확인
        if(i % 400 == 0 || (i % 4 == 0 && i % 100 !=0 )) return true;
        return false;
    }

    public int getDay(int year, int month, int day) {
        //일자 확인
        int i;
        int d = -2;
        int yn = 0;

        for(i=0; i<year; i++) {
            if(chkLeap(i)) d = d + 366;
            else d = d + 365;
        }
        if(chkLeap(i)) yn = 1;
        else yn = 0;

        for(i=1; i<month; i++) {
            if ( i == 2 ) d = d + yn + md[i];
            d = d + md[i];
        }
        d = d + day;
        return d - Out_Day;

    }
    public int getWeekday(int year, int month, int day) { //요일
        return (getDay(year,month,day)+Out_Day)%7 + 1;
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
        Week = Week - 1;
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
    public void Next_Day() {
        Day = Day + 1;
        if( Day > md[Month]) {
            Day = 1;
            Next_Month();
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

    public int getTodayTaskNums(int today) {
        return task.getNum(today);
    }
    public String[] getTodayTask(int today) {
        return task.getTask(today);
    }
    public void addTask(int today, String text) {
        task.addTask(today, text);
    }
    public boolean isTask(int today) {
        return task.isTask(today);
    }
    public void delTask(int today, int num) {
        task.Del(today, num);
    }
    private int max(int a, int b) {
        if ( a > b) return a;
        else return b;
    }
}