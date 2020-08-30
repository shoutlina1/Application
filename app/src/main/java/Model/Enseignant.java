package Model;

public class Enseignant extends Utilisateur {
    private long id=1;




    public Enseignant() {
    }

    public Enseignant(String nom, String prénom, String username, String mot_de_passe, String email, String numero) {

        super(nom, prénom, username, mot_de_passe, email, numero);
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }
}