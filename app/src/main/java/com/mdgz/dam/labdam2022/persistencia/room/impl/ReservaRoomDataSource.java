package com.mdgz.dam.labdam2022.persistencia.room.impl;

import android.content.Context;

import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.persistencia.interfaces.ReservaDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.bd.BaseDeDatos;
import com.mdgz.dam.labdam2022.persistencia.room.daos.ReservaDao;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.ReservaDepartamentoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.ReservaHabitacionEntity;
import com.mdgz.dam.labdam2022.persistencia.room.mapeadores.ReservaMapper;

import java.util.ArrayList;
import java.util.List;

public class ReservaRoomDataSource implements ReservaDataSource {

    private ReservaDao reservaDao;
    private AlojamientoRoomDataSource alojamientoRoomDataSource;
    private UsuarioRoomDataSource usuarioRoomDataSource;

    public ReservaRoomDataSource(Context ctx)
    {
        BaseDeDatos bd = BaseDeDatos.getInstance(ctx);
        reservaDao = bd.reservaDao();
        alojamientoRoomDataSource = new AlojamientoRoomDataSource(ctx);
        usuarioRoomDataSource = new UsuarioRoomDataSource(ctx);
    }


    @Override
    public void guardar(Reserva entidad, GuardarReservaCallback callback)
    {
        try
        {
            usuarioRoomDataSource.guardar(entidad.getUsuario(),exito -> {});
            if(entidad.getAlojamiento().getClass() == Departamento.class)
            {
                alojamientoRoomDataSource.guardar((Departamento) entidad.getAlojamiento(), exito -> {
                });
                reservaDao.insertar(ReservaMapper.toDepartamentoEntity(entidad));
            }
            else
            {
                alojamientoRoomDataSource.guardar((Habitacion) entidad.getAlojamiento(), exito -> {
                });
                reservaDao.insertar(ReservaMapper.toHabitacionEntity(entidad));
            }
            callback.resultado(true);
        }catch (Exception e){
            callback.resultado(false);
        }
    }

    @Override
    public void getTodas(RecuperarReservasCallback callback)
    {

        try {
            List<ReservaHabitacionEntity> entities1 = reservaDao.getHabitaciones();
            List<ReservaDepartamentoEntity> entities2 = reservaDao.getDepartamentos();
            List<Reserva> reservas = new ArrayList<>();

            for (ReservaHabitacionEntity entity : entities1)
            {
                Reserva reserva = ReservaMapper.toModel(entity);
                reservas.add(reserva);
            }

            for (ReservaDepartamentoEntity entity : entities2)
            {
                Reserva reserva = ReservaMapper.toModel(entity);
                reservas.add(reserva);
            }
            callback.resultado(true,reservas);
        } catch (Exception e)
        {
            callback.resultado(false,null);
        }

    }
}
