package com.mdgz.dam.labdam2022.persistencia.room;

import android.content.Context;

import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.persistencia.ReservaDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.bd.BaseDeDatos;
import com.mdgz.dam.labdam2022.persistencia.room.daos.AlojamientoDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.DepartamentoDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.HabitacionDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.ReservaDao;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.AlojamientoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.ReservaEntity;
import com.mdgz.dam.labdam2022.persistencia.enumerados.TipoAlojamiento;
import com.mdgz.dam.labdam2022.persistencia.room.mapeadores.ReservaMapper;

import java.util.ArrayList;
import java.util.List;

public class ReservaRoomDataSource implements ReservaDataSource {

    private ReservaDao reservaDao;
    private DepartamentoDao departamentoDao;
    private HabitacionDao habitacionDao;
    private AlojamientoDao alojamientoDao;
    private Context context;

    public ReservaRoomDataSource(Context ctx){
        BaseDeDatos bd = BaseDeDatos.getInstance(ctx);
        reservaDao = bd.reservaDao();
        departamentoDao = bd.departamentoDao();
        habitacionDao = bd.habitacionDao();
        alojamientoDao = bd.alojamientoDao();
        context = ctx;
    }


    @Override
    public void guardarReserva(Reserva entidad, GuardarReservaCallback callback) {
        try{
            reservaDao.insertarReserva(ReservaMapper.toEntity(entidad));
            callback.resultado(true);
        }catch (Exception e){
            callback.resultado(false);
        }
    }

    @Override
    public void recuperarReservas(RecuperarReservasCallback callback) {

        AlojamientoRoomDataSource alojamientoRoomDataSource = new AlojamientoRoomDataSource(context);

        try {
            AlojamientoEntity alojamientoEntity;

            List<Reserva> reservas = new ArrayList<>();
            Reserva reserva;

            List<ReservaEntity> reservaEntities = reservaDao.recuperarReservas();

            for (ReservaEntity fav: reservaEntities) {
                reserva = ReservaMapper.toReserva(fav);

                alojamientoEntity = alojamientoDao.getAlojamientoPorId(fav.getAlojamientoID());

                //Si es un Reserva de un departamento
                if(alojamientoEntity.getTipo().equals(TipoAlojamiento.DEPARTAMENTO.name())){
                    Departamento dep = alojamientoRoomDataSource.armarDepartamento(departamentoDao.getDepartamentoPorId(fav.getAlojamientoID()));
                    reserva.setAlojamiento(dep);
                }
                //Si es un Reserva de una habitacion
                else{
                    Habitacion hab = alojamientoRoomDataSource.armarHabitacion(habitacionDao.getHabitacionPorId(fav.getAlojamientoID()));
                    reserva.setAlojamiento(hab);
                }

                reservas.add(reserva);
            }

        }catch (Exception e){
            callback.resultado(false, null);
        }

    }
}
