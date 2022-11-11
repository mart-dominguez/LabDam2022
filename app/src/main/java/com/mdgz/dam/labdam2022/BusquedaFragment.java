package com.mdgz.dam.labdam2022;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.google.android.material.slider.RangeSlider;
import com.mdgz.dam.labdam2022.databinding.FragmentBusquedaBinding;
import com.mdgz.dam.labdam2022.utilities.Utilities;
import com.mdgz.dam.labdam2022.viewmodels.LogViewModel;

public class BusquedaFragment extends Fragment {

    private FragmentBusquedaBinding binding;
    private SharedPreferences pref;
    private LogViewModel logViewModel;
    private LayoutInflater inflater;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentBusquedaBinding.inflate(getLayoutInflater());
        this.inflater = inflater;
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view,savedInstanceState);

        binding.bHotelCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                binding.bWifiSwitch.setEnabled(b);
            }
        });

        binding.bPrecioSlider.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser)
            {
                Float[] valores = binding.bPrecioSlider.getValues().toArray(new Float[0]);
                binding.bMinimoTitle.setText("Mínimo: " + Utilities.round2(valores[0]));
                binding.bMaximoTitle.setText("Máximo: " + Utilities.round2(valores[1]));
            }
        });

        binding.bOcupantesEdit.addTextChangedListener(new Utilities.TextWatcherExtender() {
            @Override
            public void afterTextChanged(Editable editable) {

                if(binding.bOcupantesEdit.getText().length() == 0 || Utilities.editableToInteger(binding.bOcupantesEdit.getText()) < 1)
                    binding.bOcupantesEdit.setText("1");
            }
        });


        binding.bLimpiarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiar();
            }
        });

        binding.bBuscarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscar(view);
            }
        });

        binding.bAgregarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int valor = Utilities.editableToInteger(binding.bOcupantesEdit.getText()) + 1;
                binding.bOcupantesEdit.setText("" + valor);

            }
        });

        binding.bRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int valor = Utilities.editableToInteger(binding.bOcupantesEdit.getText()) - 1;
                binding.bOcupantesEdit.setText("" + valor);

            }
        });

        pref = getContext().getSharedPreferences("com.mdgz.dam.labdam2022_preferences",0);
        logViewModel = new ViewModelProvider(getActivity()).get(LogViewModel.class);

    }

    protected void buscar(View v)
    {

        //Preguntar si la preferencia esta activada
        if(pref.getBoolean("check_uso_app",false))
        {
            logViewModel.setGuardado(true);
            LogViewModel.SearchLog log = logViewModel.getLog();

            //Guardar todos los datos en el view model
            log.setCant_ocupantes(Utilities.editableToInteger(binding.bOcupantesEdit.getText()));
            log.setCiudad(binding.bCiudadSpinner.getSelectedItem().toString());
            log.setDpto(binding.bDepartamentoCheck.isChecked());
            log.setHotel(binding.bHotelCheck.isChecked());
            log.setWifi(binding.bWifiSwitch.isChecked());
            Float[] valores = binding.bPrecioSlider.getValues().toArray(new Float[0]);
            log.setVal_max(valores[1]);
            log.setVal_min(valores[0]);
            log.setTimestamp(Utilities.nowArgentina().toString());

        } else logViewModel.setGuardado(false);

        NavDirections action = BusquedaFragmentDirections.actionBusquedaFragmentToResultadoBusquedaFragment();
        Navigation.findNavController(v).navigate(action);
    }

    //Listener del boton limpiar
    protected void limpiar()
    {

        int[] valores = getResources().getIntArray(R.array.initial_slider_values);
        binding.bPrecioSlider.setValues((float)valores[0], (float)valores[1]);
        binding.bDepartamentoCheck.setChecked(false);
        binding.bHotelCheck.setChecked(false);
        binding.bOcupantesEdit.setText("1");
        binding.bCiudadSpinner.setSelection(0);
        binding.bWifiSwitch.setChecked(false);


    }

}