package com.mdgz.dam.labdam2022.repositorios;

import android.content.Context;

import com.mdgz.dam.labdam2022.persistencia.interfaces.UbicacionDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.impl.UbicacionRoomDataSource;

public class UbicacionRepository {

    private static UbicacionRepository _REPO = null;
    private UbicacionDataSource ubicacionDataSource;

    // patron singleton
    private UbicacionRepository(Context ctx)
    {
        ubicacionDataSource = new UbicacionRoomDataSource(ctx);
    }

    public static UbicacionRepository getInstance(Context ctx)
    {
        if(_REPO==null)_REPO = new UbicacionRepository(ctx);
        return _REPO;
    }

    public static UbicacionRepository getInstance()
    {
        if(_REPO==null) throw new RuntimeException();
        else return _REPO;
    }


    public void getByID(Integer id, UbicacionDataSource.GetByIDCallback callback)
    {
        ubicacionDataSource.getByID(id,callback);
    }
}
