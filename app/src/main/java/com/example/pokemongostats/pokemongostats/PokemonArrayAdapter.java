package com.example.pokemongostats.pokemongostats;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PokemonArrayAdapter extends ArrayAdapter {


    public PokemonArrayAdapter(@NonNull Context context, ArrayList<Pokemon> pokemonList) {
        super(context, 0, pokemonList);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        final Context CONTEXT = this.getContext();
        final LayoutInflater INFLATER = LayoutInflater.from( CONTEXT );
        final Pokemon POKEMON = (Pokemon) this.getItem( position );

        // Crear la vista si no existe
        if ( view == null ) {
            view = INFLATER.inflate( R.layout.listview_pokemon, null );
        }

        // Rellenar los datos
        final TextView lblPokemonNumber = view.findViewById( R.id.pokemonNumber );
        final TextView lblPokemonName = view.findViewById( R.id.pokemonName );

        lblPokemonNumber.setText( Integer.toString(POKEMON.getNumeroPokedex()) );
        lblPokemonName.setText( POKEMON.getNombre() );

        this.getTypeImg(view, POKEMON);


        return view;
    }

    private void getTypeImg(View view, Pokemon POKEMON){
        ImageView pokeType1 = view.findViewById( R.id.pokeType1 );
        ImageView pokeType2 = view.findViewById( R.id.pokeType2 );

        switch (POKEMON.getTipo_1()){
            case "dragon":
                pokeType1.setImageResource(R.drawable.type_dragon);
                break;
            case "grass":
                pokeType1.setImageResource(R.drawable.type_grass);
                break;
            case "steel":
                pokeType1.setImageResource(R.drawable.type_steel);
                break;
            case "water":
                pokeType1.setImageResource(R.drawable.type_water);
                break;
            case "fire":
                pokeType1.setImageResource(R.drawable.type_fire);
                break;
            case "ice":
                pokeType1.setImageResource(R.drawable.type_ice);
                break;
            case "dark":
                pokeType1.setImageResource(R.drawable.type_steel);
                break;
            case "electric":
                pokeType1.setImageResource(R.drawable.type_electric);
                break;
            case "bug":
                pokeType1.setImageResource(R.drawable.type_bug);
                break;
            case "fairy":
                pokeType1.setImageResource(R.drawable.type_fairy);
                break;
            case "flying":
                pokeType1.setImageResource(R.drawable.type_flying);
                break;
            case "ghost":
                pokeType1.setImageResource(R.drawable.type_ghost);
                break;
            case "fighting":
                pokeType1.setImageResource(R.drawable.type_fighting);
                break;
            case "ground":
                pokeType1.setImageResource(R.drawable.type_ground);
                break;
            case "normal":
                pokeType1.setImageResource(R.drawable.type_normal);
                break;
            case "poison":
                pokeType1.setImageResource(R.drawable.type_poison);
                break;
            case "psychic":
                pokeType1.setImageResource(R.drawable.type_psychic);
                break;
            case "rock":
                pokeType1.setImageResource(R.drawable.type_rock);
                break;
            default:
                break;
        }

        if(POKEMON.getTipo_2()!=null && !POKEMON.getTipo_2().isEmpty() && !POKEMON.getTipo_2().equals("null")){
            switch (POKEMON.getTipo_2()){
                case "dragon":
                    pokeType2.setImageResource(R.drawable.type_dragon);
                    break;
                case "grass":
                    pokeType2.setImageResource(R.drawable.type_grass);
                    break;
                case "steel":
                    pokeType2.setImageResource(R.drawable.type_steel);
                    break;
                case "water":
                    pokeType2.setImageResource(R.drawable.type_water);
                    break;
                case "fire":
                    pokeType2.setImageResource(R.drawable.type_fire);
                    break;
                case "ice":
                    pokeType2.setImageResource(R.drawable.type_ice);
                    break;
                case "dark":
                    pokeType2.setImageResource(R.drawable.type_steel);
                    break;
                case "electric":
                    pokeType2.setImageResource(R.drawable.type_electric);
                    break;
                case "bug":
                    pokeType2.setImageResource(R.drawable.type_bug);
                    break;
                case "fairy":
                    pokeType2.setImageResource(R.drawable.type_fairy);
                    break;
                case "flying":
                    pokeType2.setImageResource(R.drawable.type_flying);
                    break;
                case "ghost":
                    pokeType2.setImageResource(R.drawable.type_ghost);
                    break;
                case "fighting":
                    pokeType2.setImageResource(R.drawable.type_fighting);
                    break;
                case "ground":
                    pokeType2.setImageResource(R.drawable.type_ground);
                    break;
                case "normal":
                    pokeType2.setImageResource(R.drawable.type_normal);
                    break;
                case "poison":
                    pokeType2.setImageResource(R.drawable.type_poison);
                    break;
                case "psychic":
                    pokeType2.setImageResource(R.drawable.type_psychic);
                    break;
                case "rock":
                    pokeType2.setImageResource(R.drawable.type_rock);
                    break;
                default:
                    break;
            }
        }
    }


}
