package com.example.bisonapp30;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDSQLite extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "BisonApp";

    //Creacion de la BD ---Una tabla
    private String sql = "create table tareas(" +
            "idTarea int primary key ," +
            "nomTarea varchar(40)," +
            "materia varchar(40)," +
            "descripcion varchar(100)," +
            "fecha date," +
            "hora time)";

    public BDSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists tareas");
    }
}
