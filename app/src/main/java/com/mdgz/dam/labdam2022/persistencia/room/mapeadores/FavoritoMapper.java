package com.mdgz.dam.labdam2022.persistencia.room.mapeadores;

import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.FavoritoEntity;

public class FavoritoMapper {

    public static FavoritoEntity toEntity(Favorito favorito){
        return new FavoritoEntity(
                favorito.getId(),
                favorito.getAlojamiento().getId(),
                favorito.getUsuarioID()
        );
    }

    public static Favorito toFavorito(FavoritoEntity favoritoEntity){
        return new Favorito(
                favoritoEntity.getId(),
                favoritoEntity.getUsuarioID(),
                null // se lo setea en la interfaz, y se lo obtiene del mapper del alojamiento
        );
    }

}
