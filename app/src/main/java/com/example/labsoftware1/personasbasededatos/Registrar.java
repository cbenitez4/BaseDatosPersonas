package com.example.labsoftware1.personasbasededatos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

public class Registrar extends AppCompatActivity {
    private EditText cajaCedula;
    private EditText cajaNombre;
    private EditText cajaApellido;
    private RadioButton rdMasculino;
    private RadioButton rdFemenino;
    private CheckBox chkProgramar;
    private CheckBox chkLeer;
    private CheckBox chkBailar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        cajaCedula=(EditText)findViewById(R.id.txtcedula);
        cajaNombre=(EditText)findViewById(R.id.txtNombre);
        cajaApellido=(EditText)findViewById(R.id.txtApellido);
        rdMasculino=(RadioButton) findViewById(R.id.r1);
        
    }
}
