package com.mdgz.dam.labdam2022.repositorios;

import android.content.Context;

import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.persistencia.ReservaDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.ReservaRoomDataSource;

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

    public void guardarReserva(final Reserva entidad, final ReservaDataSource.GuardarReservaCallback callback){
        reservaDataSource.guardarReserva(entidad,callback);
    }
    public void recuperarReservas(final ReservaDataSource.RecuperarReservasCallback callback){
        reservaDataSource.recuperarReservas(callback);
    }
    
}
