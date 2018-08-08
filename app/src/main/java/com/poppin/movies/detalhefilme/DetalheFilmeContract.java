package com.poppin.movies.detalhefilme;

import android.media.Rating;

import com.poppin.movies.data.Avaliacao;
import com.poppin.movies.data.source.DetalheFilme;

import java.util.List;

/**
 * Created by allef on 06/08/2018.
 */

public interface DetalheFilmeContract {

    interface View {
        void PesquisaDetalhe(String NomeFilme);
        
        void ViewSetValue(DetalheFilme detalheFilme);
        

        void ViewSetRating(List<Avaliacao> ratings);


        void DetalheVazio();

        void onFailure(Throwable t);
    }

    interface Presenter {
        void PesquisaFilme(String filme);
    }

    interface GetDetalheService {

        interface OnFinishedListener {
            void onFinished(DetalheFilme detalheFilme);
            void onFailure(Throwable t);
        }
        void getDetalheFilme(OnFinishedListener onFinishedListener, String idFilme);

    }
}
