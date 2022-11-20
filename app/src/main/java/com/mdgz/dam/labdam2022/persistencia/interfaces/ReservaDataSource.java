package com.mdgz.dam.labdam2022.persistencia.interfaces;
import com.mdgz.dam.labdam2022.model.Reserva;

import java.util.List;
import java.util.UUID;

public interface ReservaDataSource {

    interface GuardarReservaCallback {
        void resultado(final boolean exito);
    }
    interface RecuperarReservasCallback {
        void resultado(final boolean exito, final List<Reserva> resultados);
    }
    interface GetByIDCallback {
        void resultado(final boolean exito, final Reserva resultado);
    }
    interface EliminarCallback {
        void resultado(final boolean exito);
    }
    void guardar(final Reserva entidad, final ReservaDataSource.GuardarReservaCallback callback);
    void getTodas(final ReservaDataSource.RecuperarReservasCallback callback);
    void getById(UUID id, final ReservaDataSource.GetByIDCallback callback);
    void getFromUser(UUID id, ReservaDataSource.RecuperarReservasCallback callback);
    void eliminar(Reserva Reserva, ReservaDataSource.EliminarCallback callback);
}
