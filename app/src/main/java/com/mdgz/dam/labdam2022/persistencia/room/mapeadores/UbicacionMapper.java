package com.mdgz.dam.labdam2022.persistencia.room.mapeadores;

import com.mdgz.dam.labdam2022.model.Ubicacion;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.UbicacionEntity;

public class UbicacionMapper {

    public static UbicacionEntity toEntity(Ubicacion ubicacion){
        return new UbicacionEntity(
                ubicacion.getId(),
                ubicacion.getLat(),
                ubicacion.getLng(),
                ubicacion.getCalle(),
                ubicacion.getNumero(),
                ubicacion.getCiudad().getId()
        );
    }

    public static Ubicacion toUbicacion(UbicacionEntity ubicacionEntity){
        return new Ubicacion(
                ubicacionEntity.getId(),
                ubicacionEntity.getLat(),
                ubicacionEntity.getLng(),
                ubicacionEntity.getCalle(),
                ubicacionEntity.getNumero(),
                null // se lo setea en la interfaz, y se lo obtiene del mapper de la ciudad
        );
    }

}
