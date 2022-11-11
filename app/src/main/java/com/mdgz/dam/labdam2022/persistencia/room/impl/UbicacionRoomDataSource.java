package com.mdgz.dam.labdam2022.persistencia.room.impl;

import android.content.Context;

import com.mdgz.dam.labdam2022.model.Ubicacion;
import com.mdgz.dam.labdam2022.persistencia.interfaces.UbicacionDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.bd.BaseDeDatos;
import com.mdgz.dam.labdam2022.persistencia.room.daos.UbicacionDao;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.UbicacionEntity;
import com.mdgz.dam.labdam2022.persistencia.room.mapeadores.UbicacionMapper;

public class UbicacionRoomDataSource implements UbicacionDataSource
{
    private BaseDeDatos bd;
    private UbicacionDao ubicacionDao;
    private CiudadRoomDataSource ciudadRoomDataSource;

    public UbicacionRoomDataSource(Context context)
    {
        bd = BaseDeDatos.getInstance(context);
        ubicacionDao = bd.ubicacionDao();
        ciudadRoomDataSource = new CiudadRoomDataSource(context);
    }

    @Override
    public void getByID(Integer id, UbicacionDataSource.GetByIDCallback callback)
    {
        try
        {
            UbicacionEntity entity = ubicacionDao.getByID(id);
            Ubicacion ubicacion = UbicacionMapper.toModel(entity);
            callback.resultado(true,ubicacion);
        }
        catch (Exception e)
        {
            callback.resultado(false,null);
        }
    }

    @Override
    public void guardar(Ubicacion entidad, GuardarCallback callback)
    {
        try
        {
            UbicacionEntity entity = UbicacionMapper.toEntity(entidad);
            ciudadRoomDataSource.guardar(entidad.getCiudad(),exito -> {});
            ubicacionDao.insertar(entity);
            callback.resultado(true);
        }
        catch(Exception e)
        {
            callback.resultado(false);
        }

    }
}
