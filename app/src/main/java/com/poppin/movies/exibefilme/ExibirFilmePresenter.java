package com.poppin.movies.exibefilme;

import android.text.TextUtils;

import com.poppin.movies.data.Filme;

import java.util.List;



public class ExibirFilmePresenter implements ExibeFilmeContract.Presenter, ExibeFilmeContract.getFilmeService.OnFinishedListener {
    private ExibeFilmeContract.View myView;
    private ExibeFilmeContract.getFilmeService filmeService;

    public ExibirFilmePresenter(ExibeFilmeContract.View myView, ExibeFilmeContract.getFilmeService filmeService) {
        this.myView = myView;
        this.filmeService = filmeService;
    }

    @Override
    public void PesquisaFilme(String filme) {

        if (!TextUtils.isEmpty(filme)) {
            filmeService.getFilmeArrayList(this, filme);
        } else {
            myView.PesquisaFilmeVazio();
        }

    }


    public void ExibirfilmeView(ExibeFilmeContract.View view) {
        myView = view;
    }

    @Override
    public void onFinished(List<Filme> filmeList) {
        if (filmeList == null){
            myView.PesquisaFilmeSemretorno();
        }else {
            myView.RecyclerViewSetValue(filmeList);
            myView.ColapsinExpanded(false);
            myView.Limpar();
        }

    }

    @Override
    public void onFailure(Throwable t) {
        myView.onResponseFaiulure(t);
    }
}
