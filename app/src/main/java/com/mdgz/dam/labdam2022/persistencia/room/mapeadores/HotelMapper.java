package com.mdgz.dam.labdam2022.persistencia.room.mapeadores;

import com.mdgz.dam.labdam2022.model.Hotel;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.HotelEntity;

public class HotelMapper {

    public static HotelEntity toEntity(Hotel hotel){
        return new HotelEntity(
                hotel.getId(),
                hotel.getNombre(),
                hotel.getCategoria(),
                hotel.getUbicacion().getId()
        );
    }

    public static Hotel toHotel(HotelEntity hotelEntity){
        return new Hotel(
                hotelEntity.getId(),
                hotelEntity.getNombre(),
                hotelEntity.getCategoria(),
                null // se lo setea en la interfaz, y se lo obtiene del mapper de la ubicacion
        );
    }

}
