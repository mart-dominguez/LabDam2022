package com.mdgz.dam.labdam2022.persistencia.interfaces;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;

import java.util.List;
import java.util.UUID;

public interface AlojamientoDataSource {

    interface GuardarCallback
    {
        void resultado(final boolean exito);
    }

    interface RecuperarCallback
    {
        void resultado(final boolean exito, final List<Alojamiento> resultados);
    }

    interface GetByIDCallback
    {
        void resultado(final boolean exito, final Alojamiento alojamiento);
    }

    void guardar(final Departamento entidad, final GuardarCallback callback);

    void guardar(final Habitacion entidad, final GuardarCallback callback);

    void getTodosDepartamentos(final RecuperarCallback callback);

    void getTodasHabitaciones(final RecuperarCallback callback);

    void getByID(UUID id, GetByIDCallback callback);

}
