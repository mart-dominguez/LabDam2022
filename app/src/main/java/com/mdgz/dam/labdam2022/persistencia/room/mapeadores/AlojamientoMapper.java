package com.mdgz.dam.labdam2022.persistencia.room.mapeadores;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.AlojamientoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.DepartamentoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.HabitacionEntity;
import com.mdgz.dam.labdam2022.persistencia.enumerados.TipoAlojamiento;

public class AlojamientoMapper {

    public static AlojamientoEntity toEntity(Alojamiento alojamiento){

        TipoAlojamiento tipo = alojamiento.getClass() == Departamento.class ? TipoAlojamiento.DEPARTAMENTO : TipoAlojamiento.HABITACION;

        return new AlojamientoEntity(
                alojamiento.getId(),
                alojamiento.getTitulo(),
                alojamiento.getDescripcion(),
                alojamiento.getCapacidad(),
                alojamiento.getPrecioBase(),
                tipo);
    }

    public static DepartamentoEntity toDepartamentoEntity(Departamento departamento){
        return new DepartamentoEntity(
                departamento.getId(),
                departamento.getTieneWifi(),
                departamento.getCostoLimpieza(),
                departamento.getCantidadHabitaciones(),
                departamento.getUbicacion().getId()
        );
    }

    public static HabitacionEntity toHabitacionEntity(Habitacion habitacion){
        return new HabitacionEntity(
                habitacion.getId(),
                habitacion.getCamasIndividuales(),
                habitacion.getCamasMatrimoniales(),
                habitacion.getTieneEstacionamiento(),
                habitacion.getHotel().getId()
        );
    }

    public static Alojamiento habitacionEntitytoAlojamiento(AlojamientoEntity alojEntity, HabitacionEntity habEntity){

        return new Habitacion(
                alojEntity.getId(),
                alojEntity.getTitulo(),
                alojEntity.getDescripcion(),
                alojEntity.getCapacidad(),
                alojEntity.getPrecioBase(),
                habEntity.getCamasIndividuales(),
                habEntity.getCamasMatrimoniales(),
                habEntity.getTieneEstacionamiento(),
                null // se lo setea en la interfaz, y se lo obtiene del mapper del hotel
        );

    }

    public static Alojamiento departamentoEntityToAlojamiento(AlojamientoEntity alojEntity, DepartamentoEntity depEntity){

        return new Departamento(
                alojEntity.getId(),
                alojEntity.getTitulo(),
                alojEntity.getDescripcion(),
                alojEntity.getCapacidad(),
                alojEntity.getPrecioBase(),
                depEntity.getTieneWifi(),
                depEntity.getCostoLimpieza(),
                depEntity.getCantidadHabitaciones(),
                null // se lo setea en la interfaz, y se lo obtiene del mapper de la ubicacion
        );

    }

}
