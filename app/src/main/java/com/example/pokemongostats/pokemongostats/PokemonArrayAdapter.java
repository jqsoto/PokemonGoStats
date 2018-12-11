package com.example.pokemongostats.pokemongostats;

import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

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

        ImageView pokeType1 = view.findViewById( R.id.pokeType1 );

        /*
        try{
            new ImageFinder(pokeType1).doInBackground(new URL("http://pokeapi.co/media/sprites/pokemon/female/25.png"));
        }catch(MalformedURLException e) {
            e.printStackTrace();
        }
*/
        //this.getTypeImg(view, POKEMON);

        queue = Volley.newRequestQueue( CONTEXT );

        this.getImages(String.valueOf(POKEMON.getNumeroPokedex()));


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

        if(!POKEMON.getTipo_2().equals("vacio")){
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
        else{
            pokeType2.setVisibility(View.INVISIBLE);
        }
    }



    private RequestQueue queue;

    private void getImages(String id){



        final String url = "http://pokeapi.co/api/v2/pokemon/" + id;

        /*
        JsonObjectRequest requets = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        });*/

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try{
                    JSONObject imagenes = response.getJSONObject("sprites");
                    Log.i("tag", "entra imagenes ---- "  + url );

                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
        });

        queue.add(request);
    }


}
