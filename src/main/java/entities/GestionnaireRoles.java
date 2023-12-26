package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestionnaireRoles {

    // Méthode pour ajouter un role à la base de données
    public static void ajouterRoles(Connection connection, Roles r){
        // Requête SQL d'insertion

        String sql = "INSERT INTO roles (nom) " + "VALUES (?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Paramétrage des valeurs
            preparedStatement.setString(1, r.getNom());

            // Exécution de la requête
            int lignesModifiees = preparedStatement.executeUpdate();

            if (lignesModifiees > 0) {
                System.out.println("Role ajouté avec succès !");
            } else {
                System.out.println("Échec de l'ajout du role.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour lister tous les utilisateurs de la base de données
    public static String listerRoles(Connection connection) {
        StringBuilder result = new StringBuilder();

        // Requête SQL de sélection
        String sql = "SELECT * FROM roles";
        result.append("------ Liste des roles ------\n");

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // Parcours des résultats
            while (resultSet.next()) {
                String nom = resultSet.getString("nom"); //On peut mettre le des nombre pour preciser la colonne

                result.append("Nom: ").append(nom);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fermerConnexion(connection);
        }

        return result.toString();
    }

    private static void fermerConnexion(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connexion fermée avec succès.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
        }
    }

}