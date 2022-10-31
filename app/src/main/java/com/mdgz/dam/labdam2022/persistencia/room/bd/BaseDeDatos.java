package com.mdgz.dam.labdam2022.persistencia.room.bd;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Ciudad;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.model.Hotel;
import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.model.Ubicacion;
import com.mdgz.dam.labdam2022.persistencia.room.daos.CiudadDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.DepartamentoDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.FavoritoDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.HabitacionDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.HotelDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.ReservaDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.UbicacionDao;
import com.mdgz.dam.labdam2022.utilities.ListaDeAlojamientos;

import java.util.concurrent.Executors;

@Database(entities = {Alojamiento.class, Favorito.class, Reserva.class, Ubicacion.class, Hotel.class, Ciudad.class, Departamento.class, Habitacion.class}, version = 1)
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

    public synchronized static BaseDeDatos getInstance(Context context){
        if (INSTANCE == null) {
            INSTANCE = buildDatabase(context);
        }
        return INSTANCE;
    }
    private static BaseDeDatos buildDatabase(final Context context) {
        return Room.databaseBuilder(context,
                BaseDeDatos.class,
                        "my-database")
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                //Cargar datos iniciales
                                ListaDeAlojamientos listas = new ListaDeAlojamientos();
                                BaseDeDatos bd = getInstance(context);
                                bd.ciudadDao().insertarTodos(listas.getCiudades()); //Ciudades
                                bd.ubicacionDao().insertarTodos(listas.getUbicaciones()); //Ubicaciones
                                bd.hotelDao().insertarTodos(listas.getHoteles()); //Hoteles
                                bd.departamentoDao().insertarTodos(listas.getDepartamentos()); //Departamentos
                                bd.habitacionDao().insertarTodos(listas.getHabitaciones()); //Habitaciones
                            }
                        });
                    }
                })
                .allowMainThreadQueries()
                .build();
    }                

}
