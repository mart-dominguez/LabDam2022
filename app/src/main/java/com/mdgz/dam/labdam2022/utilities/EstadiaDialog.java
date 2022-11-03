package com.mdgz.dam.labdam2022.utilities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.textfield.TextInputEditText;
import com.mdgz.dam.labdam2022.R;
import com.mdgz.dam.labdam2022.databinding.AlertDialogEstadiaBinding;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.persistencia.ReservaDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.ReservaRoomDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.bd.BaseDeDatos;


import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/* Devuelve un AlertDialog personalizado para las estadias */
public class EstadiaDialog implements ReservaDataSource.GuardarReservaCallback{

    EstadiaDialog estadiaDialog;
    public EstadiaDialog(){
        estadiaDialog = this;
    }

    public AlertDialog create(Activity activity, LayoutInflater inflater, Context context, Alojamiento alojamiento)
    {
        //Setting up inicial
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Nueva reserva");
        builder.setMessage("Complete los siguientes campos");
        builder.setIcon(R.drawable.ic_round_edit_calendar_24);

        AlertDialogEstadiaBinding binding = AlertDialogEstadiaBinding.inflate(inflater);
        builder.setView(binding.getRoot());
        binding.adePrecioValue.setText("$" + alojamiento.getPrecioBase());
        binding.adeReservaCaption.setText("Usted desea realizar una reserva en: " +
                alojamiento.getTitulo() + ". Compruebe sus datos antes de continuar.");

        builder.setPositiveButton("Reservar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id)
            {
                Reserva reserva;

                BaseDeDatos bd = BaseDeDatos.getInstance(context);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                LocalDate desde = LocalDate.parse(binding.adeFechaInicioInput.getText().toString(),formatter);
                LocalDate hasta = LocalDate.parse(binding.adeFechaFinInput.getText().toString(),formatter);

                if(alojamiento.getClass() == Departamento.class){
                    reserva = new Reserva(UUID.randomUUID(),
                            null,
                            alojamiento.getId(),
                            UUID.randomUUID(),
                            Date.from(desde.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                            Date.from(hasta.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
                }else{
                    reserva = new Reserva(UUID.randomUUID(),
                            alojamiento.getId(),
                            null,
                            UUID.randomUUID(),
                            Date.from(desde.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                            Date.from(hasta.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
                }

                ReservaRoomDataSource reservaRoomDataSource = new ReservaRoomDataSource(context);
                reservaRoomDataSource.guardarReserva(reserva, estadiaDialog);

            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id)
            {
                // ¿Sucederá algo en especial al cancelar?
            }
        });



        //Manejo del layout
        binding.adeFechaInicioInput.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                LocalDateTime ahora = Utilities.nowArgentina();
                //Establece desde que fecha se mostrará el calendario
                int year = ahora.getYear();
                int month = ahora.getMonthValue();
                int day = ahora.getDayOfMonth();

                //Lanzar el calendario y obtener la fecha seleccionada
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2)         //i : anio, i1: mes, i2: dia
                    {
                        binding.adeFechaInicioInput.setText(i2 + "/" + i1 + "/" + i);
                        if(binding.adeFechaFinInput.getText().length() > 0)
                        {
                            double precio = getPrecio(binding.adeFechaInicioInput,binding.adeFechaFinInput, alojamiento.getPrecioBase());
                            if(precio == alojamiento.getPrecioBase()) binding.adeFechaFinInput.setText(i2 + "/" + i1 + "/" + i);
                            binding.adePrecioValue.setText("$" + Utilities.round2(precio));
                        }
                    }
                }, year,month-1,day);
                datePickerDialog.show();
            }

        });

        //Lo mismo
        binding.adeFechaFinInput.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                LocalDateTime ahora = Utilities.nowArgentina();
                int year = ahora.getYear();
                int month = ahora.getMonthValue();
                int day = ahora.getDayOfMonth();

                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2)
                    {
                        binding.adeFechaFinInput.setText(i2 + "/" + i1 + "/" + i);
                        if(binding.adeFechaInicioInput.getText().length() > 0)
                        {
                            double precio = getPrecio(binding.adeFechaInicioInput,binding.adeFechaFinInput, alojamiento.getPrecioBase());
                            if(precio == alojamiento.getPrecioBase()) binding.adeFechaInicioInput.setText(i2 + "/" + i1 + "/" + i);
                            binding.adePrecioValue.setText("$" + Utilities.round2(precio));
                        }
                    }
                }, year,month-1,day);
                datePickerDialog.show();
            }

        });

        //Manejo de numero de ocupantes
        binding.adeOcupantesEdit.addTextChangedListener(new Utilities.TextWatcherExtender() {
            @Override
            public void afterTextChanged(Editable editable) {
                if(binding.adeOcupantesEdit.getText().length() > 0) if(Utilities.editableToInteger(binding.adeOcupantesEdit.getText()) > alojamiento.getCapacidad())
                    binding.adeOcupantesEdit.setText(alojamiento.getCapacidad().toString());
            }
        });

        return builder.create();
    }

    private static double getPrecio(TextInputEditText fechaDesde, TextInputEditText fechaHasta, double precioBase)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate desde = LocalDate.parse(fechaDesde.getText().toString(),formatter);
        LocalDate hasta = LocalDate.parse(fechaHasta.getText().toString(),formatter);
        long dias = ChronoUnit.DAYS.between(desde, hasta);
        double precio = dias > 0 ? precioBase * dias : precioBase;
        return precio;
    }

    @Override
    public void resultado(boolean exito) {
        //Mostrar que la reserva se logro con exito o no (Poner algun alert dialog)
        if(exito)Log.i("RESERVA!","EXITOSA");
        else Log.i("RESERVA!","NO EXITOSA");
    }
}
