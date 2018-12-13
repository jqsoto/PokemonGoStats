package com.example.pokemongostats.pokemongostats;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

public class DetailviewActivity extends AppCompatActivity {

    private static final String TAG = "DetailviewActivity";
    private int pokemonNumber;
    private String pokemonName;
    private String pokemonType1;
    private String pokemonType2;
    private int pokemonAttack;
    private int pokemonDefense;
    private int pokemonStamina;
    private String pokemonEvolution;
    private String pokemonCandyCostEvolucion;
    private int pokemonCandyDistance;

    public DetailviewActivity() {}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailview);
        final DBManager gestorDB = DBManager.getManager( this.getApplicationContext() );

        this.getExtrasContent();

        Button saveButton = findViewById( R.id.commentButton );
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                EditText comentarioEdit = findViewById( R.id.comment );
                String comentario = comentarioEdit.getText().toString();
                TextView number = findViewById(R.id.lblpokemonNumber);
                Comment comment = new Comment(Integer.parseInt(number.getText().toString()), comentario);
                if(gestorDB.hasThisComment(comment)){
                    Toast.makeText(getApplicationContext(), getString(R.string.already_comment), Toast.LENGTH_LONG).show();
                }else{
                    gestorDB.saveComment(comment);
                    Toast.makeText(getApplicationContext(), getString(R.string.added_comment), Toast.LENGTH_LONG).show();
                }
            }
        });

        Button viewComments = findViewById(R.id.viewComments);

        viewComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CommentsActivity.class);
                intent.putExtra("pokemonNumber", pokemonNumber);
                DetailviewActivity.this.startActivity(intent);
            }
        });

    }



    private void getExtrasContent(){
        Log.d(TAG, "getExtrasContent: checking content");

        if(getIntent().hasExtra("pokemonNumber") && getIntent().hasExtra("pokemonName")
                && getIntent().hasExtra("pokemonType1") && getIntent().hasExtra("pokemonAttack")
                && getIntent().hasExtra("pokemonDefense") && getIntent().hasExtra("pokemonStamina")
                && getIntent().hasExtra("pokemonKmCandyDistance")){
            pokemonNumber = getIntent().getIntExtra("pokemonNumber", 0);
            pokemonName = getIntent().getStringExtra("pokemonName");
            pokemonType1 = getIntent().getStringExtra("pokemonType1");
            pokemonAttack = getIntent().getIntExtra("pokemonAttack", 0);
            pokemonDefense = getIntent().getIntExtra("pokemonDefense", 0);
            pokemonStamina = getIntent().getIntExtra("pokemonStamina", 0);
            pokemonCandyDistance = getIntent().getIntExtra("pokemonKmCandyDistance", 0);
        }

        if(getIntent().hasExtra("pokemonType2"))
            pokemonType2 = getIntent().getStringExtra("pokemonType2");
        else
            pokemonType2 = null;

        if (getIntent().hasExtra("pokemonEvolution") && getIntent().hasExtra("pokemonCandyCostEvolution")){
            pokemonEvolution = getIntent().getStringExtra("pokemonEvolution");
            pokemonCandyCostEvolucion = getIntent().getStringExtra("pokemonCandyCostEvolution");
        }
        else{
            pokemonEvolution = null;
            pokemonCandyCostEvolucion = null;
        }

        this.setExtrasContent();
    }

    private void setExtrasContent(){

        TextView lblpokemonNumber = findViewById(R.id.lblpokemonNumber);
        TextView lblpokemonName = findViewById(R.id.lblpokemonName);

        this.getImg();

        TextView lblAttack = findViewById(R.id.lblAttack);
        TextView lblDefense = findViewById(R.id.lblDefense);
        TextView lblStamina = findViewById(R.id.lblStamina);

        TextView lblEvolutionName = findViewById(R.id.lblEvolution);
        TextView lblEvolutionCandyCost = findViewById(R.id.lblEvolution_candies);
        TextView lblCandyKmDistance = findViewById(R.id.lblBuddyKm);

        lblpokemonNumber.setText(String.valueOf(pokemonNumber));
        lblpokemonName.setText(pokemonName.substring(0, 1).toUpperCase() + pokemonName.substring(1));
        lblAttack.setText(String.valueOf(pokemonAttack));
        lblDefense.setText(String.valueOf(pokemonDefense));
        lblStamina.setText(String.valueOf(pokemonStamina));

        if(pokemonEvolution != null)
            lblEvolutionName.setText(pokemonEvolution.substring(0, 1).toUpperCase() + pokemonEvolution.substring(1, pokemonEvolution.length()));


        lblEvolutionCandyCost.setText(pokemonCandyCostEvolucion);
        lblCandyKmDistance.setText(String.valueOf(pokemonCandyDistance) + " " + getString(R.string.kilometers) );



    }
    private void getImg(){

        ImageView pokeImg = findViewById( R.id.pokeImg );
        final ProgressBar progress = findViewById( R.id.progress );
        progress.setVisibility(View.VISIBLE);
        Glide.with(this)
                .load("http://pokeapi.co/media/sprites/pokemon/" + String.valueOf(pokemonNumber) + ".png")
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        progress.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progress.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(pokeImg);

        ImageView pokeType1 = findViewById( R.id.pokeType1 );
        ImageView pokeType2 = findViewById( R.id.pokeType2 );
        if(getIntent().hasExtra("pokemonType1")){
            switch (getIntent().getStringExtra("pokemonType1")){
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
                    pokeType1.setImageResource(R.drawable.type_dark);
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
        }

        if(getIntent().hasExtra("pokemonType2")){
            switch (getIntent().getStringExtra("pokemonType2")){
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
                    pokeType2.setImageResource(R.drawable.type_dark);
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

    @Override
    public void onPause()
    {
        super.onPause();
    }

}
