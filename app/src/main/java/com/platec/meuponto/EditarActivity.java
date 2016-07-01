package com.platec.meuponto;

import android.os.Bundle;
import android.support.annotation.StringDef;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.nio.BufferUnderflowException;

/**
 * Created by Paulemberg on 3/22/2016.
 */
public class EditarActivity extends AppCompatActivity{


    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar);

        Bundle extra = getIntent().getExtras();
        MarcaHora marcacao;
        if(extra != null)
        {
            String value = extra.getString("id");
            BD bd = new BD(this);

            marcacao =  bd.EditarRegistro(value);
            TextView TxtData = (TextView) this.findViewById(R.id.txtEditdata);
            TextView TxtEntrada = (TextView) this.findViewById(R.id.txtEditEntrada);
            TextView TxtAlmoco = (TextView) this.findViewById(R.id.txtEditAlmoco);
            TextView TxtRetornoAlmoco = (TextView) this.findViewById(R.id.txtEditVoltaAlmoco);
            TextView TxtSaida = (TextView) this.findViewById(R.id.txtEditSaida);

            TxtData.setText(marcacao.getData());
            TxtEntrada.setText(marcacao.getEntrada());
            TxtAlmoco.setText(marcacao.getSaida_almoco());
            TxtRetornoAlmoco.setText(marcacao.getRetorno_almoco());
            TxtSaida.setText(marcacao.getSaida());
        }




      /*  if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.action_bar_container, new  PlaceholderFragment()).commit();
        }*/
    }



    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() { }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.editar,
                    container, false);



                     return rootView;
        }
    }


    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.editar);
//
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.container, new PlaceholderFragment()).commit();
//        }
//    }













