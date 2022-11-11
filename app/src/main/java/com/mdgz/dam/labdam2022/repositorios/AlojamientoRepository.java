package com.mdgz.dam.labdam2022.repositorios;

import android.content.Context;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.persistencia.interfaces.AlojamientoDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.impl.AlojamientoRoomDataSource;

import java.util.List;
import java.util.UUID;

public class AlojamientoRepository {

    private static AlojamientoRepository _REPO = null;
    private AlojamientoDataSource alojamientoDataSource;

    // patron singleton
    private AlojamientoRepository(Context ctx)
    {
        alojamientoDataSource = new AlojamientoRoomDataSource(ctx);
    }

    //El parametro context solamente se usa una vez (cuando se inicializa _REPO), en todos los demás casos es al pedo, o sea, es una exigencia innecesaria pedirlo.
    public static AlojamientoRepository getInstance(Context ctx)
    {
        if(_REPO==null)_REPO = new AlojamientoRepository(ctx);
        return _REPO;
    }

    //Por eso creé este segundo constructor que no pide context
    public static AlojamientoRepository getInstance()
    {
        if(_REPO == null) throw new RuntimeException("Te quisiste hacer el pillo, primero tenés que inicializar el REPO con context");
        else return _REPO;
    }

    public void guardar(final Habitacion entidad, final AlojamientoDataSource.GuardarCallback callback)
    {
        alojamientoDataSource.guardar(entidad, callback);
    }

    public void guardar(final Departamento entidad, final AlojamientoDataSource.GuardarCallback callback)
    {
        alojamientoDataSource.guardar(entidad, callback);
    }


    public void guardarTodos(final List<Alojamiento> alojamientos, final AlojamientoDataSource.GuardarCallback callback)
    {
        //Este callback se llama por cada alojamiento insertado, poco optimo.
        for(Alojamiento alojamiento : alojamientos )
        {
            if(alojamiento.getClass() == Departamento.class)
            guardar((Departamento) alojamiento, callback);
            else guardar((Habitacion) alojamiento, callback);
        }
    }

    public void getTodasHabitaciones(final AlojamientoDataSource.RecuperarCallback callback){
        alojamientoDataSource.getTodasHabitaciones(callback);
    }

    public void getTodosDepartamentos(final AlojamientoDataSource.RecuperarCallback callback){
        alojamientoDataSource.getTodosDepartamentos(callback);
    }

    public void getByID(UUID id, final AlojamientoDataSource.GetByIDCallback callback)
    {
        alojamientoDataSource.getByID(id,callback);
    }

}
