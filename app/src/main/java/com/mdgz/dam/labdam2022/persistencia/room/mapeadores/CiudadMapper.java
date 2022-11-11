package com.mdgz.dam.labdam2022.persistencia.room.mapeadores;

import com.mdgz.dam.labdam2022.model.Ciudad;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.CiudadEntity;

public final class CiudadMapper
{

    private CiudadMapper(){};

    public static CiudadEntity toEntity(Ciudad ciudad)
    {

        return new CiudadEntity(
                ciudad.getId(),
                ciudad.getNombre(),
                ciudad.getAbreviatura()
        );

    }

    public static Ciudad toModel(CiudadEntity ciudadEntity){

        return new Ciudad(
                ciudadEntity.getId(),
                ciudadEntity.getNombre(),
                ciudadEntity.getAbreviatura()
        );

    }

}
