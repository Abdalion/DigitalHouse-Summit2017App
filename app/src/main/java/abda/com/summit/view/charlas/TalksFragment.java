package abda.com.summit.view.charlas;


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
public class TalksFragment extends Fragment implements Listener{

    View mView;


    public TalksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_talks, container, false);
        mView = view;

        TalkController talkController = new TalkController();
        List<Talk> talks = talkController.getTalks(this.getContext());

        RecyclerView rv = (RecyclerView)view.findViewById(R.id.recycler_view);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        rv.setLayoutManager(llm);
        TalkRecyclerAdapter talkRecyclerAdapter = new TalkRecyclerAdapter(talks, this.getContext(), this);
        rv.setAdapter(talkRecyclerAdapter);

        return view;
    }

    @Override
    public void finish() {
        Snackbar.make(mView, "Charla agregada", Snackbar.LENGTH_SHORT).show();
    }
}
