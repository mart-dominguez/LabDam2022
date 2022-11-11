package com.mdgz.dam.labdam2022.persistencia.room.mapeadores;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.model.Hotel;
import com.mdgz.dam.labdam2022.model.Ubicacion;
import com.mdgz.dam.labdam2022.persistencia.interfaces.HotelDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.AlojamientoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.DepartamentoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.HabitacionEntity;
import com.mdgz.dam.labdam2022.persistencia.enumerados.TipoAlojamiento;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.HotelEntity;
import com.mdgz.dam.labdam2022.repositorios.HotelRepository;
import com.mdgz.dam.labdam2022.repositorios.UbicacionRepository;

public final class AlojamientoMapper
{

    private AlojamientoMapper(){};
    private static HotelRepository hotelRepository;
    private static UbicacionRepository ubicacionRepository;


    //Obtiene los atributos del Alojamiento
    public static AlojamientoEntity toEmbeddedEntity(Alojamiento alojamiento)
    {
        //TipoAlojamiento tipo = alojamiento.getClass() == Departamento.class ? TipoAlojamiento.DEPARTAMENTO : TipoAlojamiento.HABITACION;
        return new AlojamientoEntity(
                alojamiento.getTitulo(),
                alojamiento.getDescripcion(),
                alojamiento.getCapacidad(),
                alojamiento.getPrecioBase()
                );
    }

    public static DepartamentoEntity toEntity(Departamento departamento)
    {
        return new DepartamentoEntity
                (departamento.getId(),
                toEmbeddedEntity(departamento),
                departamento.getTieneWifi(),
                departamento.getCostoLimpieza(),
                departamento.getCantidadHabitaciones(),
                departamento.getUbicacion().getId()
        );
    }

    public static HabitacionEntity toEntity(Habitacion habitacion)
    {
        return new HabitacionEntity(
                habitacion.getId(),
                toEmbeddedEntity(habitacion),
                habitacion.getCamasIndividuales(),
                habitacion.getCamasMatrimoniales(),
                habitacion.getTieneEstacionamiento(),
                habitacion.getHotel().getId()
        );
    }

    public static Habitacion toModel(HabitacionEntity entity)
    {
        if(hotelRepository == null) hotelRepository = HotelRepository.getInstance();
        final Hotel[] hotelModel = new Hotel[1];
        hotelRepository.getByID(entity.getHotelID(), (exito, hotel) -> {hotelModel[0] = hotel;});

        return new Habitacion(
                entity.getId(),
                entity.getAlojamiento().getTitulo(),
                entity.getAlojamiento().getDescripcion(),
                entity.getAlojamiento().getCapacidad(),
                entity.getAlojamiento().getPrecioBase(),
                entity.getCamasIndividuales(),
                entity.getCamasMatrimoniales(),
                entity.getTieneEstacionamiento(),
                hotelModel[0]);

    }

    public static Departamento toModel(DepartamentoEntity entity)
    {
        if(ubicacionRepository == null) ubicacionRepository = UbicacionRepository.getInstance();
        final Ubicacion[] ubicacionModel = new Ubicacion[1];
        ubicacionRepository.getByID(entity.getUbicacionID(), (exito, ubicacion) -> {ubicacionModel[0] = ubicacion;});

        return new Departamento(
                entity.getId(),
                entity.getAlojamiento().getTitulo(),
                entity.getAlojamiento().getDescripcion(),
                entity.getAlojamiento().getCapacidad(),
                entity.getAlojamiento().getPrecioBase(),
                entity.getTieneWifi(),
                entity.getCostoLimpieza(),
                entity.getCantidadHabitaciones(),
                ubicacionModel[0]
        );

    }

}
