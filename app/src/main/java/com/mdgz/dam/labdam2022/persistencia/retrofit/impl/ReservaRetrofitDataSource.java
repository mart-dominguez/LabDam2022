package com.mdgz.dam.labdam2022.persistencia.retrofit.impl;

import android.util.Log;

import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.persistencia.interfaces.ReservaDataSource;
import com.mdgz.dam.labdam2022.persistencia.retrofit.apirest.RetrofitBD;
import com.mdgz.dam.labdam2022.persistencia.retrofit.daos.ReservaDao;
import com.mdgz.dam.labdam2022.persistencia.retrofit.entidades.ReservaEntity;
import com.mdgz.dam.labdam2022.persistencia.retrofit.mapeadores.ReservaMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservaRetrofitDataSource implements ReservaDataSource {

    private ReservaDao reservaDao;

    public ReservaRetrofitDataSource()
    {
        RetrofitBD bd = RetrofitBD.getInstance();
        reservaDao = bd.reservaDao();
    }

    @Override
    public void guardar(Reserva entidad, GuardarReservaCallback callback) {

        Call<ReservaEntity> call = reservaDao.insertar(ReservaMapper.toEntity(entidad));
        call.enqueue(new Callback<ReservaEntity>() {
            @Override
            public void onResponse(Call<ReservaEntity> call, Response<ReservaEntity> response) {
                if(response.isSuccessful()) callback.resultado(true);
                else callback.resultado(false);
            }

            @Override
            public void onFailure(Call<ReservaEntity> call, Throwable t) {
                callback.resultado(false);
            }
        });
        
    }

    @Override
    public void getTodas(RecuperarReservasCallback callback) {

        Call<List<ReservaEntity>> call = reservaDao.getAll();
        call.enqueue(new Callback<List<ReservaEntity>>() {
            @Override
            public void onResponse(Call<List<ReservaEntity>> call, Response<List<ReservaEntity>> response) {
                if(response.isSuccessful()){
                    List<ReservaEntity> lista = response.body();
                    // Pasar todos los entities a model
                    List<Reserva> reservas = new ArrayList<>();
                    for (ReservaEntity reservaEntity:lista) {
                        reservas.add(ReservaMapper.toModel(reservaEntity));
                    }
                    callback.resultado(true, reservas);
                }
                else callback.resultado(false, null);

            }

            @Override
            public void onFailure(Call<List<ReservaEntity>> call, Throwable t) {
                callback.resultado(false, null);
            }
        });
        
    }

    @Override
    public void getById(UUID id, GetByIDCallback callback) {
        //La api no admite obtener una reserva por su id
        Log.e("API:","No admite obtener una reserva por su ID.");
    }

    @Override
    public void getFromUser(UUID id, ReservaDataSource.RecuperarReservasCallback callback) {

        Call<List<ReservaEntity>> call = reservaDao.getFromUser(id);
        call.enqueue(new Callback<List<ReservaEntity>>() {
            @Override
            public void onResponse(Call<List<ReservaEntity>> call, Response<List<ReservaEntity>> response) {
                if(response.isSuccessful()){
                    List<ReservaEntity> lista = response.body();
                    // Pasar todos los entities a model
                    List<Reserva> reservas = new ArrayList<>();
                    for (ReservaEntity reservaEntity:lista) {
                        reservas.add(ReservaMapper.toModel(reservaEntity));
                    }
                    callback.resultado(true, reservas);
                }
                else callback.resultado(false, null);

            }

            @Override
            public void onFailure(Call<List<ReservaEntity>> call, Throwable t) {
                callback.resultado(false, null);
            }
        });
        
    }

    @Override
    public void eliminar(Reserva Reserva, EliminarCallback callback) {

        Call<ResponseBody> call = reservaDao.eliminar(Reserva.getAlojamiento().getId());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) callback.resultado(true);
                else callback.resultado(false);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.resultado(false);
            }
        });
        
    }
}
