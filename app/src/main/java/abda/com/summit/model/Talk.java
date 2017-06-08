package abda.com.summit.model;


/**
 * Created by dh2 on 07/06/17.
 */

public class Talk {

    private String horaInicio;
    private String tema;
    private String aula;
    private Integer ID;
    private Integer value;
    private Boolean isFavorite;
    private String speaker;

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public Integer getValue() {
        return value;
    }

    public Boolean getIsFavorite() {
        return isFavorite;
    }

    public String getSpeaker() {
        return speaker;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public String getTema() {
        return tema;
    }

    public String getAula() {
        return aula;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }
}
