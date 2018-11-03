package com.example.fimns.calender;

public class Task {
    final int Start_Day = 100;
    final int Total_Day = 3650;
    final int Out_Day = 737000;
    final private int []md = {0,31,28,31,30,31,30,31,31,30,31,30,31};
    String [][]tasks = new String[Total_Day][5];
    int []num = new int[Total_Day];
    public Task() {
        int i, j;
        for(i=0;i<Total_Day; i++) {
       //     if ( num[i] >= 1 ) ;
       //     else num[i] = 0;
        }
    }
    public void Del(int id, int num) {

            int j;
            for (j = num; j < this.num[id-Out_Day]-1; j++) {
                this.tasks[id-Out_Day][j] = this.tasks[id-Out_Day][j + 1];
            }

            this.num[id-Out_Day] = this.num[id-Out_Day] - 1;

    }
    public boolean isTask(int year, int month, int day) {
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
        if ( num[d-Out_Day] != 0 ) return true;
        else return false;
    }
    public int getNum(int year, int month, int day) {
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
        return num[d - Out_Day];

    }  public int getDay(int year, int month, int day) {
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
        return d-Out_Day;
    }
}