package com.mdgz.dam.labdam2022.persistencia.room.mapeadores;

import com.mdgz.dam.labdam2022.model.Usuario;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.UsuarioEntity;

public final class UsuarioMapper
{
    private UsuarioMapper(){};

    public static Usuario toModel(UsuarioEntity entity)
    {
        return new Usuario(entity.getId(),
                entity.getNombre(),
                entity.getPassword());
    }

    public static UsuarioEntity toEntity(Usuario usuario)
    {
        return new UsuarioEntity(usuario.getId(),
                usuario.getNombre(),
                usuario.getPassword());
    }


}
