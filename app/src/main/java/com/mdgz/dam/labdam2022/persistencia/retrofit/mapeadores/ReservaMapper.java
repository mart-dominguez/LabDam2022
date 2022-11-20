package com.mdgz.dam.labdam2022.persistencia.retrofit.mapeadores;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.model.Usuario;
import com.mdgz.dam.labdam2022.persistencia.retrofit.entidades.ReservaEntity;
import com.mdgz.dam.labdam2022.repositorios.AlojamientoRepository;
import com.mdgz.dam.labdam2022.repositorios.UsuarioRepository;

import java.util.UUID;

public class ReservaMapper {

    private static AlojamientoRepository alojamientoRepository;
    private static UsuarioRepository usuarioRepository;

    public ReservaMapper(){};

    public static ReservaEntity toEntity(Reserva reserva){
        return new ReservaEntity(
                reserva.getAlojamiento().getId(),
                reserva.getUsuario().getId(),
                reserva.getFechaIngreso(),
                reserva.getFechaSalida()
        );
    }

    public static Reserva toModel(ReservaEntity reservaEntity){

        if(alojamientoRepository == null) alojamientoRepository = AlojamientoRepository.getInstance();
        if(usuarioRepository == null) usuarioRepository = UsuarioRepository.getInstance();

        Alojamiento[] aloj = new Alojamiento[1];
        Usuario[] usuario = new Usuario[1];
        alojamientoRepository.getByID(reservaEntity.getAlojamientoId(),(exito, alojamiento) -> {
            if(alojamiento.getClass()== Departamento.class) aloj[0] = (Departamento) alojamiento;
            else aloj[0] = (Habitacion) alojamiento;
        });
        usuarioRepository.getByID(reservaEntity.getUsuarioId(),(exito, usuario1) -> {usuario[0] = usuario1;});

        return new Reserva(
                UUID.randomUUID(),
                usuario[0],
                reservaEntity.getFechaIngreso(),
                reservaEntity.getFechaSalida(),
                aloj[0]
        );
    }
}
