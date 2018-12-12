package com.example.pokemongostats.pokemongostats;

import android.content.Context;
import android.widget.ArrayAdapter;
import java.util.ArrayList;



public class AdaptadorComentarios extends ArrayAdapter<Comment> {

    Context context;
    ArrayList<Comment> comments;

    public AdaptadorComentarios(ArrayList<Comment> comments, Context context) {
        super(context, 0, comments);
        this.comments = comments;
        this.context = context;
    }



}


