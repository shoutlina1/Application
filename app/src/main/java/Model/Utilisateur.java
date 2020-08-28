package Model;

import java.io.Serializable;

public class Utilisateur implements Serializable {

    private long id=1;
    public String nom;
    private String prénom;
    private String username;
    private String mot_de_passe;
    private String email;
    private String numero;

    public Utilisateur(){

    }

    public Utilisateur(String nom,String prénom,String username,String mot_de_passe,String email, String numero){
        this.nom=nom;
        this.prénom=prénom;
        this.username=username;
        this.mot_de_passe=mot_de_passe;
        this.email=email;
        this.numero=numero;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrénom() {
        return prénom;
    }

    public void setPrénom(String prénom) {
        this.prénom = prénom;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
