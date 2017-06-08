package abda.com.summit.controller;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import abda.com.summit.dao.TalkDAO;
import abda.com.summit.model.Talk;

/**
 * Created by dh2 on 07/06/17.
 */

public class TalkController {

    public List<Talk> getTalks(Context context){
        TalkDAO talkDAO = new TalkDAO();
        return talkDAO.getTalks(context);
    }

    public ArrayList<Talk> getFavoriteTalks(Context context) {
        TalkDAO talkDAO = new TalkDAO();
        return (ArrayList<Talk>) talkDAO.getUserTalks(context);
    }

    public void addFavoriteTalk(Integer id, Context context) {
        new TalkDAO().addTalkToFavorite(id, context);
    }

    public void removeFromFavoriteTalks(Integer id, Context context) {
        TalkDAO talkDAO = new TalkDAO();
        talkDAO.removeFromFavoriteTalks(id, context);
    }

    public void addTalk(Talk talk, Context context) {
        TalkDAO talkDAO = new TalkDAO();
        talkDAO.addTalk(talk, context);
    }

    public List<Talk> getTalksBySpeaker(String speaker, Context context) {
        return new TalkDAO().getTalksBySpeaker(speaker, context);
    }


}
