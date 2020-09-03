package Model;

import java.util.Date;

public class Réponses_Forum {

    private int id =0;
    private String titre;
    private String réponseUser;
    private long RéponseTime;
    private String contenu;



    public Réponses_Forum() {
    }

    public Réponses_Forum(String réponseUser, String contenu){
        //this.titre=titre;
        this.réponseUser = réponseUser;
        this.contenu=contenu;
        RéponseTime = new Date().getTime();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }


    public String getRéponseUser() {
        return réponseUser;
    }

    public void setRéponseUser(String réponseUser) {
        this.réponseUser = réponseUser;
    }

    public long getRéponseTime() {
        return RéponseTime;
    }

    public void setRéponseTime(long réponseTime) {
        this.RéponseTime = réponseTime;
    }

/*
    public ArrayList<Réponses_Forum> getRéponses() {
        return réponses;
    }

    public void setRéponses(ArrayList<Réponses_Forum> réponses) {
        this.réponses = réponses;
    }
    public void ajouterReponse(Réponses_Forum réponse){
        réponses.add(réponse);
    }

 */
}

