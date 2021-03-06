package com.platec.meuponto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulemberg.silva on 08/10/2015.
 */
public class BD {
    private SQLiteDatabase db;


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

        }
        if (marcaHora.getSaida_almoco() != null) {
            values.put("saida_almoco", marcaHora.getSaida_almoco());
            db.update("horarios_tb", values, "_id=" + marcaHora.get_id(), null);
        }
        if (marcaHora.getRetorno_almoco() != null) {
            values.put("retorno_almoco", marcaHora.getRetorno_almoco());
            db.update("horarios_tb", values, "_id=" + marcaHora.get_id(), null);
        }
        if (marcaHora.getSaida() != null) {
            values.put("saida", marcaHora.getSaida());
            db.update("horarios_tb", values, "_id=" + marcaHora.get_id(), null);
        }


    }
    public void insereHora(MarcaHora marcaHora, boolean novoRegistro) {
        ContentValues values = new ContentValues();
        if (!novoRegistro) {
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

        }
        if (marcaHora.getSaida_almoco() != null) {
            values.put("saida_almoco", marcaHora.getSaida_almoco());
            db.update("horarios_tb", values, "_id=" + marcaHora.get_id(), null);
        }
        if (marcaHora.getRetorno_almoco() != null) {
            values.put("retorno_almoco", marcaHora.getRetorno_almoco());
            db.update("horarios_tb", values, "_id=" + marcaHora.get_id(), null);
        }
        if (marcaHora.getSaida() != null) {
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

    public MarcaHora BuscarRegistro(String id)
    {
        MarcaHora marcacao = new MarcaHora();

        String[] colunas = new String[]{"_id", "data", "entrada", "saida_almoco", "retorno_almoco", "saida"};

        Cursor cursor = db.query("horarios_tb", colunas, "_id=" + id, null, null, null, "_id desc");

        if (cursor != null &&  cursor.getCount()>0) {
            cursor.moveToFirst();
            do {
                marcacao.set_id(cursor.getString(0));
                marcacao.setData(cursor.getString(1));
                marcacao.setEntrada(cursor.getString(2));
                marcacao.setSaida_almoco(cursor.getString(3));
                marcacao.setRetorno_almoco(cursor.getString(4));
                marcacao.setSaida(cursor.getString(5));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return (marcacao);
    }

    public void AlterarRegistro(MarcaHora marcaHora)
    {
        ContentValues values = new ContentValues();

        values.put("entrada", marcaHora.getEntrada());
        values.put("saida_almoco", marcaHora.getSaida_almoco());
        values.put("retorno_almoco", marcaHora.getRetorno_almoco());
        values.put("saida", marcaHora.getSaida());
        db.update("horarios_tb", values, "_id=" + marcaHora.get_id(), null);
    }
}
