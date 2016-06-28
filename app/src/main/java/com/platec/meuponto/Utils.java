package com.platec.meuponto;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Paulemberg on 2/9/2016.
 */
public class Utils {

    public static String RetornaHora() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        String resultado = String.valueOf(df.format(cal.getTime()));

        return resultado;
    }

    public static String RetornaData() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String dataFormatada = df.format(c.getTime());
        return dataFormatada;
    }

    public static String RetornaID(ListView lv) {
        TextView txtid = (TextView) lv.findViewById(R.id.txtid);
        return txtid.getText().toString();
    }


    public static Button HabiliataBotao(Button botao, boolean habilita) {
        botao.setEnabled(habilita);
        return botao;
    }


}
