package com.mdgz.dam.labdam2022;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.google.android.material.slider.RangeSlider;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.mdgz.dam.labdam2022.databinding.FragmentBusquedaBinding;
import com.mdgz.dam.labdam2022.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;

public class BusquedaFragment extends Fragment {

    private FragmentBusquedaBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentBusquedaBinding.inflate(getLayoutInflater());
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
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


    }

    protected void buscar(View v)
    {
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