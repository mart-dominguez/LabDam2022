package com.mdgz.dam.labdam2022.persistencia.room.impl;


import android.content.Context;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.persistencia.interfaces.AlojamientoDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.bd.BaseDeDatos;
import com.mdgz.dam.labdam2022.persistencia.room.daos.DepartamentoDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.HabitacionDao;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.DepartamentoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.HabitacionEntity;
import com.mdgz.dam.labdam2022.persistencia.room.mapeadores.AlojamientoMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AlojamientoRoomDataSource implements AlojamientoDataSource {

    private DepartamentoDao departamentoDao;
    private HabitacionDao habitacionDao;

    private UbicacionRoomDataSource ubicacionRoomDataSource;
    private HotelRoomDataSource hotelRoomDataSource;

    public AlojamientoRoomDataSource(Context ctx)
    {
        BaseDeDatos bd = BaseDeDatos.getInstance(ctx);
        departamentoDao = bd.departamentoDao();
        habitacionDao = bd.habitacionDao();
        ubicacionRoomDataSource = new UbicacionRoomDataSource(ctx);
        hotelRoomDataSource = new HotelRoomDataSource(ctx);

    }

    @Override
    public void getByID(UUID id, GetByIDCallback callback)
    {
        try
        {
            DepartamentoEntity departamento = departamentoDao.getByID(id);
            HabitacionEntity habitacion;

            if (departamento == null)
            {
                habitacion = habitacionDao.getByID(id);
                callback.resultado(true,AlojamientoMapper.toModel(habitacion));
            }
            else callback.resultado(true,AlojamientoMapper.toModel(departamento));

        } catch(Exception e)
        {
            callback.resultado(false,null);
        }

    }



    @Override
    public void guardar(Habitacion entidad, GuardarCallback callback)
    {
        try
        {
            hotelRoomDataSource.guardar(entidad.getHotel(),exito -> {});
            HabitacionEntity entity = AlojamientoMapper.toEntity(entidad);
            habitacionDao.insertar(entity);

            callback.resultado(true);
        }catch (Exception e){
            callback.resultado(false);
        }

    }

    @Override
    public void guardar(Departamento entidad, GuardarCallback callback)
    {
        try
        {
            ubicacionRoomDataSource.guardar(entidad.getUbicacion(),exito -> {});
            DepartamentoEntity entity = AlojamientoMapper.toEntity(entidad);
            departamentoDao.insertar(entity);

            callback.resultado(true);
        }catch (Exception e){
            callback.resultado(false);
        }

    }

    @Override
    public void getTodosDepartamentos(RecuperarCallback callback) {

        try
        {
            List<Alojamiento> alojamientos = new ArrayList<>();

            List<DepartamentoEntity> entities = departamentoDao.getTodos();

            for(DepartamentoEntity entity : entities)
            {
               Departamento departamento = AlojamientoMapper.toModel(entity);
               alojamientos.add(departamento);
            }

            callback.resultado(true, alojamientos);

        }catch (Exception e)
        {
            callback.resultado(false, null);
        }

    }

    @Override
    public void getTodasHabitaciones(RecuperarCallback callback) {

        try
        {
            List<Alojamiento> alojamientos = new ArrayList<>();

            List<HabitacionEntity> entities = habitacionDao.getTodos();

            for(HabitacionEntity entity : entities)
            {
                Habitacion habitacion = AlojamientoMapper.toModel(entity);
                alojamientos.add(habitacion);
            }

            callback.resultado(true, alojamientos);

        }catch (Exception e)
        {
            callback.resultado(false, null);
        }

    }

}
