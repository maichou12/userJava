package entity;

public class Roles {
    private int id;
    private String nom;

    //Constructeur
    public Roles() {
    }

    public Roles(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    //Getters
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    //SETTERS

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    //Methodes

}
