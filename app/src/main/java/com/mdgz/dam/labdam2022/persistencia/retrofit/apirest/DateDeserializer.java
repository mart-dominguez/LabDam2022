package com.mdgz.dam.labdam2022.persistencia.retrofit.apirest;


import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

public class DateDeserializer implements JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {

        String fechaISOstr = element.getAsString(); // yyyy-MM-ddThh:mm:ss
        int indexT = fechaISOstr.indexOf('T');
        String fecha = fechaISOstr.substring(0,indexT);


        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }


    }
}
