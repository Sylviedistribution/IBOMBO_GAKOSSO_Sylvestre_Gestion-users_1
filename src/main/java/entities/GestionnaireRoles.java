package entities;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GestionnaireRoles {
    private static final Logger logger = LoggerFactory.getLogger(GestionnaireRoles.class);
    // Méthode pour ajouter un rôle à la base de données
    public static void ajouterRoles(Connection connection, Roles r) {
        // Requête SQL d'insertion
        String sql = "INSERT INTO roles (nom) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Paramétrage des valeurs
            preparedStatement.setString(1, r.getNom());
            // Exécution de la requête
            int lignesModifiees = preparedStatement.executeUpdate();
            if (lignesModifiees > 0) {
                logger.info("Rôle ajouté avec succès !");
            } else {
                logger.info("Échec de l'ajout du rôle.");
            }
        } catch (SQLException e) {
            logger.error("Erreur lors de l'ajout du rôle : {}", e.getMessage());
        }
    }
    // Méthode pour lister tous les rôles de la base de données
    public static String listerRoles(Connection connection) {
        StringBuilder result = new StringBuilder();
        // Requête SQL de sélection
        String sql = "SELECT * FROM roles";
        result.append("------ Liste des rôles ------\n");
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            // Parcours des résultats
            while (resultSet.next()) {
                String nom = resultSet.getString("nom");
                result.append("Nom: ").append(nom).append("\n");
            }
        } catch (SQLException e) {
            logger.error("Erreur lors de la récupération des rôles : {}", e.getMessage());
        } finally {
            fermerConnexion(connection);
        }
        return result.toString();
    }
    private static void fermerConnexion(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                logger.info("Connexion fermée avec succès.");
            }
        } catch (SQLException e) {
            logger.error("Erreur lors de la fermeture de la connexion : {}", e.getMessage());
        }
    }
}
