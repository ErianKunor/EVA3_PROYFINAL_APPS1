package com.example.bisonapp30;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Agregar extends AppCompatActivity implements View.OnClickListener {

    private EditText edTxtNom, edTxtMat, edTxtDesc, edTxtFecha, edTxtHora;
    private Button btnGuardar, btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        edTxtNom = findViewById(R.id.edTxtNom);
        edTxtMat = findViewById(R.id.edTxtMat);
        edTxtDesc = findViewById(R.id.edTxtDesc);
        edTxtFecha = findViewById(R.id.edTxtFecha);
        edTxtHora = findViewById(R.id.edTxtHora);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnCancelar = findViewById(R.id.btnCancelar);

        Bundle bundle = getIntent().getExtras();
        int dia = 0, mes = 0, ano = 0;
        dia = bundle.getInt("dia");
        mes = bundle.getInt("mes");
        ano = bundle.getInt("ano");
        edTxtFecha.setText(dia + " - " + mes + " - " + ano);

        btnGuardar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == btnGuardar.getId()){
            //Guardar los datos
            //Creamos un objeto de la clase
            BDSQLite bd = new BDSQLite(getApplication(), "BisonApp", null, 1);
            SQLiteDatabase db = bd.getWritableDatabase();

            String sql = "insert into tareas"+
                    "(nomTarea, materia, descripcion, fecha, hora) values('" +
                    edTxtNom.getText() +
                    "','" + edTxtMat.getText() +
                    "','" + edTxtDesc.getText() +
                    "','" + edTxtFecha.getText() +
                    "','" + edTxtHora.getText() + "')";
            try {
                db.execSQL(sql);

                edTxtNom.setText("");
                edTxtMat.setText("");
                edTxtDesc.setText("");
                edTxtFecha.setText("");
                edTxtHora.setText("");

            }catch (Exception e){
                Toast.makeText(getApplication(), "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }else{
            this.finish();
            return;
        }
    }
}
