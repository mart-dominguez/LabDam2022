package com.mdgz.dam.labdam2022.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Departamento extends Alojamiento implements Parcelable {

    private Boolean tieneWifi;
    private Double costoLimpieza;
    private Integer cantidadHabitaciones;
    private Ubicacion ubicacion;

    protected Departamento(Parcel in) {
        super(in);
        byte tmpTieneWifi = in.readByte();
        tieneWifi = tmpTieneWifi == 0 ? null : tmpTieneWifi == 1;
        if (in.readByte() == 0) {
            costoLimpieza = null;
        } else {
            costoLimpieza = in.readDouble();
        }
        if (in.readByte() == 0) {
            cantidadHabitaciones = null;
        } else {
            cantidadHabitaciones = in.readInt();
        }
        ubicacion = in.readParcelable(Ubicacion.class.getClassLoader());
    }

    public static final Creator<Departamento> CREATOR = new Creator<>() {
        @Override
        public Departamento createFromParcel(Parcel in) {
            return new Departamento(in);
        }

        @Override
        public Departamento[] newArray(int size) {
            return new Departamento[size];
        }
    };

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Departamento() {
        super();
    }

    @Override
    public String getTitulo() {
        return super.titulo;
    }

    @Override
    public Integer getCapacidad() {
        return super.capacidad;
    }

    @Override
    public Double getPrecioBase() {
        return super.precioBase;
    }

    @Override
    public String getDescripcion() {
        return super.descripcion;
    }

    public Departamento(Integer id, String titulo, String descripcion, Integer capacidad, Double precioBase, Boolean tieneWifi, Double costoLimpieza, Integer cantidadHabitaciones, Ubicacion ubicacion) {
        super(id, titulo, descripcion, capacidad, precioBase);
        this.tieneWifi = tieneWifi;
        this.costoLimpieza = costoLimpieza;
        this.cantidadHabitaciones = cantidadHabitaciones;
        this.ubicacion = ubicacion;
    }

    public Boolean getTieneWifi() {
        return tieneWifi;
    }

    public void setTieneWifi(Boolean tieneWifi) {
        this.tieneWifi = tieneWifi;
    }

    public Double getCostoLimpieza() {
        return costoLimpieza;
    }

    public void setCostoLimpieza(Double costoLimpieza) {
        this.costoLimpieza = costoLimpieza;
    }

    public Integer getCantidadHabitaciones() {
        return cantidadHabitaciones;
    }

    public void setCantidadHabitaciones(Integer cantidadHabitaciones) {
        this.cantidadHabitaciones = cantidadHabitaciones;
    }

    @Override
    public Ubicacion getUbicacion() {
        return null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBoolean(tieneWifi);
        parcel.writeDouble(costoLimpieza);
        parcel.writeInt(cantidadHabitaciones);
        parcel.writeParcelable(ubicacion, i);
    }

    private void readFromParcel(Parcel in) {
        tieneWifi = in.readBoolean();
        costoLimpieza = in.readDouble();
        cantidadHabitaciones = in.readInt();
        ubicacion = in.readParcelable(Ubicacion.class.getClassLoader());
    }
}