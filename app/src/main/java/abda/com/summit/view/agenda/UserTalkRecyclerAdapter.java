package abda.com.summit.view.agenda;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
public class UserTalkRecyclerAdapter extends RecyclerView.Adapter{

    private List<Talk> mTalkList;
    private Context mContext;
    private Listener mRemoveListener;


    public List<Talk> getmTalkList() {
        return mTalkList;
    }


    public UserTalkRecyclerAdapter(List<Talk> mailList, Context context, Listener removeListener) {
        mTalkList = mailList;
        mContext = context;
        mRemoveListener = removeListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        LayoutInflater inflater = (LayoutInflater)LayoutInflater.from(parent.getContext());
        View viewCelda = inflater.inflate(R.layout.item_useritem,parent,false);
        TalkViewHolder talkViewHolder  = new TalkViewHolder(viewCelda);
        return talkViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Talk talk = mTalkList.get(position);
        TalkViewHolder talkViewHolder = (TalkViewHolder) holder;
        talkViewHolder.loadUser(talk/*, mContext*/);
        ((TalkViewHolder) holder).removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TalkController().removeFromFavoriteTalks(mTalkList.get(position).getID(), mContext);
                mTalkList.remove(position);
                notifyDataSetChanged();
                mRemoveListener.finish();
            }
        });
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
        private Button removeBtn;

        public TalkViewHolder(View view) {
            super(view);
            horaTextView = (TextView) view.findViewById(R.id.item_hora);
            aulaTextView = (TextView) view.findViewById(R.id.item_aula);
            temaTextView = (TextView) view.findViewById(R.id.item_tema);
            speakerTextView = (TextView) view.findViewById(R.id.item_speaker);
            removeBtn = (Button) view.findViewById(R.id.item_remove);
        }

        public void loadUser(final Talk talk) {
            horaTextView.setText(talk.getHoraInicio());
            aulaTextView.setText(talk.getAula());
            temaTextView.setText(talk.getTema());
            speakerTextView.setText(talk.getSpeaker());

        }
    }
}
