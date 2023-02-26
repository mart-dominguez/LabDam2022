package com.mdgz.dam.labdam2022.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;

import java.util.UUID;

@Entity(tableName = "habitacion")
public class Habitacion  extends Alojamiento implements Parcelable {

    private int camasIndividuales;
    private int camasMatrimoniales;
    private Boolean tieneEstacionamiento;
    private int hotelId;
    @Ignore
    private Hotel hotel;

    public Habitacion() {
        super();
    }

    protected Habitacion(Parcel in) {
        super(in);
        camasIndividuales = in.readInt();
        camasMatrimoniales = in.readInt();
        byte tmpTieneEstacionamiento = in.readByte();
        tieneEstacionamiento = tmpTieneEstacionamiento == 0 ? null : tmpTieneEstacionamiento == 1;
        hotel = in.readParcelable(Hotel.class.getClassLoader());
    }

    public static final Creator<Habitacion> CREATOR = new Creator<Habitacion>() {
        @Override
        public Habitacion createFromParcel(Parcel in) {
            return new Habitacion(in);
        }

        @Override
        public Habitacion[] newArray(int size) {
            return new Habitacion[size];
        }
    };

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

    public Habitacion(String titulo, String descripcion, Integer capacidad, Double precioBase, int camasIndividuales, int camasMatrimoniales, Boolean tieneEstacionamiento, Hotel hotel) {
        super(titulo, descripcion, capacidad, precioBase);
        this.camasIndividuales = camasIndividuales;
        this.camasMatrimoniales = camasMatrimoniales;
        this.tieneEstacionamiento = tieneEstacionamiento;
        this.hotel = hotel;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getCamasIndividuales() {
        return camasIndividuales;
    }

    public void setCamasIndividuales(int camasIndividuales) {
        this.camasIndividuales = camasIndividuales;
    }

    public int getCamasMatrimoniales() {
        return camasMatrimoniales;
    }

    public void setCamasMatrimoniales(int camasMatrimoniales) {
        this.camasMatrimoniales = camasMatrimoniales;
    }

    public Boolean getTieneEstacionamiento() {
        return tieneEstacionamiento;
    }

    public void setTieneEstacionamiento(Boolean tieneEstacionamiento) {
        this.tieneEstacionamiento = tieneEstacionamiento;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public Ubicacion getUbicacion() {
        return hotel.getUbicacion();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(camasIndividuales);
        parcel.writeInt(camasMatrimoniales);
        parcel.writeBoolean(tieneEstacionamiento);
        parcel.writeParcelable(hotel, i);



    }
    private void readFromParcel(Parcel in) {
        camasIndividuales = in.readInt();
        camasMatrimoniales = in.readInt();
        tieneEstacionamiento = in.readBoolean();
        hotel = in.readParcelable(Hotel.class.getClassLoader());
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }
}
