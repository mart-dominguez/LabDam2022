package com.mdgz.dam.labdam2022.model;

import java.util.UUID;

public class Usuario
{
    private UUID id;
    private String nombre;
    private String password;

    public Usuario(UUID id, String nombre, String password) {
        this.id = id;
        this.nombre = nombre;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
