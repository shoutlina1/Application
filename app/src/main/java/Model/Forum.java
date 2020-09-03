package Model;

import java.util.ArrayList;

public class Forum {
    private static Forum singleton_forum;
    private ArrayList<Questions_Forum> questions =new ArrayList<>();

    private Forum(){
        super();
    }


    public final static Forum getInstance() {
        if (Forum.singleton_forum == null) {
            synchronized(Forum.class) {
                if (Forum.singleton_forum == null) {
                    Forum.singleton_forum = new Forum();
                }
            }
        }
        return Forum.singleton_forum;
    }


    public ArrayList<Questions_Forum> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Questions_Forum> questions) {
        this.questions = questions;
    }
    public void addQuestion(Questions_Forum question){
        this.questions.add(question);
    }
}
