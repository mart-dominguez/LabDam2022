package com.mdgz.dam.labdam2022.model;

import com.mdgz.dam.labdam2022.database.ReservaEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ReservaRest {
    @GET("reserva")
    Call<List<ReservaEntity>> listarTodos();

    @POST("reserva")
    Call<ReservaEntity> creaReserva(@Body ReservaEntity r);

    @GET("reserva/{id}")
    Call<List<ReservaEntity>> reservaById(@Path("id") String idReserva);
}
