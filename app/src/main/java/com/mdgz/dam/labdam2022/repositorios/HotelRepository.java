package com.mdgz.dam.labdam2022.repositorios;

import android.content.Context;

import com.mdgz.dam.labdam2022.model.Hotel;
import com.mdgz.dam.labdam2022.persistencia.interfaces.HotelDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.impl.HotelRoomDataSource;

public class HotelRepository
{
    private static HotelRepository _REPO = null;
    private HotelDataSource hotelDataSource;

    // patron singleton
    private HotelRepository(Context ctx)
    {
        hotelDataSource = new HotelRoomDataSource(ctx);
    }

    public static HotelRepository getInstance(Context ctx)
    {
        if(_REPO==null)_REPO = new HotelRepository(ctx);
        return _REPO;
    }

    public static HotelRepository getInstance()
    {
        if(_REPO==null) throw new RuntimeException();
        else return _REPO;
    }

    public void guardar(final Hotel entidad, final HotelDataSource.GuardarCallback callback)
    {
        hotelDataSource.guardar(entidad,callback);
    }

    public void getTodos(final HotelDataSource.RecuperarCallback callback)
    {
        hotelDataSource.getTodos(callback);
    }

    public void getByID(Integer id, HotelDataSource.GetByIDCallback callback)
    {
        hotelDataSource.getByID(id,callback);
    }
}
