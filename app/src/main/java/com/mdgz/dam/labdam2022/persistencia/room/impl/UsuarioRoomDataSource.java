package com.mdgz.dam.labdam2022.persistencia.room.impl;

import android.content.Context;

import com.mdgz.dam.labdam2022.model.Usuario;
import com.mdgz.dam.labdam2022.persistencia.interfaces.UsuarioDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.bd.BaseDeDatos;
import com.mdgz.dam.labdam2022.persistencia.room.daos.UsuarioDao;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.UsuarioEntity;
import com.mdgz.dam.labdam2022.persistencia.room.mapeadores.UsuarioMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UsuarioRoomDataSource implements UsuarioDataSource
{
    private BaseDeDatos bd;
    private UsuarioDao usuarioDao;

    public UsuarioRoomDataSource(Context context)
    {
        bd = BaseDeDatos.getInstance(context);
        usuarioDao = bd.usuarioDao();
    }

    @Override
    public void guardar(Usuario entidad, GuardarCallback callback)
    {
        try
        {
            UsuarioEntity entity = UsuarioMapper.toEntity(entidad);
            usuarioDao.insertar(entity);
            callback.resultado(true);
        } catch(Exception e)
        {
            callback.resultado(false);
        }

    }

    @Override
    public void getTodos(RecuperarCallback callback)
    {
        try
        {
            List<UsuarioEntity> entities = usuarioDao.getTodos();
            List<Usuario> usuarios = new ArrayList<>();
            for(UsuarioEntity entity : entities)
            {
                Usuario usuario = UsuarioMapper.toModel(entity);
                usuarios.add(usuario);
            }
            callback.resultado(true,usuarios);
        } catch(Exception e)
        {
            callback.resultado(false,null);
        }
    }

    @Override
    public void getByID(UUID id, GetByIDCallback callback)
    {
        try
        {
            UsuarioEntity entity = usuarioDao.getByID(id);
            Usuario usuario = UsuarioMapper.toModel(entity);
            callback.resultado(true,usuario);
        }catch(Exception e)
        {
            callback.resultado(false,null);
        }

    }
}
