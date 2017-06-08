package abda.com.summit.view.agenda;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import abda.com.summit.Listener;
import abda.com.summit.R;
import abda.com.summit.controller.TalkController;
import abda.com.summit.view.charlas.CharlasActivity;

public class AgendaActivity extends AppCompatActivity implements Listener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        // my_child_toolbar is defined in the layout file
        Toolbar myChildToolbar =
                (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myChildToolbar);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("MI AGENDA");
        ab.setDisplayHomeAsUpEnabled(true);

        TalkController talkController = new TalkController();

        if(talkController.getFavoriteTalks(this) != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.agenda_container, new UserTalkFragment())
                    .commit();
        }



    }


    @Override
    public void finish() {
        Intent intent = new Intent(this, CharlasActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        NavUtils.navigateUpFromSameTask(this);
    }
}
