package com.mdgz.dam.labdam2022.persistencia.room.bd;

import android.content.Context;
import android.util.Log;

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
import com.mdgz.dam.labdam2022.persistencia.AlojamientoDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.AlojamientoRoomDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.daos.AlojamientoDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.CiudadDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.DepartamentoDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.FavoritoDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.HabitacionDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.HotelDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.ReservaDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.UbicacionDao;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.AlojamientoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.CiudadEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.DepartamentoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.FavoritoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.HabitacionEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.HotelEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.ReservaEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.UbicacionEntity;
import com.mdgz.dam.labdam2022.repositorios.AlojamientoRepository;
import com.mdgz.dam.labdam2022.utilities.ListaDeAlojamientos;

import java.util.List;
import java.util.concurrent.Executors;
//
@Database(entities = {AlojamientoEntity.class, FavoritoEntity.class, ReservaEntity.class, UbicacionEntity.class, HotelEntity.class, CiudadEntity.class, DepartamentoEntity.class, HabitacionEntity.class}, version = 1)
@TypeConverters(Converters.class)
public abstract class BaseDeDatos extends RoomDatabase implements AlojamientoDataSource.GuardarAlojamientoCallback {

    private static BaseDeDatos INSTANCE;

    public abstract DepartamentoDao departamentoDao();
    public abstract HabitacionDao habitacionDao();
    public abstract FavoritoDao favoritoDao();
    public abstract ReservaDao reservaDao();
    public abstract CiudadDao ciudadDao();
    public abstract HotelDao hotelDao();
    public abstract UbicacionDao ubicacionDao();
    public abstract AlojamientoDao alojamientoDao();

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
                                BaseDeDatos bd = getInstance(context);
                                bd.cargarDatosIniciales(bd, context);
                            }
                        });
                    }
                })
                .allowMainThreadQueries()
                .build();
    }

    private void cargarDatosIniciales(BaseDeDatos bd, Context ctx){

        ListaDeAlojamientos listas = new ListaDeAlojamientos();

        AlojamientoRepository alojamientoRepository = AlojamientoRepository.getInstance(ctx);

        List<Alojamiento> alojamientos = listas.getLista();
        for (Alojamiento aloj: alojamientos) {
            alojamientoRepository.guardarAlojamiento(aloj, bd);
        }

    }

    @Override
    public void resultado(final boolean exito){
        if(exito) Log.i("CARGA INICIAL DE DATOS:", "EXITOSA");
        else Log.i("CARGA INICIAL DE DATOS:", "NO EXITOSA");
    }

}
