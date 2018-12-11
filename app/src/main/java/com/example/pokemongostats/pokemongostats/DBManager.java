package com.example.pokemongostats.pokemongostats;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/** Maneja el acceso a la base de datos. */
public class DBManager extends SQLiteOpenHelper {
    public static final String DB_NOMBRE = "PokemonGoDB";
    public static final int DB_VERSION = 3;

    public static final String TABLA_POKEMON = "pokemon";
    public static final String POKEMON_COL_NUMERO = "_id";
    public static final String POKEMON_COL_NOMBRE = "nombre";
    public static final String POKEMON_COL_TIPO1 = "tipo1";
    public static final String POKEMON_COL_TIPO2 = "tipo2";
    public static final String POKEMON_COL_ATAQUE = "ataque";
    public static final String POKEMON_COL_DEFENSA = "defensa";
    public static final String POKEMON_COL_RESISTENCIA = "resistencia";
    public static final String POKEMON_COL_EVOLUCION = "evolucion";
    public static final String POKEMON_COL_CARAMELOS_EVOLUCION = "caramelosEvolucion";
    public static final String POKEMON_COL_KMDISTANCIA = "kmdistancia";

    private DBManager(Context context)
    {
        super( context, DB_NOMBRE, null, DB_VERSION);
    }

    private static DBManager instancia;

    public static DBManager getManager(Context context)
    {
        if ( instancia == null ) {
            instancia = new DBManager(context);
        }
        return instancia;
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Log.i(  "DBManager",
                "Creando BBDD " + DB_NOMBRE + " v" + DB_VERSION);

        try {
            db.beginTransaction();
            db.execSQL( "CREATE TABLE IF NOT EXISTS " + TABLA_POKEMON + "( "
                    + POKEMON_COL_NUMERO + " integer PRIMARY KEY NOT NULL, "
                    + POKEMON_COL_NOMBRE + " string(30) NOT NULL,"
                    + POKEMON_COL_TIPO1 + " string(15) NOT NULL,"
                    + POKEMON_COL_TIPO2 + " string(15) ,"
                    + POKEMON_COL_ATAQUE + " integer NOT NULL,"
                    + POKEMON_COL_DEFENSA + " integer NOT NULL, "
                    + POKEMON_COL_RESISTENCIA + " integer NOT NULL, "
                    + POKEMON_COL_EVOLUCION + " string(50), "
                    + POKEMON_COL_CARAMELOS_EVOLUCION + " string(10), "
                    + POKEMON_COL_KMDISTANCIA + " integer NOT NULL"
                    + " )");
            db.setTransactionSuccessful();
        }
        catch(SQLException exc)
        {
            Log.e( "DBManager.onCreate", exc.getMessage() );
        }
        finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("DBManager",
                "DB: " + DB_NOMBRE + ": v" + oldVersion + " -> v" + newVersion);

        try {
            db.beginTransaction();
            db.execSQL("DROP TABLE IF EXISTS " + TABLA_POKEMON);
            db.setTransactionSuccessful();
        } catch (SQLException exc) {
            Log.e("DBManager.onUpgrade", exc.getMessage());
        } finally {
            db.endTransaction();
        }

        this.onCreate(db);
    }


    /** Devuelve todos los pokemons en la BD
     * @return Un Cursor con los pokemons. */
    public ArrayList<Pokemon> getPokemons()
    {
        ArrayList<Pokemon> pokemonList = new ArrayList<>();
        Cursor cursor = this.getReadableDatabase().query( TABLA_POKEMON,
                null, null, null, null, null, null );

        if ( cursor.moveToFirst() ) {
            do {
                pokemonList.add(new Pokemon(cursor.getInt(cursor.getColumnIndex(POKEMON_COL_NUMERO)),
                        cursor.getString( cursor.getColumnIndex(POKEMON_COL_NOMBRE )),
                        cursor.getString( cursor.getColumnIndex(POKEMON_COL_TIPO1 )),
                        cursor.getString( cursor.getColumnIndex(POKEMON_COL_TIPO2 )),
                        cursor.getInt( cursor.getColumnIndex(POKEMON_COL_ATAQUE )),
                        cursor.getInt( cursor.getColumnIndex(POKEMON_COL_DEFENSA )),
                        cursor.getInt( cursor.getColumnIndex(POKEMON_COL_RESISTENCIA )),
                        cursor.getString( cursor.getColumnIndex(POKEMON_COL_EVOLUCION )),
                        cursor.getString( cursor.getColumnIndex(POKEMON_COL_CARAMELOS_EVOLUCION )),
                        cursor.getInt( cursor.getColumnIndex(POKEMON_COL_KMDISTANCIA ))));
            } while ( cursor.moveToNext() );
        }
        cursor.close();
        return pokemonList;
    }

    /** Devuelve un Pokemno de la BD
     *  @param @String El id del pokemon.
     * @return Un Cursor con el Pokemon.
     */
    public Pokemon getPokemon(String id){
        Cursor cursor =  this.getReadableDatabase().query( TABLA_POKEMON,
                null, POKEMON_COL_NUMERO + "=?" , new String[]{ id }, null, null, null );
        Pokemon pokemon = null;
        if(cursor.moveToFirst() ){
            pokemon =  new Pokemon(cursor.getInt(cursor.getColumnIndex(POKEMON_COL_NUMERO)),
                    cursor.getString( cursor.getColumnIndex(POKEMON_COL_NOMBRE )),
                    cursor.getString( cursor.getColumnIndex(POKEMON_COL_TIPO1 )),
                    cursor.getString( cursor.getColumnIndex(POKEMON_COL_TIPO2 )),
                    cursor.getInt( cursor.getColumnIndex(POKEMON_COL_ATAQUE )),
                    cursor.getInt( cursor.getColumnIndex(POKEMON_COL_DEFENSA )),
                    cursor.getInt( cursor.getColumnIndex(POKEMON_COL_RESISTENCIA )),
                    cursor.getString( cursor.getColumnIndex(POKEMON_COL_EVOLUCION )),
                    cursor.getString( cursor.getColumnIndex(POKEMON_COL_CARAMELOS_EVOLUCION )),
                    cursor.getInt( cursor.getColumnIndex(POKEMON_COL_KMDISTANCIA )));

        }

        cursor.close();
        return pokemon;

    }


    public boolean hasData(){
        Cursor cursor = null;
        cursor = this.getReadableDatabase().query( TABLA_POKEMON,
                null, null, null, null, null, null );

        if ( cursor.getCount() > 0 ) {
            return true;
        } else {
            return false;
        }
    }


    /** Inserta un nuevo Pokemon.
     * @param @pokemon El Objeto pokemon.
     * @return true si se pudo insertar (o modificar), false en otro caso.
     */

    public boolean insertarPokemon(Pokemon pokemon)
    {
        boolean toret = false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put( POKEMON_COL_NUMERO, pokemon.getNumeroPokedex() );
        values.put( POKEMON_COL_NOMBRE, pokemon.getNombre() );
        values.put( POKEMON_COL_TIPO1, pokemon.getTipo_1() );
        values.put( POKEMON_COL_TIPO2, pokemon.getTipo_2() );
        values.put( POKEMON_COL_ATAQUE, pokemon.getAtaque() );
        values.put( POKEMON_COL_DEFENSA, pokemon.getDefensa() );
        values.put( POKEMON_COL_RESISTENCIA, pokemon.getResistencia() );
        values.put( POKEMON_COL_EVOLUCION, pokemon.getEvolucion() );
        values.put( POKEMON_COL_CARAMELOS_EVOLUCION, pokemon.getCaramelos_evolucion() );
        values.put( POKEMON_COL_KMDISTANCIA, pokemon.getCaramelos_km_distancia() );

        try {
            db.beginTransaction();
            db.insert( TABLA_POKEMON, null, values );
            db.setTransactionSuccessful();
            toret = true;
        } catch(SQLException exc)
        {
            Log.e( "DBManager.inserta", exc.getMessage() );
        }
        finally {
            db.endTransaction();
        }
        return toret;
    }

    /** Elimina un elemento de la base de datos
     * @param nombre El identificador del elemento.
     * @return true si se pudo eliminar, false en otro caso.
     */
    /*
    public boolean eliminaItem(String nombre)
    {
        boolean toret = false;
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            db.beginTransaction();
            db.delete( TABLA_COMPRA, COMPRA_COL_NOMBRE + "=?", new String[]{ nombre } );
            db.setTransactionSuccessful();
            toret = true;
        } catch(SQLException exc) {
            Log.e( "DBManager.elimina", exc.getMessage() );
        } finally {
            db.endTransaction();
        }

        return toret;
    }
    */
}
