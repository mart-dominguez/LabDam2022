package com.mdgz.dam.labdam2022.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface HabitacionRest {
    @GET("habitacion/")
    Call<List<Habitacion>> listarTodos();

    @POST("habitacion/")
    Call<Habitacion> creaHabitacion(@Body Habitacion h);

    @GET("habitacion/{id}")
    Call<List<Habitacion>> habitacionById(@Path("id") String idHabitacion);
}
