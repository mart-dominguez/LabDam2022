package com.mdgz.dam.labdam2022.utilities;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Ciudad;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.model.Hotel;
import com.mdgz.dam.labdam2022.model.Ubicacion;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListaDeAlojamientos {

    List<Ciudad> ciudades;
    List<Ubicacion> ubicaciones;
    List<Hotel> hoteles;
    List<Departamento> departamentos;
    List<Habitacion> habitaciones;
    List<Alojamiento> lista;

    //Crear datos iniciales
    public ListaDeAlojamientos(){

        crearCiudades();
        crearUbicaciones();
        crearAlojamientos();

    }

    public List<Alojamiento> getLista() {
        return lista;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public List<Ciudad> getCiudades() {
        return ciudades;
    }

    public List<Ubicacion> getUbicaciones() {
        return ubicaciones;
    }

    public List<Hotel> getHoteles() {
        return hoteles;
    }

    private void crearCiudades(){
        ciudades = new ArrayList<>();
        ciudades.add(new Ciudad(1, "Santa Fe", "SF"));
        ciudades.add(new Ciudad(2, "Rosario", "ROS"));
        ciudades.add(new Ciudad(3, "Rafaela", "RAF"));

    }
    private void crearUbicaciones(){
        ubicaciones = new ArrayList<>();
        ubicaciones.add(new Ubicacion(1,-42.6,-38.3,"San Martin","1989", ciudades.get(0)));
        ubicaciones.add(new Ubicacion(2,-41.2,-37.5,"Francia","1561", ciudades.get(0)));
        ubicaciones.add(new Ubicacion(3,-42.25,-38.2,"Lopez y Planes","2007", ciudades.get(1)));
        ubicaciones.add(new Ubicacion(4,-42.31,-38.7,"Urquiza","4231", ciudades.get(1)));
    }
    private void crearAlojamientos(){

        hoteles = new ArrayList<>();
        hoteles.add(new Hotel(1, "Hotel Puerto Amarras", 5, ubicaciones.get(3)));

        habitaciones = List.of(
                new Habitacion(UUID.fromString("e7443858-6b68-4784-9ab6-b7c38512c8ce"), "Luminosa habitacion", "Habitacion 5D, con vista a toda la ciudad",
                        3,4200.0,1,2,true, hoteles.get(0)),

                new Habitacion(UUID.fromString("a67fea88-0ae5-4593-8355-84afe690f9d9"), "Ideal para descansar", "Habitacion 20H, con con jacuzzi!",
                        5,8700.0,3,1,true,hoteles.get(0)),

                new Habitacion(UUID.fromString("6f7bd132-1ff4-469f-9c1e-30bdf81650b8"), "La mejor vista", "Habitacion con vista al Nahuel Huapi",
                        3,5100.0,1,2,true,hoteles.get(0))
        );

        departamentos = List.of(
                new Departamento(UUID.fromString("7d91903d-ed18-4166-a0b8-e222c79adabf"), "Frente al mar", "Departamento a 10 m. del mar",
                        4, 5400.0, true, 1000.0, 3,ubicaciones.get(0)),

                new Departamento(UUID.fromString("8c331fe8-b37a-44b5-89c2-f7b7dd900f48"), "En el centro", "Departamento a 5 cuadras del centro",
                        5, 6500.0, true, 1000.0, 4,ubicaciones.get(1)),

                new Departamento(UUID.fromString("13048bcc-d1ab-4dc5-a106-935bf1a0099d"), "Gran vista de la ciudad", "Departamento con una hermosa vista a la ciudad",
                        2, 3050.0, true, 1000.0, 1,ubicaciones.get(2))
        );

        lista = Stream.concat(departamentos.stream(), habitaciones.stream()).collect(Collectors.toList());

    }
}
