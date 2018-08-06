package com.poppin.movies.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class ListaFilme implements Serializable {

    @SerializedName("Search")
    @Expose
    private List<Filme> filmes = new ArrayList<>();
    @SerializedName("totalResults")
    @Expose
    private String totalResults;
    @SerializedName("Response")
    @Expose
    private String response;
    private final static long serialVersionUID = -2732731473422173222L;

    public ListaFilme(List<Filme> filmes, String totalResults, String response) {
        this.filmes = filmes;
        this.totalResults = totalResults;
        this.response = response;
    }

    public List<Filme> getFilmes() {
        return filmes;
    }

    public void setFilmes(List<Filme> filmes) {
        this.filmes = filmes;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
