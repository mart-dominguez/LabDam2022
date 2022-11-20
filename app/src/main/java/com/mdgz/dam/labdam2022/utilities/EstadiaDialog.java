package com.mdgz.dam.labdam2022.utilities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.textfield.TextInputEditText;
import com.mdgz.dam.labdam2022.R;
import com.mdgz.dam.labdam2022.databinding.AlertDialogEstadiaBinding;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.model.Usuario;
import com.mdgz.dam.labdam2022.persistencia.interfaces.ReservaDataSource;
import com.mdgz.dam.labdam2022.repositorios.ReservaRepository;
import com.mdgz.dam.labdam2022.repositorios.UsuarioRepository;


import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

/* Devuelve un AlertDialog personalizado para las estadias */
public final class EstadiaDialog
{

    private EstadiaDialog(){};
    private static UsuarioRepository usuarioRepository;
    private static ReservaRepository reservaRepository;


    public static AlertDialog create(Activity activity, LayoutInflater inflater, Context context, Alojamiento alojamiento)
    {
        //Setting up inicial
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Nueva reserva");
        builder.setMessage("Complete los siguientes campos");
        builder.setIcon(R.drawable.ic_round_edit_calendar_24);
        builder.setPositiveButton("Reservar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id)
            {
                //La funcion se sobreescribe al final con onShow()
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id)
            {
                // ¿Sucederá algo en especial al cancelar?
            }
        });

        //Layout
        AlertDialogEstadiaBinding binding = AlertDialogEstadiaBinding.inflate(inflater);
        binding.adePrecioValue.setText("$" + alojamiento.getPrecioBase());
        StringBuilder reservaCaption = new StringBuilder();
        reservaCaption.append("Usted desea realizar una reserva en: ").append(alojamiento.getTitulo()).append(". Compruebe sus datos antes de continuar.");
        binding.adeReservaCaption.setText(reservaCaption.toString());
        binding.adeOcupantesEdit.clearFocus();
        binding.getRoot().requestFocus();


        //Manejo del layout
        binding.adeFechaInicioInput.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                LocalDateTime ahora = Utilities.nowArgentina();
                //Establece desde que fecha se mostrará el calendario
                int year = ahora.getYear();
                int month = ahora.getMonthValue() - 1;
                int day = ahora.getDayOfMonth();

                //Lanzar el calendario y obtener la fecha seleccionada
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2)         //i : anio, i1: mes, i2: dia
                    {
                        i1 += 1;
                        binding.adeFechaInicioInput.setText(i2 + "/" + i1 + "/" + i);
                        if(binding.adeFechaFinInput.getText().length() > 0)
                        {
                            double precio = getPrecio(binding.adeFechaInicioInput,binding.adeFechaFinInput, alojamiento.getPrecioBase());
                            if(precio == alojamiento.getPrecioBase()) binding.adeFechaFinInput.setText(i2 + "/" + i1 + "/" + i);
                            binding.adePrecioValue.setText("$" + Utilities.round2(precio));
                        }
                    }
                }, year,month,day);
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
                int month = ahora.getMonthValue() - 1;
                int day = ahora.getDayOfMonth();

                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2)
                    {
                        i1 += 1;
                        binding.adeFechaFinInput.setText(i2 + "/" + i1 + "/" + i);
                        if(binding.adeFechaInicioInput.getText().length() > 0)
                        {
                            double precio = getPrecio(binding.adeFechaInicioInput,binding.adeFechaFinInput, alojamiento.getPrecioBase());
                            if(precio == alojamiento.getPrecioBase()) binding.adeFechaInicioInput.setText(i2 + "/" + i1 + "/" + i);
                            binding.adePrecioValue.setText("$" + Utilities.round2(precio));
                        }
                    }
                }, year,month,day);
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

        builder.setView(binding.getRoot());

        //Se sobreescribe el metodo onShow que evita que el AlertDialog desaparezca siempre que se presiona el boton reserva
        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener()
        {
            @Override
            public void onShow(DialogInterface dialogInterface)
            {
                Button reservar = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                reservar.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        boolean valido = false;

                        if(binding.adeFechaInicioInput.getText().length() == 0)
                            binding.adeFechaInicioInput.setError("Sin selección");
                        else if(binding.adeFechaFinInput.getText().length() == 0)
                            binding.adeFechaFinInput.setError("Sin selección");
                        else if(binding.adeOcupantesEdit.getText().length() == 0)
                            binding.adeOcupantesEdit.setError("Sin selección");
                        else valido = true;

                        if(valido)
                        {
                            if(usuarioRepository == null) usuarioRepository = UsuarioRepository.getInstance();
                            if(reservaRepository == null) reservaRepository = ReservaRepository.getInstance();
                            Usuario[] usuario = new Usuario[1];
                            usuarioRepository.getTodos((exito, resultados) -> {usuario[0] = resultados.get(0);});

                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                            LocalDate desde = LocalDate.parse(binding.adeFechaInicioInput.getText().toString(),formatter);
                            LocalDate hasta = LocalDate.parse(binding.adeFechaFinInput.getText().toString(),formatter);

                            Reserva reserva = new Reserva(
                                    UUID.randomUUID(),
                                    usuario[0],
                                    Date.from(desde.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                    Date.from(hasta.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                    alojamiento
                            );

                            reservaRepository.guardar(reserva, exito ->
                            {
                                EstadiaDialogConfirm.create(activity,exito).show(); // aca no hay manera de hacer que no se muestre dos veces
                            });
                            dialog.dismiss(); //Para que el AlertDialog desaparezca cuando los datos son validos

                        }

                    }
                });
            }
        });

        return dialog;
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

    private static class EstadiaDialogConfirm
    {
        public static AlertDialog create(Activity activity, boolean resultado)
        {
            //Setting up inicial
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            if(resultado)
            {
                builder.setTitle("Confirmación");
                builder.setMessage("La reserva ha sido completada!");
                builder.setIcon(R.drawable.ic_baseline_check_24);
            }
            else
            {
                builder.setTitle("Error");
                builder.setMessage("Ha ocurrido un error y la acción no se pudo completar");
                builder.setIcon(R.drawable.ic_baseline_close_24);
            }

            builder.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //Continuar
                }
            });

            return builder.create();
        }
    }
}
