package com.mdgz.dam.labdam2022.persistencia.room.mapeadores;

import com.mdgz.dam.labdam2022.model.Ciudad;
import com.mdgz.dam.labdam2022.model.Ubicacion;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.UbicacionEntity;
import com.mdgz.dam.labdam2022.repositorios.CiudadRepository;

public final class UbicacionMapper
{
    private UbicacionMapper(){};

    private static CiudadRepository ciudadRepository;

    public static UbicacionEntity toEntity(Ubicacion ubicacion)
    {
        return new UbicacionEntity(
                ubicacion.getId(),
                ubicacion.getLat(),
                ubicacion.getLng(),
                ubicacion.getCalle(),
                ubicacion.getNumero(),
                ubicacion.getCiudad().getId()
        );
    }

    public static Ubicacion toModel(UbicacionEntity ubicacionEntity)
    {
        if(ciudadRepository == null) ciudadRepository = CiudadRepository.getInstance();

        Ciudad[] ciudad = new Ciudad[1];
        ciudadRepository.getByID(ubicacionEntity.getCiudadID(),(exito, ciudad1) -> {ciudad[0] = ciudad1;});

        return new Ubicacion(
                ubicacionEntity.getId(),
                ubicacionEntity.getLat(),
                ubicacionEntity.getLng(),
                ubicacionEntity.getCalle(),
                ubicacionEntity.getNumero(),
                ciudad[0]
        );
    }

}
