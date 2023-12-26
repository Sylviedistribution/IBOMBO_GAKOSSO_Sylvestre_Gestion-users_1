package main;

import entities.*;
import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner =  new Scanner(System.in);
        Utilisateurs u = new Utilisateurs();
        GestionnaireUtilisateurs gu = new GestionnaireUtilisateurs();

        String url = "jdbc:mysql://127.0.0.1:3306/login_schema";
        String utilisateur = "root";
        String motDePasse = "Passer123@";


        boolean ok = true;
        int choix;

        try{
            Connection connection = DriverManager.getConnection(url, utilisateur, motDePasse);

            System.out.println("Vous etes connectes");
            System.out.println();
            do{
                do{
                    afficherMenu();
                    choix = scanner.nextInt();
                }while(choix<1 || choix > 4);
                switch(choix){
                   case 1:
                       gu.ajouterUtilisateur( connection, u.saisieUtilisateur());
                       break;
                   case 2:
                       System.out.println(gu.listerUtilisateurs( connection));
                       break;
                    case 3:
                        ok=false;
                }
            }while(ok==true);






        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    private static void afficherMenu() {
        System.out.println("------MENU------");
        System.out.println("1-Ajouter un utilisateur");
        System.out.println("2-Lister les utilisateurs");
        System.out.println("3-Quitter");
        System.out.print("Faites votre choix : ");
    }

}