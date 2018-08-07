package com.poppin.movies.exibefilme;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.poppin.movies.R;
import com.poppin.movies.data.Filme;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by allef on 05/08/2018.
 */

public class AdapterFilme extends RecyclerView.Adapter<AdapterFilme.ViewHolder> {

    private Context context;
    private List<Filme> filmeList = new ArrayList<>();
    private RecyclerItemClickListener recyclerItemClickListener;


    public AdapterFilme(Context context, List<Filme> filmeList, RecyclerItemClickListener recyclerItemClickListener) {
        this.context = context;
        this.filmeList = filmeList;
        this.recyclerItemClickListener = recyclerItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_filme, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textNomeFilme.setText(filmeList.get(position).getTitle());
        holder.textDataFilme.setText(filmeList.get(position).getYear());


        if (filmeList.get(position).getPoster().equals("N/A")) {
            holder.logoFilme.setImageResource(R.drawable.placeholder);
        } else {
            Glide.with(context).load(filmeList.get(position).getPoster()).into(holder.logoFilme);
        }


        holder.view.setOnClickListener(v ->
                recyclerItemClickListener.onItemClick(filmeList.get(position)));


    }

    @Override
    public int getItemCount() {
        return filmeList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textNomeFilme;
        TextView textDataFilme;
        ImageView logoFilme;
        View view;

        public ViewHolder(View v) {
            super(v);
            textNomeFilme = v.findViewById(R.id.TnomeFilme);
            textDataFilme = v.findViewById(R.id.TanoLancamento);
            logoFilme = v.findViewById(R.id.Ifilme);
            view = v.findViewById(R.id.lyt_parent);
        }


    }
}
