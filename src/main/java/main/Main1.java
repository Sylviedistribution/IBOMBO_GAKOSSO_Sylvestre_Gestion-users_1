package main;

import entities.*;
import java.sql.*;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main1 {

    private static final Logger logger = LoggerFactory.getLogger(Main1.class);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Utilisateurs u = new Utilisateurs();
        GestionnaireUtilisateurs gu = new GestionnaireUtilisateurs();

        String url = "jdbc:mysql://127.0.0.1:3306/login_schema";
        String utilisateur = "root";
        String motDePasse = "Passer";

        boolean ok = true;
        int choix;

        try {
            Connection connection = DriverManager.getConnection(url, utilisateur, motDePasse);

            logger.info("Vous etes connectes");

            do {
                do {
                    afficherMenu();
                    choix = scanner.nextInt();
                } while (choix < 1 || choix > 4);
                switch (choix) {
                    case 1:
                        gu.ajouterUtilisateur(connection, u.saisieUtilisateur());
                        break;
                    case 2:
                        logger.info(gu.listerUtilisateurs(connection));
                        break;
                    case 3:
                        ok = false;
                }
            } while (ok);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        logger.info("Fin ");

    }

    private static void afficherMenu() {
        logger.info("------MENU------");
        logger.info("1-Ajouter un utilisateur");
        logger.info("2-Lister les utilisateurs");
        logger.info("3-Quitter");
        logger.info("Faites votre choix : ");
    }

}
