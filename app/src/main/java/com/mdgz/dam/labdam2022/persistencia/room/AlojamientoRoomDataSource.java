package com.mdgz.dam.labdam2022.persistencia.room;


import android.content.Context;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.persistencia.AlojamientoDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.bd.BaseDeDatos;

public class AlojamientoRoomDataSource implements AlojamientoDataSource {

    private BaseDeDatos bd;
    public AlojamientoRoomDataSource(Context ctx){
        bd = BaseDeDatos.getInstance(ctx);
    }


    @Override
    public void guardarAlojamiento(Alojamiento entidad, GuardarAlojamientoCallback callback) {

    }

    @Override
    public void recuperarAlojamiento(RecuperarAlojamientoCallback callback) {

    }
}
