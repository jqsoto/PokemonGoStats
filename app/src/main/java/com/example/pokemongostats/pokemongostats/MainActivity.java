package com.example.pokemongostats.pokemongostats;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.gestorDB = DBManager.getManager( this.getApplicationContext() ); // Conexión con la BD

        checkData();

        pokemonList = this.gestorDB.getPokemons();

       this.creaLista();



    }

    /* Comprueba si y ahay datos cargados en la BD*/
    private void checkData(){
        if(!this.gestorDB.hasData()){
            this.chargeJSON();
        }
    }

    @Override
    public void onPause()
    {
        super.onPause();

        this.gestorDB.close();
        //this.adaptadorDB.getCursor().close();
    }

    private String readJSON(){
            String json;
            try {
                InputStream is = getAssets().open("stats.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                json = new String(buffer, "UTF-8");
            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }
            return json;
    }

    private void chargeJSON(){
        try {
            JSONObject obj = new JSONObject(readJSON());
            JSONArray m_jArry = obj.getJSONArray("templates");
            ArrayList<Pokemon> pokemonList = new ArrayList<>();

            for (int i = 0; i < m_jArry.length(); i++) {
                int pokemonPokedex = 0;
                String pokemonName = null;
                String type1 = null;
                String type2 = null;
                String type1Split;
                String type2Split = null;
                int pokemonStamina = 0;
                int pokemonAttack = 0;
                int pokemonDefense = 0;
                String evolutionName = "";
                String candyCost = "";
                int kmCandyDistance = 0;

                JSONObject jo_inside = m_jArry.getJSONObject(i);

                String pokemonNumeroString = jo_inside.getString("templateId");
                String [] pokemonJSONID_split = pokemonNumeroString.split("_");

                if(pokemonJSONID_split[1].equals("POKEMON")){
                    pokemonPokedex = Integer.parseInt(pokemonJSONID_split[0].substring(1));
                }

                if(jo_inside.has("pokemonSettings")) {
                    JSONObject pokemonSettings = jo_inside.getJSONObject("pokemonSettings");
                    pokemonName = pokemonSettings.getString("pokemonId").toLowerCase();
                    type1 = pokemonSettings.getString("type").toLowerCase();
                    if(!pokemonSettings.optString("type2").equals("")){
                        type2 = pokemonSettings.getString("type2").toLowerCase();
                    }

                    if(pokemonSettings.has("stats") ) {
                        JSONObject pokemonStats = pokemonSettings.getJSONObject("stats");
                        pokemonStamina = pokemonStats.getInt("baseStamina");
                        pokemonAttack = pokemonStats.getInt("baseAttack");
                        pokemonDefense = pokemonStats.getInt("baseDefense");
                    }

                    if(pokemonSettings.has("evolutionBranch")){
                        JSONArray evo_array = pokemonSettings.getJSONArray("evolutionBranch");
                        for(int z = 0; z < evo_array.length(); z++) {
                            evolutionName += (evo_array.getJSONObject(z).getString("evolution").toLowerCase() + ".");
                            candyCost += (evo_array.getJSONObject(z).getInt("candyCost") + ".");
                        }
                    }
                    else{
                        evolutionName = null;
                        candyCost = null;
                    }

                    kmCandyDistance = pokemonSettings.getInt("kmBuddyDistance");
                }

                if(pokemonName != null){
                    type1Split = type1.split("_")[2];
                    if(type2 != null ){
                        type2Split = type2.split("_")[2];
                    }
                    pokemonList.add(new Pokemon(pokemonPokedex, pokemonName, type1Split, type2Split, pokemonAttack, pokemonDefense,
                            pokemonStamina, evolutionName, candyCost, kmCandyDistance));
                }
            }

            this.insertPokemons(pokemonList);

        } catch (JSONException e) {
            Log.v("tag", e.getMessage());
            e.printStackTrace();
        }
    }

    /* Recorre el Array List con los datos de los pokemons y llama al metodo insertarPokemon*/
    private void insertPokemons(ArrayList<Pokemon> pokemonList){
        for (Pokemon pokemon : pokemonList ){
            System.out.println("Insertando en BD -- Pokemon: " + pokemon.getNumeroPokedex() + " ( " + pokemon.getNombre() + " )" );
            this.gestorDB.insertarPokemon(pokemon);
        }
    }

    private void creaLista()
    {
        recyclerPokemons = (RecyclerView) this.findViewById( R.id.pokemonList );
        recyclerPokemons.setLayoutManager(new LinearLayoutManager(this));



        this.adapterList = new AdaptadorPokemons( this.pokemonList , this);
        recyclerPokemons.setAdapter(adapterList);
    }

    public DBManager gmanetDBManager() {
        return this.gestorDB;
    }


    private DBManager gestorDB;
    private AdaptadorPokemons adapterList;
    private ArrayList<Pokemon> pokemonList;
    private RecyclerView recyclerPokemons;
}
