package com.example.bisonapp30;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Ver extends AppCompatActivity implements AdapterView.OnItemLongClickListener {

    private ListView listVw;
    private ArrayAdapter<String> arrayAdapter;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        listVw = findViewById(R.id.listVw);
        listVw.setOnItemLongClickListener(this);

        Bundle bundle = getIntent().getExtras();
        int dia = 0, mes = 0, ano = 0;
        dia = bundle.getInt("dia");
        mes = bundle.getInt("mes");
        ano = bundle.getInt("ano");
        String cadena = dia + " - " + mes + " - " + ano;

        BDSQLite bd = new BDSQLite(getApplicationContext(), "BisonApp", null, 1);
        db = bd.getReadableDatabase();
        String sql = "select * from tareas where fecha = '" + cadena + "'";
        //Variable de tipo cursor para almacenar los regustros que nos devuelva la consulta
        Cursor c;

        String nombre, materia, descripcion, fecha, hora;
        try {
            c = db.rawQuery(sql, null);
            //Instanciar el array
            arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
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
                listVw.setAdapter(arrayAdapter);
            }else{
                this.finish();
            }
        } catch (Exception e) {
            Toast.makeText(getApplication(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            this.finish();
        }

    }

    private void eliminar(String dato){
        String [] datos = dato.split(", ");
        String sql = "delete from tareas where nomTarea = '" + datos[1] + "' and " +
                "materia = '" + datos[2] + "' and descripcion ='" + datos[3] + "' and fecha = '" +
                datos[4] + "' and hora = '" + datos[5] + "'";

        try {
            arrayAdapter.remove(dato);
            //Cargar el ArrayAdapter al listView
            listVw.setAdapter(arrayAdapter);
            db.execSQL(sql);
            Toast.makeText(getApplication(), "Tarea eliminada", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getApplication(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {
        //Desplegar dialogo con el que se pueda eliminar la tarea
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        CharSequence [] items = new CharSequence[2];
        items [0] = "Eliminar tarea";
        items [1] = "Cancelar";
        builder.setTitle("Eliminar evento").setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                    //Eliminar la tarea

                    //Pasando el dato que seleccionamos
                    eliminar(parent.getItemAtPosition(position).toString());

                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        return false;
    }
}
