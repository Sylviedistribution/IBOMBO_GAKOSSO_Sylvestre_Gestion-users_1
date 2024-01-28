package entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Roles {
    private static final Logger logger = LoggerFactory.getLogger(Roles.class);
    private Scanner scanner = new Scanner(System.in);

    private String nom;

    // GETTER
    public String getNom() {
        return nom;
    }

    // SETTER
    public void setNom(String nom) {
        this.nom = nom;
    }

    // CONSTRUCTORS
    public Roles() {
    }

    public Roles(String nom) {
        this.nom = nom;
    }

    // FONCTIONS
    public Roles saisieRole() {
        Roles r = new Roles();
        logger.info("Nom:");
        r.nom = scanner.nextLine();
        return r;
    }
}
