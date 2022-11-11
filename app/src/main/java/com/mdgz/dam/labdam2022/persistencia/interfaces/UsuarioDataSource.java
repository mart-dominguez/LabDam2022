package com.mdgz.dam.labdam2022.persistencia.interfaces;

import com.mdgz.dam.labdam2022.model.Hotel;
import com.mdgz.dam.labdam2022.model.Usuario;

import java.util.List;
import java.util.UUID;

public interface UsuarioDataSource
{
    interface GuardarCallback
    {
        void resultado(final boolean exito);
    }

    interface RecuperarCallback
    {
        void resultado(final boolean exito, final List<Usuario> resultados);
    }

    interface GetByIDCallback
    {
        void resultado(final boolean exito, final Usuario usuario);
    }

    void guardar(final Usuario entidad, final UsuarioDataSource.GuardarCallback callback);

    void getTodos(final UsuarioDataSource.RecuperarCallback callback);

    void getByID(UUID id, UsuarioDataSource.GetByIDCallback callback);
}
