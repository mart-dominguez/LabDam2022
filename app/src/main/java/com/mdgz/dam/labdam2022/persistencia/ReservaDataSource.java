package com.mdgz.dam.labdam2022.persistencia;

import com.mdgz.dam.labdam2022.model.Reserva;

import java.util.List;

public interface ReservaDataSource {

    interface GuardarReservaCallback {
        void resultado(final boolean exito);
    }
    interface RecuperarReservasCallback {
        void resultado(final boolean exito, final List<Reserva> resultados);
    }
    void guardarReserva(final Reserva entidad, final ReservaDataSource.GuardarReservaCallback callback);
    void recuperarReservas(final ReservaDataSource.RecuperarReservasCallback callback);
    
}
