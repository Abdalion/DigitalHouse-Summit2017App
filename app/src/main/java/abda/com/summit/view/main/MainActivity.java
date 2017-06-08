package abda.com.summit.view.main;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;

import abda.com.summit.controller.TalkController;
import abda.com.summit.model.Speaker;
import abda.com.summit.model.Talk;
import abda.com.summit.model.Talks;
import abda.com.summit.view.agenda.AgendaActivity;
import abda.com.summit.R;
import abda.com.summit.view.charlas.CharlasActivity;
import abda.com.summit.view.speakers.SpeakersActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            if(new TalkController().getTalks(this).get(0) == null) {
                try {
                    parsejson();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
            try {
                parsejson();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        final Button agendaBtn = (Button) findViewById(R.id.main_agendaBtn);
        agendaBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AgendaActivity.class);
                View sharedView = agendaBtn;
                String transitionName = getString(R.string.charlasTransition);
                ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, sharedView, transitionName);
                startActivity(intent, transitionActivityOptions.toBundle());
            }
        });

        Button charlasBtn = (Button) findViewById(R.id.main_charlasBtn);
        charlasBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CharlasActivity.class);
                startActivity(intent);
            }
        });

        Button speakersBtn = (Button) findViewById(R.id.main_speakersBtn);
        speakersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SpeakersActivity.class);
                startActivity(intent);
            }
        });




    }

    private void parsejson() throws IOException {
        AssetManager manager = this.getAssets();
        InputStream talksJson = manager.open("talks.json");
        BufferedReader bufferReaderIn = new BufferedReader(new InputStreamReader(talksJson));
        Gson gson = new Gson();
        Talks talks = gson.fromJson(bufferReaderIn, Talks.class);

        TalkController talkController = new TalkController();

        for(Talk talk : talks.getTalks()) {
            talkController.addTalk(talk,this);

            }

    }

}
