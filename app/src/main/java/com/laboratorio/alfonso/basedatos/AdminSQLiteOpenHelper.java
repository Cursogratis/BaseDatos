package com.laboratorio.alfonso.basedatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    //llamamos al constructor
    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //creamos la tabla
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usuario(u_ced integer primary key, u_nom text, u_ape text, u_eda integer)");
    }

    //borrar la tabla y crear la nueva tabla
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists usuario");
        db.execSQL("create table usuario(u_ced integer primary key, u_nom text, u_ape text, u_eda integer)");
    }

}
