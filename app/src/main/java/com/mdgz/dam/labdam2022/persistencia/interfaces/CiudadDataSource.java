package com.mdgz.dam.labdam2022.persistencia.interfaces;

import com.mdgz.dam.labdam2022.model.Ciudad;
import com.mdgz.dam.labdam2022.model.Ubicacion;

public interface CiudadDataSource
{
    interface GetByIDCallback
    {
        void resultado(final boolean exito, final Ciudad ciudad);
    }

    interface GuardarCallback
    {
        void resultado(final boolean exito);
    }

    void getByID(Integer id, CiudadDataSource.GetByIDCallback callback);

    void guardar(final Ciudad ciudad, final CiudadDataSource.GuardarCallback callback);

}
