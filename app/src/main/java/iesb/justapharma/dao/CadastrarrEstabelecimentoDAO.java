package iesb.justapharma.dao;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SAMSUNG on 11/08/2015.
 */
public class CadastrarrEstabelecimentoDAO extends SQLiteOpenHelper
 {
     public CadastrarrEstabelecimentoDAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
         super(context, name, factory, version);
     }

     public CadastrarrEstabelecimentoDAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
         super(context, name, factory, version, errorHandler);
     }

     @Override
     public void onCreate(SQLiteDatabase db) {

     }

     @Override
     public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

     }
 }
