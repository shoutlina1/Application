package Model;

import java.util.ArrayList;
import java.util.Date;

public class Questions_Forum {

    private int id =0;
    private String titre;
    private String contenu;
    private String questionUser;
    private long questionTime;
    private ArrayList<Réponses_Forum> réponses;


    public Questions_Forum() {
    }

    public Questions_Forum(String titre,String questionUser,String contenu,ArrayList<Réponses_Forum> réponses){
        this.titre=titre;
        this.questionUser = questionUser;
        this.contenu=contenu;
        questionTime = new Date().getTime();
        this.réponses = réponses;

    }

    public String getQuestionUser() {
        return questionUser;
    }

    public void setQuestionUser(String questionUser) {
        this.questionUser = questionUser;
    }

    public long getQuestionTime() {
        return questionTime;
    }

    public void setQuestionTime(long questionTime) {
        this.questionTime = questionTime;
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

    public ArrayList<Réponses_Forum> getRéponses() {
        return réponses;
    }

    public void setRéponses(ArrayList<Réponses_Forum> réponses) {
        this.réponses = réponses;
    }
    public void ajouterReponse(Réponses_Forum réponse){
        réponses.add(réponse);
    }


}

