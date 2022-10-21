package com.mdgz.dam.labdam2022;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.mdgz.dam.labdam2022.databinding.FragmentDetalleAlojamientoBinding;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.repo.AlojamientoRepository;

import java.time.Duration;
import java.time.temporal.Temporal;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleAlojamientoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleAlojamientoFragment extends Fragment {
    Button btnInicio;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetalleAlojamientoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetalleAlojamientoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetalleAlojamientoFragment newInstance(String param1, String param2) {
        DetalleAlojamientoFragment fragment = new DetalleAlojamientoFragment();
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
        FragmentDetalleAlojamientoBinding binding;
        binding= FragmentDetalleAlojamientoBinding.inflate(inflater, container,false);
        View view = binding.getRoot();

        Bundle bundle = this.getArguments();
        if(bundle!= null){
            //AlojamientoRepository alojamiento = new AlojamientoRepository();
            //int id = bundle.getInt("id");
            binding.txtNombre.setText(bundle.getString("nombre"));
            binding.txtDescripcion.setText(bundle.getString("descripcion"));
            binding.txtCapacidad.setText(bundle.getString("capacidad"));
            Double precioFinal = bundle.getDouble("precio base");
            binding.txtPrecio.setText("$"+precioFinal.toString());
        }

        binding.btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 binding.txtFinEstadia.setText("hate");
                MisFavoritosFragment misFavoritosFragment = new MisFavoritosFragment();
                misFavoritosFragment.setArguments(bundle);

            }
        });
        binding.btnInicioestadia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view ){
                final Calendar calendario = Calendar.getInstance();
                int yy = calendario.get(Calendar.YEAR);
                int mm = calendario.get(Calendar.MONTH);
                int dd = calendario.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {

                        binding.txtInicioEstadia.setText( day+"/"+month+"/"+year);


                    }
                }, yy, mm, dd);
                datePicker.show();
            }
            });
        binding.btnFinestadia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view ){
                final Calendar calendario = Calendar.getInstance();
                int yy = calendario.get(Calendar.YEAR);
                int mm = calendario.get(Calendar.MONTH);
                int dd = calendario.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {

                        binding.txtFinEstadia.setText( day+"/"+month+"/"+year);


                    }
                }, yy, mm, dd);
                datePicker.show();
            }
        });



        return view;
    }
    private class InputValuesTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }


}