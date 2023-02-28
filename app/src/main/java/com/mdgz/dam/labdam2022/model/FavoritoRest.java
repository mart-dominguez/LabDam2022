package com.mdgz.dam.labdam2022.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FavoritoRest {
    @GET("favorito/")
    Call<List<Favorito>> listarTodos();

    @POST("favorito/")
    Call<Favorito> creaFavorito(@Body Favorito f);

    @GET("favorito/{id}")
    Call<List<Favorito>> favoritoById(@Path("id") String idFavorito);
}
