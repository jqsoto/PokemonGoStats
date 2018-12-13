package com.example.pokemongostats.pokemongostats;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CommentsActivity extends AppCompatActivity {

    private ListView listaComments;
    private ArrayList<Comment> comentarios;
    private AdaptadorComentarios adaptadorComentarios;
    private int pokemonNumber;
    private DBManager gestorDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        gestorDB = DBManager.getManager(this.getApplicationContext());

        this.getContent();

        this.cargarComentarios(gestorDB);

        listaComments.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                boolean toret;
                if( i >= 0 ){
                    Toast.makeText(getApplicationContext(), getString(R.string.deleted_comment), Toast.LENGTH_LONG).show();
                    TextView lblcomment = view.findViewById(R.id.comment);
                    String commentString = lblcomment.getText().toString();
                    Comment comment = new Comment(comentarios.get(i).getPokemonNumber(), commentString);
                    adaptadorComentarios.remove(comment);
                    toret = true;
                }
                else
                    toret = false;

                return toret;
            }
        });
    }


    private void getContent(){
        if(getIntent().hasExtra("pokemonNumber")){
            pokemonNumber = getIntent().getIntExtra("pokemonNumber", 0);
        }
        else
            pokemonNumber = 0;
    }

    private void cargarComentarios(DBManager gestorDB)
    {
        listaComments = findViewById(R.id.commentsView);
        if(pokemonNumber != 0){
            comentarios = gestorDB.getComments(String.valueOf(this.pokemonNumber));
            adaptadorComentarios = new AdaptadorComentarios( comentarios , this, gestorDB);
            listaComments.setAdapter(adaptadorComentarios);
        }else{
            throw new RuntimeException();
        }
    }

}
