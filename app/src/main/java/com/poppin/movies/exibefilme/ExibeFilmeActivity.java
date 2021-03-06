package com.poppin.movies.exibefilme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.poppin.movies.R;
import com.poppin.movies.data.Filme;
import com.poppin.movies.detalhefilme.DetalheFilmeActivity;
import com.poppin.movies.utils.SpacingItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;


import static com.poppin.movies.utils.dpUtils.dpToPx;

public class ExibeFilmeActivity extends AppCompatActivity implements ExibeFilmeContract.View {

    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.my_recycler_view)
    RecyclerView recyclerViewfilme;
    @BindView(R.id.et_search)
    EditText editFilme;
    private ExibeFilmeContract.Presenter mPresenter;
    private AdapterFilme adapterFilme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPresenter = new ExibirFilmePresenter(this,new getFilmeServiceImpl());

        editFilme.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (!TextUtils.isEmpty(editFilme.getText().toString())) {
                    if (i == EditorInfo.IME_ACTION_SEARCH) {
                        hideKeyboard(ExibeFilmeActivity.this);
                        String tituloFilme;
                        tituloFilme = editFilme.getText().toString();
                        mPresenter.PesquisaFilme(tituloFilme);



                    }
                }
                return false;
            }
        });



    }


    public void RecyclerViewSetValue(List<Filme> filmeList) {
        adapterFilme = new AdapterFilme(ExibeFilmeActivity.this, filmeList, recyclerItemClickListener);
        recyclerViewfilme.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerViewfilme.addItemDecoration(new SpacingItemDecoration(2, dpToPx(this, 4), true));
        recyclerViewfilme.setAdapter(adapterFilme);

    }

    @Override
    public void onResponseFaiulure(Throwable t) {
        Toast.makeText(this, "OnError" + t.getMessage(), Toast.LENGTH_SHORT).show();
    }


    private RecyclerItemClickListener recyclerItemClickListener = new RecyclerItemClickListener() {
        @Override
        public void onItemClick(Filme filme) {
            Intent intent = new Intent(ExibeFilmeActivity.this, DetalheFilmeActivity.class);
            intent.putExtra("idFilme",filme.getImdbID());
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.slide_out_right);

        }
    };




    @Override
    public void PesquisaFilmeVazio() {
        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Oops...")
                .setContentText("Nome do filme não pode ser Vazio !")
                .show();

    }

    @Override
    public void PesquisaFilmeSemretorno() {
        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Oops...")
                .setContentText("Filme não encontrado.")
                .show();
    }



    @Override
    public void hideKeyboard(Activity activity) {


        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void Limpar() {
        editFilme.getText().clear();

    }

    @Override
    public void ColapsinExpanded(Boolean aBoolean) {
        appBarLayout.setExpanded(aBoolean);

    }
    public boolean  Network(){
        boolean retorno = true;
        ConnectivityManager conMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        if ( conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED
                || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED ) {

            retorno = true;

        }
        else if ( conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.DISCONNECTED
                || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.DISCONNECTED) {
            retorno = false;

        }

        return  retorno;
    }

    @Override
    public void usuarioSemConexao() {
        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Oops...")
                .setContentText("Verifique sua conexão com a internet !")
                .show();
    }
}
