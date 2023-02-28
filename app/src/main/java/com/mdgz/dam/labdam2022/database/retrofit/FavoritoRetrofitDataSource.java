package com.mdgz.dam.labdam2022.database.retrofit;

import com.mdgz.dam.labdam2022.database.FavoritoDataSource;
import com.mdgz.dam.labdam2022.model.AppRetrofit;
import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.model.FavoritoRest;

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
            favoritoRest.creaFavorito(entidad);
            callback.resultado(true);
        }
        catch (Exception e) {
            callback.resultado(true);
        }
    }

    @Override
    public void recuperarFavorito(RecuperarFavoritoCallback callback) {
        try {
            Call<List<Favorito>> call = favoritoRest.listarTodos();

            call.enqueue(new Callback<>() {
                @Override
                public void onResponse(Call<List<Favorito>> call, Response<List<Favorito>> response) {
                    callback.resultado(true, response.body());
                }

                @Override
                public void onFailure(Call<List<Favorito>> call, Throwable t) {
                    callback.resultado(false, null);
                }
            });
        }
        catch(Exception e) {
            callback.resultado(false, null);
        }
    }
}
