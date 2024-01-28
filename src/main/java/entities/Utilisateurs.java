package entities;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Utilisateurs {
    private static final Logger logger = LoggerFactory.getLogger(Utilisateurs.class);
    private Scanner scanner = new Scanner(System.in);
    private String nom;
    private String prenom;
    private String email;
    private String motdepasse;
    private String motdepasse_crypte;
    private int idrole;

    // GETTERS
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

    // SETTERS
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

    // CONSTRUCTORS
    public Utilisateurs() {
    }

    public Utilisateurs(String nom, String prenom, String email, String motdepasse, String motdepasse_crypte, int idrole) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motdepasse = motdepasse;
        this.motdepasse_crypte = motdepasse_crypte;
        this.idrole = idrole;
    }

    // FONCTIONS

    public Utilisateurs saisieUtilisateur() {
        Utilisateurs u = new Utilisateurs();
        logger.info("Nom:");
        u.setNom(scanner.nextLine());
        logger.info("Prenom:");
        u.setPrenom(scanner.nextLine());

        do {
            logger.info("Email:");
            u.setEmail(scanner.nextLine());
        } while (!u.getEmail().contains("@"));

        do {
            logger.info("Mot de passe (8 caractères minimum):");
            u.setMotdepasse(scanner.nextLine());
        } while (u.getMotdepasse().length() < 8);

        u.setMotdepasse_crypte(hashPassword(u.getMotdepasse()));

        do {
            logger.info("1-ADMINISTRATEUR");
            logger.info("2-SUPER_UTILISATEUR");
            logger.info("3-UTILISATEUR");
            logger.info("Choisissez le rôle");
            u.setIdrole(scanner.nextInt());
        } while (u.getIdrole() < 1 || u.getIdrole() > 3);
        scanner.nextLine();

        return u;
    }

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
