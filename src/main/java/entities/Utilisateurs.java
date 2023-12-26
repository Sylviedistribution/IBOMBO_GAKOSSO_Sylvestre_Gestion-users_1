package entities;

import org.mindrot.jbcrypt.BCrypt;
import java.util.Scanner;
import java.sql.*;

public class Utilisateurs {
    Scanner scanner = new Scanner(System.in);
    private String nom;
    private String prenom;
    private String tel;
    private String email;
    private String motdepasse;
    private String motdepasse_crypte;

    //GETTERS
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTel() {
        return tel;
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

    //SETTERS
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setTel(String tel) {
        this.tel = tel;
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


    //CONSTRUCTORS
    public Utilisateurs() {
    }

    public Utilisateurs(String nom, String prenom, String tel, String email, String motdepasse, String motdepasse_crypte) {
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.email = email;
        this.motdepasse = motdepasse;
        this.motdepasse_crypte = motdepasse;
    }

    //FONCTIONS

    public Utilisateurs saisieUtilisateur() {
        Utilisateurs u = new Utilisateurs();
        System.out.println("Nom:");
        u.setNom(scanner.nextLine());
        System.out.println("Prenom:");
        u.setPrenom(scanner.nextLine());
        do {
            System.out.println("Tel:");
            u.setTel(scanner.nextLine());
        } while (u.getTel().length() != 9);

        do {
            System.out.println("Email:");
            u.setEmail(scanner.nextLine());
        } while (!u.getEmail().contains("@"));

        do {
            System.out.println("Mot de passe (8 caracteres minimum):");
            u.setMotdepasse(scanner.nextLine());
        } while (u.getMotdepasse().length() < 8);

        u.setMotdepasse_crypte(hashPassword(u.getMotdepasse()));

        return u;
    }

    /*Methode peu efficace
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


