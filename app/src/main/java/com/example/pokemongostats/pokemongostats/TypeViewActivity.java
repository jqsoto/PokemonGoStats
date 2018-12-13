package com.example.pokemongostats.pokemongostats;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class TypeViewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typeview);
        gestorDB = DBManager.getManager(this.getApplicationContext());
        pokemonList = new ArrayList<>();

        this.getExtraContent();

        this.creaLista();

    }

    private void getExtraContent(){
        Log.v("tag", "get Extra COntent");
        ArrayList<Pokemon> allPokemons = gestorDB.getPokemons();

        for ( int i = 0; i < allPokemons.size(); i++ ){
            if(getIntent().hasExtra(String.valueOf(allPokemons.get(i).getNumeroPokedex()))){
                pokemonList.add(allPokemons.get(i));
            }
        }
    }

    private void creaLista()
    {
        layoutManager = new GridLayoutManager(this, 3);
        recyclerPokemons = this.findViewById( R.id.pokemonList );
        recyclerPokemons.setHasFixedSize(true);
        recyclerPokemons.setLayoutManager(layoutManager);

        this.adapterList = new AdaptadorPokemons( this.pokemonList , this);
        recyclerPokemons.setAdapter(adapterList);
    }

    private DBManager gestorDB;
    private AdaptadorPokemons adapterList;
    private ArrayList<Pokemon> pokemonList;
    private RecyclerView recyclerPokemons;
    private RecyclerView.LayoutManager layoutManager;

}
