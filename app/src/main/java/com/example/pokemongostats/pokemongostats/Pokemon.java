package com.example.pokemongostats.pokemongostats;

public class Pokemon {
    public Pokemon(int numero, String nombre, String tipo_1, String tipo_2, int ataque, int defensa,
                   int resistencia, String evolucion, int caramelos_evolucion, int caramelos_km_distancia)
    {
        this.numero = numero;
        this.nombre = nombre;
        this.tipo_1 = tipo_1;
        this.tipo_2 = tipo_2;
        this.ataque = ataque;
        this.defensa = defensa;
        this.resistencia = resistencia;
        this.evolucion = evolucion;
        this.caramelos_evolucion = caramelos_evolucion;
        this.caramelos_km_distancia = caramelos_km_distancia;
    }

    public int getNumero() {return numero;}
    public String getNombre() {return nombre;}
    public String getTipo_1() {return tipo_1;}
    public String getTipo_2() {return tipo_2;}
    public int getAtaque() {return ataque;}
    public int getDefensa() {return defensa;}
    public int getResistencia() {return resistencia;}
    public String getEvolucion() {return evolucion;}
    public int getCaramelos_evolucion() {return caramelos_evolucion;}
    public int getCaramelos_km_distancia() {return caramelos_km_distancia;}

    public String toString()
    {
        String toret = "";
        /*
        final String SUPERMERCADO = this.getSupermercado();
        String toret;
        if ( SUPERMERCADO != null
                && !SUPERMERCADO.isEmpty() )
        {
            toret = String.format( "%s (%s)", this.getNombre(), this.getSupermercado() );
        } else {
            toret = this.getNombre();
        }
        return toret;
        */
        return toret;
    }

    private int numero;
    private String nombre;
    private String tipo_1;
    private String tipo_2;
    private int ataque;
    private int defensa;
    private int resistencia;
    private String evolucion;
    private int caramelos_evolucion;
    private int caramelos_km_distancia;

}
