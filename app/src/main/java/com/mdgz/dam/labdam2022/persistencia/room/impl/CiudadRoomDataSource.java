package com.mdgz.dam.labdam2022.persistencia.room.impl;

import android.content.Context;

import com.mdgz.dam.labdam2022.model.Ciudad;
import com.mdgz.dam.labdam2022.persistencia.interfaces.CiudadDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.bd.BaseDeDatos;
import com.mdgz.dam.labdam2022.persistencia.room.daos.CiudadDao;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.CiudadEntity;
import com.mdgz.dam.labdam2022.persistencia.room.mapeadores.CiudadMapper;

public class CiudadRoomDataSource implements CiudadDataSource
{
    private BaseDeDatos bd;
    private CiudadDao ciudadDao;

    public CiudadRoomDataSource(Context context)
    {
        bd = BaseDeDatos.getInstance(context);
        ciudadDao = bd.ciudadDao();
    }

    @Override
    public void getByID(Integer id, GetByIDCallback callback)
    {
        try
        {
            CiudadEntity entity = ciudadDao.getByID(id);
            Ciudad ciudad = CiudadMapper.toModel(entity);
            callback.resultado(true,ciudad);
        }
        catch (Exception e)
        {
            callback.resultado(false,null);
        }
    }

    @Override
    public void guardar(Ciudad ciudad, GuardarCallback callback)
    {
        try
        {
            CiudadEntity entity = CiudadMapper.toEntity(ciudad);
            ciudadDao.insertar(entity);
            callback.resultado(true);

        } catch(Exception e)
        {
            callback.resultado(false);
        }
    }
}
