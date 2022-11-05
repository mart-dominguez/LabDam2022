package com.mdgz.dam.labdam2022.persistencia.room.mapeadores;

import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.ReservaEntity;

public class ReservaMapper {

    public static ReservaEntity toEntity(Reserva reserva){
        return new ReservaEntity(
                reserva.getId(),
                reserva.getAlojamiento().getId(),
                reserva.getUsuarioID(),
                reserva.getFechaIngreso(),
                reserva.getFechaSalida()
        );
    }

    public static Reserva toReserva(ReservaEntity reservaEntity){
        return new Reserva(
                reservaEntity.getId(),
                reservaEntity.getUsuarioID(),
                reservaEntity.getFechaIngreso(),
                reservaEntity.getFechaSalida(),
                null // se lo setea en la interfaz, y se lo obtiene del mapper del alojamiento
        );
    }

}
