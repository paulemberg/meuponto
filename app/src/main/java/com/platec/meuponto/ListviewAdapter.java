package com.platec.meuponto;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by paulemberg.silva on 20/10/2015.
 */
public class ListviewAdapter extends BaseAdapter {
    private Context context;
    private List<MarcaHora> marcaHoraList;

    public ListviewAdapter(Context context, List<MarcaHora> marcaHoraList) {
        this.context = context;
        this.marcaHoraList = marcaHoraList;
    }

    @Override
    public int getCount() {
        return marcaHoraList.size();
    }

    @Override
    public Object getItem(int position) {
        return marcaHoraList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Recupera o estado da posição atual
        MarcaHora marcaHora = marcaHoraList.get(position);


        // Cria uma instância do layout XML para os objetos correspondentes
        // na View
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.itemlistview, null);

        TextView txtdata = (TextView) view.findViewById(R.id.txtdata);
        txtdata.setText("Data: " + marcaHora.getData());

        TextView txtid = (TextView) view.findViewById(R.id.txtid);
        txtid.setText(marcaHora.get_id());

        // Entrada - Hora da entrada
        TextView txthoraEntrada = (TextView) view.findViewById(R.id.txtHoraEntrada);
        txthoraEntrada.setText(marcaHora.getEntrada());

        TextView txthoraAlmoco = (TextView) view.findViewById(R.id.txtHoraAlmoco);
        txthoraAlmoco.setText(marcaHora.getSaida_almoco());

        TextView txthoravoltaAlmoco = (TextView) view.findViewById(R.id.txtHoraVoltaAlmoco);
        txthoravoltaAlmoco.setText(marcaHora.getRetorno_almoco());

        TextView txthoraSaida = (TextView) view.findViewById(R.id.txtHoraSaida);
        txthoraSaida.setText(marcaHora.getSaida());


        ImageView imgBarra = (ImageView) view.findViewById(R.id.imgBarra);
        int ultimaCor;
        if (marcaHora.getEntrada() != null && marcaHora.getSaida_almoco() != null
                && marcaHora.getRetorno_almoco() != null && marcaHora.getSaida() != null) {
            int cor;
            cor = retornaCor();
            ultimaCor = cor;
            if(cor != ultimaCor)
            {
                imgBarra.setBackgroundColor(cor);
            }else
            {
                cor = retornaCor();
                imgBarra.setBackgroundColor(cor);
            }
            ultimaCor = cor;
        }
        return view;
    }

    private int retornaCor() {

        /*Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));*/
        int[] colors = context.getResources().getIntArray(R.array.rainbow);
        int totalColors = colors.length;
        return colors[new Random().nextInt(totalColors)];


//        String[] colors = context.getResources().getStringArray(R.array.rainbow);
//        int totalColors = colors.length;
//        String randomStr = array[new Random().nextInt(array.length)];
//        return Color.parseColor(randomStr);
    }

}
