package com.mdgz.dam.labdam2022.persistencia.interfaces;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Ubicacion;

public interface UbicacionDataSource
{
    interface GetByIDCallback
    {
        void resultado(final boolean exito, final Ubicacion ubicacion);
    }

    interface GuardarCallback
    {
        void resultado(final boolean exito);
    }

    void getByID(Integer id, UbicacionDataSource.GetByIDCallback callback);

    void guardar(final Ubicacion entidad, final UbicacionDataSource.GuardarCallback callback);
}
