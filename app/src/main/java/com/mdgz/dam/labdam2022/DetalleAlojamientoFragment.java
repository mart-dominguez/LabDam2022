package com.mdgz.dam.labdam2022;

import static java.time.temporal.ChronoUnit.DAYS;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.mdgz.dam.labdam2022.databinding.FragmentDetalleAlojamientoBinding;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass. Use the {@link DetalleAlojamientoFragment#newInstance}
 * factory method to create an instance of this fragment.
 */
public class DetalleAlojamientoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    FragmentDetalleAlojamientoBinding binding;
    Double precioFinal;
    LocalDate fechaInicio = null;
    LocalDate fechaFin = null;
    long cantidadDeDias = -1;
    double precioBase = -1;
//    Calendar finicio = Calendar.getInstance();
//    Calendar ffin = Calendar.getInstance();

    public DetalleAlojamientoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of this fragment using the provided
     * parameters.
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
        binding = FragmentDetalleAlojamientoBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            //AlojamientoRepository alojamiento = new AlojamientoRepository();
            //int id = bundle.getInt("id");
            binding.txtNombre.setText(bundle.getString("nombre"));
            binding.txtDescripcion.setText(bundle.getString("descripcion"));
            binding.txtCapacidad.setText(bundle.getString("capacidad"));
            precioFinal = bundle.getDouble("precio base");
            binding.txtPrecio.setText("$" + precioFinal.toString());
            precioBase = bundle.getDouble("precio base");


//            binding.txtFinEstadia.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//                }
//
//                @Override
//                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
////                    //long daysDiff = ffin.getTime().getTime() - finicio.getTime().getTime();
////                    setearCantidadDeDias();
////                    Log.e("Dias", "cantidadedias: " + cantidadDeDias);
////                    precioFinal = bundle.getDouble("precio base") * cantidadDeDias;
////                    binding.txtPrecio.setText("$" + precioFinal.toString());
//                }
//
//                @Override
//                public void afterTextChanged(Editable editable) {
////long daysDiff = ffin.getTime().getTime() - finicio.getTime().getTime();
////                    setearCantidadDeDias();
////                    Log.e("Dias", "cantidadedias: " + cantidadDeDias);
////                    precioFinal = bundle.getDouble("precio base") * cantidadDeDias;
////                    binding.txtPrecio.setText("$" + precioFinal.toString());
//                }
//            });
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
            public void onClick(View view) {
                final Calendar calendario = Calendar.getInstance();
                int yy = calendario.get(Calendar.YEAR);
                int mm = calendario.get(Calendar.MONTH);
                int dd = calendario.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        int month2 = month + 1;
                        binding.txtInicioEstadia.setText(day + "/" + month2 + "/" + year);
                        fechaInicio = LocalDate.of(year, month2, day);
//                        finicio.set(year,month,day);
                        setearCantidadDeDias();
                    }
                }, yy, mm, dd);
                datePicker.show();
            }
        });
        binding.btnFinestadia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendario = Calendar.getInstance();
                int yy = calendario.get(Calendar.YEAR);
                int mm = calendario.get(Calendar.MONTH);
                int dd = calendario.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        int month2 = month + 1;
                        binding.txtFinEstadia.setText(day + "/" + month2 + "/" + year);
//                        ffin.set(year,month,day);
                        fechaFin = LocalDate.of(year, month2, day);
                        setearCantidadDeDias();
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

    private void calcularDias() {
        if (fechaFin != null && fechaInicio != null) {
            cantidadDeDias = DAYS.between(fechaInicio, fechaFin);
        }else{
            cantidadDeDias = -1;
        }
    }

    private void setearCantidadDeDias() {
        calcularDias();
        binding.cantidadDeDias.setText("Cantidad de dias: " + cantidadDeDias);
        if(cantidadDeDias != -1 && precioBase != -1){
            binding.txtPrecio.setText("Precio: " + cantidadDeDias * precioBase);
        }
    }

}