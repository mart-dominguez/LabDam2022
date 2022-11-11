package com.mdgz.dam.labdam2022.persistencia.interfaces;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Hotel;

import java.util.List;
import java.util.UUID;

public interface HotelDataSource {

    interface GuardarCallback
    {
        void resultado(final boolean exito);
    }

    interface RecuperarCallback
    {
        void resultado(final boolean exito, final List<Hotel> resultados);
    }

    interface GetByIDCallback
    {
        void resultado(final boolean exito, final Hotel hotel);
    }

    void guardar(final Hotel entidad, final HotelDataSource.GuardarCallback callback);

    void getTodos(final HotelDataSource.RecuperarCallback callback);

    void getByID(Integer id, HotelDataSource.GetByIDCallback callback);

}
