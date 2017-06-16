package com.quepexinxa.carlos.quepexinxa;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class PromocaoDetalheFragment extends Fragment {
    private ItemPromocao itemPromocao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null){
            itemPromocao = (ItemPromocao) getArguments().getSerializable(MainActivity.PROMOCAO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_promocao_detalhe, container, false);

        if (itemPromocao == null){
            return view;
        }

        TextView produto = (TextView) view.findViewById(R.id.item_produto);
        produto.setText(itemPromocao.getProduto());

        TextView lojista = (TextView) view.findViewById(R.id.item_lojista);
        lojista.setText(itemPromocao.getLojista());

        TextView dataInicio = (TextView) view.findViewById(R.id.item_datainicio);
        dataInicio.setText(itemPromocao.getDataInicio());

        TextView dataFim = (TextView) view.findViewById(R.id.item_datafim);
        dataFim.setText(itemPromocao.getDataFim());

        TextView valorAntigo = (TextView) view.findViewById(R.id.item_valorantigo);
        valorAntigo.setText(itemPromocao.getValorAntigo());

        TextView valorAtual = (TextView) view.findViewById(R.id.item_valoratual);
        valorAtual.setText(itemPromocao.getValorAtual());

        ImageView imagemDestaque = (ImageView) view.findViewById(R.id.item_poster);
        new DownloadImage(imagemDestaque).execute(itemPromocao.getImagem());
        return view;
    }
}