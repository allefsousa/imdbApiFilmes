package com.poppin.movies.exibefilme;

import com.poppin.movies.data.ListaFilme;
import com.poppin.movies.data.source.RetrofitInstance;
import com.poppin.movies.data.source.remote.FilmeInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by allef on 06/08/2018.
 */

public class getFilmeServiceImpl implements ExibeFilmeContract.getFilmeService {
    private final String apiKey = "34261c18";
    private final String tipoFilme = "movie";

    @Override
    public void getFilmeArrayList(OnFinishedListener onFinishedListener,String nomeFilme) {

        FilmeInterface filmeInterface = RetrofitInstance.getRetrofitInstance().create(FilmeInterface.class);


        Call<ListaFilme> call = filmeInterface.RequestFilme(apiKey,nomeFilme,tipoFilme);
        call.enqueue(new Callback<ListaFilme>() {
            @Override
            public void onResponse(Call<ListaFilme> call, Response<ListaFilme> response) {
                onFinishedListener.onFinished(response.body().getFilmes());
            }

            @Override
            public void onFailure(Call<ListaFilme> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });


    }


}
