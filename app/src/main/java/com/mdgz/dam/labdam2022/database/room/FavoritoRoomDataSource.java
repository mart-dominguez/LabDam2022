package com.mdgz.dam.labdam2022.database.room;

import android.content.Context;

import com.mdgz.dam.labdam2022.database.FavoritoDataSource;
import com.mdgz.dam.labdam2022.database.ReservaDataSource;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.AppDatabase;
import com.mdgz.dam.labdam2022.model.CiudadDao;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.DepartamentoDao;
import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.model.FavoritoDao;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.model.HabitacionDao;
import com.mdgz.dam.labdam2022.model.HotelDao;
import com.mdgz.dam.labdam2022.model.ReservaDao;
import com.mdgz.dam.labdam2022.model.UbicacionDao;

import java.util.List;

public class FavoritoRoomDataSource implements FavoritoDataSource {

    private FavoritoDao favoritoDao;
    private HabitacionDao habitacionDao;
    private DepartamentoDao departamentoDao;
    private HotelDao hotelDao;
    private UbicacionDao ubicacionDao;
    private CiudadDao ciudadDao;

    public FavoritoRoomDataSource(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        favoritoDao = db.favoritoDao();
        departamentoDao = db.departamentoDao();
        habitacionDao = db.habitacionDao();
        hotelDao = db.hotelDao();
        ubicacionDao = db.ubicacionDao();
        ciudadDao = db.ciudadDao();
    }

    @Override
    public void guardarFavorito(Favorito entidad, GuardarFavoritoCallback callback) {
        try {
            favoritoDao.insert(entidad);
            callback.resultado(true);
        }
        catch (Exception e) {
            callback.resultado(false);
        }
    }

    @Override
    public void recuperarFavorito(RecuperarFavoritoCallback callback) {
        try {
            List<Favorito> favoritos = favoritoDao.obtenerFavoritos();
            for (Favorito favorito : favoritos) {
                if (!favorito.getHabitacionId().equals(null)) {
                    Habitacion habitacion = habitacionDao.obtenerHabitacion(favorito.getHabitacionId());
                    habitacion.setHotel(hotelDao.obtenerHotel(habitacion.getHotelId()));
                    habitacion.getHotel().setUbicacion(ubicacionDao.obtenerUbicacion(habitacion.getHotel().getUbicacionId()));
                    favorito.setAlojamiento(habitacion);
                }
                else {
                    Departamento departamento = departamentoDao.obtenerDepartamento(favorito.getDepartamentoId());
                    departamento.setUbicacion(ubicacionDao.obtenerUbicacion(departamento.getUbicacionId()));
                    departamento.getUbicacion().setCiudad(ciudadDao.obtenerCiudad(departamento.getUbicacion().getCiudadId()));
                    favorito.setAlojamiento(departamento);
                }
            }
        }
        catch (Exception e) {
            callback.resultado(false, null);
        }
    }
}
