package com.quepexinxa.carlos.quepexinxa;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class ItemPromocao implements Serializable {

    private int promocaoID;
    private String produto;
    private String lojista;
    private String dataInicio;
    private String dataFim;
    private String valorAntigo;
    private String valorAtual;
    private String imagem;

    public int getPromocaoID() {
        return promocaoID;
    }
    public void setPromocaoID(int promocaoID) {
        this.promocaoID = promocaoID;
    }
    public String getProduto() {
        return produto;
    }
    public void setProduto(String produto) {
        this.produto = produto;
    }
    public String getLojista() {
        return lojista;
    }
    public void setLojista(String lojista) {
        this.lojista = lojista;
    }
    public String getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }
    public String getDataFim() {
        return dataFim;
    }
    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }
    public String getValorAntigo() {
        return valorAntigo;
    }
    public void setValorAntigo(String valorAntigo) {
        this.valorAntigo = valorAntigo;
    }
    public String getValorAtual() {
        return valorAtual;
    }
    public void setValorAtual(String valorAtual) {
        this.valorAtual = valorAtual;
    }
    public String getImagem() {
        return CaminhoImagem(imagem);
    }
    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public ItemPromocao(JSONObject jsonObject) throws JSONException {
        this.promocaoID = jsonObject.getInt("PromocaoID");
        this.produto = jsonObject.getString("Produto");
        this.lojista = jsonObject.getString("Lojista");
        this.dataInicio = jsonObject.getString("DataInicioTexto");
        //SimpleDateFormat sdfdata = new SimpleDateFormat("dd/MM/yyyy");
        //Date d = sdfdata.parse(dataInicio);
        //SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");
        //Date hora = sdfHora.parse(dataInicio);
        //String dataString = sdfdata.format(d);
        this.valorAntigo = jsonObject.getString("ValorAntigoTexto");
        this.valorAtual = jsonObject.getString("ValorAtualTexto");
        this.imagem = jsonObject.getString("Imagem");
    }

    private String CaminhoImagem(String imagem){
        StringBuilder builder = new StringBuilder();
        builder.append("http://www.devnet2.com.br/quepexinxa/Account/Anexos/");
        builder.append(imagem);
        return  builder.toString();
    }
}
