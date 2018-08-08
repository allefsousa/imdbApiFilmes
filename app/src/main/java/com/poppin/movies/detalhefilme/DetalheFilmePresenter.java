package com.poppin.movies.detalhefilme;

import android.text.TextUtils;

import com.poppin.movies.data.source.DetalheFilme;

/**
 * Created by allef on 07/08/2018.
 */

public class DetalheFilmePresenter implements DetalheFilmeContract.Presenter,DetalheFilmeContract.GetDetalheService.OnFinishedListener{

    DetalheFilmeContract.GetDetalheService getDetalheService;
    DetalheFilmeContract.View myDetViews;


    public DetalheFilmePresenter(DetalheFilmeContract.GetDetalheService getDetalheService, DetalheFilmeContract.View myDetViews) {
        this.getDetalheService = getDetalheService;
        this.myDetViews = myDetViews;
    }

    @Override
    public void PesquisaFilme(String idfilme) {

        if (!TextUtils.isEmpty(idfilme)) {
            getDetalheService.getDetalheFilme(this,idfilme);
        } else {
            myDetViews.DetalheVazio();
        }

    }

    @Override
    public void onFinished(DetalheFilme detalheFilme) {
        if (detalheFilme == null){
            myDetViews.DetalheVazio();
        }else {
            myDetViews.ViewSetValue(detalheFilme);
            myDetViews.ViewSetRating(detalheFilme.getRatings());
        }


    }



    @Override
    public void onFailure(Throwable t) {
        myDetViews.onFailure(t);

    }
}
