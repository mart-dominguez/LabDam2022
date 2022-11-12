package com.mdgz.dam.labdam2022.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Ubicacion implements Parcelable {
    private double lat;
    private double lng;
    private String calle;
    private String numero;
    private Ciudad ciudad;

    public Ubicacion(double lat, double lng, String calle, String numero, Ciudad ciudad) {
        this.lat = lat;
        this.lng = lng;
        this.calle = calle;
        this.numero = numero;
        this.ciudad = ciudad;
    }

    protected Ubicacion(Parcel in) {
        lat = in.readDouble();
        lng = in.readDouble();
        calle = in.readString();
        numero = in.readString();
        ciudad = in.readParcelable(Ciudad.class.getClassLoader());
    }

    public static final Creator<Ubicacion> CREATOR = new Creator<Ubicacion>() {
        @Override
        public Ubicacion createFromParcel(Parcel in) {
            return new Ubicacion(in);
        }

        @Override
        public Ubicacion[] newArray(int size) {
            return new Ubicacion[size];
        }
    };

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(lat);
        parcel.writeDouble(lng);
        parcel.writeString(calle);
        parcel.writeString(numero);
        parcel.writeParcelable(ciudad, i);
    }

    private void readFromParcel(Parcel in) {
        lat = in.readDouble();
        lng = in.readDouble();
        calle = in.readString();
        numero = in.readString();
        ciudad = in.readParcelable(Ciudad.class.getClassLoader());
    }

}
