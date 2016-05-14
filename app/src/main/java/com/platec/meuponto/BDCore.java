package com.platec.meuponto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by paulemberg.silva on 08/10/2015.
 */
public class BDCore extends SQLiteOpenHelper {
    private  static final String NOME_BD = "horas_bd";
    private  static final Integer VERSAO_BD = 3;

    public BDCore(Context ctx) {
        super(ctx, NOME_BD, null, VERSAO_BD);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS horarios_tb(_id integer PRIMARY  KEY, data text, entrada text, saida_almoco text, retorno_almoco text, saida text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table horarios_tb;");
        onCreate(db);
    }
}
