package com.mdgz.dam.labdam2022.utilities;

import android.text.Editable;
import android.text.TextWatcher;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

public final class Utilities {

    private Utilities(){}

    public static String round2(Double valor)
    {
        float f = valor.floatValue();
        return String.format("%.02f", f);
    }

    public static String round2(Float valor)
    {
        return String.format("%.02f", valor);
    }

    public static int editableToInteger(Editable valor)
    {
        return Integer.parseInt(String.valueOf(valor));
    }


    //Esta clase extiende la interfaz TextWatcher para no tener que escribir todos los metodos abstractos asquerosos y solo utilizar el necesario
    public abstract static class TextWatcherExtender implements TextWatcher
    {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public abstract void afterTextChanged(Editable editable);
    }

    //LocalDateTime con el UTC de Argentina (UTC -3)
    public static LocalDateTime nowArgentina()
    {
        ZoneId id = ZoneId.of("America/Argentina/Buenos_Aires");
        return LocalDateTime.now(id);
    }

}
