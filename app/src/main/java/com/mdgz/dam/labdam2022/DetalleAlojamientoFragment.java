package com.mdgz.dam.labdam2022;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.mdgz.dam.labdam2022.databinding.FragmentDetalleAlojamientoBinding;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;


public class DetalleAlojamientoFragment extends Fragment {
    private FragmentDetalleAlojamientoBinding binding ;
    private boolean favorito=false;//logica guardar en favoritos hardcodeada. Los alojamientos no tienen el atributo faorito.
    private LocalDate fechaCheckIn;
    private LocalDate fechaCheckout;
    private Double precioFinal;
    private Double precioBase;
    public DetalleAlojamientoFragment() {
    }

    public static DetalleAlojamientoFragment newInstance(String param1, String param2) {
        DetalleAlojamientoFragment fragment = new DetalleAlojamientoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentDetalleAlojamientoBinding.inflate(inflater, container,  false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(getArguments() != null){
            Bundle bundle = getArguments();
            binding.tituloAlojamiento.setText(bundle.getString("titulo"));
            binding.descripcionAlojamiento.setText(bundle.getString("descripcion"));
            binding.textoCapacidadMax.setText("máx: "+bundle.getInt("capacidad"));
            binding.textoPrecioBase.setText(String.valueOf(bundle.getDouble("precio")));
            precioBase=bundle.getDouble("precio");
            if(bundle.getString("tipoAlojamiento").equals("Departamento")){
                binding.imagenTipoDetalle.setImageResource(R.drawable.ic_baseline_apartment_24);
                binding.textoTipo.setText("Departamento");
                binding.textoUbicacion.setText(bundle.getString("ubicacion"));
                binding.vf.setDisplayedChild(0);
                TextView textoHab = binding.getRoot().findViewById(R.id.cant_habitaciones);
                textoHab.setText("Habitaciones: "+bundle.getInt("cantHabitaciones"));
                TextView wifi =binding.getRoot().findViewById(R.id.wifi);
                if(bundle.getBoolean("wifi"))wifi.setText("Wifi: Incluye");
                else wifi.setText("Wifi: No incluye");
                TextView costoLimp = binding.getRoot().findViewById(R.id.costo_limpieza);
                costoLimp.setText("Costo limpieza: "+bundle.getDouble("costoLimpieza"));

            }
            else{
                binding.imagenTipoDetalle.setImageResource(R.drawable.ic_baseline_hotel_24);
                binding.textoTipo.setText("Habitacion");
                binding.textoUbicacion.setText(bundle.getString("ubicacion"));
                binding.vf.setDisplayedChild(1);
                TextView textoHotel = binding.getRoot().findViewById(R.id.nombre_hotel);
                textoHotel.setText("Hotel: "+bundle.getString("nombreHotel"));
                TextView camasInd = binding.getRoot().findViewById(R.id.camas_individuales);
                camasInd.setText("Camas Individuales: "+ bundle.getInt("cantCamasInd"));
                TextView camasMat = binding.getRoot().findViewById(R.id.camas_matrimoniales);
                camasMat.setText("Camas Matrimoniales: "+ bundle.getInt("cantCamasMat"));
                TextView Estacionamiento =binding.getRoot().findViewById(R.id.estacionamiento);
                if(bundle.getBoolean("incluyeParking"))Estacionamiento.setText("Estacionamiento: Incluye");
                else Estacionamiento.setText("Estacionamiento: No incluye");

            }
        }
        binding.botonFav.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                if(!favorito) {
                    binding.botonFav.setImageResource(R.drawable.ic_baseline_star_24);
                    favorito=true;
                }
                else {
                    binding.botonFav.setImageResource(R.drawable.ic_baseline_star_border_24);
                    favorito=false;
                }

            }
        });
        binding.fechaCheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog selector_fecha = new DatePickerDialog(getContext());
                selector_fecha.show();
               selector_fecha.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                   @Override
                   public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1++;
                       if(LocalDate.of(i,i1,i2).compareTo(LocalDate.now())<0){
                           Toast.makeText(getContext(), "CheckIn debe ser porterior a la fecha actual", Toast.LENGTH_LONG).show();
                       }else{
                           if(fechaCheckout!= null && LocalDate.of(i,i1,i2).compareTo(fechaCheckout)>=0){
                               Toast.makeText(getContext(), "El CheckIn debe ser anterior al CheckOut", Toast.LENGTH_SHORT).show();
                           }
                       else {
                               fechaCheckIn=LocalDate.of(i,i1,i2);
                               binding.fechaCheckin.setText(i2 + "/" + i1 + "/" + i);
                           }
                       }


                   }
               });
            }
        });
        binding.fechaCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fechaCheckIn==null){
                    Toast.makeText(getContext(), "Seleccione fecha de CheckIn", Toast.LENGTH_LONG).show();
                }
                else {
                    DatePickerDialog selector_fecha = new DatePickerDialog(getContext());
                    selector_fecha.show();
                    selector_fecha.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                            i1++;

                            if(LocalDate.of(i,i1,i2).compareTo(fechaCheckIn)<=0){
                                Toast.makeText(getContext(), "La fecha Checkout debe ser posterior al CheckIn", Toast.LENGTH_LONG).show();
                            }
                            else {
                                fechaCheckout=LocalDate.of(i,i1,i2);
                                binding.fechaCheckout.setText(i2 + "/" + i1 + "/" + i);
                            }

                        }
                    });
                }
            }
        });
        binding.fechaCheckin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
            calcularPrecioFinal();
            activarBotonReservar();
            }
        });
        binding.fechaCheckout.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
            calcularPrecioFinal();
            activarBotonReservar();

            }
        });
        binding.huespedes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
            activarBotonReservar();
            }
        });
        binding.botonReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //aqúi iria la la parte de guardar en mís reservas.
                Toast.makeText(getContext(), "Se ha guardado la Reserva en Mis Reservas", Toast.LENGTH_LONG).show();
                Navigation.findNavController(view).navigate(R.id.action_detalleAlojamientoFragment_to_busquedaFragment);
            }
        });
    }
    private void calcularPrecioFinal(){
        if(fechaCheckIn!=null && fechaCheckout!= null){
            precioFinal= precioBase* ((double) DAYS.between(fechaCheckIn,fechaCheckout));
            binding.precioFinal.setText("Precio Final: $"+precioFinal);
        }
    }
    private void activarBotonReservar(){
        if(!binding.fechaCheckout.getText().toString().isEmpty() && !binding.fechaCheckin.getText().toString().isEmpty() && !binding.huespedes.getText().toString().isEmpty()){
            binding.botonReservar.setEnabled(true);
        }
        else {
            binding.botonReservar.setEnabled(false);
        }
    }

}