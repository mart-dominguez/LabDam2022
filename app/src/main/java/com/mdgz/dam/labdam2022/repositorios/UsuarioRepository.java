package com.mdgz.dam.labdam2022.repositorios;

import android.content.Context;

import com.mdgz.dam.labdam2022.model.Usuario;
import com.mdgz.dam.labdam2022.persistencia.interfaces.UsuarioDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.impl.UsuarioRoomDataSource;

import java.util.UUID;

public class UsuarioRepository
{
    private static UsuarioRepository _REPO = null;
    private UsuarioDataSource usuarioDataSource;

    // patron singleton
    private UsuarioRepository(Context ctx)
    {
        usuarioDataSource = new UsuarioRoomDataSource(ctx);
    }

    public static UsuarioRepository getInstance(Context ctx)
    {
        if(_REPO==null)_REPO = new UsuarioRepository(ctx);
        return _REPO;
    }

    public static UsuarioRepository getInstance()
    {
        if(_REPO==null) throw new RuntimeException();
        else return _REPO;
    }


    public void getByID(UUID id, UsuarioDataSource.GetByIDCallback callback)
    {
        usuarioDataSource.getByID(id,callback);
    }

    public void getTodos(UsuarioDataSource.RecuperarCallback callback)
    {
        usuarioDataSource.getTodos(callback);
    }

    public void guardar(Usuario usuario, UsuarioDataSource.GuardarCallback callback)
    {
        usuarioDataSource.guardar(usuario,callback);
    }
}
