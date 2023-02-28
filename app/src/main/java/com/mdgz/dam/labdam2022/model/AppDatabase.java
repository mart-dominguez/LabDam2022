package com.mdgz.dam.labdam2022.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.mdgz.dam.labdam2022.repo.AlojamientoRepository;
import com.mdgz.dam.labdam2022.repo.CiudadRepository;
import com.mdgz.dam.labdam2022.repo.UbicacionRepository;

import java.util.List;
import java.util.concurrent.Executors;

@Database(entities = {Alojamiento.class, Departamento.class, Habitacion.class, Hotel.class, Ubicacion.class, Ciudad.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    public abstract DepartamentoDao departamentoDao();
    public abstract HabitacionDao habitacionDao();
    public abstract HotelDao hotelDao();
    public abstract UbicacionDao ubicacionDao();
    public abstract CiudadDao ciudadDao();

    public synchronized static AppDatabase getInstance(Context context){
        if (INSTANCE == null) {
            INSTANCE = buildDatabase(context);
        }
        return INSTANCE;
    }
    private static AppDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class, "my-database7").allowMainThreadQueries()
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(new
                             Runnable() {
                                 @Override
                                 public void run() {
                                     List<Alojamiento> alojamientos = AlojamientoRepository._ALOJAMIENTOS;
                                     for (Alojamiento thisAlojamiento : alojamientos) {
                                         if (thisAlojamiento instanceof Departamento)
                                             getInstance(context).departamentoDao().insert((Departamento) thisAlojamiento);
                                         else
                                             getInstance(context).habitacionDao().insert((Habitacion) thisAlojamiento);
                                     }
                                     List<Ciudad> ciudades = CiudadRepository._CIUDADES;
                                     getInstance(context).ciudadDao().insertAll(ciudades);

                                     getInstance(context).ubicacionDao().insertAll(UbicacionRepository._UBICACIONES);
                                 }
                             });
                    }
                })
                .build();
    }
}