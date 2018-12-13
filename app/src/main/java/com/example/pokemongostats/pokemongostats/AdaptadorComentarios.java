package com.example.pokemongostats.pokemongostats;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;



public class AdaptadorComentarios extends ArrayAdapter<Comment> {

    private Context context;
    private ArrayList<Comment> comments;
    private DBManager db;

    public AdaptadorComentarios(ArrayList<Comment> comments, Context context, DBManager db) {
        super(context, 0, comments);
        this.comments = comments;
        this.context = context;
        this.db = db;

    }

    @Override
    public View getView(final int position, View view, ViewGroup parent)
    {
        final Context CONTEXT = this.getContext();
        final LayoutInflater INFLATER = LayoutInflater.from( CONTEXT );

        // Crear la vista si no existe
        if ( view == null ) {
            view = INFLATER.inflate( R.layout.listview_comments, null );
        }
        // Rellenar los datos
        final TextView lblNombre = view.findViewById( R.id.comment );

        lblNombre.setText( comments.get(position).getComment() );



        return view;
    }



}


