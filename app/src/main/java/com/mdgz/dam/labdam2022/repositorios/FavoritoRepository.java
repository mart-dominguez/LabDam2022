package com.mdgz.dam.labdam2022.repositorios;

import android.content.Context;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.persistencia.AlojamientoDataSource;
import com.mdgz.dam.labdam2022.persistencia.FavoritoDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.AlojamientoRoomDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.FavoritoRoomDataSource;

public class FavoritoRepository {

    private static FavoritoRepository _REPO = null;
    private FavoritoDataSource favoritoDataSource;

    // patron singleton
    private FavoritoRepository(Context ctx){
        favoritoDataSource = new FavoritoRoomDataSource(ctx);
    }

    public static FavoritoRepository getInstance(Context ctx){
        if(_REPO==null)_REPO = new FavoritoRepository(ctx);
        return _REPO;
    }

    public void guardarFavorito(final Favorito entidad, final FavoritoDataSource.GuardarFavoritoCallback callback){
        favoritoDataSource.guardarFavorito(entidad,callback);
    }
    public void recuperarFavoritos(final FavoritoDataSource.RecuperarFavoritosCallback callback){
        favoritoDataSource.recuperarFavoritos(callback);
    }
}
