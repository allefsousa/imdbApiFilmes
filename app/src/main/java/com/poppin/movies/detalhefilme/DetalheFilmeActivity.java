package com.poppin.movies.detalhefilme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.poppin.movies.R;
import com.poppin.movies.data.source.DetalheFilme;

import butterknife.BindView;
import butterknife.ButterKnife;

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



    private DetalheFilmeContract.Presenter mpresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_filme);
        ButterKnife.bind(this);

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
    public void DetalheVazio() {

    }

    @Override
    public void DetalheSemRetrono() {

    }

    @Override
    public void DetalheSemConexao() {

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

        if (detalheFilme1.equals("N/A")){
            logoFilme.setImageResource(R.drawable.placeholder);
        }else {
            Glide.with(this).load(detalheFilme1.getPoster()).into(logoFilme);

        }


    }

    @Override
    public void onResponseFaiulure(Throwable t) {

    }

    @Override
    public void onFailure(Throwable t) {

    }

    @Override
    public void getDetalheFilme(OnFinishedListener onFinishedListener, String idFilme) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_out_right);
    }
}
