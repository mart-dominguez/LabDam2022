package com.mdgz.dam.labdam2022.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ReservaRest {
    @GET("reserva/")
    Call<List<Reserva>> listarTodos();

    @POST("reserva/")
    Call<Reserva> creaReserva(@Body Reserva r);

    @GET("reserva/{id}")
    Call<List<Reserva>> reservaById(@Path("id") String idReserva);
}
