package com.platec.meuponto;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SimpleCursorAdapter dataAdapter;
    private Button btnEntrada;
    private Button btnSaidaAlmoco;
    private Button btnRetornoAlmoco;
    private Button btnSaida;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client2;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final BD bd = new BD(this);
        final ListView lista = (ListView) findViewById(R.id.lst_principal);
        btnEntrada = (Button) findViewById(R.id.btn_entrada);
        btnSaidaAlmoco = (Button) findViewById(R.id.btn_saida_Almoco);
        btnRetornoAlmoco = (Button) findViewById(R.id.btn_ret_almoco);
        btnSaida = (Button) findViewById(R.id.btn_saida);
        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.toolbar);

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
                boolean registroExiste;
                mhora.setEntrada(Utils.RetornaHora());
                mhora.setData(Utils.RetornaData());
                registroExiste = bd.ValidaPrimeiroRegistro(mhora);

                //for test add a new register without compare date uncoment line down;
                //registroExiste = false;

                if (!registroExiste) {
                    //bd.insereHora(mhora);
                    bd.insereHora(mhora, registroExiste);
                    carregaListView(lista);
                    Snackbar.make(v, getString(R.string.saudacao_entrada), Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.registro_duplicado, Toast.LENGTH_SHORT).show();
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

                    Snackbar.make(v, R.string.saudacao_almoco, Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.registro_inexistente, Toast.LENGTH_SHORT).show();
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

                    Snackbar.make(v, R.string.saudacao_retorno_almoco, Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();

                } else {
                    Toast.makeText(getApplicationContext(), R.string.registro_inexistente, Toast.LENGTH_SHORT).show();
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
                    Snackbar.make(v, R.string.saudacao_saida, Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.registro_inexistente, Toast.LENGTH_SHORT).show();
                }
            }

        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               /* TextView id_ = (TextView) view.findViewById(R.id.txtid);
                Bundle bundle = new Bundle();
                bundle.putString("id", id_.getText().toString());
                Intent intent = new Intent(MainActivity.this, EditarActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);*/

                return false;
            }
        });


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(MainActivity.this, EditarActivity.class);
//                startActivity(intent);
//
//                //bd.deletarRegistros();
//                //Toast.makeText(getApplicationContext(), "Registros apagados", Toast.LENGTH_SHORT).show();
//                //Snackbar.make(view, "Registros apagados", Snackbar.LENGTH_LONG)
//                //        .setAction("Action", null).show();
//                //finish();
//
//            }
//        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        client2 = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void carregaListView(ListView lista) {

        BD bd = new BD(this);
        List<MarcaHora> list = bd.buscar();
        lista.setAdapter(new ListviewAdapter(this, list));

        if (list.size() > 0)
        {
            String dt = Utils.RetornaData();

            if (list.get(0).getEntrada() != null ) {
                btnEntrada.setVisibility(View.INVISIBLE);
            }

            if(!list.get(0).getData().equals(dt))
              {
                  btnEntrada.setVisibility(View.VISIBLE);
                  return;
              }else {

                if (list.get(0).getSaida_almoco() != null) {
                    btnSaidaAlmoco.setVisibility(View.INVISIBLE);
                } else {
                    btnSaidaAlmoco.setVisibility(View.VISIBLE);
                    return;
                }


                if (list.get(0).getRetorno_almoco() != null) {
                    btnRetornoAlmoco.setVisibility(View.INVISIBLE);
                } else {
                    btnRetornoAlmoco.setVisibility(View.VISIBLE);
                    return;
                }


                if (list.get(0).getSaida() != null) {
                    btnSaida.setVisibility(View.INVISIBLE);
                } else {
                    btnSaida.setVisibility(View.VISIBLE);
                    return;
                }
            }

            String data = Utils.RetornaData();
            if (list.get(0).getData() != null && !list.get(0).getData().equals(data)
                        && list.get(0).getSaida() != null)
                {
                    btnEntrada.setVisibility(View.VISIBLE);
                    btnEntrada.setEnabled(true);
                }
            else{
                btnEntrada.setVisibility(View.VISIBLE);
                btnEntrada.setEnabled(true);
            }
        }else
        {
            btnEntrada.setVisibility(View.VISIBLE);
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
        switch (item.getItemId()) {
            case R.id.excluir:
                BD bd = new BD(this);
                bd.deletarRegistros();
                Toast.makeText(getApplicationContext(), getString(R.string.registro_apagado), Toast.LENGTH_SHORT).show();
                finish();
                startActivity(getIntent());
                return true;
          /* case R.id.help:
                showHelp();
                return true;*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.platec.meuponto/http/host/path")
        );
        AppIndex.AppIndexApi.start(client2, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.platec.meuponto/http/host/path")
        );
        AppIndex.AppIndexApi.end(client2, viewAction);
        client2.disconnect();
    }




   /* @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.platec.meuponto/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.platec.meuponto/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }*/
}
