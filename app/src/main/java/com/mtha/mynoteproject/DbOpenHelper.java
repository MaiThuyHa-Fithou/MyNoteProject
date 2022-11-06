package com.mtha.mynoteproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbOpenHelper extends SQLiteOpenHelper {
    final static String DB_NAME ="demo.db";
    final static int DB_VERSION=1;
    public DbOpenHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //tao cac bang can co trong csdl
        String sql ="CREATE TABLE tblNote (id integer primary key, " +
                "tieuDe text, noiDung text, ngayTao text); ";
        //thuc thi cau lenh tao bang
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tbNote; ");
        onCreate(db);
    }
}
