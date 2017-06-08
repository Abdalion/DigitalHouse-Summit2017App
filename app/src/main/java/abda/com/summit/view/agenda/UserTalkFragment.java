package abda.com.summit.view.agenda;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import abda.com.summit.Listener;
import abda.com.summit.R;
import abda.com.summit.controller.TalkController;
import abda.com.summit.model.Talk;
import abda.com.summit.view.charlas.CharlasActivity;
import abda.com.summit.view.main.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserTalkFragment extends Fragment implements Listener{

    View mView;


    public UserTalkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_talk_editor, container, false);
        mView = view;
        view.findViewById(R.id.agendar_ir_agendaBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CharlasActivity.class);
                startActivity(intent);            }
        });

        TalkController talkController = new TalkController();
        List<Talk> talks = talkController.getFavoriteTalks(this.getContext());
        if(talks.size() != 0) {
            view.findViewById(R.id.agendaVacia_tv).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.agendar_ir_agendaBtn).setVisibility(View.INVISIBLE);
        }
        else {
            view.findViewById(R.id.agendaVacia_tv).setVisibility(View.VISIBLE);
            view.findViewById(R.id.agendar_ir_agendaBtn).setVisibility(View.VISIBLE);
        }

        RecyclerView rv = (RecyclerView)view.findViewById(R.id.user_recycler_view);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        rv.setLayoutManager(llm);
        UserTalkRecyclerAdapter userTalkRecyclerAdapter = new UserTalkRecyclerAdapter(talks, this.getContext(), this);
        rv.setAdapter(userTalkRecyclerAdapter);


        return view;
    }

    @Override
    public void finish() {
        Snackbar.make(mView, "Charla removida de agenda.", Snackbar.LENGTH_SHORT).show();
    }


}
