package com.poppin.movies.exibefilme;

import android.app.Activity;
import android.content.Context;

import com.poppin.movies.data.Filme;
import com.poppin.movies.data.ListaFilme;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by allef on 06/08/2018.
 */

public interface ExibeFilmeContract {

    interface View{
        void PesquisaFilmeVazio();
        void PesquisaFilmeSemretorno();
        void PesquisaFilmeSemConexao();
        void hideKeyboard(Activity activity);
        void Limpar();
        void ColapsinExpanded(Boolean aBoolean);
        void RecyclerViewSetValue(List<Filme> filmeList);
        void onResponseFaiulure(Throwable t);
        boolean Network();
        void usuarioSemConexao();
    }

    interface Presenter{
        void PesquisaFilme(String filme);

    }

    interface getFilmeService {

        interface OnFinishedListener {
            void onFinished(List<Filme> noticeArrayList);
            void onFailure(Throwable t);
        }

        void getFilmeArrayList(OnFinishedListener onFinishedListener,String nomeFilme);
    }
}
