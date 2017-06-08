package abda.com.summit.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import abda.com.summit.model.Talk;

/**
 * Created by dh2 on 07/06/17.
 */

    public class TalkDBhelper extends SQLiteOpenHelper {

        private static final String HORAINICIO = "HORAINICIO";
        private static final String TEMA = "TEMA";
        private static final String AULA = "AULA";
        private static final String VALUE = "VALUE";
        private static final String SPEAKER = "SPEAKER";
        private static final String ID = "ID";
        private static final String ISFAVORITE = "ISFAVORITE";
        private static final String TABLENAME = "TALKS";

    public TalkDBhelper(Context context) {
        super(context, "TalkDB", null, 1);
    }

    @Override
        public void onCreate(SQLiteDatabase database) {

            //DEFINIR LA ESTRUCTURA DE NUESTRA BASE DE DATOS
            String createSQL =
                    "CREATE TABLE " + TABLENAME + " ( " +
                            HORAINICIO + " TEXT, " +
                            ID + " INTEGER PRIMARY KEY autoincrement, " +
                            TEMA + " TEXT, " +
                            AULA + " TEXT, " +
                            SPEAKER + " TEXT, " +
                            ISFAVORITE + " INTEGER, " +
                            VALUE + " INTEGER" + ")";

            database.execSQL(createSQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

        //INSERTAR UNA PERSONA EN LA TABLA DE PERSONAS
        public void addTalkToDatabase(Talk talk){

            ContentValues fila = new ContentValues();

            //CARGO LOS DATOS DENTRO DE LA FILA
            fila.put(HORAINICIO, talk.getHoraInicio());
            fila.put(TEMA, talk.getTema());
            fila.put(AULA, talk.getAula());
            fila.put(SPEAKER, talk.getSpeaker());
            fila.put(ISFAVORITE, talk.getIsFavorite());
            fila.put(VALUE, talk.getValue());

            //PIDO UNA CONEXION A LA BASE DE DATOS
            SQLiteDatabase database = getWritableDatabase();

            //INSERTA EN LA TABLA PERSONAS LA FILA
            database.insert(TABLENAME, null, fila);

            //CERRAR LA CONEXION
            database.close();
        }

        public void addTalkToFavorite(Integer id) {
            SQLiteDatabase database = getWritableDatabase();

            ContentValues data=new ContentValues();
            data.put(ISFAVORITE,1);
            database.update(TABLENAME, data, "ID=" + id, null);
            database.close();

        }

        public void removeFromFavorites(Integer id) {
            SQLiteDatabase database = getWritableDatabase();

            ContentValues data=new ContentValues();
            data.put(ISFAVORITE,0);
            database.update(TABLENAME, data, "ID=" + id, null);
            database.close();
        }



    public List<Talk> getFavoritesFromDatabase(){

        String selectQuery = "SELECT * FROM " + TABLENAME + " WHERE "+ ISFAVORITE +" = 1" + " ORDER BY " + VALUE + " ASC";

        //PIDO UNA CONEXION A LA BASE DE DATOS
        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.rawQuery(selectQuery, null);
        List<Talk> talkList = new ArrayList<>();

        while(cursor.moveToNext()){

            Talk talk = getTalk(cursor);
            talkList.add(talk);
        }

        cursor.close();
        database.close();
        return talkList;
    }

        private Talk getTalk(Cursor cursor){
            Talk talk = new Talk();

            String hora = cursor.getString(cursor.getColumnIndex(HORAINICIO));
            talk.setHoraInicio(hora);

            String tema = cursor.getString(cursor.getColumnIndex(TEMA));
            talk.setTema(tema);

            String aula = cursor.getString(cursor.getColumnIndex(AULA));
            talk.setAula(aula);

            String speaker = cursor.getString(cursor.getColumnIndex(SPEAKER));
            talk.setSpeaker(speaker);

            Integer value = cursor.getInt(cursor.getColumnIndex(VALUE));
            talk.setValue(value);

            Integer isfavorite = cursor.getInt(cursor.getColumnIndex(ISFAVORITE));
            talk.setFavorite(isfavorite != 0);

            Integer id = cursor.getInt(cursor.getColumnIndex(ID));
            talk.setID(id);

            return talk;
        }

    public List<Talk> getTalksBySpeaker(String speaker) {

            String selectQuery = "SELECT * FROM " + TABLENAME + " WHERE "+ SPEAKER +" = " + "\"" + speaker + "\" " + " ORDER BY " + VALUE + " ASC";

            //PIDO UNA CONEXION A LA BASE DE DATOS
            SQLiteDatabase database = getReadableDatabase();

            Cursor cursor = database.rawQuery(selectQuery, null);
            List<Talk> talkList = new ArrayList<>();

            while(cursor.moveToNext()){

                Talk talk = getTalk(cursor);
                talkList.add(talk);
            }

            cursor.close();
            database.close();
            return talkList;
    }

    /*        public Talk getPersonFromDatabase(Integer id){

                String selectQuery =
                        "SELECT * FROM " + TABLENAME +
                                " WHERE " + ID + "=" + id;

                //PIDO UNA CONEXION A LA BASE DE DATOS
                SQLiteDatabase database = getReadableDatabase();


                Cursor cursor = database.rawQuery(selectQuery, null);

                Person talk = null;

                while(cursor.moveToNext()){
                    //LEER CADA FILA DE LOS RESULTADOS

                    talk = getPerson(cursor);
                }

                cursor.close();
                database.close();
                return talk;
            }*/

    public List<Talk> getTalksFromDatabase(){

        String selectQuery = "SELECT * FROM " + TABLENAME + " ORDER BY " + VALUE + " ASC";

        //PIDO UNA CONEXION A LA BASE DE DATOS
        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.rawQuery(selectQuery, null);
        List<Talk> talkList = new ArrayList<>();

        while(cursor.moveToNext()){

            Talk talk = getTalk(cursor);
            talkList.add(talk);
        }

        cursor.close();
        database.close();
        return talkList;
    }
}