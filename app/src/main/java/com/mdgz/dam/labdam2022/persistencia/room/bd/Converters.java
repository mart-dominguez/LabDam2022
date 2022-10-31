package com.mdgz.dam.labdam2022.persistencia.room.bd;

import androidx.room.ProvidedTypeConverter;
import androidx.room.TypeConverter;

import java.util.Date;
import java.util.UUID;

@ProvidedTypeConverter
public class Converters {

    @TypeConverter
    public static Date fromTimestamp(Long valor){
        return valor == null ? null : new Date(valor);
    }
    @TypeConverter
    public static Long dateToTimestamp(Date date){
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static Boolean intToBoolean(Integer valor){
        return valor == 1 ? true : false;
    }
    @TypeConverter
    public static Integer fromBoolean(Boolean valor){
        return valor == true ? 1 : 0;
    }

    @TypeConverter
    public static UUID fromString(String num){
        return num == null ? null : UUID.fromString(num);
    }
    @TypeConverter
    public static String UUIDtoString(UUID num){
        return num == null ? null : num.toString();
    }
}
