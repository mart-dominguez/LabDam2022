package com.mdgz.dam.labdam2022.repositorios;

import android.content.Context;

import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.model.Usuario;
import com.mdgz.dam.labdam2022.persistencia.interfaces.FavoritoDataSource;
import com.mdgz.dam.labdam2022.persistencia.retrofit.impl.FavoritoRetrofitDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.impl.FavoritoRoomDataSource;

public class FavoritoRepository {

    private static FavoritoRepository _REPO = null;
    private FavoritoDataSource favoritoRoomDataSource;
    private FavoritoDataSource favoritoRetrofitDataSource;

    // patron singleton
    private FavoritoRepository(Context ctx){
        favoritoRoomDataSource = new FavoritoRoomDataSource(ctx); // ROOM
        favoritoRetrofitDataSource = new FavoritoRetrofitDataSource(); // RETROFIT
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
        favoritoRoomDataSource.guardar(entidad,callback); // ROOM
        favoritoRetrofitDataSource.guardar(entidad,callback); // RETROFIT
    }
    public void getTodos(final FavoritoDataSource.RecuperarCallback callback){
        favoritoRoomDataSource.getTodos(callback); // ROOM
        favoritoRetrofitDataSource.getTodos(callback); // RETROFIT
    }

    // Solo disponible en RETROFIT
    public void getTodosDelUsuario(Usuario usuario, FavoritoDataSource.RecuperarCallback callback){
        // favoritoRoomDataSource.getFromUser(usuario.getId(),callback); // no implementado
        favoritoRetrofitDataSource.getFromUser(usuario.getId(),callback);
    }

    public void eliminar(Favorito favorito, FavoritoDataSource.EliminarCallback callback)
    {
        favoritoRoomDataSource.eliminar(favorito,callback); // ROOM
        favoritoRetrofitDataSource.eliminar(favorito,callback); // RETROFIT

    }
}
