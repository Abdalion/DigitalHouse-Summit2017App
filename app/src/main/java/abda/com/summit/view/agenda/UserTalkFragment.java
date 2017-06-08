package abda.com.summit.view.agenda;


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
        TalkController talkController = new TalkController();
        List<Talk> talks = talkController.getFavoriteTalks(this.getContext());

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
