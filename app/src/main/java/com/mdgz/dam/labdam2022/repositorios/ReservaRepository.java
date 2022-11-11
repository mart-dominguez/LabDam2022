package com.mdgz.dam.labdam2022.repositorios;

import android.content.Context;

import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.persistencia.interfaces.ReservaDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.impl.ReservaRoomDataSource;

public class ReservaRepository {

    private static ReservaRepository _REPO = null;
    private ReservaDataSource reservaDataSource;

    // patron singleton
    private ReservaRepository(Context ctx){
        reservaDataSource = new ReservaRoomDataSource(ctx);
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
        reservaDataSource.guardar(entidad,callback);
    }
    public void getTodas(final ReservaDataSource.RecuperarReservasCallback callback){
        reservaDataSource.getTodas(callback);
    }
    
}
