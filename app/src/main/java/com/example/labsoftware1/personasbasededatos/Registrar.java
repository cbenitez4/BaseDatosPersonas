package com.example.labsoftware1.personasbasededatos;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

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

        cajaCedula = (EditText) findViewById(R.id.txtcedula);
        cajaNombre = (EditText) findViewById(R.id.txtNombre);
        cajaApellido = (EditText) findViewById(R.id.txtApellido);
        rdMasculino = (RadioButton) findViewById(R.id.r1);
        rdFemenino = (RadioButton) findViewById(R.id.r2);
        chkProgramar = (CheckBox) findViewById(R.id.chkProgramar);
        chkLeer = (CheckBox) findViewById(R.id.chkLeer);
        chkBailar = (CheckBox) findViewById(R.id.chkBailar);

    }

    public boolean validartodo() {
        if (cajaCedula.getText().toString().isEmpty()) {
            cajaCedula.setError("Digite cedula");
            cajaCedula.requestFocus();
            return false;
        }
        if (cajaNombre.getText().toString().isEmpty()) {
            cajaNombre.setError("Digite nombre");
            cajaNombre.requestFocus();
            return false;
        }
        if (cajaApellido.getText().toString().isEmpty()) {
            cajaApellido.setError("Digite cedula");
            cajaApellido.requestFocus();
            return false;
        }if ((!chkProgramar.isChecked())&&(!chkLeer.isChecked())&&(!chkBailar.isChecked())){
            new AlertDialog.Builder(this).setMessage("Seleccione por lo menos un pasatiempo").setCancelable(true).show();
            return false;
        }return true;
    }

    public void guardar(View v){
        String foto,cedula,nombre,apellido,sexo,pasatiempo ="";
        Persona p;
        if (validartodo()){
            cedula = cajaCedula.getText().toString();
            foto = String.valueOf(fotoAleatoria());
            nombre = cajaNombre.getText().toString();
            apellido = cajaApellido.getText().toString();

            if (rdMasculino.isChecked()) sexo =getResources().getString(R.string.masculino);
            else sexo =getResources().getString(R.string.femenino);

            if (chkProgramar.isChecked()){
                pasatiempo=getResources().getString(R.string.programar)+",";
            }if (chkLeer.isChecked()){
                pasatiempo=  pasatiempo + getResources().getString(R.string.leer)+",";
            }if (chkBailar.isChecked()){
                pasatiempo= pasatiempo + getResources().getString(R.string.bailar)+",";
            }
            pasatiempo =pasatiempo.substring(0,pasatiempo.length()-1);

            p = new Persona(foto,cedula,nombre,apellido,sexo,pasatiempo);
            p.guardar(getApplicationContext());


            limpiar();
            //Intent intent = new Intent(this, Principal.class);
            //this.startActivity(intent);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Datos");
            builder.setMessage("Persona Guardada Exitosamente!");
            builder.setPositiveButton("OK",null);
            builder.create();
            builder.show();


            //new AlertDialog.Builder(this).setMessage("Persona Guardada Exitosamente!").setCancelable(true).show();
        }
    }


    public int fotoAleatoria(){
        int fotos[] = {R.drawable.images,R.drawable.images2,R.drawable.images3};
        int numero = (int)(Math.random()*3);
        return fotos[numero];
    }

    public void limpiar(){
        cajaCedula.setText("");
        cajaNombre.setText("");
        cajaApellido.setText("");
        rdMasculino.setChecked(true);
        rdFemenino.setChecked(false);
        chkProgramar.setChecked(false);
        chkBailar.setChecked(false);
        chkLeer.setChecked(false);

    }

    public void limpiarboton(View v){
        limpiar();
    }

    public boolean validarCedula(){
        if (cajaCedula.getText().toString().isEmpty()) {
            cajaCedula.setError("Digite cedula");
            cajaCedula.requestFocus();
            return false;}
        return true;
    }

    public void buscar(View v){
        Persona p;
        if (validarCedula()) {
        p = Datos.buscarPersona(getApplicationContext(), cajaCedula.getText().toString());
        if (p!=null){
            cajaNombre.setText(p.getNombre());
            cajaApellido.setText(p.getApellido());
            if (p.getSexo().equalsIgnoreCase(getResources().getString(R.string.masculino)))rdMasculino.setChecked(true);
            else rdFemenino.setChecked(true);
            if (p.getPasatiempo().contains(getResources().getString(R.string.programar)))chkProgramar.setChecked(true);
            if (p.getPasatiempo().contains(getResources().getString(R.string.leer)))chkLeer.setChecked(true);
            if (p.getPasatiempo().contains(getResources().getString(R.string.bailar)))chkBailar.setChecked(true);

            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(cajaCedula.getWindowToken(), 0);
        }else {
            new AlertDialog.Builder(this).setMessage("Cedula no encontrada registrese").setCancelable(true).show();
            cajaNombre.requestFocus();
        }

        }
    }

    public void eliminar(View v){
        final Persona p;
        if (validarCedula()) {
            p = Datos.buscarPersona(getApplicationContext(), cajaCedula.getText().toString());
            if (p!=null){

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setMessage("¿Desea eliminar el dato "+cajaCedula.getText()+"?");
                alertDialog.setTitle("Eliminar Dato");
                alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                alertDialog.setCancelable(false);
                alertDialog.setPositiveButton("Sí", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        p.eliminar(getApplicationContext());
                        limpiar();
                        Toast t=Toast.makeText(getApplicationContext(),"Dato eliminado exitosamente.", Toast.LENGTH_SHORT);
                        t.show();
                    }
                });
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast t=Toast.makeText(getApplicationContext()," El dato no ha sido eliminado.", Toast.LENGTH_SHORT);
                        t.show();
                    }
                });
                alertDialog.show();
            }}}


    public void modificar(View v) {
        Persona p, p2;
        String nombre, apellido, sexo, pasatiempo = "";
        if (validarCedula()) {
            p = Datos.buscarPersona(getApplicationContext(), cajaCedula.getText().toString());
            if (p != null) {
                nombre = cajaNombre.getText().toString();
                apellido = cajaApellido.getText().toString();

                if (rdMasculino.isChecked()) sexo = getResources().getString(R.string.masculino);
                else sexo = getResources().getString(R.string.femenino);

                if (chkProgramar.isChecked()) {
                    pasatiempo = getResources().getString(R.string.programar) + ",";
                }
                if (chkLeer.isChecked()) {
                    pasatiempo = pasatiempo + getResources().getString(R.string.leer) + ",";
                }
                if (chkBailar.isChecked()) {
                    pasatiempo = pasatiempo + getResources().getString(R.string.bailar) + ",";
                }
                pasatiempo = pasatiempo.substring(0, pasatiempo.length() - 1);

                p2 = new Persona(p.getFoto(), p.getCedula(), nombre, apellido, sexo, pasatiempo);
                p2.modificar(getApplicationContext());


                Toast t=Toast.makeText(getApplicationContext()," El dato ha sido modificado.", Toast.LENGTH_SHORT);
                t.show();
                limpiar();


            }
        }


    }


}