package com.mdgz.dam.labdam2022.database.retrofit;

import com.mdgz.dam.labdam2022.database.FavoritoDataSource;
import com.mdgz.dam.labdam2022.database.FavoritoEntity;
import com.mdgz.dam.labdam2022.database.room.AlojamientoRoomDataSource;
import com.mdgz.dam.labdam2022.model.AppRetrofit;
import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.model.FavoritoRest;
import com.mdgz.dam.labdam2022.model.Reserva;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoritoRetrofitDataSource implements FavoritoDataSource {
    private FavoritoRest favoritoRest;

    public FavoritoRetrofitDataSource() {
        AppRetrofit appRetrofit = AppRetrofit.getInstance();
        favoritoRest = appRetrofit.favoritoRest();
    }

    @Override
    public void guardarFavorito(Favorito entidad, GuardarFavoritoCallback callback) {
        try {
            Call<FavoritoEntity> call = favoritoRest.creaFavorito(entidad.toEntity());
            call.enqueue(new Callback<>() {
                @Override
                public void onResponse(Call<FavoritoEntity> call, Response<FavoritoEntity> response) {
                    System.out.println(response.isSuccessful());
                    callback.resultado(response.isSuccessful());
                }

                @Override
                public void onFailure(Call<FavoritoEntity> call, Throwable t) {
                    callback.resultado(false);
                }
            });
        }
        catch (Exception e) {
            callback.resultado(true);
        }
    }

    @Override
    public void recuperarFavorito(RecuperarFavoritoCallback callback) {
        try {
            Call<List<FavoritoEntity>> call = favoritoRest.listarTodos();

            call.enqueue(new Callback<>() {
                @Override
                public void onResponse(Call<List<FavoritoEntity>> call, Response<List<FavoritoEntity>> response) {
                    List<FavoritoEntity> listaResponse = response.body();
                    List<Favorito> listaFavoritos = new ArrayList<>();

                    for (FavoritoEntity favoritoEntity : listaResponse) {
                        listaFavoritos.add(new Favorito(favoritoEntity.getAlojamientoID()));
                    }

                    callback.resultado(response.isSuccessful(), listaFavoritos);
                }

                @Override
                public void onFailure(Call<List<FavoritoEntity>> call, Throwable t) {
                    callback.resultado(false, null);
                }
            });
        }
        catch(Exception e) {
            callback.resultado(false, null);
        }
    }
}
