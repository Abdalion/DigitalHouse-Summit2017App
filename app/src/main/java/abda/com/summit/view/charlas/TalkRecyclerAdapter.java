package abda.com.summit.view.charlas;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import abda.com.summit.Listener;
import abda.com.summit.R;
import abda.com.summit.controller.TalkController;
import abda.com.summit.model.Talk;

/**
 * Created by dh2 on 07/06/17.
 */

public class TalkRecyclerAdapter extends RecyclerView.Adapter {

    private List<Talk> mTalkList;
    private Context mContext;
    private Listener mAddListener;

    public List<Talk> getmTalkList() {
        return mTalkList;
    }

    public TalkRecyclerAdapter(List<Talk> mailList, Context context, Listener addListener) {
        mTalkList = mailList;
        mContext = context;
        mAddListener = addListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        LayoutInflater inflater = (LayoutInflater)LayoutInflater.from(parent.getContext());
        View viewCelda = inflater.inflate(R.layout.item_allitem,parent,false);
        TalkViewHolder talkViewHolder  = new TalkViewHolder(viewCelda);
        return talkViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Talk talk = mTalkList.get(position);
        TalkViewHolder talkViewHolder = (TalkViewHolder) holder;
        talkViewHolder.loadUser(talk/*, mContext*/);
    }

    @Override
    public int getItemCount() {
        return mTalkList.size();
    }

    private class TalkViewHolder extends RecyclerView.ViewHolder {
        private TextView horaTextView;
        private TextView aulaTextView;
        private TextView temaTextView;
        private TextView speakerTextView;
        private Button addBtn;

        public TalkViewHolder(View view) {
            super(view);
            horaTextView = (TextView) view.findViewById(R.id.item_hora);
            aulaTextView = (TextView) view.findViewById(R.id.item_aula);
            temaTextView = (TextView) view.findViewById(R.id.item_tema);
            speakerTextView = (TextView) view.findViewById(R.id.item_speaker);
            addBtn = (Button) view.findViewById(R.id.item_add);

        }

        public void loadUser(final Talk talk) {
            horaTextView.setText(talk.getHoraInicio());
            aulaTextView.setText(talk.getAula());
            temaTextView.setText(talk.getTema());
            speakerTextView.setText(talk.getSpeaker());
            if(talk.getIsFavorite()) {
                addBtn.setVisibility(View.INVISIBLE);
            }else{
                addBtn.setVisibility(View.VISIBLE);
            }
            addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new TalkController().addFavoriteTalk(talk.getID(), mContext);
                    addBtn.setVisibility(View.INVISIBLE);
                    mAddListener.finish();
                }
            });
        }
    }
}
