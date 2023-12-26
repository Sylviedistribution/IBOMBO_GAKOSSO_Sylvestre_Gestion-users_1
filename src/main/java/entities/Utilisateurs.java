package entities;

import org.mindrot.jbcrypt.BCrypt;
import java.util.Scanner;
import java.sql.*;

public class Utilisateurs {
    Scanner scanner = new Scanner(System.in);
    private String nom;
    private String prenom;
    private String email;
    private String motdepasse;
    private String motdepasse_crypte;
    private int idrole;


    //GETTERS
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public String getMotdepasse_crypte() {
        return motdepasse_crypte;
    }
    public int getIdrole() {
        return idrole;
    }


    //SETTERS
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public void setMotdepasse_crypte(String motdepasse_crypte) {
        this.motdepasse_crypte = motdepasse_crypte;
    }
    public void setIdrole(int idrole) {
        this.idrole = idrole;
    }


    //CONSTRUCTORS
    public Utilisateurs() {
    }

    public Utilisateurs(String nom, String prenom, String email, String motdepasse, String motdepasse_crypte, int idrole) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motdepasse = motdepasse;
        this.motdepasse_crypte = motdepasse;
        this.idrole = idrole;

    }

    //FONCTIONS

    public Utilisateurs saisieUtilisateur() {
        Utilisateurs u = new Utilisateurs();
        System.out.println("Nom:");
        u.setNom(scanner.nextLine());
        System.out.println("Prenom:");
        u.setPrenom(scanner.nextLine());

        do {
            System.out.println("Email:");
            u.setEmail(scanner.nextLine());
        } while (!u.getEmail().contains("@"));

        do {
            System.out.println("Mot de passe (8 caracteres minimum):");
            u.setMotdepasse(scanner.nextLine());
        } while (u.getMotdepasse().length() < 8);

        u.setMotdepasse_crypte(hashPassword(u.getMotdepasse()));

        do {
            System.out.println("1-ADMINISTRATEUR");
            System.out.println("2-SUPER_UTILISATEUR");
            System.out.println("3-UTILISATEUR");
            System.out.println("Chosissez le role");
            u.setIdrole(scanner.nextInt());
        } while (u.getIdrole()<1 || u.getIdrole() > 3);
        scanner.nextLine();

        return u;
    }

    /*Methode peu efficace contre la force brute
    public StringBuilder crypte(String motdepasse){
        int key = 3;
        StringBuilder crypte = new StringBuilder();
        char[] chars = motdepasse.toCharArray();
        for(char c : chars){
            c+= (char) key;
            crypte.append(c);
        }
       return crypte;
    }*/

    public static String hashPassword(String motdepasse) {
        // Génération du sel
        String salt = BCrypt.gensalt();

        // Hachage du mot de passe avec le sel
        String hashedPassword = BCrypt.hashpw(motdepasse, salt);

        return hashedPassword;
    }

    public static boolean checkPassword(String motdepasse, String hashedPassword) {
        // Vérification du mot de passe haché
        return BCrypt.checkpw(motdepasse, hashedPassword);
    }

}


