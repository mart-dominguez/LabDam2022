package com.mdgz.dam.labdam2022.persistencia.room.impl;

import android.content.Context;

import com.mdgz.dam.labdam2022.model.Hotel;
import com.mdgz.dam.labdam2022.persistencia.interfaces.HotelDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.bd.BaseDeDatos;
import com.mdgz.dam.labdam2022.persistencia.room.daos.HotelDao;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.HotelEntity;
import com.mdgz.dam.labdam2022.persistencia.room.mapeadores.HotelMapper;

import java.util.ArrayList;
import java.util.List;

public class HotelRoomDataSource implements HotelDataSource
{
    private final HotelDao hotelDao;
    private UbicacionRoomDataSource ubicacionRoomDataSource;
    private final BaseDeDatos bd;

    public HotelRoomDataSource(Context context)
    {
        bd = BaseDeDatos.getInstance(context);
        hotelDao = bd.hotelDao();
        ubicacionRoomDataSource = new UbicacionRoomDataSource(context);
    }

    @Override
    public void guardar(Hotel entidad, GuardarCallback callback)
    {
        try
        {
            HotelEntity entity = HotelMapper.toEntity(entidad);
            ubicacionRoomDataSource.guardar(entidad.getUbicacion(),exito -> {});
            hotelDao.insertar(entity);
            callback.resultado(true);
        }
        catch(Exception e)
        {
            callback.resultado(false);
        }
    }

    @Override
    public void getTodos(RecuperarCallback callback)
    {
        try
        {
            List<HotelEntity> entities = hotelDao.getTodos();
            List<Hotel> hoteles = new ArrayList<>();
            for(HotelEntity entity : entities)
            {
                Hotel hotel = HotelMapper.toModel(entity);
                hoteles.add(hotel);
            }
            callback.resultado(true,hoteles);
        }
        catch (Exception e)
        {
            callback.resultado(false, null);
        }

    }

    @Override
    public void getByID(Integer id, GetByIDCallback callback)
    {
        try
        {
            HotelEntity entity = hotelDao.getByID(id);
            Hotel hotel = HotelMapper.toModel(entity);
            callback.resultado(true,hotel);
        }
        catch (Exception e)
        {
            callback.resultado(false,null);
        }
    }
}
