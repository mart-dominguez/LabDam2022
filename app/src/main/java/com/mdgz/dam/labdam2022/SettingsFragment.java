package com.mdgz.dam.labdam2022;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.preference.CheckBoxPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import java.io.File;

public class SettingsFragment extends PreferenceFragmentCompat {



    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey)
    {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        metodoMoneda();
        infoDetalles();

    }

    private void metodoMoneda()
    {
        SharedPreferences sharedPreferences = getPreferenceManager().getSharedPreferences();

        Preference moneda = findPreference("moneda_pago");
        Preference metodoPago = findPreference("metodo_pago");
        String metodoPagoValor = sharedPreferences.getString("metodo_pago", null);


        //Para cuando se cree por primera vez, me fijo en que estado se encuentra metodo de pago

        moneda.setEnabled(metodoPagoValor.equals("EF"));

        //Listener del metodo de pago

        metodoPago.setOnPreferenceChangeListener((Preference preference, Object newValue) ->
        {
            moneda.setEnabled(newValue.toString().equals("EF"));
            return true;    //True para actualizar el valor
        });

    }

    private void infoDetalles()
    {
        CheckBoxPreference checkUso = findPreference("check_uso_app");
        Preference detalleUso = findPreference("detalle_uso");

        checkUso.setOnPreferenceChangeListener((Preference preference, Object newValue) ->
        {
            if(!checkUso.isChecked())
            {
                //Eliminar el archivo de logs
                try
                {
                    File log = new File(getContext().getFilesDir() + "/logs.json");
                    log.delete();
                }
                catch (Exception e) {e.printStackTrace();}
            }
            return true;
        });

        detalleUso.setOnPreferenceClickListener((Preference preference) ->
        {
            NavDirections action = SettingsFragmentDirections.actionSettingsFragmentToDetalleLogs();
            NavHostFragment.findNavController(this).navigate(action);   //Se puede usar este metodo (en vez de Navigation.findNavController(view))
            return false;                                                       //cuando no se tiene una referencia sencilla a la view
        });

    }

}