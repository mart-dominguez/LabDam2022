package com.mdgz.dam.labdam2022.persistencia.room.mapeadores;

import com.mdgz.dam.labdam2022.model.Ciudad;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.CiudadEntity;

public class CiudadMapper {

    public static CiudadEntity toEntity(Ciudad ciudad){

        return new CiudadEntity(
                ciudad.getId(),
                ciudad.getNombre(),
                ciudad.getAbreviatura()
        );

    }

    public static Ciudad toCiudad(CiudadEntity ciudadEntity){

        return new Ciudad(
                ciudadEntity.getId(),
                ciudadEntity.getNombre(),
                ciudadEntity.getAbreviatura()
        );

    }

}
