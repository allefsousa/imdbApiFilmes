package com.poppin.movies.detalhefilme;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.poppin.movies.R;
import com.poppin.movies.data.Avaliacao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by allef on 05/08/2018.
 */

public class AdapterDetalheFilme extends RecyclerView.Adapter<AdapterDetalheFilme.ViewHolder> {

    private Context context;
    private List<Avaliacao> avaliacaoList = new ArrayList<>();

    public AdapterDetalheFilme(Context context, List<Avaliacao> avaliacaoList) {
        this.context = context;
        this.avaliacaoList = avaliacaoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detalhe, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tFonteavalicao.setText(avaliacaoList.get(position).getSource());
        holder.tvalor.setText(avaliacaoList.get(position).getValue());

    }

    @Override
    public int getItemCount() {
        return avaliacaoList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tFonteavalicao;
        TextView tvalor;

        public ViewHolder(View v) {
            super(v);
            tFonteavalicao = v.findViewById(R.id.tSource);
            tvalor = v.findViewById(R.id.tValor);

        }


    }
}
