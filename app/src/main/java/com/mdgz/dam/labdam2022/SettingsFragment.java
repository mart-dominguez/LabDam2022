package com.mdgz.dam.labdam2022;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

public class SettingsFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
        ListPreference metodoPagoPref = findPreference("metodo_pago");
        final ListPreference monedaPreferidaPref = findPreference("moneda_preferida");

        metodoPagoPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if (newValue.equals("Efectivo")) {
                    monedaPreferidaPref.setEnabled(true);
                } else {
                    monedaPreferidaPref.setEnabled(false);
                }
                return true;
            }
        });
        Preference logs= findPreference("detalles_uso");
        logs.setOnPreferenceClickListener(e->{
          NavHostFragment.findNavController(this).navigate(R.id.action_global_verLog);
            return true;
        });



    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (preference.getKey().equals("email") || preference.getKey().equals("cuit")) {
            editor.putString(preference.getKey(), (String) newValue);
        } else if (preference.getKey().equals("metodo_pago") || preference.getKey().equals("moneda_preferida")) {
            editor.putString(preference.getKey(), (String) newValue);
        } else if (preference.getKey().equals("notificaciones")) {
            editor.putBoolean(preference.getKey(), (Boolean) newValue);
        } else if (preference.getKey().equals("autorizar")) {
            editor.putBoolean(preference.getKey(), (Boolean) newValue);
        }

        editor.apply();

        return true;
    }

}