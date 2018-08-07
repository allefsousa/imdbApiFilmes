package com.poppin.movies.detalhefilme;

import com.poppin.movies.data.Filme;
import com.poppin.movies.data.ListaFilme;
import com.poppin.movies.data.source.DetalheFilme;
import com.poppin.movies.data.source.RetrofitInstance;
import com.poppin.movies.data.source.remote.FilmeInterface;
import com.poppin.movies.exibefilme.ExibeFilmeContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class getDetalheServiceImpl implements DetalheFilmeContract.GetDetalheService {
    private final String apiKey = "34261c18";
    private final String tipoFilme = "movie";


    @Override
    public void getDetalheFilme(OnFinishedListener onFinishedListener, String idFilme) {

        FilmeInterface filmeInterface = RetrofitInstance.getRetrofitInstance().create(FilmeInterface.class);


        Call<DetalheFilme> call = filmeInterface.RequestDetalheFilme(apiKey,idFilme);
        call.enqueue(new Callback<DetalheFilme>() {
            @Override
            public void onResponse(Call<DetalheFilme> call, Response<DetalheFilme> response) {
                onFinishedListener.onFinished(response.body());
            }

            @Override
            public void onFailure(Call<DetalheFilme> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });

    }
}
