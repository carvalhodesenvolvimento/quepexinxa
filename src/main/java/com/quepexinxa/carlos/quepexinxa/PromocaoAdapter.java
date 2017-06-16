package com.quepexinxa.carlos.quepexinxa;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PromocaoAdapter extends ArrayAdapter<ItemPromocao> {

    private static final int VIEW_TYPE_DESTAQUE = 0;
    private static final int VIEW_TYPE_ITEM = 1;

    public PromocaoAdapter(Context context, ArrayList<ItemPromocao> promocao){
        super(context, 0, promocao);
    }

    private static class  ItemPromocaoHolder{
        TextView produto;
        TextView lojista;
        TextView dataInicio;
        TextView dataFim;
        TextView valorAntigo;
        TextView valorAtual;
        ImageView imagem;

        public ItemPromocaoHolder(View view) {
            produto = (TextView) view.findViewById(R.id.item_produto);
            lojista = (TextView) view.findViewById(R.id.item_lojista);
            dataInicio = (TextView) view.findViewById(R.id.item_datainicio);
            dataFim = (TextView) view.findViewById(R.id.item_datafim);
            valorAntigo = (TextView) view.findViewById(R.id.item_valorantigo);
            valorAtual = (TextView) view.findViewById(R.id.item_valoratual);
            imagem = (ImageView) view.findViewById(R.id.item_poster);
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        int viewType = getItemViewType(position);
        ItemPromocao itemPromocao = getItem(position);
        View itemView = convertView;

        switch (viewType){
            case VIEW_TYPE_DESTAQUE:{
                itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_promocao_destaque, parent, false);

                TextView produto = (TextView) itemView.findViewById(R.id.item_produto);
                produto.setText(itemPromocao.getProduto());

                TextView lojista = (TextView) itemView.findViewById(R.id.item_lojista);
                lojista.setText(itemPromocao.getLojista());

                ImageView capa = (ImageView) itemView.findViewById(R.id.item_poster);
                new DownloadImage(capa).execute(itemPromocao.getImagem());
                break;
            }
            case VIEW_TYPE_ITEM:{
                itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_promocao, parent, false);
                ItemPromocaoHolder holder;

                if(itemView.getTag() == null)
                {
                    holder = new ItemPromocaoHolder(itemView);
                    itemView.setTag(holder);
                }
                else
                {
                    holder = (ItemPromocaoHolder) itemView.getTag();
                }

                holder.produto.setText(itemPromocao.getProduto());
                holder.lojista.setText(itemPromocao.getLojista());
                //holder.dataInicio.setText(itemPromocao.getDataInicio());
                holder.dataFim.setText(itemPromocao.getDataFim());
                holder.valorAntigo.setText(itemPromocao.getValorAntigo());
                holder.valorAtual.setText(itemPromocao.getValorAtual());
                new DownloadImage(holder.imagem).execute(itemPromocao.getImagem());
                break;
            }
        }
        return itemView;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == 0 ? VIEW_TYPE_DESTAQUE : VIEW_TYPE_ITEM);
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }
}