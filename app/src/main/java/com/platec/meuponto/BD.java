package com.platec.meuponto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Switch;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulemberg.silva on 08/10/2015.
 */
public class BD {
    private SQLiteDatabase db;

    //final String[] colunas = new String[]{"_id","data","entrada","saida_almoco","retorno_almoco","saida"};
    public BD(Context context) {
        BDCore auxBd = new BDCore(context);
        db = auxBd.getWritableDatabase();
    }

    public void insereHora(MarcaHora marcaHora) {
        ContentValues values = new ContentValues();
        if (!ValidaPrimeiroRegistro(marcaHora)) {

            if (marcaHora.getEntrada() != null) {
                values.put("data", String.valueOf(marcaHora.getData()));
                values.put("entrada", marcaHora.getEntrada());
                db.insert("horarios_tb", null, values);
            }else
            {
                values.put("data", Utils.RetornaData());
                values.put("entrada", marcaHora.getEntrada());
                db.insert("horarios_tb", null, values);
            }

        }else if (marcaHora.getSaida_almoco() != null) {
            values.put("saida_almoco", marcaHora.getSaida_almoco());
            db.update("horarios_tb", values, "_id=" + marcaHora.get_id(), null);
        } else if (marcaHora.getRetorno_almoco() != null) {
            values.put("retorno_almoco", marcaHora.getRetorno_almoco());
            db.update("horarios_tb", values, "_id=" + marcaHora.get_id(), null);
        } else if (marcaHora.getSaida() != null) {
            values.put("saida", marcaHora.getSaida());
            db.update("horarios_tb", values, "_id=" + marcaHora.get_id(), null);
        }


    }

    public void deletarRegistros() {
        db.delete("horarios_tb", null, null);
    }

    public boolean ValidaPrimeiroRegistro(MarcaHora data) {
        String[] colunas = new String[]{"data"};
        boolean retorno;
        Cursor cursor = db.query("horarios_tb", colunas, "data='" + data.getData() + "'", null, null, null, null, null);

        if (cursor.getCount() > 0) {
            retorno = true;
        } else {
            retorno = false;
        }

        cursor.close();

        return  retorno;
    }

    public List<MarcaHora> buscar() {
        List<MarcaHora> list = new ArrayList<>();
        String[] colunas = new String[]{"_id", "data", "entrada", "saida_almoco", "retorno_almoco", "saida"};

        Cursor cursor = db.query("horarios_tb", colunas, null, null, null, null, "_id desc");

        if (cursor != null &&  cursor.getCount()>0) {
            cursor.moveToFirst();
            do {
                MarcaHora mh = new MarcaHora();
                mh.set_id(cursor.getString(0));
//                Log.d("CERCA-FRANGO", cursor.getString(0));
                mh.setData(cursor.getString(1));
                mh.setEntrada(cursor.getString(2));
                mh.setSaida_almoco(cursor.getString(3));
                mh.setRetorno_almoco(cursor.getString(4));
                mh.setSaida(cursor.getString(5));
                list.add(mh);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return (list);
    }
}
