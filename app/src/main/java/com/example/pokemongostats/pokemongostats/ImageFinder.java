package com.example.pokemongostats.pokemongostats;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;


public class ImageFinder extends AsyncTask<URL, Void, Boolean> {


    private Exception exception;
    private ImageView i;

    public ImageFinder(ImageView i) {
        this.i=i;
    }


    @Override
    protected Boolean doInBackground(URL... urls) {

        try{
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream) urls[0].getContent());
            i.setImageBitmap(bitmap);
        }
        catch (Exception e) {
            this.exception = e;
        }
        return true;
    }

    @Override
    protected void onPreExecute() {
        /*final TextView lblStatus = (TextView) this.activity.findViewById( R.id.lblStatus );
        this.activity.setStatus( R.string.status_connecting );
        this.setDefaultValues();*/
    }



    @Override
    public void onPostExecute(Boolean result)
    {
        /*
        final TextView lblStatus = (TextView) this.activity.findViewById( R.id.lblStatus );
        int idFinalStatus = R.string.status_ok;
        if ( !result ) {
            idFinalStatus = R.string.status_error;
        }
        lblStatus.setText( idFinalStatus );
        this.activity.setTimeInfo( time, timeInfo, gmtInfo );
        */
    }
}
