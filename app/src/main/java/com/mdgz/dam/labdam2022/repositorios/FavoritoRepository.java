package com.mdgz.dam.labdam2022.repositorios;

import android.content.Context;

import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.persistencia.interfaces.FavoritoDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.impl.FavoritoRoomDataSource;

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

    public static FavoritoRepository getInstance()
    {
        if(_REPO==null) throw new RuntimeException();
        else return _REPO;
    }

    public void guardar(final Favorito entidad, final FavoritoDataSource.GuardarCallback callback){
        favoritoDataSource.guardar(entidad,callback);
    }
    public void getTodos(final FavoritoDataSource.RecuperarCallback callback){
        favoritoDataSource.getTodos(callback);
    }

    public void eliminar(Favorito favorito, FavoritoDataSource.EliminarCallback callback)
    {
        favoritoDataSource.eliminar(favorito,callback);
    }
}
