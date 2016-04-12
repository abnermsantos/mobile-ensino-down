package br.edu.ifspsaocarlos.sdm.cienciasdown.View;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import br.edu.ifspsaocarlos.sdm.cienciasdown.R;

public class VideoTeste extends AppCompatActivity {


    // Declare variables
    ProgressDialog pDialog;
    VideoView videoview;

    // Insert your Video URL
    //String VideoURL = "http://www.androidbegin.com/tutorial/AndroidCommercial.3gp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the layout from video_main.xml
        setContentView(R.layout.activity_video_teste);
        // Find your VideoView in your video_main.xml layout
        videoview = (VideoView) findViewById(R.id.VideoViewTeste);
        // Execute StreamVideo AsyncTask

        // Create a progressbar
        pDialog = new ProgressDialog(VideoTeste.this);
        // Set progressbar title
        pDialog.setTitle("Android Video Streaming Tutorial");
        // Set progressbar message
        pDialog.setMessage("Buffering...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        // Show progressbar
        pDialog.show();

        try {
            // Start the MediaController
            MediaController mediacontroller = new MediaController(
                    VideoTeste.this);
            mediacontroller.setAnchorView(videoview);
            // Get the URL from String VideoURL
            //Uri video = Uri.parse(VideoURL);
            String path = "android.resource://" + getPackageName() + "/" + R.raw.ciclo;
            videoview.setMediaController(mediacontroller);
            videoview.setVideoURI(Uri.parse(path));

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        videoview.requestFocus();
        videoview.setOnPreparedListener(new OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                pDialog.dismiss();
                videoview.start();
            }
        });

        //VideoView view = (VideoView)findViewById(R.id.VideoViewTeste);
        //String path = "android.resource://" + getPackageName() + "/" + R.raw.evap;
        //view.setVideoURI(Uri.parse(path));
        //view.start();
    }

}
