package com.mdgz.dam.labdam2022;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import java.io.File;
import java.io.FileNotFoundException;

public class ConfiguracionFragment extends PreferenceFragmentCompat implements PreferenceFragmentCompat.OnPreferenceStartFragmentCallback{
    SharedPreferences sharedPrefs;
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.root_preferences);

         sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());
         agregarDescripciones();


    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        container.getContext().setTheme(R.style.Estilo_Preferencia);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        sharedPrefs.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
                actuaizarPreferencias(sharedPreferences, s);
            }
        });
        Preference historialPreference = findPreference("fragmento_historial");
        historialPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                NavHostFragment.findNavController(ConfiguracionFragment.this).navigate(R.id.action_configuracionFragment_to_historialBusquedaFragment);
                return true;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        sharedPrefs.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
            actuaizarPreferencias(sharedPreferences, s);
            }
        });
    }
    private void agregarDescripciones(){
        Preference pref = findPreference("text_mail");
        if(sharedPrefs.getString("text_mail", "") !=null){
            pref.setSummary(sharedPrefs.getString("text_mail",""));
            pref.setPersistent(true);
        }
        pref = findPreference("text_cuit");
        if(sharedPrefs.getString("text_cuit", "") !=null){
            pref.setSummary(sharedPrefs.getString("text_cuit",""));
            pref.setPersistent(true);
        }
        pref = findPreference("método_de_pago_fav");
        if(sharedPrefs.getString("método_de_pago_fav", "") !=null){
            pref.setSummary(sharedPrefs.getString("método_de_pago_fav",""));
            if(sharedPrefs.getString("método_de_pago_fav","").equals("Efectivo")) findPreference("Moneda_favorita").setEnabled(true);
            pref.setPersistent(true);
        }
        pref = findPreference("Moneda_favorita");
        if(sharedPrefs.getString("Moneda_favorita", "") !=null){
            pref.setSummary(sharedPrefs.getString("Moneda_favorita",""));
            pref.setPersistent(true);
        }
        return;

    }
    private void actuaizarPreferencias(SharedPreferences sharedPreferences, String s){
        if(s.equals("text_mail")){
            Preference pref = findPreference("text_mail");
            pref.setSummary(sharedPreferences.getString(s,""));
            pref.setPersistent(true);
        }
        if(s.equals("text_cuit")){
            Preference pref = findPreference("text_cuit");
            pref.setSummary(sharedPreferences.getString(s,""));
            pref.setPersistent(true);
        }
        if(s.equals("método_de_pago_fav")){
            if(sharedPreferences.getString(s,"").equals("Efectivo")){
                Preference pref = findPreference("Moneda_favorita");
                pref.setEnabled(true);
            }else{
                Preference pref = findPreference("Moneda_favorita");
                pref.setEnabled(false);
                pref.setPersistent(false);
            }
            Preference pref = findPreference("método_de_pago_fav");
            pref.setSummary(sharedPreferences.getString(s,""));
            pref.setPersistent(true);
        }
        if(s.equals("guardado_info")){
            if(!sharedPrefs.getBoolean("guardado_info",false)){
                    getActivity().deleteFile("datos_uso_app");
            }
        }
    }

    @Override
    public boolean onPreferenceStartFragment(PreferenceFragmentCompat caller, Preference pref) {
        if(pref.getKey().equals("fragmento_historial")) {
            NavHostFragment.findNavController(ConfiguracionFragment.this).navigate(R.id.action_configuracionFragment_to_historialBusquedaFragment);
        }
        return false;
    }
}