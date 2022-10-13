package com.mdgz.dam.labdam2022;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.preference.CheckBoxPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }

    private ListPreference listaMetodos;
    private Preference detalleUso;
    private CheckBoxPreference checkUso;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        handleMetodoYMonedaDePago();

        handleInfDeUso();

        handleDetalleDeUso(view);
    }

    private void handleMetodoYMonedaDePago(){

        SharedPreferences sharedPreferences = getPreferenceManager().getSharedPreferences();

        //Para cuando se cree por primera vez, me fijo en que estado se encuentra metodo de pago
        String metodo = sharedPreferences.getString("metodo_pago", "def");
        if(!metodo.equals("EF")) findPreference("moneda_pago").setEnabled(false);

        //Listener del metodo de pago
        listaMetodos = getPreferenceManager().findPreference("metodo_pago");
        listaMetodos.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if(newValue.toString().equals("EF")){
                    findPreference("moneda_pago").setEnabled(true);
                }else findPreference("moneda_pago").setEnabled(false);
                return true;
            }
        });

    }

    private void handleInfDeUso(){
        checkUso = getPreferenceManager().findPreference("check_uso_app");
        checkUso.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                if(!checkUso.isChecked()){
                    //Eliminar el archivo de logs
                    try {
                        File log = new File(getContext().getFilesDir() + "/logs.json");
                        log.delete();
                    }catch (Exception e) {e.printStackTrace();}
                }

                return false;
            }
        });

    }

    private void handleDetalleDeUso(View view){
        detalleUso = getPreferenceManager().findPreference("detalle_uso");
        detalleUso.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {

                NavDirections action = SettingsFragmentDirections.actionSettingsFragmentToDetalleLogs();
                Navigation.findNavController(view).navigate(action);

                return false;
            }
        });
    }

}