package com.mdgz.dam.labdam2022.database;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Reserva;

import java.util.List;

public interface ReservaDataSource {
    interface GuardarReservaCallback {
        void resultado(final boolean exito);
    }
    interface RecuperarReservaCallback {
        void resultado(final boolean exito, final List<Reserva> resultados);
    }
    void guardarReserva(final Reserva entidad, final GuardarReservaCallback callback);
    void recuperarReserva(final RecuperarReservaCallback callback);
}