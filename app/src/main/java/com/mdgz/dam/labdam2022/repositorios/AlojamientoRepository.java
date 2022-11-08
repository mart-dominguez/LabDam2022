package com.mdgz.dam.labdam2022.repositorios;

import android.content.Context;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.persistencia.AlojamientoDataSource;
import com.mdgz.dam.labdam2022.persistencia.enumerados.TipoPersistencia;
import com.mdgz.dam.labdam2022.persistencia.room.AlojamientoRoomDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.bd.BaseDeDatos;

public class AlojamientoRepository {

    private static AlojamientoRepository _REPO = null;
    private AlojamientoDataSource alojamientoDataSource;

    // patron singleton
    private AlojamientoRepository(Context ctx)
    {
        alojamientoDataSource = new AlojamientoRoomDataSource(ctx);
    }

    public static AlojamientoRepository getInstance(Context ctx){
        if(_REPO==null)_REPO = new AlojamientoRepository(ctx);
        return _REPO;
    }

    public void guardarAlojamiento(final Alojamiento entidad, final AlojamientoDataSource.GuardarAlojamientoCallback callback){
        alojamientoDataSource.guardarAlojamiento(entidad,callback);
    }
    public void recuperarAlojamientos(final AlojamientoDataSource.RecuperarAlojamientosCallback callback){
        alojamientoDataSource.recuperarAlojamientos(callback);
    }

}
