package com.mdgz.dam.labdam2022;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }

    private ListPreference listaMetodos;
    private Preference detalleUso;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        handleMetodoYMonedaDePago();

        handleDetalleDeUso();
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

    private void handleDetalleDeUso(){
        detalleUso = getPreferenceManager().findPreference("detalle_uso");
        detalleUso.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                //Navegar al fragmento que muestra el log
                return false;
            }
        });
    }

}