package com.mdgz.dam.labdam2022.persistencia.room;


import android.content.Context;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.persistencia.AlojamientoDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.bd.BaseDeDatos;
import com.mdgz.dam.labdam2022.persistencia.room.daos.CiudadDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.DepartamentoDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.HabitacionDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.HotelDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.UbicacionDao;

import java.util.ArrayList;
import java.util.List;

public class AlojamientoRoomDataSource implements AlojamientoDataSource {

    private DepartamentoDao departamentoDao;
    private HabitacionDao habitacionDao;
    private CiudadDao ciudadDao;
    private HotelDao hotelDao;
    private UbicacionDao ubicacionDao;

    public AlojamientoRoomDataSource(Context ctx){
        BaseDeDatos bd = BaseDeDatos.getInstance(ctx);
        departamentoDao = bd.departamentoDao();
        habitacionDao = bd.habitacionDao();
        ciudadDao = bd.ciudadDao();
        hotelDao = bd.hotelDao();
        ubicacionDao = bd.ubicacionDao();
    }


    @Override
    public void guardarAlojamiento(Alojamiento entidad, GuardarAlojamientoCallback callback) {

        try{
            if(entidad.getClass() == Departamento.class) departamentoDao.insertarDepartamento((Departamento) entidad);
            else habitacionDao.insertarHabitacion((Habitacion) entidad);
            callback.resultado(true);
        }catch (Exception e){
            callback.resultado(false);
        }

    }

    @Override
    public void recuperarAlojamientos(RecuperarAlojamientosCallback callback) {

        try{
            // -- Medio rustico esto, hay que mejorarlo, pero para probar sirve
            List<Alojamiento> alojamientos = new ArrayList<>();

            List<Habitacion> habitaciones = new ArrayList<>();
            habitaciones.addAll(habitacionDao.recuperarHabitaciones());
            for (Habitacion hab:habitaciones) {
                hab.setHotel(hotelDao.getHotelPorId(hab.getHotelID()));
                hab.getHotel().setUbicacion(ubicacionDao.getUbicacionPorId(hab.getHotel().getUbicacionID()));
                hab.getUbicacion().setCiudad(ciudadDao.getCiudadPorId(hab.getUbicacion().getCiudadID()));
            }

            List<Departamento> departamentos = new ArrayList<>();
            departamentos.addAll(departamentoDao.recuperarDepartamentos());
            for (Departamento dep: departamentos) {
                dep.setUbicacion(ubicacionDao.getUbicacionPorId(dep.getUbicacionID()));
                dep.getUbicacion().setCiudad(ciudadDao.getCiudadPorId(dep.getUbicacion().getCiudadID()));
            }

            alojamientos.addAll(habitaciones);
            alojamientos.addAll(departamentos);
            // -- Fin de lo rustico

            callback.resultado(true, alojamientos);

        }catch (Exception e){
            callback.resultado(false, null);
        }

    }
}
