package com.mdgz.dam.labdam2022.database.room;

import android.content.Context;

import com.mdgz.dam.labdam2022.database.ReservaDataSource;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.AppDatabase;
import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.model.ReservaDao;

import java.util.ArrayList;
import java.util.List;

public class ReservaRoomDataSource implements ReservaDataSource {

    private ReservaDao reservaDao;

    public ReservaRoomDataSource(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        reservaDao = db.reservaDao();
    }

    @Override
    public void guardarReserva(Reserva entidad, GuardarReservaCallback callback) {
        try {
            reservaDao.insert(entidad);
            callback.resultado(true);
        }
        catch(Exception e) {
            callback.resultado(false);
        }

    }

    @Override
    public void recuperarReserva(RecuperarReservaCallback callback) {
        List<Reserva> reservas;

        try {
            reservas = reservaDao.obtenerReservas();
            callback.resultado(true, reservas);
        }
        catch(Exception e) {
            callback.resultado(false, null);
        }
        //System.out.println(reservas);
    }
}
