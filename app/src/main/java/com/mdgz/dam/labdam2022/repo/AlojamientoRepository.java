package com.mdgz.dam.labdam2022.repo;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.model.Hotel;
import com.mdgz.dam.labdam2022.model.Ubicacion;

import java.util.List;
import java.util.UUID;

public class AlojamientoRepository {


    public static final List<Alojamiento> _ALOJAMIENTOS = List.of(
            new Departamento("Depto 1", "luminoso y amplio", 6, 300.0,true, 1500.0, 3,UbicacionRepository._UBICACIONES.get(0)),
            new Habitacion("Habitacion 2", "Espectacular suite",4, 1200.0, 2,1,false,new Hotel(1,"Hotel 1",3,UbicacionRepository._UBICACIONES.get(1)) )
    );

    public List<Alojamiento> listaCiudades(){
        return  _ALOJAMIENTOS;
    }
}
