package com.mdgz.dam.labdam2022.model;

import java.io.Serializable;

public class BusquedaDTO implements Serializable {

    private String TipoAlojamiento;
    private Integer cantOcupantes;
    private Boolean wifi;
    private Double precioMin;
    private Double precioMax;
    private String ciudad;
    private Long timestampInicio;
    private Long tiempoBusqueda;
    private Integer cantidadResultados;

    public BusquedaDTO(){}

    public String getTipoAlojamiento() {
        return TipoAlojamiento;
    }

    public void setTipoAlojamiento(String tipoAlojamiento) {
        TipoAlojamiento = tipoAlojamiento;
    }

    public Integer getCantOcupantes() {
        return cantOcupantes;
    }

    public void setCantOcupantes(Integer cantOcupantes) {
        this.cantOcupantes = cantOcupantes;
    }

    public Boolean getWifi() {
        return wifi;
    }

    public void setWifi(Boolean wifi) {
        this.wifi = wifi;
    }

    public Double getPrecioMin() {
        return precioMin;
    }

    public void setPrecioMin(Double precioMin) {
        this.precioMin = precioMin;
    }

    public Double getPrecioMax() {
        return precioMax;
    }

    public void setPrecioMax(Double precioMax) {
        this.precioMax = precioMax;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Long getTimestampInicio() {
        return timestampInicio;
    }

    public void setTimestampInicio(Long timestampInicio) {
        this.timestampInicio = timestampInicio;
    }

    public Long getTiempoBusqueda() {
        return tiempoBusqueda;
    }

    public void setTiempoBusqueda(Long tiempoBusqueda) {
        this.tiempoBusqueda = tiempoBusqueda;
    }

    public Integer getCantidadResultados() {
        return cantidadResultados;
    }

    public void setCantidadResultados(Integer cantidadResultados) {
        this.cantidadResultados = cantidadResultados;
    }
}
