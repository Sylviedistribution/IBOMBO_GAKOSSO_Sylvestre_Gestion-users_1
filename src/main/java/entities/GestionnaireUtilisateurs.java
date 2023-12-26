package entities;

import java.sql.*;


public class GestionnaireUtilisateurs {

    // Méthode pour ajouter un utilisateur à la base de données
    public static void ajouterUtilisateur(Connection connection, Utilisateurs u) {
        // Requête SQL d'insertion

        String sql = "INSERT INTO utilisateurs (nom, prenom, email, motdepasse, motdepasse_crypte, idroles) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Paramétrage des valeurs
            preparedStatement.setString(1, u.getNom());
            preparedStatement.setString(2, u.getPrenom());
            preparedStatement.setString(3, u.getEmail());
            preparedStatement.setString(4, u.getMotdepasse());
            preparedStatement.setString(5, u.getMotdepasse_crypte());
            preparedStatement.setInt(6, u.getIdrole());


            // Exécution de la requête
            int lignesModifiees = preparedStatement.executeUpdate();

            if (lignesModifiees > 0) {
                System.out.println("Utilisateur ajouté avec succès !");
            } else {
                System.out.println("Échec de l'ajout de l'utilisateur.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour lister tous les utilisateurs de la base de données
    public static String listerUtilisateurs(Connection connection) {
        StringBuilder result = new StringBuilder();

        // Requête SQL de sélection
        String sql = "SELECT * FROM utilisateurs";
        result.append("------ Liste des Utilisateurs ------\n");

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // Parcours des résultats
            while (resultSet.next()) {
                String nom = resultSet.getString("nom"); //On peut mettre le des nombre pour preciser la colonne
                String prenom = resultSet.getString("prenom");
                String email = resultSet.getString("email");
                String motdepasse = resultSet.getString("motdepasse");
                String motdepasse_crypte = resultSet.getString("motdepasse_crypte");
                int idroles = resultSet.getInt("idroles");

                result.append("Nom: ").append(nom).append(", ");
                result.append("Prénom: ").append(prenom).append(", ");
                result.append("Email: ").append(email).append(", ");
                result.append("Mot de passe: ").append(motdepasse).append(", ");
                result.append("Mot de passe crypté: ").append(motdepasse_crypte).append(", ");
                result.append("Idroles: ").append(motdepasse_crypte).append("\n");

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