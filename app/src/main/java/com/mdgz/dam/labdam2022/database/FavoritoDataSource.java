package com.mdgz.dam.labdam2022.database;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Favorito;

import java.util.List;

public interface FavoritoDataSource {
    interface GuardarFavoritoCallback {
        void resultado(final boolean exito);
    }
    interface RecuperarFavoritoCallback {
        void resultado(final boolean exito, final List<Favorito> resultados);
    }
    void guardarFavorito(final Favorito entidad, final GuardarFavoritoCallback callback);
    void recuperarFavorito(final RecuperarFavoritoCallback callback);
}