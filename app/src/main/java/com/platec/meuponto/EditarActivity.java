package com.platec.meuponto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Paulemberg on 3/22/2016.
 */
public class EditarActivity extends AppCompatActivity{

    private TextView  TxtData;
    private TextView TxtEntrada;
    private TextView TxtAlmoco;
    private TextView TxtRetornoAlmoco;
    private TextView TxtSaida;

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar);
        Button btnSalvar = (Button) findViewById(R.id.btnSalvar);
        final BD bd = new BD(this);
        final Bundle extra = getIntent().getExtras();
        final MarcaHora marcacao;
        if(extra != null)
        {
            String valueId = extra.getString("id");

            marcacao =  bd.BuscarRegistro(valueId);
            TxtData = (TextView) this.findViewById(R.id.txtEditdata);
            TxtEntrada = (TextView) this.findViewById(R.id.txtEditEntrada);
            TxtAlmoco = (TextView) this.findViewById(R.id.txtEditAlmoco);
            TxtRetornoAlmoco = (TextView) this.findViewById(R.id.txtEditVoltaAlmoco);
            TxtSaida = (TextView) this.findViewById(R.id.txtEditSaida);

            TxtData.setText(marcacao.getData());
            TxtEntrada.setText(marcacao.getEntrada());
            TxtAlmoco.setText(marcacao.getSaida_almoco());
            TxtRetornoAlmoco.setText(marcacao.getRetorno_almoco());
            TxtSaida.setText(marcacao.getSaida());
        }

        btnSalvar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                MarcaHora marcacaoAlterada = new MarcaHora();
                marcacaoAlterada.setEntrada(TxtEntrada.getText().toString());
                marcacaoAlterada.setSaida_almoco(TxtAlmoco.getText().toString());
                marcacaoAlterada.setRetorno_almoco(TxtRetornoAlmoco.getText().toString());
                marcacaoAlterada.setSaida(TxtSaida.getText().toString());
                marcacaoAlterada.set_id(extra.getString("id"));
                try {
                    bd.AlterarRegistro(marcacaoAlterada);
                    Toast.makeText(EditarActivity.this, "Registro Atualizado", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent("android.intent.action.MAIN");
                    finish();
                    startActivity(intent);

                }catch (Exception ex)
                {
                    Log.e("Gravacao Banco",ex.toString());
                }


            }
        });


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













