package com.mdgz.dam.labdam2022.repo;

import com.mdgz.dam.labdam2022.model.Ciudad;
import com.mdgz.dam.labdam2022.model.Ubicacion;

import java.util.List;
import java.util.UUID;

public class UbicacionRepository {

    public static final List<Ubicacion> _UBICACIONES = List.of(

            new Ubicacion(UUID.fromString("5fc03087-d265-11e7-b8c6-83e29cd24f4c"), -42.6,-38.3,"San Martin","1989",CiudadRepository._CIUDADES.get(0)),
            new Ubicacion(UUID.fromString("58e0a7d7-eebc-11d8-9669-0800200c9a66"), -42.25,-38.2,"Lopez y Planes","2007",CiudadRepository._CIUDADES.get(1))
        );

    public List<Ubicacion> listaCiudades(){
        return  _UBICACIONES;
    }
}
