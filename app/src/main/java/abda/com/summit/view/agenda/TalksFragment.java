package abda.com.summit.view.agenda;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import abda.com.summit.R;
import abda.com.summit.controller.TalkController;
import abda.com.summit.model.Talk;

/**
 * A simple {@link Fragment} subclass.
 */
public class TalksFragment extends Fragment {


    public TalksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_talks, container, false);

        TalkController talkController = new TalkController();
        List<Talk> talks = talkController.getTalks(this.getContext());

        RecyclerView rv = (RecyclerView)view.findViewById(R.id.recycler_view);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        rv.setLayoutManager(llm);
        TalkRecyclerAdapter talkRecyclerAdapter = new TalkRecyclerAdapter(talks, this.getContext());
        rv.setAdapter(talkRecyclerAdapter);

        return view;
    }

}
