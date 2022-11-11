package com.mdgz.dam.labdam2022.persistencia.interfaces;

import com.mdgz.dam.labdam2022.model.Reserva;

import java.util.List;

public interface ReservaDataSource {

    interface GuardarReservaCallback {
        void resultado(final boolean exito);
    }
    interface RecuperarReservasCallback {
        void resultado(final boolean exito, final List<Reserva> resultados);
    }
    void guardar(final Reserva entidad, final ReservaDataSource.GuardarReservaCallback callback);
    void getTodas(final ReservaDataSource.RecuperarReservasCallback callback);
    
}
