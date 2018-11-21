package com.example.fimns.calender;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static android.content.Context.*;
import static android.content.Context.MODE_PRIVATE;
import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;


public class Task{

    SQLiteDatabase db;
    final int Start_Day = 100;
    static final int Total_Day = 3650;
    final int Out_Day = 737000;
    final private int []md = {0,31,28,31,30,31,30,31,31,30,31,30,31};
    public static String [][]tasks = new String[Total_Day][5];
    public static int []num = new int[Total_Day];

    public Task() {
    }

    public Task(Context context) {
        for(int i=0; i<Total_Day; i++) {
            num[i] = 0;
            for(int j=0; j<5; j++) tasks[i][j] = "";
        }
        try {
                db = context.openOrCreateDatabase("db",MODE_PRIVATE,null);
                //db.execSQL("drop table task;");
                db.execSQL("create table task (i integer, num integer, ta text);");
            }
            catch (Exception ex) {
            }
        try {
        Cursor c1 = db.rawQuery("select * from task;", null);
        int count = c1.getCount(), j;
        int _id, _num;
        String _task;
        for (int i = 0; i < count; i++) {
            c1.moveToNext();
            _id = c1.getInt(0);
            _num = c1.getInt(1);
            num[_id] = max(num[_id], _num + 1);
            _task = c1.getString(2);
            tasks[_id][_num] = _task;
        }
        c1.close();} catch(Exception ex) {}

    }

    public void Del(int id, int num) {
        for (int j = num; j < this.num[id]-1; j++) {
            this.tasks[id][j] = this.tasks[id][j + 1];
        }
        this.num[id] = this.num[id] - 1;
        db.execSQL("delete from task where i=" + String.valueOf(id) + ";");
        for(int j=0; j<this.num[id]; j++) {
            db.execSQL("insert into task(i, num, ta) values ( "+ String.valueOf(id)+" , "+ String.valueOf(j)+" ,'"+
                    String.valueOf(tasks[id][j])+"') ;");
        }
    }

    //해당 일자에 작업이 있는지
    public boolean isTask(int today) {
        if ( num[today] != 0 ) return true;
        else return false;
    }

    public void addTask(int today, String text) {
        db.execSQL("insert into task(i, num, ta) values ( "+ String.valueOf(today)+" , "+ String.valueOf(num[today])+" ,'"+
                text+"') ;");
        tasks[today][num[today]] = String.valueOf(text);
        num[today] = num[today] + 1;

    }

    public String[] getTask(int today) {
        return tasks[today];
    }

    public int getNum(int today) {
        return num[today];
    }
    private void LoadDB() {

        Cursor c1 = db.rawQuery("select * from task;",null);
        int count = c1.getCount(), j;
        int _id, _num, start_frag;
        String _task;
        for(int i =0; i<count; i++) {
            c1.moveToNext();
            _id = c1.getInt(0);
            _num = c1.getInt(1);
            num[_id] = max(num[_id],_num+1);
            _task = c1.getString(2);
            tasks[_id][_num] = _task;
        }
        try {
            Cursor c2 = db.rawQuery("select i from open;",null);

            c2.close();
        } catch(Exception ex) {

        }
        c1.close();
    }

    private int max(int a, int b) {
        if(a>b)return a;
        else return b;
    }
}