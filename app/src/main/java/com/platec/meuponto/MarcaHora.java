package com.platec.meuponto;

import java.util.Date;

/**
 * Created by paulemberg.silva on 08/10/2015.
 */
public class MarcaHora {
    private String _id;
    private String data;
    private String entrada;
    private String saida_almoco;
    private String retorno_almoco;
    private String saida;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id){
        this._id = _id;
    }

    public String getData(){
        return data;
    }
    public void setData(String data){
        this.data = data;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada ){
        this.entrada = entrada;
    }

    public String getSaida_almoco(){
        return saida_almoco;
    }

    public void setSaida_almoco(String saida_almoco){
        this.saida_almoco = saida_almoco;
    }

    public String getRetorno_almoco(){
        return retorno_almoco;
    }

    public void setRetorno_almoco(String retorno_almoco){
        this.retorno_almoco = retorno_almoco;
    }

    public String getSaida(){
        return saida;
    }

    public void setSaida(String saida){
        this.saida = saida;
    }

}
