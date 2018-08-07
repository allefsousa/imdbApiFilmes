package com.poppin.movies.detalhefilme;

import com.poppin.movies.data.source.DetalheFilme;

/**
 * Created by allef on 06/08/2018.
 */

public interface DetalheFilmeContract {

    interface View {
        void PesquisaDetalhe(String NomeFilme);

        void DetalheVazio();

        void DetalheSemRetrono();

        void DetalheSemConexao();

        void ViewSetValue(DetalheFilme detalheFilme);

        void onResponseFaiulure(Throwable t);

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
