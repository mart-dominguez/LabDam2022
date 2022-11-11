package com.mdgz.dam.labdam2022.persistencia.room.mapeadores;

import com.mdgz.dam.labdam2022.model.Hotel;
import com.mdgz.dam.labdam2022.model.Ubicacion;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.HotelEntity;
import com.mdgz.dam.labdam2022.persistencia.room.impl.UbicacionRoomDataSource;
import com.mdgz.dam.labdam2022.repositorios.UbicacionRepository;

public final class HotelMapper
{

    private HotelMapper(){};
    private static UbicacionRepository ubicacionRepository;

    public static HotelEntity toEntity(Hotel hotel)
    {
        return new HotelEntity(
                hotel.getId(),
                hotel.getNombre(),
                hotel.getCategoria(),
                hotel.getUbicacion().getId()
        );
    }

    public static Hotel toModel(HotelEntity hotelEntity)
    {
        if(ubicacionRepository == null) ubicacionRepository = UbicacionRepository.getInstance();
        Ubicacion[] ubicacion = new Ubicacion[1];

        ubicacionRepository.getByID(hotelEntity.getUbicacionID(),(exito, ubicacion1) -> {ubicacion[0] = ubicacion1;});
        return new Hotel(
                hotelEntity.getId(),
                hotelEntity.getNombre(),
                hotelEntity.getCategoria(),
                ubicacion[0]
        );
    }

}
