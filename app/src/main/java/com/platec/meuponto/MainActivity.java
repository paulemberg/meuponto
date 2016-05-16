package com.platec.meuponto;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SimpleCursorAdapter dataAdapter;
    private Button btnEntrada;
    private Button btnSaidaAlmoco;
    private Button btnRetornoAlmoco;
    private Button btnSaida;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        final BD bd = new BD(this);
        final ListView lista = (ListView) findViewById(R.id.lst_principal);
        btnEntrada = (Button) findViewById(R.id.btn_entrada);
        btnSaidaAlmoco = (Button) findViewById(R.id.btn_saida_Almoco);
        btnRetornoAlmoco = (Button) findViewById(R.id.btn_ret_almoco);
        btnSaida = (Button) findViewById(R.id.btn_saida);

        setSupportActionBar(toolbar);
        carregaListView(lista);



        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MarcaHora mhora = new MarcaHora();
                mhora.set_id(String.valueOf(id));
            }
        });

        btnEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MarcaHora mhora = new MarcaHora();
                boolean novoregistro = false;
                mhora.setEntrada(Utils.RetornaHora());
                mhora.setData(Utils.RetornaData());
                novoregistro = bd.ValidaPrimeiroRegistro(mhora);

                if (novoregistro == false) {
                    bd.insereHora(mhora);
                    carregaListView(lista);
                    Snackbar.make(v, "Tenha um bom dia", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Já existe um Registro para essa data", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSaidaAlmoco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lista.getCount() > 0) {
                    MarcaHora mhora = new MarcaHora();
                    mhora.setSaida_almoco(Utils.RetornaHora());
                    mhora.setData(Utils.RetornaData());
                    mhora.set_id(Utils.RetornaID(lista));
                    bd.insereHora(mhora);
                    carregaListView(lista);

                    Snackbar.make(v, "Bom almoço", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Não existe registro de entrada", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRetornoAlmoco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lista.getCount() > 0) {
                    MarcaHora mhora = new MarcaHora();
                    mhora.setRetorno_almoco(Utils.RetornaHora());
                    mhora.setData(Utils.RetornaData());
                    mhora.set_id(Utils.RetornaID(lista));
                    bd.insereHora(mhora);

                    carregaListView(lista);

                    Snackbar.make(v, "Bem vindo de volta", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Não existe registro de entrada", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSaida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lista.getCount() > 0) {
                    MarcaHora mhora = new MarcaHora();
                    mhora.setSaida(Utils.RetornaHora());
                    mhora.setData(Utils.RetornaData());
                    mhora.set_id(Utils.RetornaID(lista));
                    bd.insereHora(mhora);
                    carregaListView(lista);
                    Snackbar.make(v, "Até Amanhã", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Não existe registro de entrada", Toast.LENGTH_SHORT).show();
                }
            }

        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, EditarActivity.class);
                startActivity(intent);


                //bd.deletarRegistros();
                //Toast.makeText(getApplicationContext(), "Registros apagados", Toast.LENGTH_SHORT).show();
                //Snackbar.make(view, "Registros apagados", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                //finish();

            }
        });
    }

    private void carregaListView(ListView lista) {

        BD bd = new BD(this);
        List<MarcaHora> list = bd.buscar();
        lista.setAdapter(new ListviewAdapter(this, list));

        if (list.size() > 0) {

            if (list.get(0).getEntrada() != null) {
                btnEntrada.setEnabled(false);
            }

            if (list.get(0).getSaida_almoco() != null) {
                btnSaidaAlmoco.setEnabled(false);
            }

            if (list.get(0).getRetorno_almoco() != null) {
                btnRetornoAlmoco.setEnabled(false);
            }

            if (list.get(0).getSaida() != null) {
                btnSaida.setEnabled(false);
            }

            String data = Utils.RetornaData();
            if(list.get(0).getData()!= data && list.get(0).getSaida() != null){
                btnEntrada.setEnabled(true);
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
