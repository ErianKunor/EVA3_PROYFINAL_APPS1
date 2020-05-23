package com.example.bisonapp30;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Calendario extends Fragment implements CalendarView.OnDateChangeListener {
    private CalendarView CalVw;

    public Calendario() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendario, container, false);
        CalVw = view.findViewById(R.id.CalVw);
        CalVw.setOnDateChangeListener(this);
        return CalVw;
    }
    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext()); //this
        CharSequence [] items = new CharSequence[3];
        items[0] = "Agregar Tarea";
        items[1] = "Ver Tareas";
        items[2] = "Cancelar";

        final int dia = dayOfMonth, mes = month + 1, ano = year;

        builder.setTitle("Seleccionar una opción:").setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                    //Actividad agregar tareas
                    Intent intent = new Intent(getActivity(), Agregar.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("dia", dia);
                    bundle.putInt("mes", mes);
                    bundle.putInt("ano", ano);
                    //Agregamos el objeto bundle al intento
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else if(which == 1){
                    //Ver tareas
                    Intent intent = new Intent(getActivity(), Ver.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("dia", dia);
                    bundle.putInt("mes", mes);
                    bundle.putInt("ano", ano);
                    //Agregamos el objeto bundle al intento
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else{
                    return;
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
