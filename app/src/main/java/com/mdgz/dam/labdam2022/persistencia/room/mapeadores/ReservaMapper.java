package com.mdgz.dam.labdam2022.persistencia.room.mapeadores;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.model.Usuario;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.ReservaDepartamentoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.ReservaHabitacionEntity;
import com.mdgz.dam.labdam2022.repositorios.AlojamientoRepository;
import com.mdgz.dam.labdam2022.repositorios.UsuarioRepository;

public final class ReservaMapper
{

    private ReservaMapper(){};

    private static AlojamientoRepository alojamientoRepository;
    private static UsuarioRepository usuarioRepository;

    public static ReservaHabitacionEntity toHabitacionEntity(Reserva reserva)
    {
        return new ReservaHabitacionEntity(
                reserva.getId(),
                reserva.getAlojamiento().getId(),
                reserva.getUsuario().getId(),
                reserva.getFechaIngreso(),
                reserva.getFechaSalida()
        );
    }

    public static ReservaDepartamentoEntity toDepartamentoEntity(Reserva reserva)
    {
        return new ReservaDepartamentoEntity(
                reserva.getId(),
                reserva.getAlojamiento().getId(),
                reserva.getUsuario().getId(),
                reserva.getFechaIngreso(),
                reserva.getFechaSalida()
        );
    }

    public static Reserva toModel(ReservaHabitacionEntity entity)
    {
        if(alojamientoRepository == null) alojamientoRepository = AlojamientoRepository.getInstance();
        if(usuarioRepository == null) usuarioRepository = UsuarioRepository.getInstance();

        Alojamiento[] habitacion = new Habitacion[1];
        Usuario[] usuario = new Usuario[1];
        alojamientoRepository.getByID(entity.getHabitacionID(),(exito, alojamiento) -> {habitacion[0] = alojamiento;});
        usuarioRepository.getByID(entity.getUsuarioID(),(exito, usuario1) -> {usuario[0] = usuario1;});

        return new Reserva(
                entity.getId(),
                usuario[0],
                entity.getFechaIngreso(),
                entity.getFechaSalida(),
                habitacion[0]
        );
    }

    public static Reserva toModel(ReservaDepartamentoEntity entity)
    {
        if(alojamientoRepository == null) alojamientoRepository = AlojamientoRepository.getInstance();
        if(usuarioRepository == null) usuarioRepository = UsuarioRepository.getInstance();

        Alojamiento[] departamento = new Departamento[1];
        Usuario[] usuario = new Usuario[1];
        alojamientoRepository.getByID(entity.getDepartamentoID(),(exito, alojamiento) -> {departamento[0] = alojamiento;});
        usuarioRepository.getByID(entity.getUsuarioID(),(exito, usuario1) -> {usuario[0] = usuario1;});

        return new Reserva(
                entity.getId(),
                usuario[0],
                entity.getFechaIngreso(),
                entity.getFechaSalida(),
                departamento[0]
        );
    }

}
