package com.mdgz.dam.labdam2022.persistencia.room;


import android.content.Context;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Ciudad;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.model.Hotel;
import com.mdgz.dam.labdam2022.model.Ubicacion;
import com.mdgz.dam.labdam2022.persistencia.AlojamientoDataSource;
import com.mdgz.dam.labdam2022.persistencia.enumerados.TipoAlojamiento;
import com.mdgz.dam.labdam2022.persistencia.room.bd.BaseDeDatos;
import com.mdgz.dam.labdam2022.persistencia.room.daos.AlojamientoDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.CiudadDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.DepartamentoDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.HabitacionDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.HotelDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.UbicacionDao;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.AlojamientoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.CiudadEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.DepartamentoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.HabitacionEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.HotelEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.UbicacionEntity;
import com.mdgz.dam.labdam2022.persistencia.room.mapeadores.AlojamientoMapper;
import com.mdgz.dam.labdam2022.persistencia.room.mapeadores.CiudadMapper;
import com.mdgz.dam.labdam2022.persistencia.room.mapeadores.HotelMapper;
import com.mdgz.dam.labdam2022.persistencia.room.mapeadores.UbicacionMapper;

import java.util.ArrayList;
import java.util.List;

public class AlojamientoRoomDataSource implements AlojamientoDataSource {

    private DepartamentoDao departamentoDao;
    private HabitacionDao habitacionDao;
    private CiudadDao ciudadDao;
    private HotelDao hotelDao;
    private UbicacionDao ubicacionDao;
    private AlojamientoDao alojamientoDao;

    public AlojamientoRoomDataSource(Context ctx)
    {
        BaseDeDatos bd = BaseDeDatos.getInstance(ctx);
        departamentoDao = bd.departamentoDao();
        habitacionDao = bd.habitacionDao();
        ciudadDao = bd.ciudadDao();
        hotelDao = bd.hotelDao();
        ubicacionDao = bd.ubicacionDao();
        alojamientoDao = bd.alojamientoDao();
    }


    @Override
    public void guardarAlojamiento(Alojamiento entidad, GuardarAlojamientoCallback callback) {

        try{
            CiudadEntity ciudad = CiudadMapper.toEntity(entidad.getUbicacion().getCiudad());
            ciudadDao.insertarCiudad(ciudad);

            UbicacionEntity ubicacion = UbicacionMapper.toEntity(entidad.getUbicacion());
            ubicacionDao.insertarUbicacion(ubicacion);

            AlojamientoEntity alojamiento = AlojamientoMapper.toEntity(entidad);
            alojamientoDao.insertarAlojamiento(alojamiento);

            if(alojamiento.getTipo().equals(TipoAlojamiento.DEPARTAMENTO))
            {
                DepartamentoEntity departamento = AlojamientoMapper.toDepartamentoEntity((Departamento) entidad);
                departamentoDao.insertarDepartamento(departamento);
            }
            else
            {
                HotelEntity hotel = HotelMapper.toEntity(((Habitacion)entidad).getHotel());
                hotelDao.insertarHotel(hotel);

                HabitacionEntity habitacion = AlojamientoMapper.toHabitacionEntity((Habitacion) entidad);
                habitacionDao.insertarHabitacion(habitacion);
            }

            callback.resultado(true);
        }catch (Exception e){
            callback.resultado(false);
        }

    }

    @Override
    public void recuperarAlojamientos(RecuperarAlojamientosCallback callback) {

        try{

            // -------------------- Habitaciones -------------------------
            List<Habitacion> habitaciones = new ArrayList<>();

            List<HabitacionEntity> habitacionEntities = new ArrayList<>();
            habitacionEntities.addAll(habitacionDao.recuperarHabitaciones());

            for (HabitacionEntity hab:habitacionEntities) {
                habitaciones.add(armarHabitacion(hab));
            }
            // ------------------------------------------------------------

            // ------------------- Departamentos -------------------------
            List<Departamento> departamentos = new ArrayList<>();

            List<DepartamentoEntity> departamentoEntities = new ArrayList<>();
            departamentoEntities.addAll(departamentoDao.recuperarDepartamentos());

            for (DepartamentoEntity dep: departamentoEntities) {
                departamentos.add(armarDepartamento(dep));
            }
            // ------------------------------------------------------------

            List<Alojamiento> alojamientos = new ArrayList<>();
            alojamientos.addAll(habitaciones);
            alojamientos.addAll(departamentos);

            callback.resultado(true, alojamientos);

        }catch (Exception e){
            callback.resultado(false, null);
        }

    }

    protected Habitacion armarHabitacion(HabitacionEntity hab){

        HotelEntity hotelEntity = hotelDao.getHotelPorId(hab.getHotelID());
        UbicacionEntity ubicacionEntity = ubicacionDao.getUbicacionPorId(hotelEntity.getUbicacionID());
        CiudadEntity ciudadEntity = ciudadDao.getCiudadPorId(ubicacionEntity.getCiudadID());

        Habitacion habitacion = (Habitacion) AlojamientoMapper.habitacionEntitytoAlojamiento(alojamientoDao.getAlojamientoPorId(hab.getIdHabitacion()),hab);

        habitacion.setHotel(HotelMapper.toHotel(hotelEntity));
        habitacion.getHotel().setUbicacion(UbicacionMapper.toUbicacion(ubicacionEntity));
        habitacion.getUbicacion().setCiudad(CiudadMapper.toCiudad(ciudadEntity));

        return habitacion;
    }

    protected Departamento armarDepartamento(DepartamentoEntity dep){

        UbicacionEntity ubicacionEntity = ubicacionDao.getUbicacionPorId(dep.getUbicacionID());
        CiudadEntity ciudadEntity = ciudadDao.getCiudadPorId(ubicacionEntity.getCiudadID());

        Departamento departamento = (Departamento) AlojamientoMapper.departamentoEntityToAlojamiento(alojamientoDao.getAlojamientoPorId(dep.getIdDepartamento()),dep);

        departamento.setUbicacion(UbicacionMapper.toUbicacion(ubicacionEntity));
        departamento.getUbicacion().setCiudad(CiudadMapper.toCiudad(ciudadEntity));

        return departamento;
    }

}
