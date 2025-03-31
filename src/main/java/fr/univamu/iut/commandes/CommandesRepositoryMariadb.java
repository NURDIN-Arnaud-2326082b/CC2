package fr.univamu.iut.commandes;

import jakarta.enterprise.context.ApplicationScoped;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

@ApplicationScoped
public class CommandesRepositoryMariadb implements CommandesRepositoryInterface, Closeable {

    protected Connection dbConnection;

    // Constructeur sans paramètres
    public CommandesRepositoryMariadb() {}

    // Constructeur avec paramètres
    public CommandesRepositoryMariadb(String infoConnection, String user, String pwd) throws SQLException, ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection(infoConnection, user, pwd);
    }

    @Override
    public void close() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Commandes getCommande(int id_commande) {
        Commandes selectedCommande = null;
        String query = "SELECT * FROM Commandes WHERE id_commande=?";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id_commande);
            ResultSet result = ps.executeQuery();

            if (result.next()) {
                int id_client = result.getInt("id_client");
                int id_panier = result.getInt("id_panier");
                double prix_total = result.getDouble("prix_total");
                String date_retrait = result.getString("date_retrait");
                String localisation_retrait = result.getString("localisation_retrait");
                String statut = result.getString("statut");

                selectedCommande = new Commandes(id_commande, id_client, id_panier, prix_total, date_retrait, localisation_retrait, statut);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedCommande;
    }

    @Override
    public ArrayList<Commandes> getAllCommandes() {
        ArrayList<Commandes> listCommandes = new ArrayList<>();
        String query = "SELECT * FROM Commandes";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                int id_commande = result.getInt("id_commande");
                int id_client = result.getInt("id_client");
                int id_panier = result.getInt("id_panier");
                double prix_total = result.getDouble("prix_total");
                String date_retrait = result.getString("date_retrait");
                String localisation_retrait = result.getString("localisation_retrait");
                String statut = result.getString("statut");

                Commandes currentCommande = new Commandes(id_commande, id_client, id_panier, prix_total, date_retrait, localisation_retrait, statut);

                listCommandes.add(currentCommande);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listCommandes;
    }

    @Override
    public boolean updateCommande(int id_commande, int id_client, int id_panier, double prix_total, String date_retrait, String localisation_retrait, String statut) {
        String query = "UPDATE Commandes SET id_client=?, id_panier=?, prix_total=?, date_retrait=?, localisation_retrait=?, statut=? WHERE id_commande=?";
        int nbRowModified = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id_client);
            ps.setInt(2, id_panier);
            ps.setDouble(3, prix_total);
            ps.setString(4, date_retrait);
            ps.setString(5, localisation_retrait);
            ps.setString(6, statut);
            ps.setInt(7, id_commande);

            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return (nbRowModified != 0);
    }

    @Override
    public boolean createCommande(Commandes commande) {
        String query = "INSERT INTO Commandes (id_commande, id_client, id_panier, prix_total, date_retrait, localisation_retrait, statut) VALUES (?, ?, ?, ?, ?, ?, ?)";
        int nbRowModified = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, commande.getId_commande());
            ps.setInt(2, commande.getId_client());
            ps.setInt(3, commande.getId_panier());
            ps.setDouble(4, commande.getPrix_total());
            ps.setString(5, commande.getDate_retrait());
            ps.setString(6, commande.getLocalisation_retrait());
            ps.setString(7, commande.getStatut());

            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return (nbRowModified != 0);
    }

    @Override
    public boolean deleteCommande(int id_commande) {
        String query = "DELETE FROM Commandes WHERE id_commande=?";
        int nbRowModified = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id_commande);
            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return (nbRowModified != 0);
    }
}