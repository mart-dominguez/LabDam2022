package com.mdgz.dam.labdam2022.database.retrofit;

import com.mdgz.dam.labdam2022.database.AlojamientoDataSource;
import com.mdgz.dam.labdam2022.database.ReservaDataSource;
import com.mdgz.dam.labdam2022.model.AppRetrofit;
import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.model.ReservaRest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservaRetrofitDataSource implements ReservaDataSource {
    private ReservaRest reservaRest;

    public ReservaRetrofitDataSource() {
        AppRetrofit appRetrofit = AppRetrofit.getInstance();
        reservaRest = appRetrofit.reservaRest();
    }

    @Override
    public void guardarReserva(Reserva entidad, GuardarReservaCallback callback) {
        try {
            reservaRest.creaReserva(entidad);
            callback.resultado(true);
        }
        catch (Exception e) {
            callback.resultado(false);
        }
    }

    @Override
    public void recuperarReserva(RecuperarReservaCallback callback) {
        Call<List<Reserva>> call = reservaRest.listarTodos();

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Reserva>> call, Response<List<Reserva>> response) {
                callback.resultado(true, response.body());
            }

            @Override
            public void onFailure(Call<List<Reserva>> call, Throwable t) {
                callback.resultado(false, null);
            }
        });
    }
}
