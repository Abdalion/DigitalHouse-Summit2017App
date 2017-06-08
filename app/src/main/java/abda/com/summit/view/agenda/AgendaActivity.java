package abda.com.summit.view.agenda;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import abda.com.summit.R;
import abda.com.summit.controller.TalkController;

public class AgendaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        // my_child_toolbar is defined in the layout file
        Toolbar myChildToolbar =
                (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myChildToolbar);

        Button comenzarBtn = (Button) findViewById(R.id.agenda_comenzarBtn);
        final FloatingActionButton fabBtn = (FloatingActionButton) findViewById(R.id.agenda_fab);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("MI AGENDA");
        ab.setDisplayHomeAsUpEnabled(true);

        TalkController talkController = new TalkController();

        if(talkController.getFavoriteTalks(this) != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.agenda_container, new UserTalkFragment())
                    .commit();

            comenzarBtn.setVisibility(View.INVISIBLE);
        }
        else {
            fabBtn.setVisibility(View.INVISIBLE);
        }

        comenzarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.agenda_container, new TalksFragment())
                        .commit();
            }
        });

        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.agenda_container, new TalksFragment())
                        .commit();

                fabBtn.setVisibility(View.INVISIBLE);
            }
        });







    }
}
