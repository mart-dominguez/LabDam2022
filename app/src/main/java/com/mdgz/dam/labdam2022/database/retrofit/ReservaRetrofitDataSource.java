package com.mdgz.dam.labdam2022.database.retrofit;

import com.mdgz.dam.labdam2022.database.AlojamientoDataSource;
import com.mdgz.dam.labdam2022.database.ReservaDataSource;
import com.mdgz.dam.labdam2022.database.ReservaEntity;
import com.mdgz.dam.labdam2022.model.AppRetrofit;
import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.model.ReservaRest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
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
    public void guardarReserva(Reserva entidad, GuardarReservaCallback callback)  {
        try {
            Call<ReservaEntity> call = reservaRest.creaReserva(entidad.toEntity());
            System.out.println(entidad);
             call.enqueue(new Callback<ReservaEntity>() {
                @Override
                public void onResponse(Call<ReservaEntity> call, Response<ReservaEntity> response) {
                    System.out.println("Guardado");
                    System.out.println(response.isSuccessful());
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        System.out.println(jObjError.getJSONObject("error").getString("message"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    callback.resultado(true);
                }

                @Override
                public void onFailure(Call<ReservaEntity> call, Throwable t) {
                    System.out.println(t);
                }
            });
        }
        catch (Exception e) {
            throw e;
            //callback.resultado(false);
        }
    }

    @Override
    public void recuperarReserva(RecuperarReservaCallback callback) {
        Call<List<ReservaEntity>> call = reservaRest.listarTodos();

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<ReservaEntity>> call, Response<List<ReservaEntity>> response) {
                List<ReservaEntity> respuesta = response.body();
                List<Reserva> retorno = new ArrayList<>();
                for (ReservaEntity estaReserva : respuesta) {
                    retorno.add(new Reserva(estaReserva.getFechaIngreso(), estaReserva.getFechaSalida(), Double.valueOf(0))); //Nosotros guardabamos monto en la reserva, pero la API no esta armada para eso
                }
                callback.resultado(true, retorno);
            }

            @Override
            public void onFailure(Call<List<ReservaEntity>> call, Throwable t) {
                System.out.println("error: " + t);
                callback.resultado(false, null);
            }
        });
    }
}
