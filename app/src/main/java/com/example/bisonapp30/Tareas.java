package com.example.bisonapp30;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tareas extends Fragment implements AdapterView.OnItemLongClickListener {

    ListView listVwTareas;
    private ArrayAdapter<String> arrayAdapter;
    private SQLiteDatabase db;

    public Tareas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_tareas, container, false);
        listVwTareas = view.findViewById(R.id.listVwTareas);
        listVwTareas.setOnItemLongClickListener(this);
        Bundle bundle = getActivity().getIntent().getExtras();
        int dia = 0, mes = 0, ano = 0;

        if(dia != 0 && mes != 0 && ano != 0){
        dia = bundle.getInt("dia");
        mes = bundle.getInt("mes");
        ano = bundle.getInt("ano");
        String cadena = dia + " - " + mes + " - " + ano;
        }

        BDSQLite bd = new BDSQLite(getActivity(), "BisonApp", null, 1);
        db = bd.getReadableDatabase();
        String sql = "select * from tareas";
        //Variable de tipo cursor para almacenar los regustros que nos devuelva la consulta
        Cursor c;

        String nombre, materia, descripcion, fecha, hora;
        try {
            c = db.rawQuery(sql, null);
            //Instanciar el array
            arrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1);
            //Comparamos que haya datos para leer
            if(c.moveToNext()){
                do{
                    nombre = c.getString(1);
                    materia = c.getString(2);
                    descripcion = c.getString(3);
                    fecha = c.getString(4);
                    hora = c.getString(5);
                    arrayAdapter.add(nombre + ", " + materia + ", " + descripcion + ", " + fecha + ", " + hora);
                }while(c.moveToNext());
                listVwTareas.setAdapter(arrayAdapter);
            }else{
                getActivity().finish();
                //this.finish();
            }
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            getActivity().finish();
            //this.finish();
        }
        return listVwTareas;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }
}
