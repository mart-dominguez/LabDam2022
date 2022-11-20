package com.mdgz.dam.labdam2022.repositorios;

import android.content.Context;

import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.model.Usuario;
import com.mdgz.dam.labdam2022.persistencia.interfaces.FavoritoDataSource;
import com.mdgz.dam.labdam2022.persistencia.interfaces.ReservaDataSource;
import com.mdgz.dam.labdam2022.persistencia.retrofit.impl.ReservaRetrofitDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.impl.ReservaRoomDataSource;

public class ReservaRepository {

    private static ReservaRepository _REPO = null;
    private ReservaDataSource reservaRoomDataSource;
    private ReservaDataSource reservaRetrofitDataSource;

    // patron singleton
    private ReservaRepository(Context ctx){
        reservaRoomDataSource = new ReservaRoomDataSource(ctx);
        reservaRetrofitDataSource = new ReservaRetrofitDataSource();
    }

    public static ReservaRepository getInstance(Context ctx){
        if(_REPO==null)_REPO = new ReservaRepository(ctx);
        return _REPO;
    }

    public static ReservaRepository getInstance()
    {
        if(_REPO==null) throw new RuntimeException();
        else return _REPO;
    }

    public void guardar(final Reserva entidad, final ReservaDataSource.GuardarReservaCallback callback){
        reservaRoomDataSource.guardar(entidad,callback); // ROOM
        reservaRetrofitDataSource.guardar(entidad,callback); // RETROFIT
    }
    public void getTodas(final ReservaDataSource.RecuperarReservasCallback callback){
        reservaRoomDataSource.getTodas(callback); // ROOM
        reservaRetrofitDataSource.getTodas(callback); // RETROFIT
    }

    // Solo disponible en RETROFIT
    public void getTodosDelUsuario(Usuario usuario, ReservaDataSource.RecuperarReservasCallback callback){
        // reservaRoomDataSource.getFromUser(usuario.getId(),callback); // no implementado
        reservaRetrofitDataSource.getFromUser(usuario.getId(),callback);
    }

    public void eliminar(Reserva reserva, ReservaDataSource.EliminarCallback callback)
    {
        reservaRoomDataSource.eliminar(reserva,callback); // ROOM
        reservaRetrofitDataSource.eliminar(reserva,callback); // RETROFIT

    }
    
}
