package com.mdgz.dam.labdam2022.persistencia.interfaces;

import com.mdgz.dam.labdam2022.model.Favorito;

import java.util.List;
import java.util.UUID;

public interface FavoritoDataSource {

    interface GuardarCallback {
        void resultado(final boolean exito);
    }
    interface RecuperarCallback {
        void resultado(final boolean exito, final List<Favorito> resultados);
    }
    interface GetByIDCallback {
        void resultado(final boolean exito, final Favorito resultado);
    }
    interface EliminarCallback {
        void resultado(final boolean exito);
    }

    void guardar(final Favorito entidad, final GuardarCallback callback);
    void getTodos(final RecuperarCallback callback);
    void getById(UUID id, final FavoritoDataSource.GetByIDCallback callback);
    void getFromUser(UUID id, RecuperarCallback callback);
    void eliminar(Favorito favorito, FavoritoDataSource.EliminarCallback callback);


}
