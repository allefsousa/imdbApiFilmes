package com.poppin.movies.data.source.remote;

import com.poppin.movies.data.ListaFilme;
import com.poppin.movies.data.source.DetalheFilme;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;



public interface FilmeInterface {

    @GET("?")
    Call<ListaFilme> RequestFilme(@Query("apikey") String apiKey, @Query("s") String nomeFilme, @Query("type") String tipo);


    @GET("?")
    Call<DetalheFilme> RequestDetalheFilme(@Query("apikey") String apiKey, @Query("i") String idFilme);

}
