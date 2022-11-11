package com.mdgz.dam.labdam2022.persistencia.room.bd;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.persistencia.room.daos.CiudadDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.DepartamentoDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.FavoritoDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.HabitacionDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.HotelDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.ReservaDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.UbicacionDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.UsuarioDao;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.AlojamientoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.CiudadEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.DepartamentoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.FavoritoDepartamentoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.FavoritoHabitacionEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.HabitacionEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.HotelEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.ReservaDepartamentoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.ReservaHabitacionEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.UbicacionEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.UsuarioEntity;
import com.mdgz.dam.labdam2022.repositorios.AlojamientoRepository;
import com.mdgz.dam.labdam2022.persistencia.DatosIniciales;
import com.mdgz.dam.labdam2022.repositorios.UsuarioRepository;

import java.util.List;

//
@Database(entities = {FavoritoHabitacionEntity.class, FavoritoDepartamentoEntity.class, ReservaHabitacionEntity.class, ReservaDepartamentoEntity.class, UbicacionEntity.class, HotelEntity.class, CiudadEntity.class, DepartamentoEntity.class, HabitacionEntity.class, UsuarioEntity.class}, version = 3)
@TypeConverters(Converters.class)

public abstract class BaseDeDatos extends RoomDatabase {

    private static BaseDeDatos INSTANCE;

    public abstract DepartamentoDao departamentoDao();
    public abstract HabitacionDao habitacionDao();
    public abstract FavoritoDao favoritoDao();
    public abstract ReservaDao reservaDao();
    public abstract CiudadDao ciudadDao();
    public abstract HotelDao hotelDao();
    public abstract UbicacionDao ubicacionDao();
    public abstract UsuarioDao usuarioDao();

    public synchronized static BaseDeDatos getInstance(Context context)
    {
        if (INSTANCE == null)
        {
            INSTANCE = buildDatabase(context);
            INSTANCE.cargarDatosIniciales(context);
        }
        return INSTANCE;
    }

    private static BaseDeDatos buildDatabase(final Context context)
    {
        return Room.databaseBuilder(context, BaseDeDatos.class, "my-database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    private void cargarDatosIniciales(Context ctx)
    {

        DatosIniciales listas = new DatosIniciales();
        AlojamientoRepository alojamientoRepository = AlojamientoRepository.getInstance(ctx);
        UsuarioRepository usuarioRepository = UsuarioRepository.getInstance(ctx);

        List<Alojamiento> alojamientos = listas.getLista();

        UsuarioDao dao = INSTANCE.usuarioDao();
        //Solo cargar nuevos datos si no existen datos previos
        if(dao.getTodos().size() == 0) {

            usuarioRepository.guardar(listas.getUsuario(), exito -> {
            });
            alojamientoRepository.guardarTodos(alojamientos, exito -> {
            });
        }



    }

}
