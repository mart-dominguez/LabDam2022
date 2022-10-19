package com.mdgz.dam.labdam2022.viewmodels;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.mdgz.dam.labdam2022.utilities.JSONLogs;
import com.mdgz.dam.labdam2022.utilities.Utilities;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LogViewModel extends ViewModel {

    private boolean guardado = false;
    private final SearchLog log = new SearchLog();

    public SearchLog getLog() {
        return log;
    }

    /* Pasé los atributos del log a una clase separada para que loadFromJSON no cambie el valor
    *  de los atributos del viewModel, pisando los valores previos. Ahora loadFromJSON crea una
    *  nueva instancia por cada fila en mostrarLogs() */

    public static class SearchLog
    {
        private boolean dpto, hotel, wifi;
        private LocalDateTime timestamp;
        private String tiempo_busqueda,ciudad;
        private int cant_resultados, cant_ocupantes;
        private double val_min, val_max;

        //Constructor privado para evitar que sea instanciado desde otro lado
        private SearchLog(){};

        public JSONObject toJSON()
        {
            JSONObject log = new JSONObject();
            try
            {
                log.put("timestamp", this.timestamp.toString());
                log.put("ciudad", this.ciudad);
                log.put("dpto", this.dpto);
                log.put("hotel", this.hotel);
                log.put("wifi", this.wifi);
                log.put("val_min", Utilities.round2(this.val_min));
                log.put("val_max", Utilities.round2(this.val_max));
                log.put("cant_ocupantes", this.cant_ocupantes);
                log.put("cant_resultados", this.cant_resultados);
                log.put("tiempo_busqueda", this.tiempo_busqueda);

            } catch (JSONException e)
            {
                e.printStackTrace();
            }
            return log;
        }

        public void setTimestamp(String timestamp)
        {
            ZoneId id = ZoneId.of("America/Argentina/Buenos_Aires");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault()).withZone(id);
            this.timestamp = LocalDateTime.parse(timestamp,dtf);
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

        public void setVal_min(float val_min) {
            this.val_min = val_min;
        }

        public void setVal_max(float val_max) {
            this.val_max = val_max;
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

        public LocalDateTime getTimestamp() {
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


    public SearchLog loadFromJSON(JSONObject fila)
    {
        SearchLog newLog = new SearchLog();

        try
        {
            newLog.setTimestamp(fila.getString("timestamp"));
            newLog.setCiudad(fila.getString("ciudad"));
            newLog.setDpto(fila.getBoolean("dpto"));
            newLog.setHotel(fila.getBoolean("hotel"));
            newLog.setWifi(fila.getBoolean("wifi"));
            newLog.setVal_min(Double.parseDouble(fila.getString("val_min")));
            newLog.setVal_max(Double.parseDouble(fila.getString("val_max")));
            newLog.setCant_ocupantes(fila.getInt("cant_ocupantes"));
            newLog.setCant_resultados(fila.getInt("cant_resultados"));
            newLog.setTiempo_busqueda(fila.getString("tiempo_busqueda"));

        } catch (JSONException e){ e.printStackTrace();}

        return newLog;

    }

    public void guardar(Context context)
    {
        JSONLogs jsonLogs = new JSONLogs(context);
        jsonLogs.writeToFile(log.toJSON());
    }


    public void setGuardado(boolean guardado) {this.guardado = guardado;}

    public boolean isGuardado() { return this.guardado;}
}
