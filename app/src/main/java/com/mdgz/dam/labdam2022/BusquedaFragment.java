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

import com.google.android.material.slider.RangeSlider;
import com.mdgz.dam.labdam2022.databinding.FragmentBusquedaBinding;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BusquedaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BusquedaFragment extends Fragment {

    private FragmentBusquedaBinding binding;

    Button btnAdd, btnRemove, btnLimpiar, btnBuscar;
    EditText editNumber;
    CheckBox checkWifi, checkDpto, checkHotel;
    RangeSlider slider;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BusquedaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BusquedaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BusquedaFragment newInstance(String param1, String param2) {
        BusquedaFragment fragment = new BusquedaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBusquedaBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        btnAdd = binding.btnAdd;
        btnRemove = binding.btnRemove;
        btnLimpiar = binding.btnLimpiar;
        btnBuscar = binding.btnBuscar;
        editNumber = binding.editNumber;
        checkWifi = binding.checkWifi;
        checkDpto = binding.checkDpto;
        checkHotel = binding.checkHotel;
        slider = binding.rangeSlider;

        handleCantPersonas();

        handleCheckBoxs();

        botonBuscar();

        botonLimpiar();

    }

    //Manejo del "widget" para seleccionar la cantidad de personas
    protected void handleCantPersonas(){

        //BOTON ADD
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numero = editNumber.getText().length()>0 ? Integer.parseInt(editNumber.getText().toString()) : 0;
                if(editNumber.hasFocus()) editNumber.clearFocus();
                if(numero >= 0 && numero < 30) numero+=1;
                editNumber.setText(String.valueOf(numero));
            }
        });
        //BOTON REMOVE
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numero = editNumber.getText().length()>0 ? Integer.parseInt(editNumber.getText().toString()) : 1;
                if(editNumber.hasFocus()) editNumber.clearFocus();
                if(numero > 1 && numero <= 30) numero-=1;
                editNumber.setText(String.valueOf(numero));

            }
        });
        //EDITTEXT NUMERO DE PERSONAS
        editNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                int numero = editNumber.getText().length()>0 ? Integer.parseInt(String.valueOf(s)) : -1;

                if(editNumber.hasFocus()){
                    if(numero == 0 || numero > 30){
                        editNumber.setText(String.valueOf(1));
                    }

                }

            }
        });

    }

    //Manejo de la aparicion del check wifi cuando se selecciona chech departamento
    protected void handleCheckBoxs(){

        checkWifi.setVisibility(View.GONE);
        //Si se habilita el check de dpto, aparece el de wifi
        checkDpto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) checkWifi.setVisibility(View.VISIBLE);
                else checkWifi.setVisibility(View.GONE);
            }
        });

    }

    //Listener del boton buscar
    protected void botonBuscar(){

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action = BusquedaFragmentDirections.actionBusquedaFragmentToResultadoBusquedaFragment();
                Navigation.findNavController(v).navigate(action);
            }
        });

    }

    //Listener del boton limpiar
    protected void botonLimpiar(){

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Numero de personas
                editNumber.setText("1");
                //Slider
                int[] valores = getResources().getIntArray(R.array.initial_slider_values);
                slider.setValues((float)valores[0], (float)valores[1]);
                //Checks deshabilitados
                checkDpto.setChecked(false);
                checkHotel.setChecked(false);
                checkWifi.setChecked(false);
                //Spinner
                //...
            }
        });

    }

}