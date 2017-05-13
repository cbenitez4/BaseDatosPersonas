package com.example.labsoftware1.personasbasededatos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Carlos on 13/05/2017.
 */

public class AdaptadorPersona extends BaseAdapter{
    private Context contexto;
    private ArrayList<Persona> personas;

    public AdaptadorPersona(Context contexto, ArrayList<Persona> personas){
        this.contexto = contexto;
        this.personas = personas;
    }

    @Override
    public int getCount() {
        return personas.size();
    }

    @Override
    public Object getItem(int position) {
        return personas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(personas.get(position).getCedula());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView cajacedula,cajanombre,cajaapellido;
        ImageView foto;
        LayoutInflater inflater;
        View itemview;

        inflater = (LayoutInflater)contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        itemview = inflater.inflate(R.layout.item_personalizado,null);

        cajacedula = (TextView)itemview.findViewById(R.id.txtCedulaP);
        cajanombre = (TextView)itemview.findViewById(R.id.txtNombreP);
        cajaapellido = (TextView)itemview.findViewById(R.id.txtApellidoP);
        foto = (ImageView)itemview.findViewById(R.id.imgFoto);

        foto.setImageResource(Integer.parseInt(personas.get(position).getFoto()));
        cajacedula.setText(personas.get(position).getCedula());
        cajanombre.setText(personas.get(position).getNombre());
        cajaapellido.setText(personas.get(position).getApellido());

        return  itemview;



    }
}
