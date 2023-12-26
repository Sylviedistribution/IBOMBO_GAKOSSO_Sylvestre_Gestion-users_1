package entities;

import java.util.Scanner;

public class Roles {
    Scanner scanner = new Scanner(System.in);

    private String nom;

    //GETTER
    public String getNom() {
        return nom;
    }
    //SETTER
    public void setNom(String nom) {
        this.nom = nom;
    }
    //CONSTRUCTORS
    public Roles() {
    }
    public Roles(String nom) {
        this.nom = nom;
    }

    //FONCTIONS

    public Roles saisieRole(){
        Roles r = new Roles();
        System.out.println("Nom:");
        r.nom = scanner.nextLine();

        return r;
    }
}
