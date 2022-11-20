package com.mdgz.dam.labdam2022.persistencia.retrofit.mapeadores;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.model.Usuario;
import com.mdgz.dam.labdam2022.persistencia.retrofit.entidades.FavoritoEntity;
import com.mdgz.dam.labdam2022.repositorios.AlojamientoRepository;
import com.mdgz.dam.labdam2022.repositorios.UsuarioRepository;

import java.util.UUID;

public class FavoritoMapper {

    private static AlojamientoRepository alojamientoRepository;
    private static UsuarioRepository usuarioRepository;

    private FavoritoMapper(){};

    public static FavoritoEntity toEntity(Favorito favorito){
        return new FavoritoEntity(
                favorito.getAlojamiento().getId(),
                favorito.getUsuario().getId()
        );
    }

    public static Favorito toModel(FavoritoEntity favoritoEntity){

        if(alojamientoRepository == null) alojamientoRepository = AlojamientoRepository.getInstance();
        if(usuarioRepository == null) usuarioRepository = UsuarioRepository.getInstance();

        Alojamiento[] aloj = new Alojamiento[1];
        Usuario[] usuario = new Usuario[1];
        alojamientoRepository.getByID(favoritoEntity.getAlojamientoId(),(exito, alojamiento) -> {
            if(alojamiento.getClass()== Departamento.class) aloj[0] = (Departamento) alojamiento;
            else aloj[0] = (Habitacion) alojamiento;
        });
        usuarioRepository.getByID(favoritoEntity.getUsuarioId(),(exito, usuario1) -> {usuario[0] = usuario1;});

        return new Favorito(
                UUID.randomUUID(),
                usuario[0],
                aloj[0]
        );
    }
}
