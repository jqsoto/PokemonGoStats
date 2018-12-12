package com.example.pokemongostats.pokemongostats;

public class Comment {
    private int pokemonNumber;
    private String comment;

    public Comment(int pokemonNumber, String comment) {
        this.pokemonNumber = pokemonNumber;
        this.comment = comment;
    }

    public int getPokemonNumber() {
        return pokemonNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setPokemonNumber(int pokemonNumber) {
        this.pokemonNumber = pokemonNumber;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
