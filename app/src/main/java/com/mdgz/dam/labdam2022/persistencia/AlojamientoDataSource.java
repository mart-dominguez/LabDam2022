package com.mdgz.dam.labdam2022.persistencia;

import com.mdgz.dam.labdam2022.model.Alojamiento;

import java.util.List;

public interface AlojamientoDataSource {

    interface GuardarAlojamientoCallback {
        void resultado(final boolean exito);
    }
    interface RecuperarAlojamientosCallback {
        void resultado(final boolean exito, final List<Alojamiento> resultados);
    }
    void guardarAlojamiento(final Alojamiento entidad, final GuardarAlojamientoCallback callback);
    void recuperarAlojamientos(final RecuperarAlojamientosCallback callback);

}
