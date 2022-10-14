package com.mdgz.dam.labdam2022.viewmodels;

import androidx.lifecycle.ViewModel;

import org.json.JSONException;
import org.json.JSONObject;

public class LogViewModel extends ViewModel {

    boolean guardado;
    String timestamp;
    int cant_resultados;
    String tiempo_busqueda;
    String ciudad;
    boolean dpto, hotel, wifi;
    double val_min, val_max;
    int cant_ocupantes;

    public LogViewModel(){
        guardado = true;
        timestamp = null;
        cant_resultados = 0;
        tiempo_busqueda = null;
        ciudad = null;
        dpto = false;
        hotel = false;
        wifi = false;
        val_min = 0;
        val_max = 25000;
        cant_ocupantes = 0;
    }

    public JSONObject toJSON(){
        JSONObject log = new JSONObject();
        try{
            log.put("timestamp", this.timestamp);
            log.put("ciudad", this.ciudad);
            log.put("dpto", this.dpto);
            log.put("hotel", this.hotel);
            log.put("wifi", this.wifi);
            log.put("val_min", this.val_min);
            log.put("val_max", this.val_max);
            log.put("cant_ocupantes", this.cant_ocupantes);
            log.put("cant_resultados", this. cant_resultados);
            log.put("tiempo_busqueda", this.tiempo_busqueda);
        } catch (JSONException e){ e.printStackTrace();}
        return log;
    }

    public void loadFromJSON(JSONObject fila){
        try{
            this.setTimestamp(fila.getString("timestamp"));
            this.setCiudad(fila.getString("ciudad"));
            this.setDpto(fila.getBoolean("dpto"));
            this.setHotel(fila.getBoolean("hotel"));
            this.setWifi(fila.getBoolean("wifi"));
            this.setVal_min(fila.getDouble("val_min"));
            this.setVal_max(fila.getDouble("val_max"));
            this.setCant_ocupantes(fila.getInt("cant_ocupantes"));
            this.setCant_resultados(fila.getInt("cant_resultados"));
            this.setTiempo_busqueda(fila.getString("tiempo_busqueda"));
        }catch (JSONException e){ e.printStackTrace();}
    }

    //Setters

    public void setGuardado(boolean guardado) {
        this.guardado = guardado;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setCant_resultados(int cant_resultados) {
        this.cant_resultados = cant_resultados;
    }

    public void setTiempo_busqueda(String tiempo_busqueda) {
        this.tiempo_busqueda = tiempo_busqueda;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setDpto(boolean dpto) {
        this.dpto = dpto;
    }

    public void setHotel(boolean hotel) {
        this.hotel = hotel;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public void setVal_min(double val_min) {
        this.val_min = val_min;
    }

    public void setVal_max(double val_max) {
        this.val_max = val_max;
    }

    public void setCant_ocupantes(int cant_ocupantes) {
        this.cant_ocupantes = cant_ocupantes;
    }

    //Getters

    public boolean isGuardado() {
        return guardado;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public int getCant_resultados() {
        return cant_resultados;
    }

    public String getTiempo_busqueda() {
        return tiempo_busqueda;
    }

    public String getCiudad() {
        return ciudad;
    }

    public boolean isDpto() {
        return dpto;
    }

    public boolean isHotel() {
        return hotel;
    }

    public boolean isWifi() {
        return wifi;
    }

    public double getVal_min() {
        return val_min;
    }

    public double getVal_max() {
        return val_max;
    }

    public int getCant_ocupantes() {
        return cant_ocupantes;
    }
}
