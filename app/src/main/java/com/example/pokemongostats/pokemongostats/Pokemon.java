package com.example.pokemongostats.pokemongostats;

import android.util.Log;

public class Pokemon {
    public Pokemon(int numeroPokedex, String nombre, String tipo_1, String tipo_2, int ataque, int defensa,
                   int resistencia, String evolucion, String caramelos_evolucion, int caramelos_km_distancia)
    {
        this.numeroPokedex = numeroPokedex;
        this.nombre = nombre;
        this.tipo_1 = tipo_1;
        this.setTipo_2(tipo_2);
        this.ataque = ataque;
        this.defensa = defensa;
        this.resistencia = resistencia;
        this.evolucion = evolucion;
        this.caramelos_evolucion = caramelos_evolucion;
        this.caramelos_km_distancia = caramelos_km_distancia;

        //this.prueba();
    }

    public int getNumeroPokedex() {return numeroPokedex;}
    public String getNombre() {return nombre;}
    public String getTipo_1() {return tipo_1;}
    public String getTipo_2() {return tipo_2;}
    public int getAtaque() {return ataque;}
    public int getDefensa() {return defensa;}
    public int getResistencia() {return resistencia;}
    public String getEvolucion() {return evolucion;}
    public String getCaramelos_evolucion() {return caramelos_evolucion;}
    public int getCaramelos_km_distancia() {return caramelos_km_distancia;}

    public void setTipo_2(String tipo_2) {
        if(tipo_2 != null)
            this.tipo_2 = tipo_2;
        else
            this.tipo_2 = "vacio";
    }

    /*public String toString()
    {
        String toret = "";

        final String TIPO2 = this.getTipo_2();
        final String EVOLUCION = this.getEvolucion();
        final String CARAMELOS_EVOLUCION = this.getCaramelos_evolucion();

        if ( TIPO2 != null
                && !TIPO2.isEmpty() && EVOLUCION != null && EVOLUCION.isEmpty() && CARAMELOS_EVOLUCION.isEmpty() )
        {
            toret = String.format( "%s (%s - %s)", this.getNombre(), this.getTipo_1(), this.getTipo_2() );
        } else {
            toret = this.getNombre() + " wedqw";
        }
        return toret;

    }*/

    public void prueba(){
        //Log.i("tag", "Pokemon Numero: " );
        System.out.println("Pokemon Numero: " + this.getNumeroPokedex());
        System.out.println("Nombre Pokemon : " + this.getNombre());
        System.out.println("Tipo 1: " + this.getTipo_1());
        System.out.println("Tipo 2: " + this.getTipo_2());
        System.out.println("Ataque: " + this.getAtaque());
        System.out.println("Defensa: " + this.getDefensa());
        System.out.println("Resistencia: " + this.getResistencia());
        System.out.println("Evolucion: " + this.getEvolucion());
        System.out.println("Caramelos evolucion: " + this.getCaramelos_evolucion());
        System.out.println("Caramelos Distancia: " + this.getCaramelos_km_distancia());
        System.out.println(" ------------ ");
    }

    private int numeroPokedex;
    private String nombre;
    private String tipo_1;
    private String tipo_2;
    private int ataque;
    private int defensa;
    private int resistencia;
    private String evolucion;
    private String caramelos_evolucion;
    private int caramelos_km_distancia;

}
