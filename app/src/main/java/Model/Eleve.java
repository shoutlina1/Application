package Model;

public class Eleve extends Utilisateur {

    private String numdetudiant  ;


    public Eleve(){}

    public Eleve(String nom,String prénom,String username,String mot_de_passe,String email, String numero,String numdetudiant){

        super(nom, prénom, username, mot_de_passe, email, numero);
        this.numdetudiant=numdetudiant ;
    }
 {
    }

    public String getNumdetudiant() {
        return numdetudiant;
    }

    public void setNumdetudiant(String numdetudiant) {
        this.numdetudiant = numdetudiant;
    }


}
