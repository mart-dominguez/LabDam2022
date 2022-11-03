package com.mdgz.dam.labdam2022.persistencia.room;

import android.content.Context;

import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.persistencia.ReservaDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.bd.BaseDeDatos;
import com.mdgz.dam.labdam2022.persistencia.room.daos.CiudadDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.DepartamentoDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.HabitacionDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.HotelDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.ReservaDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.UbicacionDao;

import java.util.List;

public class ReservaRoomDataSource implements ReservaDataSource {

    private ReservaDao reservaDao;
    private DepartamentoDao departamentoDao;
    private HabitacionDao habitacionDao;
    private CiudadDao ciudadDao;
    private HotelDao hotelDao;
    private UbicacionDao ubicacionDao;

    public ReservaRoomDataSource(Context ctx){
        BaseDeDatos bd = BaseDeDatos.getInstance(ctx);
        reservaDao = bd.reservaDao();
        departamentoDao = bd.departamentoDao();
        habitacionDao = bd.habitacionDao();
        ciudadDao = bd.ciudadDao();
        hotelDao = bd.hotelDao();
        ubicacionDao = bd.ubicacionDao();
    }


    @Override
    public void guardarReserva(Reserva entidad, GuardarReservaCallback callback) {
        try{
            reservaDao.insertarReserva(entidad);
            callback.resultado(true);
        }catch (Exception e){
            callback.resultado(false);
        }
    }

    @Override
    public void recuperarReservas(RecuperarReservasCallback callback) {

        try {
            List<Reserva> reservas = reservaDao.recuperarReservas();
            for (Reserva res: reservas) {
                //Si es una reserva de un departamento
                if(res.getHabitacionID().equals(null)){
                    Departamento dep = departamentoDao.getDepartamentoPorId(res.getDepartamentoID());
                    dep.setUbicacion(ubicacionDao.getUbicacionPorId(dep.getUbicacionID()));
                    dep.getUbicacion().setCiudad(ciudadDao.getCiudadPorId(dep.getUbicacion().getCiudadID()));
                    res.setAlojamiento(dep);
                }else { //Es una reserva de habitacion
                    Habitacion hab = habitacionDao.getHabitacionPorId(res.getHabitacionID());
                    hab.setHotel(hotelDao.getHotelPorId(hab.getHotelID()));
                    hab.getHotel().setUbicacion(ubicacionDao.getUbicacionPorId(hab.getHotel().getUbicacionID()));
                    hab.getUbicacion().setCiudad(ciudadDao.getCiudadPorId(hab.getUbicacion().getCiudadID()));
                    res.setAlojamiento(hab);
                }
            }

            callback.resultado(true, reservas);

        }catch (Exception e){
            callback.resultado(false, null);
        }

    }
}
