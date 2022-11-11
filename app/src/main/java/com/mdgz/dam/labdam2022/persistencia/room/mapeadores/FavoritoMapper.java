package com.mdgz.dam.labdam2022.persistencia.room.mapeadores;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.model.Usuario;
import com.mdgz.dam.labdam2022.persistencia.interfaces.AlojamientoDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.FavoritoDepartamentoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.FavoritoHabitacionEntity;
import com.mdgz.dam.labdam2022.repositorios.AlojamientoRepository;
import com.mdgz.dam.labdam2022.repositorios.UsuarioRepository;

public final class FavoritoMapper
{

    private static AlojamientoRepository alojamientoRepository;
    private static UsuarioRepository usuarioRepository;

    private FavoritoMapper(){};

    public static FavoritoHabitacionEntity toHabitacionEntity(Favorito favorito)
    {
        return new FavoritoHabitacionEntity(
                favorito.getId(),
                favorito.getAlojamiento().getId(),
                favorito.getUsuario().getId()
        );
    }

    public static FavoritoDepartamentoEntity toDepartamentoEntity(Favorito favorito)
    {
        return new FavoritoDepartamentoEntity(
                favorito.getId(),
                favorito.getAlojamiento().getId(),
                favorito.getUsuario().getId()
        );
    }

    public static Favorito toModel(FavoritoHabitacionEntity entity)
    {
        if(alojamientoRepository == null) alojamientoRepository = AlojamientoRepository.getInstance();
        if(usuarioRepository == null) usuarioRepository = UsuarioRepository.getInstance();

        Alojamiento[] habitacion = new Habitacion[1];
        Usuario[] usuario = new Usuario[1];
        alojamientoRepository.getByID(entity.getHabitacionID(),(exito, alojamiento) -> {habitacion[0] = alojamiento;});
        usuarioRepository.getByID(entity.getUsuarioID(),(exito, usuario1) -> {usuario[0] = usuario1;});

        return new Favorito(
                entity.getId(),
                usuario[0],
                habitacion[0]
        );
    }

    public static Favorito toModel(FavoritoDepartamentoEntity entity)
    {
        if(alojamientoRepository == null) alojamientoRepository = AlojamientoRepository.getInstance();
        if(usuarioRepository == null) usuarioRepository = UsuarioRepository.getInstance();

        Alojamiento[] departamento = new Departamento[1];
        Usuario[] usuario = new Usuario[1];
        alojamientoRepository.getByID(entity.getDepartamentoID(),(exito, alojamiento) -> {departamento[0] = alojamiento;});
        usuarioRepository.getByID(entity.getUsuarioID(),(exito, usuario1) -> {usuario[0] = usuario1;});

        return new Favorito(
                entity.getId(),
                usuario[0],
                departamento[0]
        );
    }

}
