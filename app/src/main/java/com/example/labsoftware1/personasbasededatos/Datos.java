package com.example.labsoftware1.personasbasededatos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Carlos on 13/05/2017.
 */

public class Datos {
    public static ArrayList<Persona> traerPersonas(Context contexto){
        ArrayList<Persona> personas = new ArrayList<>();
        SQLiteDatabase db;
        String sql,foto, nombre, cedula, apellido, sexo, pasatiempo;
        Persona p;

        PersonaSQLiteOpenHelper aux = new PersonaSQLiteOpenHelper(contexto,"DBPersonas",null,2);
        db =aux.getReadableDatabase();

        sql="select * from Personas";
        Cursor c = db.rawQuery(sql,null);

        if (c.moveToFirst()){
            do {
                foto = c.getString(0);
                cedula = c.getString(1);
                nombre = c.getString(2);
                apellido = c.getString(3);
                sexo = c.getString(4);
                pasatiempo = c.getString(5);
                p = new Persona(foto,cedula,nombre,apellido,sexo,pasatiempo);
                personas.add(p);

            }while (c.moveToNext());
        }db.close();
        return personas;
    }


    public static Persona buscarPersona(Context contexto, String ced){

        SQLiteDatabase db;
        String sql,foto, nombre, cedula, apellido, sexo, pasatiempo;
        Persona p=null;

        PersonaSQLiteOpenHelper aux = new PersonaSQLiteOpenHelper(contexto,"DBPersonas",null,2);
        db =aux.getReadableDatabase();

        sql="select * from Personas where cedula ='"+ced+"'";
        Cursor c = db.rawQuery(sql,null);

        if (c.moveToFirst()){

                foto = c.getString(0);
                cedula = c.getString(1);
                nombre = c.getString(2);
                apellido = c.getString(3);
                sexo = c.getString(4);
                pasatiempo = c.getString(5);
                p = new Persona(foto,cedula,nombre,apellido,sexo,pasatiempo);


        }db.close();
        return p;
    }



}
