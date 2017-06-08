package abda.com.summit.dao;

import android.content.Context;
import android.util.Log;

import java.util.List;

import abda.com.summit.model.Talk;

/**
 * Created by dh2 on 07/06/17.
 */

public class TalkDAO {

    public List<Talk> getTalks(Context context) {
        return new TalkDBhelper(context).getTalksFromDatabase();
    }

    public List<Talk> getUserTalks(Context context) {
        return new TalkDBhelper(context).getFavoritesFromDatabase();
    }

    public void addTalkToFavorite(Integer id, Context context) {
        new TalkDBhelper(context).addTalkToFavorite(id);
    }

    public void addTalk(Talk talk, Context context) {
        TalkDBhelper talkDBhelper = new TalkDBhelper(context);
        talkDBhelper.addTalkToDatabase(talk);
    }

    public void removeFromFavoriteTalks(Integer id, Context context) {
        new TalkDBhelper(context).removeFromFavorites(id);
    }

    public List<Talk> getTalksBySpeaker(String speaker, Context context) {
        return new TalkDBhelper(context).getTalksBySpeaker(speaker);
    }


}
