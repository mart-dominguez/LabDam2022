package com.mdgz.dam.labdam2022.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity
public class Hotel implements Parcelable {

    @PrimaryKey
    @NonNull
    Integer id;
    String nombre;
    Integer categoria;
    UUID ubicacionId;
    @Ignore
    Ubicacion ubicacion;

    public Hotel(){
        super();
    }

    public Hotel(Integer id, String nombre, Integer categoria, Ubicacion ubicacion) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.ubicacion = ubicacion;
    }

    protected Hotel(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        nombre = in.readString();
        if (in.readByte() == 0) {
            categoria = null;
        } else {
            categoria = in.readInt();
        }
        ubicacion = in.readParcelable(Ubicacion.class.getClassLoader());
        ubicacionId = ubicacion.getId();
    }

    public static final Creator<Hotel> CREATOR = new Creator<Hotel>() {
        @Override
        public Hotel createFromParcel(Parcel in) {
            return new Hotel(in);
        }

        @Override
        public Hotel[] newArray(int size) {
            return new Hotel[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(nombre);
        parcel.writeInt(categoria);
        parcel.writeParcelable(ubicacion, i);
    }

    private void readFromParcel(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        categoria = in.readInt();
        ubicacion = in.readParcelable(Ubicacion.class.getClassLoader());
    }

    public UUID getUbicacionId() {
        return ubicacionId;
    }

    public void setUbicacionId(UUID ubicacionId) {
        this.ubicacionId = ubicacionId;
    }
}
