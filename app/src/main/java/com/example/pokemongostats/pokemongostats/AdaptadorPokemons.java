package com.example.pokemongostats.pokemongostats;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

public class AdaptadorPokemons extends RecyclerView.Adapter<AdaptadorPokemons.ViewHolderPokemons>{

    private ArrayList<Pokemon> listaPokemons;
    private Context context;
    private static final String TAG = "AdaptadorPokemons";

    public AdaptadorPokemons(ArrayList<Pokemon> listaPokemons, Context context){
        this.listaPokemons = listaPokemons;
        this.context = context;
    }

    @NonNull
    @Override
    public AdaptadorPokemons.ViewHolderPokemons onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listview_pokemon, null, false);
        return new ViewHolderPokemons(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPokemons.ViewHolderPokemons viewHolderPokemons, int i) {

        final AdaptadorPokemons.ViewHolderPokemons v = viewHolderPokemons;
        viewHolderPokemons.progress.setVisibility(View.VISIBLE);
        Glide.with(context)
                .load("http://pokeapi.co/media/sprites/pokemon/" + String.valueOf(listaPokemons.get(i).getNumeroPokedex()) + ".png")
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        v.progress.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        v.progress.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(viewHolderPokemons.pokeImg);

        viewHolderPokemons.parentLayout.setOnClickListener((view) -> {

        });



        viewHolderPokemons.pokemonNumber.setText(String.valueOf(listaPokemons.get(i).getNumeroPokedex()));
        viewHolderPokemons.pokemonName.setText(listaPokemons.get(i).getNombre());

    }

    @Override
    public int getItemCount() {
        return listaPokemons.size();
    }

    public class ViewHolderPokemons extends RecyclerView.ViewHolder{

        ImageView pokeImg;
        TextView pokemonNumber, pokemonName;
        ProgressBar progress;
        RelativeLayout parentLayout;


        public ViewHolderPokemons(@NonNull View itemView) {
            super(itemView);
            pokeImg = itemView.findViewById(R.id.pokeImg);
            pokemonNumber = itemView.findViewById(R.id.pokemonNumber);
            pokemonName = itemView.findViewById(R.id.pokemonName);
            progress = itemView.findViewById(R.id.progress);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
