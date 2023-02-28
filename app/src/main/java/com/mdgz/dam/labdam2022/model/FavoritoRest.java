package com.mdgz.dam.labdam2022.model;

import com.mdgz.dam.labdam2022.database.FavoritoEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FavoritoRest {
    @GET("favorito")
    Call<List<FavoritoEntity>> listarTodos();

    @POST("favorito")
    Call<FavoritoEntity> creaFavorito(@Body FavoritoEntity f);

    @GET("favorito/{id}")
    Call<List<FavoritoEntity>> favoritoById(@Path("id") String idFavorito);
}
