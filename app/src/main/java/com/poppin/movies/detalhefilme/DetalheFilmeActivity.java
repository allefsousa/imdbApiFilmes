package com.poppin.movies.detalhefilme;

import android.content.Context;
import android.content.Intent;
import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.poppin.movies.R;
import com.poppin.movies.data.Avaliacao;
import com.poppin.movies.data.source.DetalheFilme;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class DetalheFilmeActivity extends AppCompatActivity implements DetalheFilmeContract.View, DetalheFilmeContract.GetDetalheService {

    @BindView(R.id.Tnomedetalhe)
    TextView textNome;
    @BindView(R.id.Tdata)
    TextView textData;
    @BindView(R.id.Tclassificacao)
    TextView textclassificacao;
    @BindView(R.id.Tgenero)
    TextView textGenero;
    @BindView(R.id.Tdiretor)
    TextView textDiretor;
    @BindView(R.id.Troteirista)
    TextView textRoteirista;
    @BindView(R.id.Tdescricao)
    TextView textdescri;
    @BindView(R.id.Idetalhefilme)
    ImageView logoFilme;
    @BindView(R.id.recyclerDetalhe)
    RecyclerView recyclerView;
    private Context context;



    private DetalheFilmeContract.Presenter mpresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_filme);
        ButterKnife.bind(this);
        context = DetalheFilmeActivity.this;

        mpresenter = new DetalheFilmePresenter(new getDetalheServiceImpl(),this);

        Intent intent = getIntent();
        Bundle dados = intent.getExtras();
        String idfilme = (String) dados.get("idFilme");
        PesquisaDetalhe(idfilme);



    }

    @Override
    public void PesquisaDetalhe(String NomeFilme) {
        mpresenter.PesquisaFilme(NomeFilme);
    }


    @Override
    public void ViewSetValue(DetalheFilme detalheFilme1) {
        textNome.setText(detalheFilme1.getTitle());
        textData.setText(detalheFilme1.getReleased());
        textDiretor.setText(detalheFilme1.getDirector());
        textGenero.setText(detalheFilme1.getGenre());
        textRoteirista.setText(detalheFilme1.getReleased());
        textdescri.setText(detalheFilme1.getPlot());
        textRoteirista.setText(detalheFilme1.getWriter());
        textclassificacao.setText(detalheFilme1.getRated());

        if (detalheFilme1.getPoster().equals("N/A")){
            logoFilme.setImageResource(R.drawable.placeholder);
        }else {
            Glide.with(this).load(detalheFilme1.getPoster()).into(logoFilme);

        }


    }


    @Override
    public void ViewSetRating(List<Avaliacao> ratings) {
         AdapterDetalheFilme adapterDetalheFilme = new AdapterDetalheFilme(context,ratings);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(adapterDetalheFilme);



    }

    @Override
    public void DetalheVazio() {
        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Oops...")
                .setContentText("Tente Novamanete, Erro Inesperado.")
                .show();
    }

    @Override
    public void onFailure(Throwable t) {
        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Oops...")
                .setContentText("Tente Novamanete, Erro Inesperado.")
                .show();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_out_right);
    }

    @Override
    public void getDetalheFilme(OnFinishedListener onFinishedListener, String idFilme) {

    }
}
