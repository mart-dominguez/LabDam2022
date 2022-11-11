package com.mdgz.dam.labdam2022.repositorios;

import android.content.Context;

import com.mdgz.dam.labdam2022.model.Ciudad;
import com.mdgz.dam.labdam2022.persistencia.interfaces.CiudadDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.impl.CiudadRoomDataSource;

public class CiudadRepository
{
    private static CiudadRepository _REPO = null;
    private CiudadDataSource ciudadDataSource;

    // patron singleton
    private CiudadRepository(Context ctx)
    {
        ciudadDataSource = new CiudadRoomDataSource(ctx);
    }

    public static CiudadRepository getInstance(Context ctx)
    {
        if(_REPO==null)_REPO = new CiudadRepository(ctx);
        return _REPO;
    }

    public static CiudadRepository getInstance()
    {
        if(_REPO==null) throw new RuntimeException();
        else return _REPO;
    }


    public void getByID(Integer id, CiudadDataSource.GetByIDCallback callback)
    {
        ciudadDataSource.getByID(id,callback);
    }

    public void guardar(Ciudad ciudad, CiudadDataSource.GuardarCallback callback)
    {
        ciudadDataSource.guardar(ciudad,callback);
    }

}
